package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.ExcursionDAO;

public class ExcursionModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente
    // implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de ExcursionDAO utilizando el objeto
    // factory.
    static ExcursionDAO excursionDAO = factory.instanciaExcursionDAO();
    // Se crea una lista estática para almacenar objetos ExcursionModel.
    static ArrayList<ExcursionModel> excursiones = new ArrayList<>();

    // Propiedades de clase
    int numeroExcursion;
    String descripcion;
    Date fecha;
    int numeroDias;
    double precioInscripcion;

    // Constructor
    public ExcursionModel(int numeroExcursion, String descripcion, Date fecha, int numeroDias, double precioInscripcion) {
        this.numeroExcursion = numeroExcursion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numeroDias = numeroDias;
        this.precioInscripcion = precioInscripcion;
    }

    public int getNumeroExcursion() {
        return numeroExcursion;
    }

    public void setNumeroExcursion(int numeroExcursion) {
        this.numeroExcursion = numeroExcursion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public void setPrecioInscripcion(double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }

    // Método para crear una excursion
    public String crearExcursionModel(ExcursionModel excursion) throws SQLException {
        try {
            excursionDAO.crearExcursion(excursion);
            return "¡Se ha guardado correctamente!";
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Metodo para devolver el nombre de la excursion.
    public static String obtenerNombreExcursionPorNumeroExcursion(int numeroExcursion) throws SQLException {
        try {
            return excursionDAO.obtenerPorNumeroExcursion(numeroExcursion).getDescripcion();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Metodo para obtener el precio de la excursion.
    public static double obtenerPrecioExcursion(int numeroExcursion) throws SQLException {
        try {
            return excursionDAO.obtenerPorNumeroExcursion(numeroExcursion).getPrecioInscripcion();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Metodo para mostrar escursiones por fecha
    public static String mostrarExcursiones(Date fechaInicio, Date fechaFin) {
        // Se obtienen todas las excursiones con el DAO y se almacenan en un array
        // temporal.
        try {
            excursiones = excursionDAO.obtenerTodasExcursiones();
        } catch (SQLException e) {
            // Implementar logica para devolver error a la vista.
            return e.getMessage();
        }
        // Atributos.
        String listado = "";
        int contador = 0;
        // Primero comprueba que haya excursiones dentro del ArrayList
        for (ExcursionModel excursion : excursiones) {
            // Comprueba si la fecha de la excursión está dentro del rango introducido e
            // imprime la info de la misma
            if (!excursion.fecha.before(fechaInicio) && !excursion.fecha.after(fechaFin)) {
                contador++;
                listado += "\n    - " + contador + ". Código: " + excursion.numeroExcursion + " | Descripción: "
                        + excursion.descripcion + " | Fecha: "
                        + excursion.fecha + " | Número de días: " + excursion.numeroDias
                        + " | Precio de inscripción: " + excursion.precioInscripcion;
            }
        }
        if (contador == 0) {
            listado = "- Sin datos.";
        }
        return listado;
    }

    // Metodo para mostrar una lista de excursiones
    public static String[] obtenerListadoExcursiones() {
        try {
            excursiones = excursionDAO.obtenerTodasExcursiones();
            // Lógica para convertir las excursiones a una lista de cadenas de texto
            // y devolverlas en un arreglo
        } catch (SQLException e) {
            // Captura de la excepción y devolución del mensaje de error
            return new String[] { "Fallo al obtener las excursiones: " + e.getMessage() };
        }
        // Atributos
        String listado = "";
        int contador = 0;
        for (ExcursionModel excursion : excursiones) {
            contador++;
            listado += "\n    - " + contador + ". Descripción: " + excursion.getDescripcion() + " | Precio: "
                    + excursion.getPrecioInscripcion();
        }
        if (contador == 0) {
            listado = "- Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
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
    public static ExcursionModel obtenerExcursionPorNumeroExcursion(int numeroExcursion) throws SQLException {
        try {
            return excursionDAO.obtenerPorNumeroExcursion(numeroExcursion);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
}