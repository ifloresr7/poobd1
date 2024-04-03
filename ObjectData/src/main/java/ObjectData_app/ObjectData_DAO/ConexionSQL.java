package ObjectData_app.ObjectData_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    // Método para establecer la conexión
    public static Connection getConnection() throws SQLException {
        try{
            String hostname = "62.72.37.1";
            String port = "3306";
            String database = "u450392368_ObjectData";
            String username = "u450392368_ObjectData";
            String password = "WmAb[>gM1]";
            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            throw e;
        }
    }
}