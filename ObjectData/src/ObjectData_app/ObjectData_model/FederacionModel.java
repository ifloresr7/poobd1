package ObjectData_app.ObjectData_model;

import ObjectData_app.ObjectData_model.Datos;

public class FederacionModel {
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
    public static String obtenerListadoFederacion(Datos BBDD){
        String listado = "";
        int num = 1;
        for (FederacionModel federacion : BBDD.federacion) {
            listado += "\n" + num + ". " + federacion.toString();
            num++;
        }
        return listado;
    }

    public static FederacionModel obtenerFederacion(Datos BBDD, int seleccion){
        int contador = 1;
        for (FederacionModel federacion : BBDD.federacion) {
            if(contador == seleccion){
                return federacion;
            }
            contador++;
        }
        return null;
    }
}