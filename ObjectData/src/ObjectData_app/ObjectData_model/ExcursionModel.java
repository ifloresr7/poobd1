package ObjectData_app.ObjectData_model;

import java.util.Date;

public class ExcursionModel{
    //Propiedades de clase
    private int codigo;
    private String descripcion;
    private Date fecha;
    private int numeroDias;
    private double precioInscripcion;
    // Constructor
    public ExcursionModel(int codigo, String descripcion, Date fecha, int numeroDias, double precioInscripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numeroDias = numeroDias;
        this.precioInscripcion = precioInscripcion;
    }

    // Getters
    public int getCodigo() {
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
    public void setCodigo(int codigo) {
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
}
