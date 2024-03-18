import java.util.Scanner;
public class AppView extends AppController {
    //Teclado, colores de texto y limpieza de consola.
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[38;5;208m";
    static String p = "\u001B[38;5;220m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";
    //Propiedades de clase
    void menuInicioView() {
        System.out.println(limpiezaConsola + h1 + "---MENU PRINCIPAL DE LA APLICACION---" + p);
        System.out.println("    1. Gestión Excursiones");
        System.out.println("    2. Gestión de Socios");
        System.out.println("    3. Gestión de Inscripciones");
        System.out.println("    4. Salir");
    }
    void menuGestionExcursionesView() {
        System.out.println(limpiezaConsola + h1 + "---GESTIÓN DE EXCURSIONES---" + p);
        System.out.println("    1. Añadir Excursión");
        System.out.println("    2. Mostrar Excursiones con filtro entre fechas");
        System.out.println("    3. Volver");
    }
    void menuCrearExcursionView() {
        System.out.println(limpiezaConsola + "  - Formulario para crear excursiones");
    }
    void menuMostarExcursionFechaView() {
        System.out.println(limpiezaConsola + "  - Formulario para mostrar excursiones segun fecha");
    }
    void menuGestionSociosView() {
        System.out.println(limpiezaConsola + h1 + "---GESTIÓN DE SOCIOS---" + p);
        System.out.println("    1. Añadir Socio Estándar");
        System.out.println("    2. Modificar tipo de seguro de un socio estándar");
        System.out.println("    3. Añadir Socio Federado");
        System.out.println("    4. Añadir Socio Infantil");
        System.out.println("    5. Eliminar socio");
        System.out.println("    6. Mostrar Socios (Todos o por tipo de socio)");
        System.out.println("    7. Mostrar Factura mensual por socios");
        System.out.println("    8. Volver");
    }
    void formCrearSocioEstandarView() {
        System.out.println(limpiezaConsola + "  - Formulario Añadir Socio Estándar");
    }
    void formModificarTipoSeguroView() {
        System.out.println(limpiezaConsola + "  - Formulario para Modificar tipo de seguro de un socio estándar");
    }
    void formCrearSocioFederadoView() {
        System.out.println(limpiezaConsola + "  - Formulario para Añadir Socio Federado");
    }
    void formCrearSocioInfantilView() {
        System.out.println(limpiezaConsola + "  - Formulario para Añadir Socio Infantil");
    }
    void formEliminarSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Eliminar socio");
    }
    void formMostrarSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Mostrar Socios (Todos o por tipo de socio)");
    }
    void formMostrarFacturaMensualSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Mostrar Factura mensual por socios");
    }
    void menuGestionInscripcionesView() {
        System.out.println(limpiezaConsola + h1 + "---GESTIÓN DE INSCRIPCIONES---" + p);
        System.out.println("    1. Añadir Inscripción");
        System.out.println("    2. Eliminar Inscripción");
        System.out.println("    3. Mostrar inscripciones con las opciones de filtrar por socio y/o fechas");
        System.out.println("    4. Volver");
    }
    void formCrearInscripcionView() {
        System.out.println(limpiezaConsola + "  - Formulario para Crear inscripcion");
    }
    void formEliminarInscripcionView() {
        System.out.println(limpiezaConsola + "  - Formulario para Eliminar inscripcion");
    }
    void formMostrarInscripcionView() {
        System.out.println(limpiezaConsola + "  - Formulario para Mostrar inscripciones con las opciones de filtrar por socio y/o fechas");
    }
    int getOpcionView(int valores) {
        try {
            System.out.print(p2 + "Elije una opción válida (1-" + valores + "): " + p);
            int opcion = Integer.parseInt(teclado.nextLine());
            if (opcion < 1 || opcion > valores) {
                System.out.println(error + "Opción no válida." + p);
                return getOpcionView(valores);
            }
            return opcion;
        } catch (NumberFormatException e) {
            System.out.println(error + "Opción no válida. Debe ingresar un número." + p);
            return getOpcionView(valores);
        }
    }
}