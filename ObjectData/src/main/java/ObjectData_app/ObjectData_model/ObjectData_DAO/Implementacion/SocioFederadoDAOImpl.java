package ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.FederacionModel;
import ObjectData_app.ObjectData_model.SocioFederadoModel;
import ObjectData_app.ObjectData_model.ObjectData_DAO.ConexionBD;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.FederacionDAO;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioFederadoDAO;

public class SocioFederadoDAOImpl implements SocioFederadoDAO {
    //Con este metodo lo que hacemos es una sentencia SQL para obtener todos los socios estandar almacenados en la base de datos.
    @Override
    public ArrayList<SocioFederadoModel> obtenerTodosSocioFederado() throws SQLException {
        ArrayList<SocioFederadoModel> socios = new ArrayList<>(); // Se crea una lista para almacenar los socios federados encontrados
        try (
            Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
            PreparedStatement pst = con.prepareStatement("SELECT * FROM socioFederado"); // Se prepara una consulta SQL para seleccionar todos los socios federados
            ResultSet respuestaBD = pst.executeQuery() // Se ejecuta la consulta y se almacena el resultado en un ResultSet
        ) {
            while (respuestaBD.next()) { // Mientras haya resultados en el ResultSet...
                String codigoFederacion = respuestaBD.getString("codigoFederacion"); // Se obtiene el código de federación del resultado
                FederacionDAO federacionDAO = new FederacionDAOImpl(); // Se crea una instancia de FederacionDAOImpl
                FederacionModel federacion = federacionDAO.obtenerPorCodigo(codigoFederacion); // Se obtiene la federación correspondiente al código obtenido
                SocioFederadoModel socio = new SocioFederadoModel( // Se crea un objeto SocioFederadoModel con los datos del resultado
                    respuestaBD.getInt("numeroSocio"), // Se obtiene el valor del campo 'numeroSocio' del resultado
                    respuestaBD.getString("nombre"), // Se obtiene el valor del campo 'nombre' del resultado
                    respuestaBD.getString("NIF"), // Se obtiene el valor del campo 'NIF' del resultado
                    federacion // Se asigna el objeto FederacionModel obtenido al atributo de federación del objeto SocioFederadoModel
                );
                socios.add(socio); // Se agrega el objeto SocioFederadoModel a la lista de socios federados
            }
        } catch (Exception e) { // Si ocurre alguna excepción durante el proceso...
            throw new SQLException("Error al obtener todos los socios federados.", e); // Se lanza una excepción indicando el error
        }
        return socios; // Se devuelve la lista de socios federados encontrados
    }
    //Con este metodo se obtiene el socio estandar que coincida con el numero de socio.
    @Override
    public SocioFederadoModel obtenerPorNumeroSocio(int numeroSocio) throws SQLException {
        SocioFederadoModel socio = null;
        try (
            Connection con = ConexionBD.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM socioFederado WHERE numeroSocio=?")
        ) {
            pst.setInt(1, numeroSocio);
            try (ResultSet respuestaBD = pst.executeQuery()) {
                if (respuestaBD.next()) {
                    String codigoFederacion = respuestaBD.getString("codigoFederacion");
                    FederacionDAO federacionDAO = new FederacionDAOImpl();
                    FederacionModel federacion = federacionDAO.obtenerPorCodigo(codigoFederacion);
                    federacion.setCodigo(respuestaBD.getString("codigoFederacion"));
                    socio = new SocioFederadoModel(
                        respuestaBD.getInt("numeroSocio"),
                        respuestaBD.getString("nombre"),
                        respuestaBD.getString("NIF"),
                        federacion
                    );
                }
            }
        } catch (Exception e) {
            throw new SQLException("Error al obtener el socio estándar por número de socio", e);
        }
        return socio;
    }
    //Metodo para crear un socio federado.
    @Override
    public void crearSocioFederado(SocioFederadoModel socio) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("INSERT INTO socioFederado (numeroSocio, nombre, NIF, codigoFederacion) VALUES (?, ?, ?, ?)")
        ){
            pst.setInt(1, socio.getNumeroSocio());
            pst.setString(2, socio.getNombre());
            pst.setString(3, socio.getNIF());
            pst.setString(4, socio.getFederacion().getCodigo().toString());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Unimplemented method 'crearSocioFederado'");
        }
    }
    //Metodo para eliminar al socio Federado.
    @Override
    public void eliminarSocioFederado(int numeroSocio) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("DELETE FROM socioFederado WHERE numeroSocio=?")
        ) {
            pst.setInt(1, numeroSocio);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Unimplemented method 'eliminarSocioFederado'");
        }
    }
}