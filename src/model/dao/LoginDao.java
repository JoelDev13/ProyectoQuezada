package model.dao;

import DBconexion.ConexionDB;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author la
 */
public class LoginDao {

    private Connection conn;
    private CallableStatement cs;
    
    /**
     * Verifica las credenciales del usuario y recupera su información
     *
     * @param nombreUsuario El nombre de usuario ingresado
     * @param contrasena La contraseña ingresada
     * @return Usuario si las credenciales son correctas, o null si no coinciden
     */
    public Usuario verificarCredenciales(String usuario, String contrasena){
        String sql = "{CALL VerificarLogin(?, ?)}";
        
        Usuario user = null;

        try(Connection conn = ConexionDB.obtenerConeccion();
           CallableStatement cs = conn.prepareCall(sql)){;

            // Establece los parámetros de la consulta SQL
            cs.setString(1, usuario); // Asignamos el nombre de usuario al primer parámetro
            cs.setString(2, contrasena);     // Asignamos la contraseña al segundo parámetro

            try( ResultSet rs = cs.executeQuery()){

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setTelefono(rs.getString("telefono"));
                user.setUsuario(rs.getString("usuario"));
                user.setContrasena(rs.getString("contrasena"));
                user.setEmail(rs.getString("email"));
                user.setImagen(rs.getBytes("imagen"));
                user.setRol(rs.getString("rol"));
                 }
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores en caso de que la consulta falle
        } 
        
        return user;
    }
}