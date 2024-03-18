//Este archivo es el que busca el programa al iniciar la aplicacion
//por primera vez, en este archivo tenemos la clase Main que contiene
//la llamada al metodo menuInicio del controlador.
package ObjectData_app;
//Se importa el APP controler que contiene el inico de la app.
import ObjectData_app.ObjectData_controller.AppController;

public class Main{
    public static void main(String[] args) {
        AppController.inicio();
    }
}