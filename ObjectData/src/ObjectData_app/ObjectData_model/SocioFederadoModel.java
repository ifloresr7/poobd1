package ObjectData_app.ObjectData_model;
public class SocioFederadoModel extends SocioModel {
    private String NIF;
    private FederacionModel federacion;

    // Constructor
    public SocioFederadoModel(int numeroSocio, String nombre, String NIF, FederacionModel federacion) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.federacion = federacion;
    }

    // Getters
    public String getNIF() {
        return NIF;
    }

    public FederacionModel getFederacion() {
        return federacion;
    }

    // Setters
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setFederacion(FederacionModel federacion) {
        this.federacion = federacion;
    }

    // Método toString
    @Override
    public String toString() {
        return "SocioFederado{" +
                "numeroSocio=" + getNumeroSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", NIF='" + NIF + '\'' +
                ", federacion=" + federacion +
                '}';
    }
}
