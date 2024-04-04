package ObjectData_app.ObjectData_model;

import java.util.ArrayList;
import java.util.Date;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.ExcursionDAO;

public class ExcursionModel {
    static DAOFactory factory = new DAOFactoryImpl();
    static ExcursionDAO excursionDAO = factory.instanciaExcursionDAO();
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
    public String crearExcursionModel(ExcursionModel excursion) {
        try {
            excursiones.add(excursion);
            return "¡Se ha guardado correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }

    public static String obtenerNombreExcursionPorId(int idExcursion) {
        // Iterar sobre la lista de excursiones para encontrar la excursión con el ID dado
        for (ExcursionModel excursion : excursiones) {
            if (excursion.getNumeroExcursion()==idExcursion) {
                return excursion.getDescripcion(); // Devolver el nombre de la excursión si se encuentra la coincidencia
            }
        }
        return "Nombre de excursión desconocido"; // Devolver un mensaje de error si no se encuentra la excursión
    }
    public static double obtenerPrecioExcursion(int numeroExcursion) {
        // Suponiendo que tienes una lista de excursiones en tu base de datos llamada "excursiones"
        for (ExcursionModel excursion : excursiones) {
            if (excursion.getNumeroExcursion() == numeroExcursion) {
                return excursion.getPrecioInscripcion();
            }
        }
        // Si no se encuentra la excursión, podrías devolver un valor predeterminado o manejar el caso según tus necesidades
        return 0.0; // Por ejemplo, devolver 0 como precio predeterminado si no se encuentra la excursión
    }

    // Metodo para mostrar escursiones por fecha
    public static String mostrarExcursiones(Date fechaInicio, Date fechaFin) {
        String listado = "";
        int contador = 0;
        // Primero comprueba que haya excursiones dentro del ArrayList
        for (ExcursionModel excursion : excursiones) {
            // Comprueba si la fecha de la excursión está dentro del rango introducido e
            // imprime la info de la misma
            if (!excursion.fecha.before(fechaInicio) && !excursion.fecha.after(fechaFin)) {
                contador++;
                listado += "\n    - " +contador+". Código: " + excursion.numeroExcursion + " | Descripción: " + excursion.descripcion + " | Fecha: "
                        + excursion.fecha + " | Número de días: " + excursion.numeroDias
                        + " | Precio de inscripción: " + excursion.precioInscripcion;
            }
        }
        if(contador == 0){
            listado = "- Sin datos.";
        }
        return listado;
    }

    // Metodo para comprobar si existe una excursion
    public static boolean comprobarExcursionPorNumExcursion(int numeroExcursion) {
        for (ExcursionModel excursion : excursiones) {
            if(excursion.getNumeroExcursion() == numeroExcursion){
                return true;
            }
        }
        return false;
    }

    // Metodo para mostrar una lista de excursiones
    public static String[] obtenerListadoExcursiones() {
        String listado = "";
        int contador = 0;
        for (ExcursionModel excursion : excursiones) {
            contador++;
            listado += "\n    - " + contador + ". Descripción: " + excursion.getDescripcion() + " | Precio: " + excursion.getPrecioInscripcion();
        }
        if (contador == 0) {
            listado = "- Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    // Metodo para obtener la excursion mediante seleccion de lista
    public static ExcursionModel obtenerExcursionDesdeLista(int seleccion) {
        int contador = 0;
        for (ExcursionModel excursion : excursiones) {
            contador++;
            if (contador == seleccion) {
                return excursion;
            }
        }
        return null;
    }

        // Metodo para obtener la excursion mediante seleccion de lista
        public static ExcursionModel obtenerExcursionByCodigo(int codigo) {
            for (ExcursionModel excursion : excursiones) {
                if (excursion.getNumeroExcursion() == codigo) {
                    return excursion;
                }
            }
            return null;
        }
}