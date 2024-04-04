package ObjectData_app.ObjectData_model;

import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioFederadoDAO;

public class SocioFederadoModel extends SocioModel {

    static DAOFactory factory = new DAOFactoryImpl();
    static SocioFederadoDAO socioFederadoDAO = factory.instanciaSocioFederadoDAO();
    static ArrayList<SocioFederadoModel> sociosFederados = new ArrayList<>();
 
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
    public String crearSocioFederado(SocioFederadoModel socio) {
        try {
            sociosFederados.add(socio);
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }
    public SocioFederadoModel getSocioFederado(int numSocio) {
        for(SocioFederadoModel socio : sociosFederados){
            if(numSocio == socio.getNumeroSocio()){
                return socio;
            }
        }
        return null;
    }
     // Método para eliminar socio infantil de la base de datos
     public static boolean eliminarSocioModel(int numSocio) {
        for (SocioFederadoModel socio : sociosFederados) {
            if (socio.getNumeroSocio() == numSocio) {
                sociosFederados.remove(socio);
                return true; // Socio eliminado
            }
        }
        return false; // Socio no encontrado
    }

}
