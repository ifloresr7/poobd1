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

    // Metodo para mostrar escursiones por fecha
    public static String mostrarExcursiones(Datos BBDD, Date fechaInicio, Date fechaFin) {
        // Primero comprueba que haya excursiones dentro del ArrayList
        try {
            if (BBDD.excursion.isEmpty()) {
                return "¡No hay excursiones para mostrar!";
            }
            for (ExcursionModel excursion : BBDD.excursion) {
                // Comprueba si la fecha de la excursión está dentro del rango introducido e
                // imprime la info de la misma
                if (!excursion.fecha.before(fechaInicio) && !excursion.fecha.after(fechaFin)) {
                    return "Código: " + excursion.numeroExcursion + ", Descripción: " + excursion.descripcion + ", Fecha: "
                            + excursion.fecha + ", Número de días: " + excursion.numeroDias
                            + ", Precio de inscripción: " + excursion.precioInscripcion;
                } else {
                    // Si no hay escursion entonces esto:
                    return "No hay excursiones disponibles entre las fechas proporcionadas.";
                }
            }
        } catch (Exception error) {
            return "Fallo al mostrar las excursiones: " + error;
        }
        return null;
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
            listado += "\n    - " + contador + ". " + excursion.toString();
        }
        if (contador == 0) {
            listado = "- Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    // Metodo para obtener la excursion mediante seleccion
    public static ExcursionModel obtenerExcursion(Datos BBDD, int seleccion) {
        int contador = 0;
        for (ExcursionModel escursion : BBDD.excursion) {
            contador++;
            if (contador == seleccion) {
                return escursion;
            }
        }
        return null;
    }
}