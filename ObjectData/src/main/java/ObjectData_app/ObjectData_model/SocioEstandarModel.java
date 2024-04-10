package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioEstandarDAO;

public class SocioEstandarModel extends SocioModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente
    // implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de SocioEstandarDAO utilizando el objeto
    // factory.
    static SocioEstandarDAO socioEstandarDAO = factory.instanciaSocioEstandarDAO();
    // Se crea una lista estática para almacenar objetos SocioEstandarModel.
    static ArrayList<SocioEstandarModel> sociosEstandar = new ArrayList<>();

    // Atributos
    String NIF;
    SeguroModel seguro;

    // Constructor
    public SocioEstandarModel(int numeroSocio, String nombre, String NIF, SeguroModel seguro) {
        super(numeroSocio, nombre);
        this.NIF = NIF;
        this.seguro = seguro;
    }

    // Getters y Setters
    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public SeguroModel getSeguro() {
        return seguro;
    }

    public void setSeguro(SeguroModel seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return "SocioEstandarModel{" +
                "NIF='" + NIF + '\'' +
                ", seguro=" + seguro +
                '}';
    }
    // Método para agregar un socio estandar a la lista en Datos.
    public void crearSocioEstandar(SocioEstandarModel socioEstandar) throws SQLException{
        try {
            socioEstandarDAO.crearSocioEstandar(socioEstandar);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
    // Metodo para buscar por numero de socio y devolver el objeto.
    public static SocioEstandarModel getSocioEstandarNumeroSocio(int numeroSocio) throws SQLException{
        // Se obtienen los datos desde el DAO.
        try {
            return socioEstandarDAO.obtenerSocioEstandarPorNumeroSocio(numeroSocio);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
    // Metodo para actualizar el seguro del socio:
    public void actualizarSeguroSocioEstandar(SeguroModel seguro, SocioEstandarModel socio) throws SQLException{
        try {
            socio.setSeguro(seguro);
            socioEstandarDAO.actualizarSocioEstandar(socio);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
    // Metodo para obtener el precio del seguro.
    public static double obtenerPrecioSeguroPorNumeroSocio(int numeroSocio) throws SQLException {
        // Se obtienen los datos desde el DAO.
        try {
            return socioEstandarDAO.obtenerSocioEstandarPorNumeroSocio(numeroSocio).seguro.getPrecio();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
    // Método para eliminar socio estandar de la base de datos
    public static void eliminarSocioModel(int numeroSocio) throws SQLException {
        try {
            socioEstandarDAO.eliminarSocioEstandar(numeroSocio);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
}
