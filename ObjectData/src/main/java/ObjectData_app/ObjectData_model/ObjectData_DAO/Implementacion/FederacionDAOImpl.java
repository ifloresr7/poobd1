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
        ArrayList<FederacionModel> federaciones = new ArrayList<>(); // Se crea una lista para almacenar las federaciones encontradas
        try (
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM federacion"); // Se prepara la consulta SQL para seleccionar todas las federaciones
            ResultSet respuestaBD = pst.executeQuery() // Se ejecuta la consulta y se almacena el resultado en un ResultSet
        ) {
            while (respuestaBD.next()) { // Mientras haya resultados en el ResultSet...
                FederacionModel federacion = new FederacionModel( // Se crea un objeto FederacionModel con los datos del resultado
                    respuestaBD.getString("codigo"), // Se obtiene el valor del campo 'codigo' del resultado
                    respuestaBD.getString("nombre") // Se obtiene el valor del campo 'nombre' del resultado
                );
                federaciones.add(federacion); // Se agrega el objeto FederacionModel a la lista de federaciones
            }
        } catch (SQLException e) { // Si ocurre alguna excepción durante el proceso...
            throw new UnsupportedOperationException("Unimplemented method 'obtenerTodas'"); // Se lanza una excepción indicando que el método no está implementado
        }
        return federaciones; // Se devuelve la lista de federaciones encontradas
    }

    // Método para obtener una federación mediante el código
    @Override
    public FederacionModel obtenerPorCodigo(String codigo) throws SQLException {
        FederacionModel federacion = null; // Se inicializa la variable que almacenará la federación encontrada
        try (
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM federacion WHERE codigo=?"); // Se prepara la consulta SQL para seleccionar la federación con el código proporcionado
        ) {
            pst.setString(1, codigo); // Se establece el valor del parámetro 'codigo' en la consulta preparada
            ResultSet respuestaBD = pst.executeQuery(); // Se ejecuta la consulta y se almacena el resultado en un ResultSet
            if (respuestaBD.next()) { // Si hay un resultado en el ResultSet...
                federacion = new FederacionModel( // Se crea un objeto FederacionModel con los datos del resultado
                    respuestaBD.getString("codigo"), // Se obtiene el valor del campo 'codigo' del resultado
                    respuestaBD.getString("nombre") // Se obtiene el valor del campo 'nombre' del resultado
                );
            }
        } catch (SQLException e) { // Si ocurre alguna excepción durante el proceso...
            throw new UnsupportedOperationException("Unimplemented method 'obtenerPorCodigo'"); // Se lanza una excepción indicando que el método no está implementado
        }
        return federacion; // Se devuelve el objeto FederacionModel encontrado (o null si no se encontró ninguna federación con el código proporcionado)
    }
}