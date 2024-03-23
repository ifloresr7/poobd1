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
        System.out.println(limpiezaConsola + h1 + "-- CREA UNA INSCRIPCIÓN --" + p);
        System.out.println("NOTA: Puede cancelar la creación de la inscripción al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
        System.out.print(p2 + "- Introduzca el número de socio: " + p);
        String numSocio = teclado.nextLine();
        return numSocio;
    }
    public String formListadoExcursionesView(String listado){
        System.out.println(limpiezaConsola + h2 + "- Listado de excursiones." + p);
        System.out.println(listado);
        System.out.print(p2 + "- Ingrese el número de la excursión que quieres seleccionar: " + p);
        return  teclado.nextLine();
    }


    public String formEliminarInscripcionView() {
        System.out.println(limpiezaConsola + h1 + "-- ELIMINA UNA INSCRIPCIÓN --" + p);
        System.out.println("NOTA: Puede cancelar la eliminación de una inscripción al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
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
        System.out.println(p2 + "- Introduzca el número de socio para filtrar: " + p);
        String numSocio = teclado.nextLine();
        return new String[]{numSocio};
    }

    public String[] formFiltrarPorFechas() {
        System.out.println(p2 + "- Introduzca la fecha de inicio para el filtro (dd/mm/aaaa): " + p);
        String fechaInicio = teclado.nextLine();
        System.out.println(p2 + "- Introduzca la fecha de fin para el filtro (dd/mm/aaaa): " + p);
        String fechaFin = teclado.nextLine();
        return new String[]{fechaInicio, fechaFin};
    }

    // Este metodo se usa para devolver respuestas del controlador, tipo: "Fallo al
    // guardar, Guardado Correcto, los objetos o lo que sea..., es decir los datos
    // almacenados entre otros mensajes."
    public void respuestaControllerView(String respuesta) {
        System.out.println(respuesta);
        System.out.println("\nPulsa un tecla para continuar...");
        teclado.nextLine();
    }
}