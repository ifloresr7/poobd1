package ObjectData_app.ObjectData_model;
public class SeguroModel {
    // Enumeración para los tipos de seguro
    public enum TipoSeguro {
        BASICO,
        COMPLETO
    }

    private TipoSeguro tipo;
    private double precio;

    // Constructor
    public SeguroModel(TipoSeguro tipo, double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    // Getters
    public TipoSeguro getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters
    public void setTipo(TipoSeguro tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método toString
    @Override
    public String toString() {
        return "Seguro{" +
                "tipo=" + tipo +
                ", precio=" + precio +
                '}';
    }
}

