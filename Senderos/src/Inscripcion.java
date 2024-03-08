import java.util.Date;

class Inscripcion {
    private int numeroInscripcion;
    private Socio socio;
    private Excursion excursion;
    private Date fechaInscripcion;

    // Constructor
    public Inscripcion(int numeroInscripcion, Socio socio, Excursion excursion, Date fechaInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    // Getters
    public int getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public Socio getSocio() {
        return socio;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    // Setters
    public void setNumeroInscripcion(int numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setExcursion(Excursion excursion) {
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
                ", socio=" + socio +
                ", excursion=" + excursion +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}
