/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author la
 */
public class LoginDao {
    /**
     * Verifica las credenciales del usuario y recupera su información
     * @param nombreUsuario El nombre de usuario ingresado
     * @param contrasena La contraseña ingresada
     * @return Usuario si las credenciales son correctas, o null si no coinciden
     */
    public Usuario verificarCredenciales(String nombreUsuario, String contrasena) {
        String sql = "{CALL sp_verificar_usuario(?, ?)}";
        Usuario usuario = null;

        try (Connection conn = ConexionDB.obtenerConeccion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los parámetros de la consulta SQL
            stmt.setString(1, nombreUsuario); // Asignamos el nombre de usuario al primer parámetro
            stmt.setString(2, contrasena);     // Asignamos la contraseña al segundo parámetro

            ResultSet rs = stmt.executeQuery();

            
            if (rs.next()) {
                // Comprobamos que los datos de la contraseña coincidan
                String contrasenaEnBaseDeDatos = rs.getString("Constrasena");

                if (contrasena.equals(contrasenaEnBaseDeDatos)) {
                    // Si las contraseñas coinciden, creamos un objeto Usuario y asignamos todos los datos
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("ID"));
                    usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                    usuario.setEmail(rs.getString("Email"));
                    usuario.setImagen(rs.getBytes("Imagen"));
                    usuario.setRol(rs.getString("Rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores en caso de que la consulta falle
        }

        return usuario;
    }
}   

