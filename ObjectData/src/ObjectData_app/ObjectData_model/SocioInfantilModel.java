package ObjectData_app.ObjectData_model;

public class SocioInfantilModel extends SocioModel {
    private int numeroSocioPadreMadre;
    // Constructor
    public SocioInfantilModel(int numeroSocio, String nombre, int numeroSocioPadreMadre) {
        super(numeroSocio, nombre);
        this.numeroSocioPadreMadre = numeroSocioPadreMadre;
    }

    // Getter y Setter
    public int getNumeroSocioPadreMadre() {return numeroSocioPadreMadre;}
    public void setNumeroSocioPadreMadre(int numeroSocioPadreMadre) {this.numeroSocioPadreMadre = numeroSocioPadreMadre;}

    // Método toString
    @Override
    public String toString() {
        return "SocioInfantil{" +
                "numeroSocio=" + getNumeroSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", numeroSocioPadreMadre=" + numeroSocioPadreMadre +
                '}';
    }
}
