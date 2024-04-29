package ObjectData_app.ObjectData_model;

<<<<<<< HEAD

import java.util.ArrayList;
import java.util.Date;


import java.text.SimpleDateFormat;

public class ExcursionModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente
    // implementa la interfaz DAOFactory.
    //static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de ExcursionDAO utilizando el objeto
    // factory.
    //static ExcursionDAO excursionDAO = factory.instanciaExcursionDAO();
    // Se crea una lista estática para almacenar objetos ExcursionModel.
    static ArrayList<ExcursionModel> excursiones = new ArrayList<>();
=======
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
>>>>>>> 6fb1032c7e8c2240476f9187b008448c21c4a887

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
<<<<<<< HEAD
           // excursionDAO.crearExcursion(excursion);
            return "¡Se ha guardado correctamente!";
=======
            transaction = session.beginTransaction();
            ExcursionModelHib excursionHib = new ExcursionModelHib(excursion.getNumeroExcursion(), excursion.getDescripcion(), excursion.getFecha(), excursion.getNumeroDias(), excursion.getPrecioInscripcion());
            session.persist(excursionHib);
            transaction.commit();
>>>>>>> 6fb1032c7e8c2240476f9187b008448c21c4a887
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
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
<<<<<<< HEAD
      //      return excursionDAO.obtenerPorNumeroExcursion(numeroExcursion).getDescripcion();
    return null;    
    } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
=======
            session.beginTransaction();
            excursion = session.createQuery("FROM ExcursionModelHib WHERE numeroExcursion = :numeroExcursion", ExcursionModelHib.class)
                               .setParameter("numeroExcursion", numeroExcursion)
                               .uniqueResult();
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
>>>>>>> 6fb1032c7e8c2240476f9187b008448c21c4a887
        }
        //Uso de operador ternario: condición ? valorSiVerdadero : valorSiFalso;
        return excursion != null ? excursion.getDescripcion() : null;
    }

    public static double obtenerPrecioExcursion(int numeroExcursion) {
        crearSessionHib();
        ExcursionModelHib excursion = null;
        try {
<<<<<<< HEAD
            return 0.0;//excursionDAO.obtenerPorNumeroExcursion(numeroExcursion).getPrecioInscripcion();
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
=======
            session.beginTransaction();
            excursion = session.createQuery("FROM ExcursionModelHib WHERE numeroExcursion = :numeroExcursion", ExcursionModelHib.class)
                               .setParameter("numeroExcursion", numeroExcursion)
                               .uniqueResult();
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
>>>>>>> 6fb1032c7e8c2240476f9187b008448c21c4a887
        }
        //Uso de operador ternario: condición ? valorSiVerdadero : valorSiFalso;
        return excursion != null ? excursion.getPrecioInscripcion() : 0;
    }

    public static String mostrarExcursiones(Date fechaInicio, Date fechaFin) {
        crearSessionHib();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
<<<<<<< HEAD
        try {
            //excursiones = excursionDAO.obtenerTodasExcursiones();
        } catch (Exception e) {
            // Implementar logica para devolver error a la vista.
            return e.getMessage();
        }
        // Atributos.
=======
        List<ExcursionModelHib> excursions = null;
>>>>>>> 6fb1032c7e8c2240476f9187b008448c21c4a887
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
<<<<<<< HEAD

    // Metodo para mostrar una lista de excursiones
    public static String[] obtenerListadoExcursiones() {
        try {
            //excursiones = excursionDAO.obtenerTodasExcursiones();
            // Lógica para convertir las excursiones a una lista de cadenas de texto
            // y devolverlas en un arreglo
        } catch (Exception e) {
            // Captura de la excepción y devolución del mensaje de error
            return new String[] { "Fallo al obtener las excursiones: " + e.getMessage() };
        }
        // Atributos
        StringBuilder listado = new StringBuilder();
        int contador = 0;
        for (ExcursionModel excursion : excursiones) {
            contador++;
            listado.append("\n- ").append(contador).append(". Descripción: ").append(excursion.getDescripcion())
                    .append(" | Precio: ").append(excursion.getPrecioInscripcion());
        }
        if (contador == 0) {
            listado.append("\n  - Sin datos.");
        }
        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    // Metodo para obtener la excursion mediante seleccion de lista
    public static ExcursionModel obtenerExcursionDesdeLista(int seleccion) {
        // Atributos
        int contador = 0;
        // Logica
        for (ExcursionModel excursion : excursiones) {
            contador++;
            if (contador == seleccion) {
                return excursion;
            }
        }
        return null;
    }

    // Metodo para obtener la excursion mediante seleccion de lista
    public static ExcursionModel obtenerExcursionPorNumeroExcursion(int numeroExcursion) {
        try {
            return null;
            //return excursionDAO.obtenerPorNumeroExcursion(numeroExcursion);
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
}
=======
}
>>>>>>> 6fb1032c7e8c2240476f9187b008448c21c4a887
