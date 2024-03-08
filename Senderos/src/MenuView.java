import java.util.Scanner;
public class MenuView {
    private static int opcion;
    private static boolean salir = false;
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[33m";
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";
    static void menuApp() {
        do {
            System.out.println(limpiezaConsola + h1 + "---MENU PRINCIPAL DE LA APLICACION---" + p);
            System.out.println("1. Gestión Excursiones");
            System.out.println("2. Gestión de Socios");
            System.out.println("3. Gestión de Inscripciones");
            System.out.println("4. Salir");
            opcion = getOpcion(4);
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
                    salir = true;
                    break;
            }
        } while (!salir);
    }
    static void gestionExcursiones() {
        System.out.println(limpiezaConsola + h1 + "---GESTIÓN DE EXCURSIONES---" + p);
        System.out.println("1. Añadir Excursión");
        System.out.println("2. Mostrar Excursiones con filtro entre fechas");
        System.out.println("3. Volver");
        opcion = getOpcion(3);
        switch (opcion) {
            case 1:
                crearExcursion();
                break;
            case 2:
                AppController.mostarEscursionFecha();
                break;
            case 3:
                menuApp();
                break;
        }
    }
    static void crearExcursion() {
        System.out.println(limpiezaConsola + h1 + "---CREAR UNA EXCURSIÓN---" + p);
        System.out.print("Ingrese el código alfanumérico: ");
        String codigo = teclado.nextLine();
        System.out.print("Ingrese la descripción: ");
        String descripcion = teclado.nextLine();
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String fecha = teclado.nextLine();
        System.out.print("Ingrese el número de días: ");
        int numDias = Integer.parseInt(teclado.nextLine());
        System.out.print("Ingrese el precio de la inscripción: ");
        double precio = Double.parseDouble(teclado.nextLine());
        AppController.crearExcursion(codigo, descripcion, fecha, numDias, precio);
    }

    static void gestionSocios() {
        System.out.println(limpiezaConsola + h1 + "---GESTIÓN DE SOCIOS---" + p);
        System.out.println("1. Añadir Socio Estándar");
        System.out.println("2. Modificar tipo de seguro de un socio estándar");
        System.out.println("3. Añadir Socio Federado");
        System.out.println("4. Añadir Socio Infantil");
        System.out.println("5. Eliminar socio");
        System.out.println("6. Mostrar Socios (Todos o por tipo de socio)");
        System.out.println("7. Mostrar Factura mensual por socios");
        System.out.println("8. Volver");
        opcion = getOpcion(8);
        switch (opcion) {
            case 1:
                AppController.crearSocioEstandar();
                break;
            case 2:
                AppController.modificarSeguroSocioEstandar();
                break;
            case 3:
                AppController.crearSocioFederado();
                break;
            case 4:
                AppController.crearSocioInfantil();
                break;
            case 5:
                AppController.eliminarSocio();
                break;
            case 6:
                AppController.mostrarSocio();
                break;
            case 7:
                AppController.facturaMensualSocio();
                break;
            case 8:
                menuApp();
                break;
        }
    }
    static void gestionInscripciones() {
        System.out.println(limpiezaConsola + h1 + "---GESTIÓN DE INSCRIPCIONES---" + p);
        System.out.println("1. Añadir Inscripción");
        System.out.println("2. Eliminar Inscripción");
        System.out.println("3. Mostrar inscripciones con las opciones de filtrar por socio y/o fechas");
        System.out.println("4. Volver");
        opcion = getOpcion(4);
        switch (opcion) {
            case 1:
                AppController.crearInscripcion();
                break;
            case 2:
                AppController.eliminarInscripcion();
                break;
            case 3:
                AppController.mostrarInscripcion();
                break;
            case 4:
                menuApp();
                break;
        }
    }
    static int getOpcion(int valores) {
        try {
            System.out.print(p2 + "Elije una opción válida (1-" + valores + "): " + p);
            opcion = Integer.parseInt(teclado.nextLine());
            if (opcion < 1 || opcion > valores) {
                System.out.println(error + "Opción no válida." + p);
                return getOpcion(valores);
            }
            return opcion;
        } catch (NumberFormatException e) {
            System.out.println(error + "Opción no válida. Debe ingresar un número." + p);
            return getOpcion(valores);
        }
    }
}