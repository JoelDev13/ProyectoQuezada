/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.doctor;

import DBconexion.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.doctor.Doctor;

/**
 *
 * @author YELIANA
 */
public class DoctorDAO {

    public DoctorDAO() {
    }

    public List<Doctor> filtrarDoctores(String textoFiltro, Integer especialidad) {
        List<Doctor> doctores = new ArrayList<>();
        String sql = "{CALL sp_filtrar_doctores(?, ?)}";

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, textoFiltro); // nombre_pattern
            this.PonerIntNulo(cs, 2, especialidad);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    
                    Doctor doctor  = new Doctor ();
                    doctor.setId(rs.getInt("id"));
                    doctor.setUsuario(rs.getString("usuario"));
                    doctor.setNombre(rs.getString("Nombre"));
                    doctor.setApellido(rs.getString("Apellido"));
                    doctor.setEmail(rs.getString("Email"));
                    doctor.setTelefono(rs.getString("Telefono"));
                    
                    
                    doctores.add(doctor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // ¡Reemplaza esto con un logger en producción!
        }
        return doctores;
    }

    /**
     * Este metodo se encarga de poner Null explicitamente en los campos int del
     * Callabe Statemnt para evitar errores
     *
     * @param cs Callable Statement
     * @param indiceSQL Indice que se va a sustituir
     * @param valor valor a sustituir
     * @throws SQLException con mensaje de la db
     */
    private void PonerIntNulo(CallableStatement cs, int indiceSQL, Integer valor) throws SQLException {
        if (valor != null) {
            cs.setInt(indiceSQL, valor);
        } else {
            cs.setNull(indiceSQL, java.sql.Types.INTEGER);
        }

    }

}
