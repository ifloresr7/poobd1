package ObjectData_app.ObjectData_model;
public class SocioFederadoModel extends SocioModel {
    String NIF;
    FederacionModel federacion;

    // Constructor
    public SocioFederadoModel(int numeroSocio, String nombre, String NIF, FederacionModel federacion) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.federacion = federacion;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public FederacionModel getFederacion() {
        return federacion;
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

    //Metodos propios
    //Crear socio Federado
    public String crearSocioFederado(Datos BBDD, SocioFederadoModel socio) {
        try {
            BBDD.socioFederado.add(socio);
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }
    public SocioFederadoModel getSocioFederado(Datos BBDD, int numSocio) {
        for(SocioFederadoModel socio : BBDD.socioFederado){
            if(numSocio == socio.getNumeroSocio()){
                return socio;
            }
        }
        return null;
    }

}
