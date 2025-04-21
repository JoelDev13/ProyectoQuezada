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
 *
 * @author luis-
 */
public class ServiciosDao {
    
    public void eliminarServicio(int id) throws SQLException {
        String sql = "CALL sp_eliminar_servicio(?)";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
            cs.close();
        }
    }

    
    
    public void actualizarServicio(Servicio s) throws SQLException {
        String sql = "CALL sp_actualizar_servicio(?,?,?)";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, s.getId());
            cs.setString(2, s.getDescripcion());
            cs.setDouble(3, s.getPrecio());
            cs.execute();
            cs.close();
        }
    }
    
    
    
    public void registrarServicio(Servicio s) throws SQLException {
        String sql = "CALL sp_registrar_servicio(?,?)";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, s.getDescripcion());
            cs.setDouble(2, s.getPrecio());
            cs.execute();
            cs.close();
        }
    }

    /**
     * Lista todos los servicios asociados a una especialidad
     *
     * @return <code>List Servicio</code> con todos los servicios asociados ala especialidad
     * @throws SQLException con mensaje de la db
     */

    public List<Servicio> listarServiciosDeUnaEspecialidad(int idEspecialidad) throws SQLException {
        String sql = "{CALL sp_listar_servicios_de_especialidad(?)}";
        List<Servicio> servicios = new ArrayList<>();
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idEspecialidad);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Servicio s = new Servicio();
                    s.setId(rs.getInt("id_servicio"));
                    s.setDescripcion(rs.getString("servicio_descripcion"));
                    s.setPrecio(rs.getDouble("servicio_precio"));
                    servicios.add(s);
                }
            }
            return servicios;
        }

    }

    /**
     * Lista todos los servicios que ofrece un medico
     *
     * @return <code>List Servicio</code> con todos los servicios que ofrece el medico
     * @throws SQLException con mensaje de la db
     */
    public List<Servicio> listarServiciosDeUnMedico(int idDoc) throws SQLException {
        String sql = "{CALL sp_listar_servicios_medico_especifico(?)}";
        List<Servicio> servicios = new ArrayList<>();
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idDoc);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Servicio s = new Servicio();
                    s.setId(rs.getInt("id_servicio"));
                    s.setDescripcion(rs.getString("servicio"));
                    s.setPrecio(rs.getDouble("precio"));
                    servicios.add(s);
                }
            }
            return servicios;
        }

    }

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
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Servicio s = new Servicio();
                    s.setId(rs.getInt("id"));
                    s.setDescripcion(rs.getString("descripcion"));
                    s.setPrecio(rs.getDouble("precio"));
                    servicios.add(s);
                }
            }
            return servicios;
        }

    }
}
