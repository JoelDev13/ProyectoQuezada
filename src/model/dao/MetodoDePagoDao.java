package model.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import DBconexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MetodoDePagoModel;

/**
 * esta clase se encarga de manipular la informacion de los metodos
 * de pago en la base de datos.
 * @author la
 */
public class MetodoDePagoDao {
    
    private static final Logger LOGGER = Logger.getLogger(MetodoDePagoDao.class.getName());

    /**
     * Lista todos los metodos de pagos registrados en el sistema.
     * @return List&lt;MetodoDePagoModel&gt; con todos los Metodos de pago del sistema.
     */
    public List<MetodoDePagoModel> listarMetodos() {
        List<MetodoDePagoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM metodos_pagos";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MetodoDePagoModel metodo = new MetodoDePagoModel();
                metodo.setId(rs.getInt("id"));
                metodo.setDescripcion(rs.getString("descripcion"));
                lista.add(metodo);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar métodos de pago", e);
        }
        return lista;
    }

    /**
     * ingresa un nuevo metodo de pago al sistema.
     * @param descripcion descripcion del nuevo metodo de pago
     * 
     */
    public boolean insertarMetodo(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            LOGGER.warning("Descripción vacía o nula. No se insertó.");
            return false;
        }

        String sql = "CALL insertar_metodo_pago(?)";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descripcion);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar método de pago", e);
            return false;
        }
    }
    
    /**
     
     * @param id
     * @param descripcion
     * @return 
     */
    public boolean actualizarMetodo(int id, String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            LOGGER.warning("Descripcion vacia o nula. No se actualizó");
            return false;
        }

        String sql = "UPDATE metodos_pagos SET descripcion = ? WHERE id = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descripcion);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar método de pago", e);
            return false;
        }
    }

    public boolean eliminarMetodo(int id) {
        String sql = "DELETE FROM metodos_pagos WHERE id = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar método de pago", e);
            return false;
        }
    }

    public void guardarMetodoDePago(MetodoDePagoModel metodo) {
        if (metodo == null || metodo.getDescripcion() == null || metodo.getDescripcion().trim().isEmpty()) {
            LOGGER.warning("Método de pago inválido. No se guardó.");
            return;
        }

        if (metodo.getId() <= 0) {
            insertarMetodo(metodo.getDescripcion());
        } else {
            actualizarMetodo(metodo.getId(), metodo.getDescripcion());
        }
    }

    public MetodoDePagoModel obtenerPorId(int id) {
        String sql = "SELECT * FROM metodos_pagos WHERE id = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                MetodoDePagoModel metodo = new MetodoDePagoModel();
                metodo.setId(rs.getInt("id"));
                metodo.setDescripcion(rs.getString("descripcion"));
                return metodo;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener método de pago por ID", e);
        }
        return null;
    }
    
    
    /**
     * Obtiene un metodo de pago en especifico por 
     * @param descripcion
     * @return 
     */
    public MetodoDePagoModel obtenerPorDescripcion(String descripcion) {
    String sql = "SELECT * FROM metodos_pagos WHERE descripcion = ?";
    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, descripcion);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            MetodoDePagoModel metodo = new MetodoDePagoModel();
            metodo.setId(rs.getInt("id"));
            metodo.setDescripcion(rs.getString("descripcion"));
            return metodo;
        }

    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Error al obtener método de pago por descripción", e);
    }
    return null;
}

    
}
