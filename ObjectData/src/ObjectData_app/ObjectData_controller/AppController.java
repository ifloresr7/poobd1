package ObjectData_app.ObjectData_controller;
//Se añade la vista principal
import ObjectData_app.ObjectData_view.AppMenuView;
import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_view.ExcursionesView;

public class AppController {
    static AppMenuView menuView = new AppMenuView();
    static Datos datos = new Datos();
    static boolean cerrarApp = false;
    // Inicio del menu de la APP.
    public static void inicio() {
        do { 
            menuView.menuInicioView();
            int opcion = menuView.getOpcionView(4);
            switch (opcion) {
                case 1:
                    gestionExcursiones();
                    break;
                case 2:
                    gestionSocios();
                    break;
                case 3:
                    gestionInscripciones();
                    break;
                case 4:
                    cerrarApp = true;
                    break;
            }
        } while (!cerrarApp);
    }
    // Metodos de control para Excursiones.
    public static void gestionExcursiones() {
        menuView.menuGestionExcursionesView();
        int opcion = menuView.getOpcionView(3);
        switch (opcion) {
            case 1:
                ExcursionesView excursion = new ExcursionesView();
                excursion.menuCrearExcursionView();
                break;
            case 2:
                ExcursionesView excursion2 = new ExcursionesView();
                excursion2.menuMostarExcursionFechaView();
                break;
            case 3:
                inicio();
                break;
        }
    }
    // Metodos de control para Socios.
    public static void gestionSocios() {
        menuView.menuGestionSociosView();
        int opcion = menuView.getOpcionView(8);
        switch (opcion) {
            case 1:
                SocioController.crearSocioEstandar();
                break;
            case 2:
                SocioController.modificarSeguroSocioEstandar();
                break;
            case 3:
                SocioController.crearSocioFederado();
                break;
            case 4:
                SocioController.crearSocioInfantil();
                break;
            case 5:
                SocioController.eliminarSocio();
                break;
            case 6:
                SocioController.mostrarSocio();
                break;
            case 7:
                SocioController.facturaMensualSocio();
                break;
            case 8:
                inicio();
                break;
        }
    }
    // Metodos de control para Inscripciones.
    public static void gestionInscripciones() {
        menuView.menuGestionInscripcionesView();
        int opcion = menuView.getOpcionView(4);
        switch (opcion) {
            case 1:
                InscripcionController.crearInscripcion();
                break;
            case 2:
                InscripcionController.eliminarInscripcion();
                break;
            case 3:
                InscripcionController.mostrarInscripcion();
                break;
            case 4:
                inicio();
                break;
        }
    }
}