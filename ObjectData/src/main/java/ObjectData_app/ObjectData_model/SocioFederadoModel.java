package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ObjectData_app.ObjectData_model.ObjectData_Hibernate.SocioFederadoHib;
import ObjectData_app.ObjectData_model.ObjectData_Hibernate.SocioInfantilHib;


public class SocioFederadoModel extends SocioModel {
    static SessionFactory sessionFactory;
    static Session session;
    static ArrayList<SocioFederadoModel> sociosFederados = new ArrayList<>();
    String NIF;
    FederacionModel federacion;

    // Constructor
    public SocioFederadoModel(int numeroSocio, String nombre, String NIF, FederacionModel federacion) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.federacion = federacion;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public FederacionModel getFederacion() {
        return federacion;
    }

    public void setFederacion(FederacionModel federacion) {
        this.federacion = federacion;
    }

    // Método toString
    @Override
    public String toString() {
        return "SocioFederado{" +
                "numeroSocio=" + getNumeroSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", NIF='" + NIF + '\'' +
                ", federacion=" + federacion +
                '}';
    }
    private static void crearSessionHib() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SocioFederadoHib.class)
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

    // Metodos propios
    // Crear socio Federado
    public void crearSocioFederado(SocioFederadoModel socio) {
        crearSessionHib();
        try {
            session.beginTransaction();
            SocioFederadoHib socioI = new SocioFederadoHib(
                    socio.getNumeroSocio(), // Obtenemos el numero de socio desde el objeto socioEstandar
                                                    // //Columna numeroSocio
                    socio.getNombre(), // Obtenemos el nombre de socio desde el objeto socioEstandar //Columna
                    socio.getNIF(),                           // nombre
                    socio.getFederacion());
            // Lanzamos el comando para insertar el objeto en la base de datos
            session.persist(socioI);
            // Confirmamos la transacción
            session.getTransaction().commit();
        } 
        catch (Exception e) 
        {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        finally 
        {
        // Finalmente cerramos la sesión y el objeto de fábrica de sesiones
        session.close();
        // Cerramos la fábrica de sesiones de Hibernate para liberar recursos
        sessionFactory.close();
        }
}

    // Obtener socio federado por número de socio.
    public static SocioFederadoModel getSocioPorNumeroSocio(int numeroSocio) {
        crearSessionHib();
        SocioFederadoHib socio = null;
        try {
            session.beginTransaction();
            // Obtenemos el socio por numero de socio
            socio = session
                        .createQuery("FROM SocioFederadoHib WHERE numeroSocio = :numeroSocio", SocioFederadoHib.class)
                        .setParameter("numeroSocio", numeroSocio)
                        .uniqueResult();
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        finally 
        {
            session.close();
            // Cerramos la fábrica de sesiones de Hibernate para liberar recursos
            sessionFactory.close();
        }
        if (socio != null) {
            return new SocioFederadoModel(socio.getNumeroSocio(),socio.getNombre(),socio.getNIF(), socio.getFederacion());
        } else {
            return null;
        }
    }
    

    // Metodo para listar los socios federados.
    public static String[] listarSocios(int valorInicialContador) {
        crearSessionHib();
        List<SocioFederadoHib> socios = null;
        StringBuilder listado = new StringBuilder();
        try 
        {
            session.beginTransaction();
            socios = session.createQuery("from SocioFederadoHib", SocioFederadoHib.class).list();
        } 
        catch (Exception e) 
        {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        // Atributos.
        StringBuilder list = new StringBuilder();
        int contador = valorInicialContador;
        for (SocioFederadoModel socio : sociosFederados) {
            contador++;
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio())
                    .append(" | Nombre: ").append(socio.getNombre()).append(" | NIF: ").append(socio.getNIF())
                    .append(" | Seguro: ").append(socio.federacion.getNombre());
        }
        if (contador == 0) {
            listado.append("\n  - Sin datos de socios Federados.");
        }
        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    // Método para eliminar socio infantil de la base de datos
    public static boolean eliminarSocioModel(int numeroSocio) {
         // Creamos una sesión de Hibernate y la iniciamos
         crearSessionHib();
        try {
            // Iniciamos una transacción en la sesión
            session.beginTransaction();
            // Creamos una consulta DELETE utilizando createMutationQuery()
            session.createMutationQuery("DELETE FROM SocioFederadoHib WHERE numeroSocio = :numeroSocio")
                    .setParameter("numeroSocio", numeroSocio)
                    .executeUpdate();
            // Confirmamos la transacción
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            // En caso de error, realizamos un rollback de la transacción
            session.getTransaction().rollback();
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        finally {
            // Finalmente cerramos la sesión y el objeto de fábrica de sesiones
            session.close();
            // Cerramos la fábrica de sesiones de Hibernate para liberar recursos
            sessionFactory.close();
        }
    }
}
