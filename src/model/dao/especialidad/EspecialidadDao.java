/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.especialidad;

import java.util.ArrayList;
import java.util.List;
import model.especialidad.Especialidad;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import DBconexion.ConexionDB;

/**
 *
 * @author luis-
 */
public class EspecialidadDao {

    public List<Especialidad> listarEspecialidades() throws SQLException {
        String sql = "{CALL filtrar_especialidades()}";
        List<Especialidad> especialidades = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Especialidad e = new Especialidad();
                e.setId(rs.getInt("id"));
                e.setDescripcion(rs.getString("descripcion"));
                especialidades.add(e);
            }
            return especialidades;
        }
    }
}
