//Este archivo es el que busca el programa al iniciar la aplicacion
//por primera vez, en este archivo tenemos la clase Main que contiene
//la llamada al metodo menuInicio del controlador.
package ObjectData_app.ObjectData_controller;
import ObjectData_app.ObjectData_model.Datos;

public class Main{
    public static void main(String[] args) {
        //Se inicializa la base de BBDD virtual.
        Datos BBDD = new Datos();
        AppController APP = new AppController();
        APP.inicio(BBDD);
    }
}