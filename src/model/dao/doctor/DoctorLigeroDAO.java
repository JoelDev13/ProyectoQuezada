/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.doctor;

import java.util.List;
import model.doctor.DoctorLigeroDTO;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import DBconexion.ConexionDB;
import java.util.ArrayList;

/**
 * Con tal de chocar con otras personas, este dao se encargara
 * de las consultas de los Dialog que filtran Doctores. Este 
 * implementa metodos y store procedures propios para ellos.
 * @author luis-
 */
public class DoctorLigeroDAO {
    
    public List<DoctorLigeroDTO> filtrarDoctores(DoctorLigeroDTO doctor) throws SQLException {
        String sql = "{CALL sp_filtrar_doctoresDTO(?,?,?)}";
        List<DoctorLigeroDTO> doctores = new ArrayList<>();
        
        try ( Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)){
            cs.setString(1, doctor.getNombre());
            cs.setString(2, doctor.getApellido());
            cs.setString(3, doctor.getEspecialidad());
            
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()) {
                DoctorLigeroDTO d = new DoctorLigeroDTO();
                d.setId(rs.getInt("ID"));
                d.setNombre(rs.getString("nombre"));
                d.setApellido(rs.getString("apellido"));
                d.setEspecialidad(rs.getString("especialidad"));
                doctores.add(d);
            }
        }
        return doctores;
    }
}
