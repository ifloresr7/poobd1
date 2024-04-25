package ObjectData_app.ObjectData_model;

import ObjectData_app.ObjectData_model.ObjectData_Hibernate.socioEstandarHib;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SocioEstandarModel extends SocioModel {
    // Propiedades de Hibernate
    static SessionFactory sessionFactory;
    static Session session;
    // Propiedades del constructor
    String NIF;
    SeguroModel seguro;

    // Constructor
    public SocioEstandarModel(int numeroSocio, String nombre, String NIF, SeguroModel seguro) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.seguro = seguro;
    }

    // Metodo para crear la session de hibernate
    private static void crearSessionHib() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(socioEstandarHib.class)
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

    // Método para agregar un socio estandar a la lista en Datos.
    public static void crearSocioEstandar(SocioEstandarModel socioEstandar) throws SQLException {
        crearSessionHib();
        try {
            //Creamos el objeto socio mapeado
            socioEstandarHib socio = new socioEstandarHib(
                socioEstandar.getNumeroSocio(), //Obtenemos el numero de socio desde el objeto socioEstandar //Columna numeroSocio
                socioEstandar.getNombre(), //Obtenemos el nombre de socio desde el objeto socioEstandar //Columna nombre
                socioEstandar.getNIF(), //Obtenemos el NIF de socio desde el objeto socioEstandar //Columna NIF
                socioEstandar.getSeguro().getTipo().toString() //Obtenemos el nombre del tipo de seguro del seguro del objeto de socioEstandar //Columna seguro
            );
            session.beginTransaction();
            session.persist(socio);
            session.getTransaction().commit();
        } catch(Exception e){
            session.getTransaction().rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    // Metodo para buscar por numero de socio y devolver el objeto.
    public static SocioEstandarModel getSocioEstandarNumeroSocio(int numeroSocio) throws SQLException {
        // Se obtienen los datos desde el DAO.
        try {
            return socioEstandarDAO.obtenerSocioEstandarPorNumeroSocio(numeroSocio);
        } catch (Exception e) {
            throw new SQLException(e.getMessage()); // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Metodo para actualizar el seguro del socio:
    public void actualizarSeguroSocioEstandar(SeguroModel seguro, SocioEstandarModel socio) throws SQLException {
        crearSessionHib();
        try {
            session.beginTransaction();
            int updateEntities = session.createMutationQuery("update socioEstandar set seguro = :seguro where numeroSocio = :numeroSocio")
                .setParameter("seguro", seguro.getTipo().toString())
                .setParameter("numeroSocio", socio.getNumeroSocio())
                .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Entidades actualizadas " + updateEntities);
        } catch(Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    // Metodo para obtener el precio del seguro.
    public static double obtenerPrecioSeguroPorNumeroSocio(int numeroSocio) throws SQLException {
        // Se obtienen los datos desde el DAO.
        try {
            return socioEstandarDAO.obtenerSocioEstandarPorNumeroSocio(numeroSocio).seguro.getPrecio();
        } catch (Exception e) {
            throw new SQLException(e.getMessage()); // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Método para eliminar socio estandar de la base de datos
    public static void eliminarSocioModel(int numeroSocio) {
        crearSessionHib();
        try {
            session.beginTransaction();
            int rowsAffeccted = session.createMutationQuery("delete socioEstandar where numeroSocio = :numeroSocio")
                    .setParameter("numeroSocio", numeroSocio)
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println(rowsAffeccted);
        } finally {
            session.close();
            sessionFactory.close();
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
