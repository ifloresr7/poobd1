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
    public ArrayList<InscripcionModel> obtenerTodasLasInscripciones() throws SQLException {
        ArrayList<InscripcionModel> inscripciones = new ArrayList<>(); // Se crea una lista para almacenar las inscripciones
        // Se inicia un bloque try-with-resources para manejar la conexión y los recursos JDBC
        Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
        PreparedStatement pst = con.prepareStatement("SELECT * FROM inscripcion") // Se prepara la consulta SQL para seleccionar inscripciones por número de socio
        con.setAutoCommit(false); //Desactiva el AutoCommit de la BBDD, basicamente para hacer el rollback
        try {
            // Se ejecuta la consulta SQL y se obtiene un conjunto de resultados
            ResultSet respuestaBD = pst.executeQuery();
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
        catch (SQLException e) 
        {
            if (con != null) 
            {
            con.rollback();  // Si algo sale mal, revertimos la transacción
            }
            throw new SQLException("Fallo en la consulta SQL al obtener todas las inscripciones.");
        }
     finally {
        if (pst != null) {
            pst.close(); //Se cierra el statement.
        }
        if (con != null) {
            con.setAutoCommit(true);  // Revertimos a la configuración por defecto
            con.close(); //Se cierra la conexión de la base de datos.                
        }
    }
        // Se devuelve la lista de inscripciones obtenida
        return inscripciones;
    }
    @Override
    public ArrayList<InscripcionModel> obtenerTodasPorNumeroSocio(int numeroSocio) throws SQLException {
        ArrayList<InscripcionModel> inscripciones = new ArrayList<>(); // Se crea una lista para almacenar las inscripciones
        // Se inicia un bloque try-with-resources para manejar la conexión y los recursos JDBC
        Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
        PreparedStatement pst = con.prepareStatement("SELECT * FROM inscripcion WHERE numeroSocio=?") // Se prepara la consulta SQL para seleccionar inscripciones por número de socio
        con.setAutoCommit(false); //Desactiva el AutoCommit de la BBDD, basicamente para hacer el rollback
        
        try{
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
            if (con != null) 
            {
            con.rollback();  // Si algo sale mal, revertimos la transacción
            }
            
            throw new SQLException("Fallo en la consulta SQL al obtener todas las inscripciones para el socio con número: " + numeroSocio);
        }
        // Se devuelve la lista de inscripciones obtenida
        finally {
            if (pst != null) {
                pst.close(); //Se cierra el statement.
            }
            if (con != null) {
                con.setAutoCommit(true);  // Revertimos a la configuración por defecto
                con.close(); //Se cierra la conexión de la base de datos.                
            }
        }
        return inscripciones;
    }
    @Override
    public void crearInscripcion(InscripcionModel inscripcion) throws SQLException {
        // Establecer la conexión a la base de datos
        Connection con = ConexionBD.obtenerConexion();
             // Preparar la sentencia SQL para la inserción de la inscripción
        PreparedStatement pst = con.prepareStatement("INSERT INTO inscripcion (numeroInscripcion, numeroSocio, numeroExcursion, fechaInscripcion) VALUES (?, ?, ?, ?)")
        con.setAutoCommit(false); //Desactiva el AutoCommit de la BBDD, basicamente para hacer el rollback
        try  {
            // Establecer los valores de los parámetros de la sentencia SQL
            pst.setInt(1, inscripcion.getNumeroInscripcion());
            pst.setInt(2, inscripcion.getNumeroSocio());
            pst.setInt(3, inscripcion.getNumeroExcursion());
            pst.setDate(4, new java.sql.Date(inscripcion.getFechaInscripcion().getTime())); // Convertir la fecha de Java a SQL
            // Ejecutar la sentencia SQL para realizar la inserción
            pst.executeUpdate();
        } catch (SQLException e) {
            // Lanzar una SQLException en caso de error
            if (con != null) 
            {
            con.rollback();  // Si algo sale mal, revertimos la transacción
            }
            throw new SQLException("Fallo en la consulta SQL al crear la inscripción.");
        }
        finally {
            if (pst != null) {
                pst.close(); //Se cierra el statement.
            }
            if (con != null) {
                con.setAutoCommit(true);  // Revertimos a la configuración por defecto
                con.close(); //Se cierra la conexión de la base de datos.                
            }
        }
    }
    @Override
    public void eliminarInscripcion(int numeroInscripcion) throws SQLException {
        Connection con = ConexionBD.obtenerConexion();
        PreparedStatement pst = con.prepareStatement("DELETE FROM inscripcion WHERE numeroInscripcion=?")
        con.setAutoCommit(false); //Desactiva el AutoCommit de la BBDD, basicamente para hacer el rollback
        
        try  
        {
            // Establecer el número de inscripción como parámetro en la sentencia SQL
            pst.setInt(1, numeroInscripcion);
            // Ejecutar la sentencia SQL para eliminar la inscripción
            pst.executeUpdate();
        } catch (SQLException e) {
            // Lanzar una SQLException en caso de error
            // Lanzar una SQLException en caso de error
            if (con != null) 
            {
            con.rollback();  // Si algo sale mal, revertimos la transacción
            }
            throw new SQLException("Fallo en la consulta SQL al eliminar la inscripción con número: " + numeroInscripcion);
        }
        finally {
            if (pst != null) {
                pst.close(); //Se cierra el statement.
            }
            if (con != null) {
                con.setAutoCommit(true);  // Revertimos a la configuración por defecto
                con.close(); //Se cierra la conexión de la base de datos.                
            }
        }

    }
}