package ObjectData_app.ObjectData_model;

import java.util.Date;

public class ExcursionModel {
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
    public String crearExcursionModel(Datos BBDD, ExcursionModel excursion) {
        try {
            BBDD.excursion.add(excursion);
            return "¡Se ha guardado correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }

    public static String obtenerNombreExcursionPorId(Datos BBDD, int idExcursion) {
        // Iterar sobre la lista de excursiones para encontrar la excursión con el ID dado
        for (ExcursionModel excursion : BBDD.excursion) {
            if (excursion.getNumeroExcursion()==idExcursion) {
                return excursion.getDescripcion(); // Devolver el nombre de la excursión si se encuentra la coincidencia
            }
        }
        return "Nombre de excursión desconocido"; // Devolver un mensaje de error si no se encuentra la excursión
    }
    public static double obtenerPrecioExcursion(Datos BBDD, int numeroExcursion) {
        // Suponiendo que tienes una lista de excursiones en tu base de datos llamada "excursiones"
        for (ExcursionModel excursion : BBDD.excursion) {
            if (excursion.getNumeroExcursion() == numeroExcursion) {
                return excursion.getPrecioInscripcion();
            }
        }
        // Si no se encuentra la excursión, podrías devolver un valor predeterminado o manejar el caso según tus necesidades
        return 0.0; // Por ejemplo, devolver 0 como precio predeterminado si no se encuentra la excursión
    }

    // Metodo para mostrar escursiones por fecha
    public static String mostrarExcursiones(Datos BBDD, Date fechaInicio, Date fechaFin) {
        String listado = "";
        int contador = 0;
        // Primero comprueba que haya excursiones dentro del ArrayList
        for (ExcursionModel excursion : BBDD.excursion) {
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
    public static boolean comprobarExcursionPorNumExcursion(Datos BBDD, int numeroExcursion) {
        for (ExcursionModel excursion : BBDD.excursion) {
            if(excursion.getNumeroExcursion() == numeroExcursion){
                return true;
            }
        }
        return false;
    }

    // Metodo para mostrar una lista de excursiones
    public static String[] obtenerListadoExcursiones(Datos BBDD) {
        String listado = "";
        int contador = 0;
        for (ExcursionModel excursion : BBDD.excursion) {
            contador++;
            listado += "\n    - " + contador + ". Descripción: " + excursion.getDescripcion() + " | Precio: " + excursion.getPrecioInscripcion();
        }
        if (contador == 0) {
            listado = "- Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    // Metodo para obtener la excursion mediante seleccion
    public static ExcursionModel obtenerExcursion(Datos BBDD, int seleccion) {
        int contador = 0;
        for (ExcursionModel excursion : BBDD.excursion) {
            contador++;
            if (contador == seleccion) {
                return excursion;
            }
        }
        return null;
    }
}