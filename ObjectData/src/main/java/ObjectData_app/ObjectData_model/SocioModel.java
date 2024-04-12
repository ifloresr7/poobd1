package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioEstandarDAO;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioFederadoDAO;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioInfantilDAO;

public abstract class SocioModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de SocioInfantilDAO a través del objeto factory.
    static SocioInfantilDAO socioInfantilDAO = factory.instanciaSocioInfantilDAO();
    // Se crea una lista estática para almacenar objetos SocioInfantilModel.
    static ArrayList<SocioInfantilModel> sociosInfantiles = new ArrayList<>();
    // Se obtiene una instancia estática de SocioFederadoDAO a través del objeto factory.
    static SocioFederadoDAO socioFederadoDAO = factory.instanciaSocioFederadoDAO();
    // Se crea una lista estática para almacenar objetos SocioFederadoModel.
    static ArrayList<SocioFederadoModel> sociosFederados = new ArrayList<>();
    // Se obtiene una instancia estática de SocioEstandarDAO a través del objeto factory.
    static SocioEstandarDAO socioEstandarDAO = factory.instanciaSocioEstandarDAO();
    // Se crea una lista estática para almacenar objetos SocioEstandarModel.
    static ArrayList<SocioEstandarModel> sociosEstandar = new ArrayList<>();

    private int numeroSocio;
    private String nombre;

    // Constructor
    public SocioModel(int numeroSocio, String nombre) {
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
    }

    // Getters
    public int getNumeroSocio() {
        return numeroSocio;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString
    @Override
    public String toString() {
        return "Socio{" +
                "numeroSocio=" + numeroSocio +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    // Método para comprobar si un socio existe mediante el numeroSocio
    public static boolean comprobarSocioPorNumSocio(int codigoSocio) throws SQLException {
        //Se obtienen los datos desde el DAO.
        try{
            sociosEstandar = socioEstandarDAO.obtenerTodosSocioEstandar();
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        // Comprobar en la lista de socios estándar
        for (SocioEstandarModel socio : sociosEstandar) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return true;
            }
        }
        // Comprobar en la lista de socios federados
        for (SocioFederadoModel socio : sociosFederados) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return true;
            }
        }
        // Comprobar en la lista de socios infantiles
        for (SocioInfantilModel socio : sociosInfantiles) {
            if (socio.getNumeroSocio() == codigoSocio) {
                return true;
            }
        }
        // Si no se encuentra en ninguna lista, devolver false
        return false;
    }

    public static String[] listarSociosModel() throws SQLException{
        //Se obtienen los datos desde el DAO.
        try{
            sociosEstandar = socioEstandarDAO.obtenerTodosSocioEstandar();
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        // Comprobar en la lista de socios estándar
        StringBuilder listado = new StringBuilder();
        int contador = 0;
        // Comprobar en la lista de socios federados
        for (SocioEstandarModel socio : sociosEstandar) {
            contador++;       
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio()).append(" | Nombre: ").append(socio.getNombre()).append(" | Tipo de socio: Estandar");
        }
        // Comprobar en la lista de socios federados
        for (SocioFederadoModel socio : sociosFederados) {
            contador++;
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio()).append(" | Nombre: ").append(socio.getNombre()).append(" | Tipo de socio: Federado");
        }
        // Añadir usuarios a la lista
        for (SocioInfantilModel socio : sociosInfantiles) {
            contador++;
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio()).append(" | Nombre: ").append(socio.getNombre()).append(" | Tipo de socio: Infantil");
        }

        if (contador == 0) {
            listado.append("\n  - Sin datos.");
        }

        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    //Metodo paras listar los socios estandar.
    public static String[] listarSociosEstandarModel() throws SQLException {
        //Atributos.
        StringBuilder listado = new StringBuilder();
        int contador = 0;        
        //Se obtienen los datos desde el DAO.
        try{
            sociosEstandar = socioEstandarDAO.obtenerTodosSocioEstandar();            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        for (SocioEstandarModel socio : sociosEstandar) {
            contador++;
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio()).append(" | Nombre: ").append(socio.getNombre()).append(" | NIF: ").append(socio.getNIF()).append(" | Seguro: ").append(socio.seguro.getTipo());
        }
        if (contador == 0) {
            listado.append("\n  - Sin datos.");
        }
        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    //Metodo para listar los socios federados.
    public static String[] listarSociosFederadosModel() throws SQLException{
        //Se obtienen los datos desde el DAO.
        try{
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();          
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        //Atributos.
        StringBuilder listado = new StringBuilder();
        int contador = 0;
        for (SocioFederadoModel socio : sociosFederados) {
            contador++;
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio()).append(" | Nombre: ").append(socio.getNombre()).append(" | NIF: ").append(socio.getNIF()).append(" | Seguro: ").append(socio.federacion.getNombre());
        }
        if (contador == 0) {
            listado.append("\n  - Sin datos.");
        }
        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    //Metodo para listar los socios infantiles.
    public static String[] listarSociosInfantilesModel() throws SQLException{
        //Se obtienen los datos desde el DAO.
        try{
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        //Atributos.
        StringBuilder listado = new StringBuilder();
        int contador = 0;
        for (SocioInfantilModel socio : sociosInfantiles) {
            contador++;
            listado.append("\n- ").append(contador).append(". Numero Socio: ").append(socio.getNumeroSocio()).append(" | Nombre: ").append(socio.getNombre()).append(" | Numero socio parental: ").append(socio.getNumeroSocioPadreMadre());
        }
        if (contador == 0) {
            listado.append("\n  - Sin datos.");
        }
        return new String[] { listado.toString(), String.valueOf(contador) };
    }

    //Metodo para obtener el tipo de socio por numero de socio.
    public static String obtenerTipoSocioPorNumSocio(int numeroSocio) throws SQLException{
        //Se obtienen los datos desde el DAO.
        try{
            sociosEstandar = socioEstandarDAO.obtenerTodosSocioEstandar();
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage()); //Captura el mensaje de error del DAO y lo envia aguas arriba.
        }
        // Buscar en el array de socios federados
        for (SocioFederadoModel socio : sociosFederados) {
            if (socio.getNumeroSocio() == numeroSocio) {
                return "Federado";
            }
        }
        // Buscar en el array de socios infantiles
        for (SocioInfantilModel socio : sociosInfantiles) {
            if (socio.getNumeroSocio() == numeroSocio) {
                return "Infantil";
            }
        }
        // Buscar en el array de socios estandar
        for (SocioEstandarModel socio : sociosEstandar) {
            if (socio.getNumeroSocio() == numeroSocio) {
                return "Estandar";
            }
        }
        return null;
    }
}