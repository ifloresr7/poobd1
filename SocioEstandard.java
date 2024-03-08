class SocioEstandar extends Socio {
    private String NIF;
    private Seguro seguro;

    // Constructor
    public SocioEstandar(int numeroSocio, String nombre, String NIF, Seguro seguro) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.seguro = seguro;
    }

    // Getters
    public String getNIF() {
        return NIF;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    // Setters
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setSeguro(Seguro seguro) {
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

