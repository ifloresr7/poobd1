package ObjectData_app.ObjectData_model;

import java.sql.SQLException;
import java.util.ArrayList;

import ObjectData_app.ObjectData_model.ObjectData_DAO.Implementacion.DAOFactoryImpl;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.DAOFactory;
import ObjectData_app.ObjectData_model.ObjectData_DAO.Interfaces.FederacionDAO;

public class FederacionModel {
    // Se crea una instancia estática de DAOFactoryImpl, que probablemente implementa la interfaz DAOFactory.
    static DAOFactory factory = new DAOFactoryImpl();
    // Se obtiene una instancia estática de FederacionDAO utilizando el objeto factory.
    static FederacionDAO federacionDAO = factory.instanciaFederacionDAO();
    // Se crea una lista estática para almacenar objetos FederacionModel.
    static ArrayList<FederacionModel> federaciones = new ArrayList<>();
    
    String codigo;
    String nombre;
    // Constructor
    public FederacionModel(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString
    @Override
    public String toString() {
        return "Codigo: " + codigo + " | Nombre: " + nombre;
    }

    //Metodos porpios
    public static String[] obtenerListadoFederacion(){
        try {
            federaciones = federacionDAO.obtenerTodasFederaciones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String listado = "";
        int contador = 0;
        for (FederacionModel federacion : federaciones) {
            contador++;
            listado += "\n    - " + contador + ". " + federacion.toString();
        }
        if(contador == 0){
            listado = "- Sin datos.";
        }
        return new String[] {listado, String.valueOf(contador)};
    }

    //Obtener federacion mediante seleccion de lista.
    public static FederacionModel obtenerFederacion(int seleccion){
        //Se obtienen todas las federaciones con el DAO
        try {
            federaciones = federacionDAO.obtenerTodasFederaciones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        int contador = 0;
        for (FederacionModel federacion : federaciones) {
            contador++;
            if(contador == seleccion){
                return federacion;
            }
        }
        return null;
    }
}