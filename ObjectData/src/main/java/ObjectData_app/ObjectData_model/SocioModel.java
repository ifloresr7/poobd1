package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioEstandarDAO;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioFederadoDAO;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.SocioInfantilDAO;

public abstract class SocioModel {
    static DAOFactory factory = new DAOFactoryImpl();
    static SocioInfantilDAO socioInfantilDAO = factory.instanciaSocioInfantilDAO();
    static ArrayList<SocioInfantilModel> sociosInfantiles = new ArrayList<>();
    static SocioFederadoDAO socioFederadoDAO = factory.instanciaSocioFederadoDAO();
    static ArrayList<SocioFederadoModel> sociosFederados = new ArrayList<>();
    static SocioEstandarDAO socioEstandarDAO = factory.instanciaSocioEstandarDAO();
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
    public static boolean comprobarSocioPorNumSocio(int codigoSocio) {
        //Se obtienen los datos desde el DAO.
        try{
            sociosEstandar = socioEstandarDAO.obtenerTodosSocioEstandar();
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();            
        }catch (SQLException e){
            System.out.println(e);
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

    public static String[] listarSociosModel(){
        //Se obtienen los datos desde el DAO.
        try{
            sociosEstandar = socioEstandarDAO.obtenerTodosSocioEstandar();
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();            
        }catch (SQLException e){
            System.out.println(e);
        }
        // Comprobar en la lista de socios estándar
        String listado = "";
        int contador = 0;
        // Comprobar en la lista de socios federados
        for (SocioEstandarModel socio : sociosEstandar) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Estandar";
        }
        // Comprobar en la lista de socios federados
        for (SocioFederadoModel socio : sociosFederados) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Federado";
        }
        // Añadir usuarios a la lista
        for (SocioInfantilModel socio : sociosInfantiles) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Tipo de socio: Infantil";

        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    //Metodo paras listar los socios estandar.
    public static String[] listarSociosEstandarModel() {
        //Se obtienen los datos desde el DAO.
        try{
            sociosEstandar = socioEstandarDAO.obtenerTodosSocioEstandar();            
        }catch (SQLException e){
            System.out.println(e);
        }

        //Atributos.
        String listado = "";
        int contador = 0;

        for (SocioEstandarModel socio : sociosEstandar) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | NIF: " + socio.getNIF() + " | Seguro: " + socio.seguro.getTipo();
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    //Metodo para listar los socios federados.
    public static String[] listarSociosFederadosModel() {
        //Se obtienen los datos desde el DAO.
        try{
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();          
        }catch (SQLException e){
            System.out.println(e);
        }
        //Atributos.
        String listado = "";
        int contador = 0;
        for (SocioFederadoModel socio : sociosFederados) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | NIF: " + socio.getNIF() + " | Federación: "
                    + socio.federacion.getNombre();
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    //Metodo para listar los socios infantiles.
    public static String[] listarSociosInfantilesModel() {
        //Se obtienen los datos desde el DAO.
        try{
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();            
        }catch (SQLException e){
            System.out.println(e);
        }
        //Atributos.
        String listado = "";
        int contador = 0;
        for (SocioInfantilModel socio : sociosInfantiles) {
            contador++;
            listado += "\n    - " + contador + ". Numero Socio: " + socio.getNumeroSocio() + " | Nombre: "
                    + socio.getNombre() + " | Numero socio parental: " + socio.getNumeroSocioPadreMadre();
        }
        if (contador == 0) {
            listado = "\n  - Sin datos.";
        }
        return new String[] { listado, String.valueOf(contador) };
    }

    //Metodo para obtener el tipo de socio por numero de socio.
    public static String obtenerTipoSocioPorNumSocio(int numeroSocio) {
        //Se obtienen los datos desde el DAO.
        try{
            sociosEstandar = socioEstandarDAO.obtenerTodosSocioEstandar();
            sociosFederados = socioFederadoDAO.obtenerTodosSocioFederado();
            sociosInfantiles = socioInfantilDAO.obtenerTodosSocioInfantil();            
        }catch (SQLException e){
            System.out.println(e);
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
        // Buscar en el array de socios infantiles
        for (SocioEstandarModel socio : sociosEstandar) {
            if (socio.getNumeroSocio() == numeroSocio) {
                return "Estandar";
            }
        }
        return null;
    }
}