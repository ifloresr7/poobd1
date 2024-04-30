package ObjectData_app.ObjectData_model;

import ObjectData_app.ObjectData_model.ObjectData_Hibernate.FederacionHib;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class FederacionModel {

    /////////////////// Propiedades de Hibernete
    private static SessionFactory sessionFactory;
    private static Session session;

   /////////////////// Constructor vacio, (por si se quiere instanciar)
    public FederacionModel() {
    }

    /////////////////// Metodo para crear la session de hibernate
    private static void crearSessionHib() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FederacionHib.class)
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static FederacionModel obtenerFederacion(int id) {
        // Crear sesión de Hibernate e inicializar
        crearSessionHib();
        try {
            // Iniciar transacción
            session.beginTransaction();
            // Consultar la federación por su id
            FederacionModel federacion = session.get(FederacionModel.class, id);
            session.getTransaction().commit(); // Confirmar transacción
            return federacion; // Devolver la federación encontrada
        } catch (Exception e) { // Capturar excepciones
            session.getTransaction().rollback(); // Deshacer transacción
            throw e;
        } finally {
            session.close(); // Cerrar la sesión
            sessionFactory.close(); // Cerrar fábrica de sesiones de Hibernate
        }
    }
    

    /////////////////// Método para obtener todas las federaciones de la base de datos
    public static String[] obtenerListadoFederacion() {
        ArrayList<String> listaFederaciones = new ArrayList<>();
        //Crear sesion hibernete e inicializacion
        crearSessionHib();
        try {
            // Iniciar transacción
            session.beginTransaction();
            
            // Consulta para obtener las federaciones y guardarlas en la lista
            List<FederacionHib> federaciones = session.createQuery("FROM FederacionHib", FederacionHib.class).list();
            for (FederacionHib federacion : federaciones) {
                listaFederaciones.add(federacion.getId() + ". " + federacion.getNombre()); // Agregar el id y el nombre de la federación
            }
            
            session.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {//Captura posibles excepciones
            session.getTransaction().rollback(); //Si hay problemas no se guardan los cambios
            throw e;
        } finally { 
            session.close();//Cerramos la sesion de hibernete
        }
        
        // Convertir la lista de cadenas a un arreglo de cadenas
        String[] arrayFederaciones = new String[listaFederaciones.size()];
        arrayFederaciones = listaFederaciones.toArray(arrayFederaciones);
        
        return arrayFederaciones; // Devolver el arreglo de cadenas2
        
            
    }
}
