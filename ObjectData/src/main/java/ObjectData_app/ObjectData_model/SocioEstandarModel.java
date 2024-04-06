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
    public String crearSocioEstandar(SocioEstandarModel socioEstandar) {
        try {
            socioEstandarDAO.crearSocioEstandar(socioEstandar);
            return "Socio estandar guardado correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }

    // Metodo para buscar por numero de socio y devolver el objeto.
    public static SocioEstandarModel getSocioEstandar(int numeroSocio) {
        // Se obtienen los datos desde el DAO.
        try {
            return socioEstandarDAO.obtenerSocioEstandarPorNumeroSocio(numeroSocio);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // Metodo para actualizar el seguro del socio:
    public String actualizarSeguroSocioEstandar(SeguroModel seguro, SocioEstandarModel socio) {
        try {
            socio.setSeguro(seguro);
            socioEstandarDAO.actualizarSocioEstandar(socio);
            return "Seguro actualizado correctamente.";
        } catch (Exception error) {
            return "No se ha podido guardar los cambios con éxito." + error;
        }
    }

    // Metodo para obtener el precio del seguro.
    public static double obtenerPrecioSeguroPorNumeroSocio(int numeroSocio) {
        // Se obtienen los datos desde el DAO.
        try {
            return socioEstandarDAO.obtenerSocioEstandarPorNumeroSocio(numeroSocio).seguro.getPrecio();
        } catch (SQLException e) {
            System.out.println(e);
        }
        // Devolver un valor predeterminado si no se encuentra el socio
        return 0.0;
    }

    // Método para eliminar socio infantil de la base de datos
    public static boolean eliminarSocioModel(int numeroSocio) {
        try {
            socioEstandarDAO.eliminarSocioEstandar(numeroSocio);
            return true;
        } catch (Exception e) {
            // poner algo
        }
        return false;
    }
}
