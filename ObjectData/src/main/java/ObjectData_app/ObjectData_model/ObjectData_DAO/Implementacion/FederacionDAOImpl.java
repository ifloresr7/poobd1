package ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.FederacionModel;
import ObjectData_app.ObjectData_model.ObjectData_DAO.ConexionBD;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.FederacionDAO;

public class FederacionDAOImpl implements FederacionDAO {
    //Metodo para obtener la lista de federaciones
    @Override
    public ArrayList<FederacionModel> obtenerTodasFederaciones() throws SQLException {
        Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
        PreparedStatement pst = null; //De decrara la variable que almacenara la consula.
        con.setAutoCommit(false); //Desactiva el AutoCommit de la BBDD, basicamente para hacer el rollback
        ArrayList<FederacionModel> federaciones = new ArrayList<>(); // Se crea una lista para almacenar las federaciones encontradas
        try{
            pst = con.prepareStatement("SELECT * FROM federacion"); // Se prepara la consulta SQL para seleccionar todas las federaciones
            ResultSet respuestaBD = pst.executeQuery(); // Se ejecuta la consulta y se almacena el resultado en un ResultSet
            while (respuestaBD.next()) { // Mientras haya resultados en el ResultSet...
                FederacionModel federacion = new FederacionModel( // Se crea un objeto FederacionModel con los datos del resultado
                    respuestaBD.getString("codigo"), // Se obtiene el valor del campo 'codigo' del resultado
                    respuestaBD.getString("nombre") // Se obtiene el valor del campo 'nombre' del resultado
                );
                federaciones.add(federacion); // Se agrega el objeto FederacionModel a la lista de federaciones
            }
        } catch (SQLException e) { // Si ocurre alguna excepción durante el proceso...
            if (con != null) {
                con.rollback();  // Si algo sale mal, revertimos la transacción
            }
            throw new SQLException("Fallo en la consulta SQL al obtener todos las federaciones.");
        } finally {
            if (pst != null) {
                pst.close(); //Se cierra el statement.
            }
            if (con != null) {
                con.setAutoCommit(true);  // Revertimos a la configuración por defecto
                con.close(); //Se cierra la conexión de la base de datos.                
            }
        }
        return federaciones; // Se devuelve la lista de federaciones encontradas
    }
    // Método para obtener una federación mediante el código
    @Override
    public FederacionModel obtenerPorCodigo(String codigo) throws SQLException {
        Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
        PreparedStatement pst = null; //De decrara la variable que almacenara la consula.
        con.setAutoCommit(false); //Desactiva el AutoCommit de la BBDD, basicamente para hacer el rollback
        FederacionModel federacion = null; // Se inicializa la variable que almacenará la federación encontrada
        try{
            pst = con.prepareStatement("SELECT * FROM federacion WHERE codigo=?"); // Se prepara la consulta SQL para seleccionar la federación con el código proporcionado
            pst.setString(1, codigo); // Se establece el valor del parámetro 'codigo' en la consulta preparada
            ResultSet respuestaBD = pst.executeQuery(); // Se ejecuta la consulta y se almacena el resultado en un ResultSet
            if (respuestaBD.next()) { // Si hay un resultado en el ResultSet...
                federacion = new FederacionModel( // Se crea un objeto FederacionModel con los datos del resultado
                    respuestaBD.getString("codigo"), // Se obtiene el valor del campo 'codigo' del resultado
                    respuestaBD.getString("nombre") // Se obtiene el valor del campo 'nombre' del resultado
                );
            }
        } catch (SQLException e) { // Si ocurre alguna excepción durante el proceso...
            if (con != null) {
                con.rollback();  // Si algo sale mal, revertimos la transacción
            }
            throw new SQLException("Fallo en la consulta SQL al obtener la federación por el codigo.");
        } finally {
            if (pst != null) {
                pst.close(); //Se cierra el statement.
            }
            if (con != null) {
                con.setAutoCommit(true);  // Revertimos a la configuración por defecto
                con.close(); //Se cierra la conexión de la base de datos.                
            }
        }
        return federacion; // Se devuelve el objeto FederacionModel encontrado (o null si no se encontró ninguna federación con el código proporcionado)
    }
}