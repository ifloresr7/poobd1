package ObjectData_app.ObjectData_controller;
//Se importan las librerias
import ObjectData_app.ObjectData_view.MenuView;

public class AppController {
    static MenuView MenuView = new MenuView();
    static boolean cerrarApp = false;
    // Inicio del menu de la APP.
    public static void inicio() {
        // arracamos el menu principal de la aplicación.
        int opcion = MenuView.menuInicioView();
        System.out.println(opcion);
        do {
             
            // int opcion =
            // switch (opcion) {
            //     case 1:
            //         gestionExcursiones();
            //         break;
            //     case 2:
            //         gestionSocios();
            //         break;
            //     case 3:
            //         gestionInscripciones();
            //         break;
            //     case 4:
            //         cerrarApp = true;
            //         break;
            // }
        } while (!cerrarApp);
    }

    // Metodos de control para Excursiones.
    public static void gestionExcursiones() {
        MenuView.menuGestionExcursionesView();
        int opcion = 2;
        switch (opcion) {
            case 1:
                ExcursionController.crearExcursion();
                break;
            case 2:
                ExcursionController.mostrarExcursionFecha();
                break;
            case 3:
                inicio();
                break;
        }
    }

    // Metodos de control para Socios.
    public static void gestionSocios() {
        // MenuView.menuGestionSociosView();
        // int opcion = MenuView.getOpcionView(6);
        // switch (opcion) {
        //     case 1:
        //         SocioController.crearNuevoSocio();
        //         break;
        //     case 2:
        //         SocioController.modificarSeguroSocioEstandar();
        //         break;
        //     case 3:
        //         SocioController.eliminarSocio();
        //         break;
        //     case 4:
        //         SocioController.mostrarSocio();
        //         break;
        //     case 5:
        //         SocioController.facturaMensualSocio();
        //         break;
        //     case 6:
        //         inicio();
        //         break;
        // }
    }

    // Metodos de control para Inscripciones.
    public static void gestionInscripciones() {
    //     MenuView.menuGestionInscripcionesView();
    //     int opcion = MenuView.getOpcionView(4);
    //     switch (opcion) {
    //         case 1:
    //             InscripcionController.crearInscripcion();
    //             break;
    //         case 2:
    //             InscripcionController.eliminarInscripcion();
    //             break;
    //         case 3:
    //             InscripcionController.mostrarInscripcion();
    //             break;
    //         case 4:
    //             inicio();
    //             break;
    //     }
    }
}