package ObjectData_app.ObjectData_model;

import java.text.ParseException;
import java.util.ArrayList;
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

    // Pendiente de implementar el filtro de fechas
    public static void mostrarExcursiones(Datos BBDD) {
        try {
            if (BBDD.excursion.isEmpty()) {
                System.out.println("¡No hay excursiones para mostrar!");
                return;
            }
            for (ExcursionModel excursion : BBDD.excursion) {
                System.out.println(excursion);
            }
        } catch (Exception error) {
            System.out.println("Fallo al mostrar las excursiones: " + error.getMessage());
        }
    }
    
}