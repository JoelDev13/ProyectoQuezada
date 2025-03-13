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
    public Usuario verificarCredenciales(String nombreUsuario, String contrasena) throws SQLException {
        String sql = "call sp_verificar_usuario(?, ?)";
        Usuario usuario = null;

        try {
            conn = ConexionDB.obtenerConeccion();
            cs = conn.prepareCall(sql);

            // Establece los parámetros de la consulta SQL
            cs.setString(1, nombreUsuario); // Asignamos el nombre de usuario al primer parámetro
            cs.setString(2, contrasena);     // Asignamos la contraseña al segundo parámetro

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                // Comprobamos que los datos de la contraseña coincidan
                String contrasenaEnBaseDeDatos = rs.getString("contrasena");

                if (contrasena.equals(contrasenaEnBaseDeDatos)) {
                    // Si las contraseñas coinciden, creamos un objeto Usuario y asignamos todos los datos
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("ID"));
                    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setImagen(rs.getBytes("imagen"));
                    usuario.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores en caso de que la consulta falle
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (cs != null ) {
                cs.close();
            }
        }
        return usuario;
    }
}
