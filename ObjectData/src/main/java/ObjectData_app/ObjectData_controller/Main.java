//Este archivo es el que busca el programa al iniciar la aplicacion
//por primera vez, en este archivo tenemos la clase Main que contiene
//la llamada al metodo menuInicio del controlador.
package ObjectData_app.ObjectData_controller;
import ObjectData_app.ObjectData_model.Datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Datos de conexión
        String hostname = "62.72.37.1";
        String port = "3306";
        String database = "u450392368_ObjectData";
        String username = "u450392368_ObjectData";
        String password = "WmAb[>gM1]";

        // URL de conexión
        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;

        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            Connection connection = DriverManager.getConnection(url, username, password);

            // Verificar si la conexión fue exitosa
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
                // Puedes realizar operaciones con la base de datos aquí
                // Por ejemplo, ejecutar consultas, insertar datos, etc.
            } else {
                System.out.println("Fallo al conectar a la base de datos.");
            }

            // Cerrar la conexión
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        Datos BBDD = new Datos();
        AppController.cargarDatosController(BBDD);
        AppController.inicio(BBDD);
    }
}