package ObjectData_app.ObjectData_view;

import java.util.Scanner;

public class InscripcionesView {
    // Teclado, colores de texto y limpieza de consola.
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[33m";
    static String h2 = "\033[32m";
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";

    // Metodos de clase
    public String formCrearInscripcionView() {
        System.out.println(limpiezaConsola + h1 + "-- CREAR UNA INSCRIPCIÓN --" + p);
        System.out.println(
                "NOTA: Puede detener la creación de inscripciones si omite algún dato durante la recolección de información.");
        System.out.print(p2 + "- ¿El socio existe? (S/N): " + p);
        String respuesta = teclado.nextLine().toUpperCase();
        if (respuesta.equals("S")) {
            System.out.print(p2 + "- Introduzca el número de socio: " + p);
            String numSocio = teclado.nextLine();
            return numSocio.isEmpty() ? "" : numSocio; // Devuelve una cadena vacía si la entrada está vacía
        } else if (respuesta.equals("N")) {
            return "0"; // Devuelve "0" para indicar que se debe crear un nuevo socio
        } else {
            // Si la respuesta no es ni "S", ni "N", ni está vacía
            return ""; // Devuelve una cadena vacía en caso de una entrada no válida
        }
    }

    public String formListadoExcursionesView(String listado) {
        System.out.println(limpiezaConsola + h2 + "- Listado de excursiones." + p);
        System.out.println(listado);
        System.out.print(p2 + "- Ingrese el número de la excursión que quieres seleccionar: " + p);
        return teclado.nextLine();
    }

    public String formEliminarInscripcionView(String[] listadoInscripciones) {

        System.out.println(
                "NOTA: Puede cancelar la eliminación de una inscripción al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
        System.out.print(p2 + "- Introduzca el número de inscripción a eliminar: " + p);
        String numInscripcion = teclado.nextLine();
        return numInscripcion;
    }

    public String formMostrarInscripcionView() {
        System.out.println(limpiezaConsola + h1 + "-- MOSTRAR INSCRIPCIONES --" + p);
        System.out.println(p2 + "Indique una opción de filtrado:" + p);
        System.out.println("    - 1. Filtrar por socio");
        System.out.println("    - 2. Filtrar por fechas");
        System.out.print(p2 + "- Seleccione una opción (1 o 2): " + p);
        String opcion = teclado.nextLine();
        return opcion;
    }

    public String[] formFiltrarPorSocio() {
        System.out.println(p2 + "- Introduzca el número de socio para filtrar (presione Enter para omitir): " + p);
        String numSocio = teclado.nextLine().trim();
        if (numSocio.isEmpty()) {
            // Si el usuario no ingresa nada, retornar un array vacío
            return new String[] {};
        } else {
            return new String[] { numSocio };
        }
    }

    public String[] formFiltrarPorFechas() {
        System.out.println(p2 + "- Introduzca la fecha de inicio para el filtro (yyyy-MM-dd): " + p);
        String fechaInicio = teclado.nextLine();
        System.out.println(p2 + "- Introduzca la fecha de fin para el filtro (yyyy-MM-dd): " + p);
        String fechaFin = teclado.nextLine();
        return new String[] { fechaInicio, fechaFin };
    }
}