package ObjectData_app.ObjectData_model;

import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioEstandarDAO;

public class SocioEstandarModel extends SocioModel {

    static DAOFactory factory = new DAOFactoryImpl();
    static SocioEstandarDAO socioEstandarDAO = factory.instanciaSocioEstandarDAO();
    static ArrayList<SocioEstandarModel> sociosEstandar = new ArrayList<>();
 
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
            sociosEstandar.add(socioEstandar);
            return "Se guardo correctamente!";
        } catch (Exception error) {
            return "Fallo al guardar: " + error;
        }
    }
    // Metodo para buscar por numero de socio y devolver el objeto.
    public static SocioEstandarModel getSocioEstandar(int numeroSocio) {
        for (SocioEstandarModel socioEstandar : sociosEstandar) {
            if(socioEstandar.getNumeroSocio() == numeroSocio){
                return socioEstandar;
            }
        }
        return null;
    }
    // Metodo para actualizar el seguro del socio:
    public String actualizarSeguroSocioEstandar(SeguroModel seguro, SocioEstandarModel socio) {
        try{
            socio.setSeguro(seguro);
            return "Seguro actualizado correctamente.";
        }catch (Exception error) {
            return "No se ha podido guardar los cambios con éxito." + error;
        }
    }
    // Metodo para eliminar por numero de socio.
    public static boolean eliminarSocioEstandar(int numeroSocio) {
        for (SocioEstandarModel socioEstandar : sociosEstandar) {
            if(socioEstandar.getNumeroSocio() == numeroSocio){
                sociosEstandar.remove(socioEstandar);
                return true;
            }
        }
        return false;
    }
    public static double obtenerPrecioSeguro(String nombreSocio) {
        // Buscar el socio por nombre en los arrays correspondientes
        for (SocioEstandarModel socio : sociosEstandar) {
            if (socio.getNombre().equals(nombreSocio)) {
                // Una vez encontrado el socio, obtenemos el tipo de seguro que tiene
                SeguroModel seguro = socio.getSeguro();
                double precioSeguro = seguro.getPrecio();
                return precioSeguro;
            }
        }
        // Devolver un valor predeterminado si no se encuentra el socio
        return 0.0;
    }

    // Método para eliminar socio infantil de la base de datos
    public static boolean eliminarSocioModel(int numSocio) {
        for (SocioEstandarModel socio : sociosEstandar) {
            if (socio.getNumeroSocio() == numSocio) {
                sociosEstandar.remove(socio);
                return true; // Socio eliminado
            }
        }
        return false; // Socio no encontrado
    }
}
