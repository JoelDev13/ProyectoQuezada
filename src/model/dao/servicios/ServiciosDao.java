/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.servicios;

import DBconexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.servicios.Servicio;

/**
 * Clase que se encarga de manipular los servicios
 * @author luis-
 */
public class ServiciosDao {

    /**
     * Lista todos los servicios registrados
     *
     * @return <code>List Servicio</code> con todos los servicios registrados
     * @throws SQLException con mensaje de la dba
     */
    public List<Servicio> listarServicios() throws SQLException {
        String sql = "{CALL listar_servicios()}";
        List<Servicio> servicios = new ArrayList<>();
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Servicio s = new Servicio();
                s.setId(rs.getInt("id"));
                s.setDescripcion(rs.getString("descripcion"));
                s.setPrecio(rs.getDouble("precio"));
                servicios.add(s);
            }

            return servicios;
        }

    }
}
