package model.dao;

import DBconexion.ConexionDB;
import model.usuario.Usuario;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.usuario.Rol;

/**
 *
 * @author la
 */
public class LoginDao {

    /**
     * Verifica las credenciales del usuario y recupera su información
     *
     * @param usuario Usuario a buscar
     * @param contrasena La contraseña ingresada
     * @return Usuario si las credenciales son correctas, o null si no coinciden
     */
    public Usuario verificarCredenciales(String usuario, String contrasena) throws SQLException {
        String sql = "{CALL VerificarLogin(?, ?)}";
        Usuario user = new Usuario();

        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {

            // Establece los parámetros de la consulta SQL
            cs.setString(1, usuario); // Asignamos el nombre de usuario al primer parámetro
            cs.setString(2, contrasena);     // Asignamos la contraseña al segundo parámetro

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setNombre(rs.getString("nombre"));
                    user.setApellido(rs.getString("apellido"));
                    user.setTelefono(rs.getString("telefono"));
                    user.setUsuario(rs.getString("usuario"));
                    user.setEmail(rs.getString("email"));
                    user.setActivo(rs.getBoolean("activo"));
                    user.setImagen(rs.getBytes("imagen"));
                    user.setRol(Rol.valueOf(rs.getString("rol")));

                }
            }
            return user;
        }
    }
}
