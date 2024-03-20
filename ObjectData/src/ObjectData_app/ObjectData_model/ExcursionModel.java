package ObjectData_app.ObjectData_model;

import java.util.ArrayList;
import java.util.Date;

public class ExcursionModel{
    //Propiedades de clase
    private String codigo;
    private String descripcion;
    private Date fecha;
    private int numeroDias;
    private double precioInscripcion;
    // Constructor
    public ExcursionModel(String codigo, String descripcion, Date fecha, int numeroDias, double precioInscripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numeroDias = numeroDias;
        this.precioInscripcion = precioInscripcion;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public int getNumeroDias() {
        return numeroDias;
    }
    public double getPrecioInscripcion() {
        return precioInscripcion;
    }
    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }
    public void setPrecioInscripcion(double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }

    //Metodos
    public String toString() {
        return "Excursion{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", numeroDias=" + numeroDias +
                ", precioInscripcion=" + precioInscripcion +
                '}';
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

