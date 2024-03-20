package ObjectData_app.ObjectData_controller;
//Se añade la vista principal
import ObjectData_app.ObjectData_view.AppMenuView;

import ObjectData_app.ObjectData_model.*;

public class AppController {
    static AppMenuView menuView = new AppMenuView();
    static boolean cerrarApp = false;
    // Inicio del menu de la APP.
    public static void inicio(Datos BBDD) {
        do { 
            menuView.menuInicioView();
            int opcion = menuView.getOpcionView(5);
            switch (opcion) {
                case 1:
                    gestionExcursiones(BBDD);
                    break;
                case 2:
                    gestionSocios(BBDD);
                    break;
                case 3:
                    gestionInscripciones(BBDD);
                    break;
                case 4:
                    CargarDatosModel cargarDatos = new CargarDatosModel();
                    cargarDatos.cargarDatos(BBDD);
                    break;
                case 5:
                    cerrarApp = true;
                    break;
            }
        } while (!cerrarApp);
    }

    // Metodos de control para Excursiones.
    public static void gestionExcursiones(Datos BBDD) {
        menuView.menuGestionExcursionesView();
        int opcion = menuView.getOpcionView(3);
        switch (opcion) {
            case 1:
                ExcursionController.crearExcursion(BBDD);
                break;
            case 2:
                ExcursionController.mostrarExcursionFecha(BBDD);
                break;
            case 3:
                inicio(BBDD);
                break;
        }
    }
    // Metodos de control para Socios.
    public static void gestionSocios(Datos BBDD) {
        menuView.menuGestionSociosView();
        int opcion = menuView.getOpcionView(8);
        switch (opcion) {
            case 1:
                SocioController.crearSocioEstandar(BBDD);
                break;
            case 2:
                SocioController.modificarSeguroSocioEstandar(BBDD);
                break;
            case 3:
                SocioController.crearSocioFederado(BBDD);
                break;
            case 4:
                SocioController.crearSocioInfantil(BBDD);
                break;
            case 5:
                SocioController.eliminarSocio(BBDD);
                break;
            case 6:
                SocioController.mostrarSocio(BBDD);
                break;
            case 7:
                SocioController.facturaMensualSocio(BBDD);
                break;
            case 8:
                inicio(BBDD);
                break;
        }
    }
    // Metodos de control para Inscripciones.
    public static void gestionInscripciones(Datos BBDD) {
        menuView.menuGestionInscripcionesView();
        int opcion = menuView.getOpcionView(4);
        switch (opcion) {
            case 1:
                InscripcionController.crearInscripcion(BBDD);
                break;
            case 2:
                InscripcionController.eliminarInscripcion(BBDD);
                break;
            case 3:
                InscripcionController.mostrarInscripcion(BBDD);
                break;
            case 4:
                inicio(BBDD);
                break;
        }
    }
}