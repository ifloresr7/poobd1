package ObjectData_app.ObjectData_model;
public class SocioEstandarModel extends SocioModel {
    private String NIF;
    private SeguroModel seguro;

    // Constructor
    public SocioEstandarModel(int numeroSocio, String nombre, String NIF, SeguroModel seguro) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.seguro = seguro;
    }

    // Getters
    public String getNIF() {
        return NIF;
    }

    public SeguroModel getSeguro() {
        return seguro;
    }

    // Setters
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setSeguro(SeguroModel seguro) {
        this.seguro = seguro;
    }

    // Método toString
    @Override
    public String toString() {
        return "SocioEstandar{" +
                "numeroSocio=" + getNumeroSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", NIF='" + NIF + '\'' +
                ", seguro=" + seguro +
                '}';
    }
}

