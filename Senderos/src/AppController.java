public class AppController {
    static AppView View = new AppView();
    static boolean cerrarApp = false;
    //Inicio del menu de la APP.
    public static void inicio() {
        do {
            View.menuInicioView();
            int opcion = View.getOpcionView(4);
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
    //Metodos de control para Excursiones.
    public static void gestionExcursiones(){
        View.menuGestionExcursionesView();
        int opcion = View.getOpcionView(3);
        switch (opcion) {
            case 1:
                crearExcursion();
                break;
            case 2:
                mostarEscursionFecha();
                break;
            case 3:
                inicio();
                break;
        }
    }
    public static void crearExcursion() {
        View.menuCrearExcursionView();
    }
    public static void mostarEscursionFecha() {
        View.menuMostarEscursionFechaView();
    }
    //Metodos de control para Socios.
    public static void gestionSocios() {
        View.menuGestionSociosView();
        int opcion = View.getOpcionView(8);
        switch (opcion) {
            case 1:
                crearSocioEstandar();
                break;
            case 2:
                modificarSeguroSocioEstandar();
                break;
            case 3:
                crearSocioFederado();
                break;
            case 4:
                crearSocioInfantil();
                break;
            case 5:
                eliminarSocio();
                break;
            case 6:
                mostrarSocio();
                break;
            case 7:
                facturaMensualSocio();
                break;
            case 8:
                inicio();
                break;
        }
    }
    public static void crearSocioEstandar() {
        View.formCrearSocioEstandarView();
    }
    public static void modificarSeguroSocioEstandar() {
        View.formModificarTipoSeguroView();
    }
    public static void crearSocioFederado() {
        View.formCrearSocioFederadoView();
    }
    public static void crearSocioInfantil() {
        View.formCrearSocioInfantilView();
    }
    public static void eliminarSocio() {
        View.formEliminarSocioView();
    }
    public static void mostrarSocio() {
        View.formMostrarSocioView();
    }
    public static void facturaMensualSocio() {
        View.formMostrarFacturaMensualSocioView();
    }
    //Metodos de control para Inscripciones.
    public static void gestionInscripciones() {
        View.menuGestionInscripcionesView();
        int opcion = View.getOpcionView(4);
        switch (opcion) {
            case 1:
                crearInscripcion();
                break;
            case 2:
                eliminarInscripcion();
                break;
            case 3:
                mostrarInscripcion();
                break;
            case 4:
                inicio();
                break;
        }
    }
    public static void crearInscripcion() {
        View.formCrearInscripcionView();
    }
    public static void eliminarInscripcion() {
        View.formEliminarInscripcionView();
    }
    public static void mostrarInscripcion() {
        View.formMostrarInscripcionView();
    }
}