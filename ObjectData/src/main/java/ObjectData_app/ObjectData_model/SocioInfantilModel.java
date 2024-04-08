package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
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
    public String crearSocioInfantil(SocioInfantilModel socio) throws SQLException{
        try {
            socioInfantilDAO.crearSocioInfantil(socio);
            return "Socio infantil guardado correctamente!";
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    //Metodo para obtener socio infantil mediante numero de socio.
    public static SocioInfantilModel getSocioInfantilNumeroSocio(int numeroSocio) throws SQLException{
        try{
            return socioInfantilDAO.obtenerPorNumeroSocio(numeroSocio);
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
    // Método para eliminar socio infantil de la base de datos
    public static boolean eliminarSocioModel(int numeroSocio) throws SQLException{
        try{
            socioInfantilDAO.eliminarSocioInfantil(numeroSocio);
            return true; 
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
}
