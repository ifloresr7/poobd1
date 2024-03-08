class SocioFederado extends Socio {
    private String NIF;
    private Federacion federacion;

    // Constructor
    public SocioFederado(int numeroSocio, String nombre, String NIF, Federacion federacion) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.federacion = federacion;
    }

    // Getters
    public String getNIF() {
        return NIF;
    }

    public Federacion getFederacion() {
        return federacion;
    }

    // Setters
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setFederacion(Federacion federacion) {
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
