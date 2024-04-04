package ObjectData_app.ObjectData_model;

import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioInfantilDAO;

public class SocioInfantilModel extends SocioModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de SocioInfantilDAO utilizando el objeto factory.
    static SocioInfantilDAO socioInfantilDAO = factory.instanciaSocioInfantilDAO();
    // Se crea una lista estática para almacenar objetos SocioInfantilModel.
    static ArrayList<SocioInfantilModel> sociosInfantiles = new ArrayList<>();

    private int numeroSocioPadreMadre;
    // Constructor
    public SocioInfantilModel(int numeroSocio, String nombre, int numeroSocioPadreMadre) {
        super(numeroSocio, nombre);
        this.numeroSocioPadreMadre = numeroSocioPadreMadre;
    }

    // Getter y Setter
    public int getNumeroSocioPadreMadre() {
        return numeroSocioPadreMadre;
    }

    public void setNumeroSocioPadreMadre(int numeroSocioPadreMadre) {
        this.numeroSocioPadreMadre = numeroSocioPadreMadre;
    }

    // Método toString
    @Override
    public String toString() {
        return "SocioInfantil{" +
                "numeroSocio=" + getNumeroSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", numeroSocioPadreMadre=" + numeroSocioPadreMadre +
                '}';
    }

    // Metodos propios
    // Crear socio infantil
    public String crearSocioInfantil(SocioInfantilModel socio) {
        try {
            socioInfantilDAO.crearSocioInfantil(socio);
            return "Socio infantil guardado correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }
    public static SocioInfantilModel getSocioInfantil(int numeroSocio) {
        try{
            return socioInfantilDAO.obtenerPorNumeroSocio(numeroSocio);
        }catch (Exception e){
            //Devolver el error a la vista
        }
        return null;
    }

    // Método para eliminar socio infantil de la base de datos
    public static boolean eliminarSocioModel(int numeroSocio) {
        try{
            socioInfantilDAO.eliminarSocioInfantil(numeroSocio);
            return true; 
        }catch (Exception e){
            //poner algo
        }
        return false;
    }
}
