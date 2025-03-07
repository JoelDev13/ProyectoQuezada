package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author la
 */
public class ConexionDB {
     private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            
            URL = prop.getProperty("url");
            USER = prop.getProperty("user");
            PASSWORD = prop.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace(); // Si hay un error, se imprimirá
        }
    }

    /**
     * 
     * 
     * @return una conexión a la base de datos
     * @throws SQLException es para si ocurre un error al conectar
     */
    public static Connection obtenerConeccion() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("No se puede conectar a la base de datos", e);
        }
    }
    
    public static void cerrarConexion(Connection conn) {
    if (conn != null) {
        try {
            conn.close();
            System.out.println("Conexión cerrada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

}
