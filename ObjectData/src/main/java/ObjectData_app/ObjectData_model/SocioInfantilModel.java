package ObjectData_app.ObjectData_model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;

import ObjectData_app.ObjectData_model.ObjectData_Hibernate.SocioInfantilHib;

import java.util.List;

public class SocioInfantilModel extends SocioModel {
    // Configuración de la fábrica de sesiones de Hibernate
    static SessionFactory sessionFactory;
    static Session session;
    private int numeroSocioPadreMadre;

    //Constructor para hibernate
    public SocioInfantilModel(int numeroSocio,String nombre)
    {
        super(numeroSocio, nombre);
    }
    
    

    // Constructor que también inicializa la clase base SocioModel
    public SocioInfantilModel(int numeroSocio, String nombre, int numeroSocioPadreMadre) {
        super(numeroSocio, nombre);
        this.numeroSocioPadreMadre = numeroSocioPadreMadre;
    }

    // Getters y Setters
    public int getNumeroSocioPadreMadre() {
        return numeroSocioPadreMadre;
    }

    public void setNumeroSocioPadreMadre(int numeroSocioPadreMadre) {
        this.numeroSocioPadreMadre = numeroSocioPadreMadre;
    }

    // Método toString para una representación en cadena del objeto
    @Override
    public String toString() {
        return "SocioInfantil{" +
                "numeroSocio=" + getNumeroSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", numeroSocioPadreMadre=" + numeroSocioPadreMadre +
                '}';
    }
    private static void crearSessionHib() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SocioInfantilHib.class)
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }
    // Métodos para manejar la persistencia de datos con Hibernate
    public void crearSocioInfantil(SocioInfantilModel socio) {
        crearSessionHib();
        try {
            session.beginTransaction();
            SocioInfantilHib socioI = new SocioInfantilHib(
                    socio.getNumeroSocio(), // Obtenemos el numero de socio desde el objeto socioEstandar
                                                    // //Columna numeroSocio
                    socio.getNombre(), // Obtenemos el nombre de socio desde el objeto socioEstandar //Columna
                                               // nombre
                    socio.getNumeroSocioPadreMadre());
            // Lanzamos el comando para insertar el objeto en la base de datos
            session.persist(socioI);
            // Confirmamos la transacción
            session.getTransaction().commit();
        } catch (Exception e) {
            // En caso de error, realizamos un rollback de la transacción
            session.getTransaction().rollback();
            // Devolvemos el error aguas arriba en las clases
            throw e;
        } finally {
            // Finalmente cerramos la sesión y el objeto de fábrica de sesiones
            session.close();
            // Cerramos la fábrica de sesiones de Hibernate para liberar recursos
            sessionFactory.close();
        }
    }
    // Método para listar todos los socios infantiles, ajustado para devolver un array de String
    public static String[] listarSocios(int valorInicialContador) {
        Session session = sessionFactory.openSession();
        List<SocioInfantilModel> socios = null;
        StringBuilder listado = new StringBuilder();
        int contador = valorInicialContador;
        try {
            socios = session.createQuery("from SocioInfantilModel", SocioInfantilModel.class).list();
            for (SocioInfantilModel socio : socios) {
                contador++;
                listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio())
                        .append(" | Nombre: ").append(socio.getNombre()).append(" | Numero socio parental: ")
                        .append(socio.getNumeroSocioPadreMadre());
            }
        } finally {
            session.close();
        }

        if (contador == valorInicialContador) {
            listado.append("\n  - Sin datos de socios Infantiles.");
        }

        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    // Método para obtener un socio infantil por su número de socio
    public static SocioInfantilModel getSocioPorNumeroSocio(int numeroSocio) {
        crearSessionHib();
        SocioInfantilHib socio = null;
        try {
            session.beginTransaction();
            // Obtenemos el socio por numero de socio
            socio = session
                        .createQuery("FROM SocioInfantilHib WHERE numeroSocio = :numeroSocio", SocioInfantilHib.class)
                        .setParameter("numeroSocio", numeroSocio)
                        .uniqueResult();
        } 
        finally 
        {
            session.close();
            // Cerramos la fábrica de sesiones de Hibernate para liberar recursos
            sessionFactory.close();
        }
        if (socio != null) {
            return new SocioInfantilModel(socio.getNumeroSocio(),socio.getNombre(), socio.getNumeroSocioTutorLegal());
        } else {
            return null;
        }
    }
          
    // Método para eliminar un socio infantil
    public static void eliminarSocioModel(int numeroSocio) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            SocioInfantilModel socio = session.get(SocioInfantilModel.class, numeroSocio);
            if (socio != null) {
                session.remove(socio);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}