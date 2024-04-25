package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO_Unused.Interfaces.SocioFederadoDAO;

public class SocioFederadoModel extends SocioModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente
    // implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de SocioFederadoDAO utilizando el objeto
    // factory.
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

    // Metodos propios
    // Crear socio Federado
    public void crearSocioFederado(SocioFederadoModel socio) {
        try {
            socioFederadoDAO.crearSocioFederado(socio);
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Obtener socio federado por número de socio.
    public static SocioFederadoModel getSocioPorNumeroSocio(int numeroSocio) {
        try {
            return socioFederadoDAO.obtenerPorNumeroSocio(numeroSocio);
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }

    // Metodo para listar los socios federados.
    public static String[] listarSocios(int valorInicialContador) {
        // Se obtienen los datos desde el DAO.
        try {
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        // Atributos.
        StringBuilder listado = new StringBuilder();
        int contador = valorInicialContador;
        for (SocioFederadoModel socio : sociosFederados) {
            contador++;
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio())
                    .append(" | Nombre: ").append(socio.getNombre()).append(" | NIF: ").append(socio.getNIF())
                    .append(" | Seguro: ").append(socio.federacion.getNombre());
        }
        if (contador == 0) {
            listado.append("\n  - Sin datos de socios Federados.");
        }
        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    // Método para eliminar socio infantil de la base de datos
    public static boolean eliminarSocioModel(int numeroSocio) {
        try {
            socioFederadoDAO.eliminarSocioFederado(numeroSocio);
            return true;
        } catch (Exception e) {
            throw e; // Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
    }
}
