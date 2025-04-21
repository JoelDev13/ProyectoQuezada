/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import DBconexion.ConexionDB;
import java.util.List;
import java.util.ArrayList;
import model.usuario.Usuario;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.usuario.Rol;

/**
 *
 * @author Keren
 */
public class UsuarioDAO {
    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "{CALL sp_listar_usuarios()}";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasena(rs.getString("contrasena"));
                u.setRol(Rol.valueOf(rs.getString("rol")));
                u.setEmail(rs.getString("email"));
                u.setTelefono(rs.getString("telefono"));
                u.setActivo(rs.getBoolean("activo"));
                u.setImagen(rs.getBytes("imagen"));
                usuarios.add(u);
            }
        }

        return usuarios;
    }

    public List<Usuario> filtrarUsuarios(Usuario u) throws SQLException {
        String sql = "{CALL sp_filtrar_usuarios(?,?,?,?,?,?,?,?)}";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, u.getNombre());
            cs.setString(2, u.getApellido());
            cs.setString(3, u.getUsuario());
            cs.setString(4, u.getRol() != null ? u.getRol().name() : null);
            cs.setString(5, u.getEmail());
            cs.setString(6, u.getTelefono());
            cs.setBoolean(7, u.isActivo());
            cs.setBytes(8, u.getImagen());

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
               usuario.setRol(Rol.valueOf(rs.getString("rol")));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setImagen(rs.getBytes("imagen"));
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    public void agregarUsuario(Usuario u) throws SQLException {
        String sql = "{CALL sp_crear_usuario(?,?,?,?,?,?,?,?,?)}";
        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, u.getNombre());
            cs.setString(2, u.getApellido());
            cs.setString(3, u.getUsuario());
            cs.setString(4, u.getContrasena());
            cs.setString(5, u.getRol() != null ? u.getRol().name() : null);
            cs.setString(6, u.getEmail());
            cs.setString(7, u.getTelefono());
             cs.setBoolean(8, u.isActivo());
            cs.setBytes(9, u.getImagen());
            cs.execute();
        }
    }

    public void actualizarUsuario(Usuario u) throws SQLException {
        String sql = "{CALL sp_actualizar_usuario(?,?,?,?,?,?,?,?,?,?)}";
        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, u.getId());
            cs.setString(2, u.getNombre());
            cs.setString(3, u.getApellido());
            cs.setString(4, u.getUsuario());
            cs.setString(5, u.getContrasena());
            cs.setString(6, u.getRol() != null ? u.getRol().name() : null);
            cs.setString(7, u.getEmail());
            cs.setString(8, u.getTelefono());
            cs.setBoolean(9, u.isActivo());
            cs.setBytes(10, u.getImagen());
            cs.execute();
        }
    }
}


