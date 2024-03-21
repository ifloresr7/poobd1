package ObjectData_app.ObjectData_model;

import java.util.Date;

public class ExcursionModel {
    // Propiedades de clase
    String codigo;
    String descripcion;
    Date fecha;
    int numeroDias;
    double precioInscripcion;

    // Constructor
    public ExcursionModel(String codigo, String descripcion, Date fecha, int numeroDias, double precioInscripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numeroDias = numeroDias;
        this.precioInscripcion = precioInscripcion;
    }

    // Métodos
    public String crearExcursionModel(Datos BBDD, ExcursionModel excursion) {
        try {
            BBDD.excursion.add(excursion);
            return "¡Se ha guardado correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }

    public static void mostrarExcursiones(Datos BBDD, Date fechaInicio, Date fechaFin) {
        //Primero comprueba que haya excursiones dentro del ArrayList
        try {
            if (BBDD.excursion.isEmpty()) {
                System.out.println("¡No hay excursiones para mostrar!");
                return;
            }

            boolean excursionEnRango = false;
            for (ExcursionModel excursion : BBDD.excursion) {
                // Comprueba si la fecha de la excursión está dentro del rango introducido e imprime la info de la misma
                if (!excursion.fecha.before(fechaInicio) && !excursion.fecha.after(fechaFin)) {
                    System.out.println("Código: " + excursion.codigo + ", Descripción: " + excursion.descripcion + ", Fecha: " + excursion.fecha + ", Número de días: " + excursion.numeroDias + ", Precio de inscripción: " + excursion.precioInscripcion);
                    excursionEnRango = true;
                }
            }

            // Si no hay excursiones en el rango de fechas proporcionado
            if (!excursionEnRango) {
                System.out.println("No hay excursiones disponibles entre las fechas proporcionadas.");
            }
        } catch (Exception error) {
            System.out.println("Fallo al mostrar las excursiones: " + error.getMessage());
        }
    }
}