package ObjectData_app.ObjectData_model.ObjectData_DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    // Método para establecer la conexión
    public static Connection obtenerConexion(){
        try{
            String hostname = "62.72.37.1";
            String port = "3306";
            String database = "u450392368_ObjectData";
            String username = "u450392368_ObjectData";
            String password = "WmAb[>gM1]";
            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println("null");
        }
        return null;
    }
}