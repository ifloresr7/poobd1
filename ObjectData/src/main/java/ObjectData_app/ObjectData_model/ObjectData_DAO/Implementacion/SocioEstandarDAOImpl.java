package ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.SeguroModel;
import ObjectData_app.ObjectData_model.SeguroModel.TipoSeguro;
import ObjectData_app.ObjectData_model.SocioEstandarModel;
import ObjectData_app.ObjectData_model.ObjectData_DAO.ConexionBD;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioEstandarDAO;

public class SocioEstandarDAOImpl implements SocioEstandarDAO {
    //Con este metodo lo que hacemos es una sentencia SQL para obtener todos los socios estandar almacenados en la base de datos.
    @Override
    public ArrayList<SocioEstandarModel> obtenerTodosSocioEstandar() throws SQLException {
        ArrayList<SocioEstandarModel> socios = new ArrayList<>();
        SeguroModel seguro;
        try(
            Connection con = ConexionBD.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM socioEstandar");
            ResultSet respuestaBD = pst.executeQuery()) {
            while (respuestaBD.next()) {
                seguro = new SeguroModel(TipoSeguro.valueOf(respuestaBD.getString("seguro")));
                SocioEstandarModel socio = new SocioEstandarModel(
                    respuestaBD.getInt("numeroSocio"),
                    respuestaBD.getString("nombre"),
                    respuestaBD.getString("NIF"),
                    seguro
                );
                socios.add(socio);
            }
        } catch (SQLException e) {
            throw new SQLException("Problema al obtener todos los socios estándar.");
        }
        return socios;
    }
    //Con este metodo se obtiene el socio estandar que coincida con el numero de socio.
    @Override
    public SocioEstandarModel obtenerSocioEstandarPorNumeroSocio(int numeroSocio) throws SQLException {
        SocioEstandarModel socio = null;
        try (
            Connection con = ConexionBD.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM socioEstandar WHERE numeroSocio=?")
        ) {
            pst.setInt(1, numeroSocio);
            try (ResultSet respuestaBD = pst.executeQuery()) {
                if (respuestaBD.next()) {
                    SeguroModel seguro = new SeguroModel(TipoSeguro.valueOf(respuestaBD.getString("seguro")));
                    socio = new SocioEstandarModel(
                        respuestaBD.getInt("numeroSocio"),
                        respuestaBD.getString("nombre"),
                        respuestaBD.getString("NIF"),
                        seguro
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Problema al obtener el socio estándar por número de socio: " + numeroSocio);
        }
        return socio;
    }
    
    @Override
    public void crearSocioEstandar(SocioEstandarModel socio) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement pst = con.prepareStatement("INSERT INTO socioEstandar (numeroSocio, nombre, NIF, seguro) VALUES (?, ?, ?, ?)")
        ) {
            pst.setInt(1, socio.getNumeroSocio());
            pst.setString(2, socio.getNombre());
            pst.setString(3, socio.getNIF());
            pst.setString(4, socio.getSeguro().getTipo().toString());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Problema al crear el socio estándar.");
        }
    }
    //Metodo usado para actualizar el socio, en este caso se usa porque podemos cambiar el seguro del tipo de socio estandar.
    @Override
    public void actualizarSocioEstandar(SocioEstandarModel socio) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement pst = con.prepareStatement("UPDATE socioEstandar SET nombre=?, NIF=?, seguro=? WHERE numeroSocio=?")
        ) {
            pst.setString(1, socio.getNombre());
            pst.setString(2, socio.getNIF());
            pst.setString(3, socio.getSeguro().getTipo().toString());
            pst.setInt(4, socio.getNumeroSocio());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Problema al actualizar el socio estándar.");
        }
    }
    //Metodo para eliminar el socio estandar.
    @Override
    public void eliminarSocioEstandar(int numeroSocio) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement pst = con.prepareStatement("DELETE FROM socioEstandar WHERE numeroSocio=?")
        ) {
            pst.setInt(1, numeroSocio);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Problema al eliminar el socio estándar.");
        }
    }
}