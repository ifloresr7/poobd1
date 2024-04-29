package ObjectData_app.ObjectData_model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import ObjectData_app.ObjectData_model.ObjectData_Hibernate.ExcursionModelHib;

public class ExcursionModel {
    static SessionFactory sessionFactory;
    static Session session;

    // Propiedades de clase
    private int numeroExcursion;
    private String descripcion;
    private Date fecha;
    private int numeroDias;
    private double precioInscripcion;

    // Constructor
    public ExcursionModel(int numeroExcursion, String descripcion, Date fecha, int numeroDias, double precioInscripcion) {
        this.numeroExcursion = numeroExcursion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numeroDias = numeroDias;
        this.precioInscripcion = precioInscripcion;
    }

    // Métodos get y set omitidos

    private static void crearSessionHib() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ExcursionModelHib.class).buildSessionFactory();
        }
        session = sessionFactory.openSession();
    }
    // Método para crear una excursion
    public void crearExcursion(ExcursionModelHib excursion) {
        crearSessionHib();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ExcursionModelHib excursionHib = new ExcursionModelHib(excursion.getNumeroExcursion(), excursion.getDescripcion(), excursion.getFecha(), excursion.getNumeroDias(), excursion.getPrecioInscripcion());
            session.persist(excursionHib);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

public static String obtenerNombreExcursionPorNumeroExcursion(int numeroExcursion) {
        crearSessionHib();
        ExcursionModelHib excursion = null;
        try {
            session.beginTransaction();
            excursion = session.createQuery("FROM ExcursionModelHib WHERE numeroExcursion = :numeroExcursion", ExcursionModelHib.class)
                               .setParameter("numeroExcursion", numeroExcursion)
                               .uniqueResult();
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
        //Uso de operador ternario: condición ? valorSiVerdadero : valorSiFalso;
        return excursion != null ? excursion.getDescripcion() : null;
    }

    public static double obtenerPrecioExcursion(int numeroExcursion) {
        crearSessionHib();
        ExcursionModelHib excursion = null;
        try {
            session.beginTransaction();
            excursion = session.createQuery("FROM ExcursionModelHib WHERE numeroExcursion = :numeroExcursion", ExcursionModelHib.class)
                               .setParameter("numeroExcursion", numeroExcursion)
                               .uniqueResult();
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
        //Uso de operador ternario: condición ? valorSiVerdadero : valorSiFalso;
        return excursion != null ? excursion.getPrecioInscripcion() : 0;
    }

    public static String mostrarExcursiones(Date fechaInicio, Date fechaFin) {
        crearSessionHib();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<ExcursionModelHib> excursions = null;
        StringBuilder listado = new StringBuilder();
        try {
            session.beginTransaction();
            excursions = session.createQuery("FROM ExcursionModelHib WHERE fecha BETWEEN :start AND :end", ExcursionModelHib.class)
                                .setParameter("start", fechaInicio)
                                .setParameter("end", fechaFin)
                                .list();
            session.getTransaction().commit();
            int contador = 0;
            for (ExcursionModelHib excursion : excursions) {
                contador++;
                listado.append("\n- ").append(contador).append(". Código: ").append(excursion.getNumeroExcursion())
                        .append(" | Descripción: ").append(excursion.getDescripcion())
                        .append(" | Fecha y hora: ").append(dateFormat.format(excursion.getFecha()))
                        .append(" | Número de días: ").append(excursion.getNumeroDias())
                        .append(" | Precio de la inscripción: ").append(excursion.getPrecioInscripcion());
            }
            if (contador == 0) {
                listado.append("\n  - Sin datos.");
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
        return listado.toString();
    }
}
