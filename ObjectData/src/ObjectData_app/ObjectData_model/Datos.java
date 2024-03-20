package ObjectData_app.ObjectData_model;

import java.util.ArrayList;

public class Datos {
    ArrayList<ExcursionModel> excursion;
    ArrayList<SocioInfantilModel> socioInfantil;
    ArrayList<SocioFederadoModel> socioFederado;
    ArrayList<SocioEstandarModel> socioEstandar;
    ArrayList<InscripcionModel> inscripcion;
    ArrayList<FederacionModel> federacion;

    public Datos() {
        excursion = new ArrayList<>();
        socioInfantil = new ArrayList<>();
        socioFederado = new ArrayList<>();
        socioEstandar = new ArrayList<>();
        inscripcion = new ArrayList<>();
        federacion = new ArrayList<>();
    }
}