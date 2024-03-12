import java.util.ArrayList;

public class Datos {
    ArrayList<ExcursionModel> excursion;
    ArrayList<SocioModel> socio;
    ArrayList<InscripcionModel> inscripcion;
    ArrayList<FederacionModel> federacion;

    public ArrayList<ExcursionModel> getExcursion() {
        return excursion;
    }
    public void setExcursion(ArrayList<ExcursionModel> excursion) {
        this.excursion = excursion;
    }
    public ArrayList<SocioModel> getSocio() {
        return socio;
    }
    public void setSocio(ArrayList<SocioModel> socio) {
        this.socio = socio;
    }
    public ArrayList<InscripcionModel> getInscripcion() {
        return inscripcion;
    }
    public void setInscripcion(ArrayList<InscripcionModel> inscripcion) {
        this.inscripcion = inscripcion;
    }
    public ArrayList<FederacionModel> getFederacion() {
        return federacion;
    }
    public void setFederacion(ArrayList<FederacionModel> federacion) {
        this.federacion = federacion;
    }
}
