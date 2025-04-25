package DBconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 *Clase que gestiona las conexiones a la base de datos
 * @author la
 * 
 */
public class ConexionDB {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private static Connection conexion;

    /**
     *
     *  Obtiene una conexion a la base de datos. Este metodo recupera las credenciales
     *  de acceso a la base de datos del archivo config/config.properties. Configura este
     *  archivo para que esta clase se conecte correctamente ala la base de datos.
     * @return una conexión a la base de datos
     * @throws SQLException es para si ocurre un error al conectar
     */
    public static Connection obtenerConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            return conexion;
        }

        try {

            InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("config/config.properties");
            Properties prop = new Properties();
            prop.load(input);

            URL = prop.getProperty("URL");
            USER = prop.getProperty("USER");
            PASSWORD = prop.getProperty("PASSWORD");

            System.out.println(URL + " " + USER + " " + PASSWORD);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            return conexion;
        } catch (IOException ex) {
            ex.printStackTrace(); // Si hay un error, se imprimirá
            return null;
        }
    }
}
