package ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.SocioInfantilModel;
import ObjectData_app.ObjectData_model.ObjectData_DAO.ConexionBD;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioInfantilDAO;

public class SocioInfantilDAOImpl implements SocioInfantilDAO {
    //Con este metodo lo que hacemos es una sentencia SQL para obtener todos los socios estandar almacenados en la base de datos.
    @Override
    public ArrayList<SocioInfantilModel> obtenerTodosSocioInfantil() throws SQLException {
        ArrayList<SocioInfantilModel> socios = new ArrayList<>(); // Se crea una lista para almacenar los socios infantiles encontrados
        try (
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM socioInfantil"); // Se prepara una consulta SQL para seleccionar todos los socios infantiles
            ResultSet respuestaBD = pst.executeQuery() // Se ejecuta la consulta y se almacena el resultado en un ResultSet
        ) {
            while (respuestaBD.next()) { // Mientras haya resultados en el ResultSet...
                SocioInfantilModel socio = new SocioInfantilModel( // Se crea un objeto SocioInfantilModel con los datos del resultado
                    respuestaBD.getInt("numeroSocio"), // Se obtiene el valor del campo 'numeroSocio' del resultado
                    respuestaBD.getString("nombre"), // Se obtiene el valor del campo 'nombre' del resultado
                    respuestaBD.getInt("numeroSocioTutorLegal") // Se obtiene el valor del campo 'numeroSocioTutorLegal' del resultado
                );
                socios.add(socio); // Se agrega el objeto SocioInfantilModel a la lista de socios infantiles
            }
        } catch (Exception e) { // Si ocurre alguna excepción durante el proceso...
            throw new SQLException("Fallo en la consulta SQL al obtener los socios infantiles."); // Se lanza una excepción indicando el error
        }
        return socios; // Se devuelve la lista de socios infantiles encontrados
    }
    
    //Metodo para obtener el socio infantil
    @Override
    public SocioInfantilModel obtenerPorNumeroSocio(int numeroSocio) throws SQLException {
        SocioInfantilModel socio; // Se declara una variable para almacenar el socio infantil encontrado
        try (
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM socioInfantil WHERE numeroSocio=?") // Se prepara una consulta SQL para seleccionar el socio infantil con el número de socio proporcionado
        ) {
            pst.setInt(1, numeroSocio); // Se establece el valor del parámetro 'numeroSocio' en la consulta preparada
            ResultSet respuestaBD = pst.executeQuery(); // Se ejecuta la consulta y se almacena el resultado en un ResultSet
            
            if(respuestaBD.next()) { // Si hay un resultado en el ResultSet...
                socio = new SocioInfantilModel( // Se crea un objeto SocioInfantilModel con los datos del resultado
                    respuestaBD.getInt("numeroSocio"), // Se obtiene el valor del campo 'numeroSocio' del resultado
                    respuestaBD.getString("nombre"), // Se obtiene el valor del campo 'nombre' del resultado
                    respuestaBD.getInt("numeroSocioTutorLegal") // Se obtiene el valor del campo 'numeroSocioTutorLegal' del resultado
                );
            } else {
                socio = null; // Si no hay resultados, se asigna null a la variable socio
            }
        } catch (Exception e) { // Si ocurre alguna excepción durante el proceso...
            throw new SQLException("Fallo en la consulta SQL al obtener el socio por nímero de socio: " + numeroSocio); // Se lanza una excepción indicando el error
        }
        return socio; // Se devuelve el objeto SocioInfantilModel encontrado (o null si no se encontró ningún socio infantil con el número de socio proporcionado)
    }
    
    @Override
    public void crearSocioInfantil(SocioInfantilModel socio) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("INSERT INTO socioInfantil (numeroSocio, nombre, numeroSocioTutorLegal) VALUES (?, ?, ?)") // Se prepara una consulta SQL para insertar un nuevo socio infantil
        ) {
            pst.setInt(1, socio.getNumeroSocio()); // Se establece el valor del parámetro 'numeroSocio' en la consulta preparada
            pst.setString(2, socio.getNombre()); // Se establece el valor del parámetro 'nombre' en la consulta preparada
            pst.setInt(3, socio.getNumeroSocioPadreMadre()); // Se establece el valor del parámetro 'NumeroSocioPadreMadre' en la consulta preparada
            pst.executeUpdate(); // Se ejecuta la consulta para insertar el nuevo socio infantil
        } catch (SQLException e) { // Si ocurre una excepción durante el proceso...
            throw new SQLException("Fallo en la consulta SQL al crear el socio infantil."); // Se lanza una excepción indicando el error
        }
    }

    //Metodo para eliminar socio infantil.
    @Override
    public void eliminarSocioInfantil(int numeroSocio) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("DELETE FROM socioInfantil WHERE numeroSocio=?") // Se prepara una consulta SQL para eliminar el socio infantil con el número de socio proporcionado
        ) {
            pst.setInt(1, numeroSocio); // Se establece el valor del parámetro 'numeroSocio' en la consulta preparada
            pst.executeUpdate(); // Se ejecuta la consulta para eliminar el socio infantil
        } catch (SQLException e) { // Si ocurre una excepción durante el proceso...
            throw new SQLException("Fallo en la consulta SQL al eliminar el socio infantil."); // Se lanza una excepción indicando el error
        }
    }
}