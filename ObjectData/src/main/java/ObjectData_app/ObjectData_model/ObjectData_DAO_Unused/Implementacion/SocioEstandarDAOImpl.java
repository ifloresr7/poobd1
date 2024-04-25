package ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.SeguroModel;
import ObjectData_app.ObjectData_model.SeguroModel.TipoSeguro;
import ObjectData_app.ObjectData_model.SocioEstandarModel;
import ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.ConexionBD;
import ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Interfaces.SocioEstandarDAO;

public class SocioEstandarDAOImpl implements SocioEstandarDAO {
    // Con este metodo lo que hacemos es una sentencia SQL para obtener todos los
    // socios estandar almacenados en la base de datos.
    @Override
    public ArrayList<SocioEstandarModel> obtenerTodosSocioEstandar() throws SQLException {
        Connection con = ConexionBD.obtenerConexion();
        PreparedStatement pst = null;
        ArrayList<SocioEstandarModel> socios = new ArrayList<>();
        SeguroModel seguro;
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement("SELECT * FROM socioEstandar");
            ResultSet respuestaBD = pst.executeQuery();
            while (respuestaBD.next()) {
                seguro = new SeguroModel(TipoSeguro.valueOf(respuestaBD.getString("seguro")));
                SocioEstandarModel socio = new SocioEstandarModel(
                        respuestaBD.getInt("numeroSocio"),
                        respuestaBD.getString("nombre"),
                        respuestaBD.getString("NIF"),
                        seguro);
                socios.add(socio);
            }
            con.commit();

        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw new SQLException("Fallo en la consulta SQL al obtener todos los socios estándar.");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
        return socios;
    }

    // Con este metodo se obtiene el socio estandar que coincida con el numero de
    // socio.
    @Override
    public SocioEstandarModel obtenerSocioEstandarPorNumeroSocio(int numeroSocio) throws SQLException {
        Connection con = ConexionBD.obtenerConexion();
        PreparedStatement pst = null;
        con.setAutoCommit(false);
        SocioEstandarModel socio = null;
        try {
            pst = con.prepareStatement("SELECT * FROM socioEstandar WHERE numeroSocio=?");
            pst.setInt(1, numeroSocio);

            try (ResultSet respuestaBD = pst.executeQuery()) {
                if (respuestaBD.next()) {
                    SeguroModel seguro = new SeguroModel(TipoSeguro.valueOf(respuestaBD.getString("seguro")));
                    socio = new SocioEstandarModel(
                            respuestaBD.getInt("numeroSocio"),
                            respuestaBD.getString("nombre"),
                            respuestaBD.getString("NIF"),
                            seguro);
                }
            }
            con.commit();

        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw new SQLException(
                    "Fallo en la consulta SQL al obtener el socio estándar por número de socio: " + numeroSocio);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
        return socio;
    }

    @Override
    public void crearSocioEstandar(SocioEstandarModel socio) throws SQLException {
        Connection con = ConexionBD.obtenerConexion();
        PreparedStatement pst = null;
        con.setAutoCommit(false);
        try {
            pst = con.prepareStatement(
                    "INSERT INTO socioEstandar (numeroSocio, nombre, NIF, seguro) VALUES (?, ?, ?, ?)");
            pst.setInt(1, socio.getNumeroSocio());
            pst.setString(2, socio.getNombre());
            pst.setString(3, socio.getNIF());
            pst.setString(4, socio.getSeguro().getTipo().toString());
            pst.executeUpdate();
            con.commit();
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw new SQLException("Fallo en la consulta SQL al crear el socio estándar.");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    // Metodo usado para actualizar el socio, en este caso se usa porque podemos
    // cambiar el seguro del tipo de socio estandar.
    @Override
    public void actualizarSocioEstandar(SocioEstandarModel socio) throws SQLException {
        Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
        PreparedStatement pst = null; // De decrara la variable que almacenara la consula.
        con.setAutoCommit(false); // Desactiva el AutoCommit de la BBDD, basicamente para hacer el rollback
        try {
            pst = con.prepareStatement("UPDATE socioEstandar SET nombre=?, NIF=?, seguro=? WHERE numeroSocio=?");
            pst.setString(1, socio.getNombre());
            pst.setString(2, socio.getNIF());
            pst.setString(3, socio.getSeguro().getTipo().toString());
            pst.setInt(4, socio.getNumeroSocio());
            pst.executeUpdate();
            con.commit(); // Si todo va bien, confirmamos la transacción
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw new SQLException("Fallo en la consulta SQL al actualizar el socio estándar.");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    // Metodo para eliminar el socio estandar.
    @Override
    public void eliminarSocioEstandar(int numeroSocio) throws SQLException {
        Connection con = ConexionBD.obtenerConexion(); // Se obtiene una conexión a la base de datos
        PreparedStatement pst = null; // Se declara la variable que almacenará la consulta
        con.setAutoCommit(false); // Desactiva el AutoCommit de la BBDD, básicamente para hacer el rollback
        try {
            pst = con.prepareStatement("DELETE FROM socioEstandar WHERE numeroSocio=?");
            pst.setInt(1, numeroSocio);
            pst.executeUpdate();
            con.commit();
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw new SQLException("Fallo en la consulta SQL al eliminar el socio estándar.");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }
}