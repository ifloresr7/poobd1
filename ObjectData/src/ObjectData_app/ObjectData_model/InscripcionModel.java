package ObjectData_app.ObjectData_model;
import java.util.Date;

public class InscripcionModel{
    private int numeroInscripcion;
    private int numeroSocio;
    private String excursion;
    private Date fechaInscripcion;

    // Constructor
    public InscripcionModel(int numeroInscripcion, int numeroSocio, String excursion, Date fechaInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
        this.numeroSocio = numeroSocio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    // Getters
    public int getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public String getExcursion() {
        return excursion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    // Setters
    public void setNumeroInscripcion(int numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public void setExcursion(String excursion) {
        this.excursion = excursion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    // Método toString
    @Override
    public String toString() {
        return "Inscripcion{" +
                "numeroInscripcion=" + numeroInscripcion +
                ", numeroSocio=" + numeroSocio +
                ", excursion=" + excursion +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
    public String crearInscripcion(Datos BBDD, InscripcionModel inscripcion) {
        try {
            BBDD.inscripcion.add(inscripcion);
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }
}
