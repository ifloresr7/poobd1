package ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.InscripcionModel;
import ObjectData_app.ObjectData_model.ObjectData_DAO.ConexionBD;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.InscripcionDAO;

public class InscripcionDAOImpl implements InscripcionDAO {
    @Override
    public ArrayList<InscripcionModel> obtenerTodasLasInscripciones() {
        ArrayList<InscripcionModel> inscripciones = new ArrayList<>(); // Se crea una lista para almacenar las inscripciones
    
        // Se inicia un bloque try-with-resources para manejar la conexión y los recursos JDBC
        try(
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM inscripcion") // Se prepara la consulta SQL para seleccionar inscripciones por número de socio
        ) {
            // Se ejecuta la consulta SQL y se obtiene un conjunto de resultados
            try (ResultSet respuestaBD = pst.executeQuery()) {
                // Se itera sobre el conjunto de resultados
                while (respuestaBD.next()) {
                    // Se crean objetos InscripcionModel utilizando los valores de las columnas del conjunto de resultados
                    InscripcionModel inscripcion = new InscripcionModel(
                        respuestaBD.getInt("numeroInscripcion"),
                        respuestaBD.getInt("numeroSocio"),
                        respuestaBD.getInt("numeroExcursion"),
                        respuestaBD.getDate("fechaInscripcion")
                    );
                    // Se agrega cada objeto InscripcionModel a la lista de inscripciones
                    inscripciones.add(inscripcion);
                }
            }
        } catch (Exception e) {
            // Logica para devolver el error a la vista:
            // "Error al obtener todas las inscripciones"
        }
        // Se devuelve la lista de inscripciones obtenida
        return inscripciones;
    }

    @Override
    public ArrayList<InscripcionModel> obtenerTodasPorNumeroSocio(int numeroSocio) throws SQLException {
        ArrayList<InscripcionModel> inscripciones = new ArrayList<>(); // Se crea una lista para almacenar las inscripciones
    
        // Se inicia un bloque try-with-resources para manejar la conexión y los recursos JDBC
        try(
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM inscripcion WHERE numeroSocio=?") // Se prepara la consulta SQL para seleccionar inscripciones por número de socio
        ) {
            pst.setInt(1, numeroSocio); // Se establece el número de socio en la consulta preparada
    
            // Se ejecuta la consulta SQL y se obtiene un conjunto de resultados
            try (ResultSet respuestaBD = pst.executeQuery()) {
                // Se itera sobre el conjunto de resultados
                while (respuestaBD.next()) {
                    // Se crean objetos InscripcionModel utilizando los valores de las columnas del conjunto de resultados
                    InscripcionModel inscripcion = new InscripcionModel(
                        respuestaBD.getInt("numeroInscripcion"),
                        respuestaBD.getInt("numeroSocio"),
                        respuestaBD.getInt("numeroExcursion"),
                        respuestaBD.getDate("fechaInscripcion")
                    );
                    // Se agrega cada objeto InscripcionModel a la lista de inscripciones
                    inscripciones.add(inscripcion);
                }
            }
        } catch (Exception e) {
            // Se lanza una SQLException en caso de error, indicando el problema
            throw new SQLException("Error al obtener todas las inscripciones para el socio con número " + numeroSocio, e);
        }
        // Se devuelve la lista de inscripciones obtenida
        return inscripciones;
    }

    @Override
    public void crearInscripcion(InscripcionModel inscripcion) throws SQLException {
        // Establecer la conexión a la base de datos
        try (Connection con = ConexionBD.obtenerConexion();
             // Preparar la sentencia SQL para la inserción de la inscripción
             PreparedStatement pst = con.prepareStatement("INSERT INTO inscripcion (numeroInscripcion, numeroSocio, numeroExcursion, fechaInscripcion) VALUES (?, ?, ?, ?)")
        ) {
            // Establecer los valores de los parámetros de la sentencia SQL
            pst.setInt(1, inscripcion.getNumeroInscripcion());
            pst.setInt(2, inscripcion.getNumeroSocio());
            pst.setInt(3, inscripcion.getNumeroExcursion());
            pst.setDate(4, new java.sql.Date(inscripcion.getFechaInscripcion().getTime())); // Convertir la fecha de Java a SQL
    
            // Ejecutar la sentencia SQL para realizar la inserción
            pst.executeUpdate();
        } catch (SQLException e) {
            // Lanzar una SQLException en caso de error
            throw new SQLException("Error al crear la inscripción", e);
        }
    }
    
    @Override
    public void eliminarExcursion(int numeroInscripcion) throws SQLException {
        try (
            Connection con = ConexionBD.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("DELETE FROM inscripcion WHERE numeroInscripcion=?")
        ) {
            // Establecer el número de inscripción como parámetro en la sentencia SQL
            pst.setInt(1, numeroInscripcion);
            
            // Ejecutar la sentencia SQL para eliminar la inscripción
            pst.executeUpdate();
        } catch (SQLException e) {
            // Lanzar una SQLException en caso de error
            throw new SQLException("Error al eliminar la inscripción con número: " + numeroInscripcion, e);
        }
    }
}