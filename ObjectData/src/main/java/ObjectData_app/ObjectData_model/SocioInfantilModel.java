package ObjectData_app.ObjectData_model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class SocioInfantilModel extends SocioModel {
    // Configuración de la fábrica de sesiones de Hibernate
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private int numeroSocioPadreMadre;

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

    // Métodos para manejar la persistencia de datos con Hibernate
    public void crearSocioInfantil(SocioInfantilModel socio) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(socio);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
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
        Session session = sessionFactory.openSession();
        SocioInfantilModel socio = null;
        try {
            socio = session.get(SocioInfantilModel.class, numeroSocio);
        } finally {
            session.close();
        }
        return socio;
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