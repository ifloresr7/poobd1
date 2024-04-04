package ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ExcursionModel;
import ObjectData_app.ObjectData_model.ObjectData_DAO.ConexionBD;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.ExcursionDAO;

public class ExcursionDAOImpl implements ExcursionDAO {
    //Metodo para obtener la lista de excursiones
    @Override
    public ArrayList<ExcursionModel> obtenerTodasExcursiones() throws SQLException {
        ArrayList<ExcursionModel> excursiones = new ArrayList<>(); // Se crea una lista para almacenar las excursiones obtenidas
        try (
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM excursion"); // Se prepara una consulta SQL para seleccionar todas las excursiones
            ResultSet respuestaBD = pst.executeQuery() // Se ejecuta la consulta y se obtiene el resultado en un ResultSet
        ) {
            while (respuestaBD.next()) { // Mientras haya resultados en el ResultSet...
                // Se crea un objeto ExcursionModel con los datos de la excursión obtenidos del resultado
                ExcursionModel excursion = new ExcursionModel(
                    respuestaBD.getInt("numeroExcursion"), // Se obtiene el número de la excursión del resultado
                    respuestaBD.getString("descripcion"), // Se obtiene la descripción de la excursión del resultado
                    respuestaBD.getDate("fecha"), // Se obtiene la fecha de la excursión del resultado
                    respuestaBD.getInt("numeroDias"), // Se obtiene el número de días de la excursión del resultado
                    respuestaBD.getDouble("precioInscripcion") // Se obtiene el precio de inscripción de la excursión del resultado
                );
                excursiones.add(excursion); // Se agrega el objeto ExcursionModel a la lista de excursiones
            }
        } catch (Exception e) { // Si ocurre alguna excepción durante el proceso...
            throw new UnsupportedOperationException("Unimplemented method 'obtenerTodas'"); // Se lanza una excepción indicando el error
        }
        return excursiones; // Se devuelve la lista de excursiones obtenidas
    }

    // Método para obtener una excursión por el número de excursión
    @Override
    public ExcursionModel obtenerPorNumeroExcursion(int numeroExcursion) throws SQLException {
        ExcursionModel excursion = null; // Se inicializa la variable que almacenará la excursión encontrada
        try (
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM excursion WHERE numeroExcursion=?") // Se prepara una consulta SQL para seleccionar la excursión con el número de excursión proporcionado
        ) {
            pst.setInt(1, numeroExcursion); // Se establece el valor del parámetro 'numeroExcursion' en la consulta preparada
            try (ResultSet respuestaBD = pst.executeQuery()) { // Se ejecuta la consulta y se obtiene el resultado en un ResultSet
                if (respuestaBD.next()) { // Si hay resultados en el ResultSet...
                    // Se crea un objeto ExcursionModel con los datos de la excursión obtenidos del resultado
                    excursion = new ExcursionModel(
                        respuestaBD.getInt("numeroExcursion"), // Se obtiene el número de la excursión del resultado
                        respuestaBD.getString("descripcion"), // Se obtiene la descripción de la excursión del resultado
                        respuestaBD.getDate("fecha"), // Se obtiene la fecha de la excursión del resultado
                        respuestaBD.getInt("numeroDias"), // Se obtiene el número de días de la excursión del resultado
                        respuestaBD.getDouble("precioInscripcion") // Se obtiene el precio de inscripción de la excursión del resultado
                    );
                }
            }
        } catch (Exception e) { // Si ocurre alguna excepción durante el proceso...
            // Se maneja la excepción
        }
        return excursion; // Se devuelve la excursión encontrada
    }

    @Override
    public void crearExcursion(ExcursionModel excursion) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
             PreparedStatement pst = con.prepareStatement("INSERT INTO excursion (numeroExcursion, descripcion, fecha, numeroDias, precioInscripcion) VALUES (?, ?, ?, ?, ?)") // Se prepara una consulta SQL para insertar una nueva excursión
        ) {
            pst.setInt(1, excursion.getNumeroExcursion()); // Se establece el valor del primer parámetro 'numeroExcursion' en la consulta preparada
            pst.setString(2, excursion.getDescripcion()); // Se establece el valor del segundo parámetro 'descripcion' en la consulta preparada
            pst.setDate(3, new java.sql.Date(excursion.getFecha().getTime())); // Se establece el valor del tercer parámetro 'fecha' en la consulta preparada, convirtiendo la fecha de Java a java.sql.Date
            pst.setInt(4, excursion.getNumeroDias()); // Se establece el valor del cuarto parámetro 'numeroDias' en la consulta preparada
            pst.setDouble(5, excursion.getPrecioInscripcion()); // Se establece el valor del quinto parámetro 'precioInscripcion' en la consulta preparada
            pst.executeUpdate(); // Se ejecuta la consulta para insertar la nueva excursión
        } catch (SQLException e) { // Si ocurre una excepción durante el proceso...
            throw new SQLException("Error al crear la excursión", e); // Se lanza una excepción indicando el error
        }
    }
    
    @Override
    public void eliminarExcursion(int numeroExcursion) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
             PreparedStatement pst = con.prepareStatement("DELETE FROM excursion WHERE numeroExcursion=?") // Se prepara una consulta SQL para eliminar la excursión con el número de excursión proporcionado
        ) {
            pst.setInt(1, numeroExcursion); // Se establece el valor del parámetro 'numeroExcursion' en la consulta preparada
            pst.executeUpdate(); // Se ejecuta la consulta para eliminar la excursión
        } catch (SQLException e) { // Si ocurre una excepción durante el proceso...
            throw new SQLException("Error al eliminar la excursión", e); // Se lanza una excepción indicando el error
        }
    }    
}