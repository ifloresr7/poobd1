package ObjectData_app.ObjectData_controller;

//Se importan las librerias
import ObjectData_app.ObjectData_view.AppControllerView;

public class AppController {
    static AppControllerView AppView = new AppControllerView();
    // Inicio del menu de la APP.
    public static void inicio() {
        //Arrancamos la ventana principal
        AppView.AppWindowsView();
    }
}