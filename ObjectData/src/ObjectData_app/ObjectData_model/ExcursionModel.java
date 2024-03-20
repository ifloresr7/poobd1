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

    public String crearExcursionModel(Datos BBDD, ExcursionModel excursion) {
        try {
            BBDD.excursion.add(excursion);
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }

    public static void añadirExcursion(Datos datos, ExcursionModel nuevaExcursion) {
        datos.getExcursiones().add(nuevaExcursion);
        System.out.println("Excursión añadida correctamente.");
    }

    public static void modificarExcursion(Datos datos, int indice, ExcursionModel excursionModificada) {
        ArrayList<ExcursionModel> excursiones = datos.getExcursiones();
        if (indice >= 0 && indice < excursiones.size()) {
            excursiones.set(indice, excursionModificada);
            System.out.println("Excursión modificada correctamente.");
        } else {
            System.out.println("El índice proporcionado está fuera de rango.");
        }
    }

    public static void mostrarExcursiones(Datos datos) {
        ArrayList<ExcursionModel> excursiones = datos.getExcursiones();
        if (excursiones.isEmpty()) {
            System.out.println("No hay excursiones para mostrar.");
        } else {
            for (ExcursionModel excursion : excursiones) {
                System.out.println(excursion);
            }
        }
    }

    public static void eliminarExcursion(Datos datos, int indice) {
        ArrayList<ExcursionModel> excursiones = datos.getExcursiones();
        if (indice >= 0 && indice < excursiones.size()) {
            excursiones.remove(indice);
            System.out.println("Excursión eliminada correctamente.");
        } else {
            System.out.println("El índice proporcionado está fuera de rango.");
        }
    }
}
