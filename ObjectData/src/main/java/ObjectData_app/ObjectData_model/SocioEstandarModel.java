package ObjectData_app.ObjectData_model;

import ObjectData_app.ObjectData_model.ObjectData_Hibernate.socioEstandarHib;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SocioEstandarModel extends SocioModel {
    // Atributos
    String NIF;
    SeguroModel seguro;

    // Constructor
    public SocioEstandarModel(int numeroSocio, String nombre, String NIF, SeguroModel seguro) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.seguro = seguro;
    }

    // Método para agregar un socio estandar a la lista en Datos.
    public void crearSocioEstandar(SocioEstandarModel socioEstandar) throws SQLException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(socioEstandarHib.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            socioEstandarHib socio = new socioEstandarHib(socioEstandar.getNumeroSocio(),socioEstandar.getNombre(),socioEstandar.getNIF(),socioEstandar.getSeguro().getTipo().toString());
            session.beginTransaction();
            session.persist(socio);
            session.getTransaction().commit();
            session.close();
        } finally {
            sessionFactory.close();
        }
    }

    // Metodo para buscar por numero de socio y devolver el objeto.
    public static SocioEstandarModel getSocioEstandarNumeroSocio(int numeroSocio) throws SQLException {
        // Se obtienen los datos desde el DAO.
        try {
            return socioEstandarDAO.obtenerSocioEstandarPorNumeroSocio(numeroSocio);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Metodo para actualizar el seguro del socio:
    public void actualizarSeguroSocioEstandar(SeguroModel seguro, SocioEstandarModel socio) throws SQLException {
        try {
            socio.setSeguro(seguro);
            socioEstandarDAO.actualizarSocioEstandar(socio);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Metodo para obtener el precio del seguro.
    public static double obtenerPrecioSeguroPorNumeroSocio(int numeroSocio) throws SQLException {
        // Se obtienen los datos desde el DAO.
        try {
            return socioEstandarDAO.obtenerSocioEstandarPorNumeroSocio(numeroSocio).seguro.getPrecio();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Método para eliminar socio estandar de la base de datos
    public static void eliminarSocioModel(int numeroSocio) throws SQLException {
        try {
            socioEstandarDAO.eliminarSocioEstandar(numeroSocio);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Getters y Setters
    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public SeguroModel getSeguro() {
        return seguro;
    }

    public void setSeguro(SeguroModel seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return "SocioEstandarModel{" +
                "NIF='" + NIF + '\'' +
                ", seguro=" + seguro +
                '}';
    }

}
