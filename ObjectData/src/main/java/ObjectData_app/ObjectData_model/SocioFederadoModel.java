package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioFederadoDAO;

public class SocioFederadoModel extends SocioModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de SocioFederadoDAO utilizando el objeto factory.
    static SocioFederadoDAO socioFederadoDAO = factory.instanciaSocioFederadoDAO();
    // Se crea una lista estática para almacenar objetos SocioFederadoModel.
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
    public void crearSocioFederado(SocioFederadoModel socio) throws SQLException {
        try {
            socioFederadoDAO.crearSocioFederado(socio);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
    // Obtener socio federado por número de socio.
    public static SocioFederadoModel getSocioFederadoNumeroSocio(int numeroSocio) throws SQLException {
        try{
            return socioFederadoDAO.obtenerPorNumeroSocio(numeroSocio);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
     // Método para eliminar socio infantil de la base de datos
     public static boolean eliminarSocioModel(int numeroSocio) throws SQLException {
        try{
            socioFederadoDAO.eliminarSocioFederado(numeroSocio);
            return true; 
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
     }
}
