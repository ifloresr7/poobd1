package ObjectData_app.ObjectData_view;

import java.util.Scanner;

public class ExcursionesView {
    // Teclado, colores de texto y limpieza de consola.
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[33m";
    static String h2 = "\033[32m";
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";

    // Metodos de clase
    public String[] menuCrearExcursionView() {
        System.out.println(limpiezaConsola + h2 + "-- FORMULARIO PARA CREAR EXCURSIONES --" + p);
        System.out.println("NOTA: Puede detener la creación de excursiones si omite algún dato durante la recolección de información.");
        System.out.print(p2 + "   - Ingrese el nombre de la excursión: " + p);
        String descripcion = teclado.nextLine();
        System.out.print(p2 + "   - Ingrese la fecha de la excursión en formato yyyy-MM-dd: " + p);
        String fecha = teclado.nextLine();
        System.out.print(p2 + "   - Ingrese el número de días de la excursión: " + p);
        String numDias = teclado.nextLine();
        System.out.print(p2 + "   - Ingrese el precio de inscripción de la excursión: " + p);
        String precio = teclado.nextLine();
        return new String[] { descripcion, fecha, numDias, precio };
    }

    // Este metodo muestra excursiones por fecha
    public String[] menuMostarExcursionFechaView() {
        System.out.println(limpiezaConsola + h2 + "-- MOSTRAR EXCURSIONES ENTRE FECHAS --" + p);
        System.out.println("NOTA: Puede detener la búsqueda de excursiones si omite algún dato durante la recolección de información.");
        System.out.print(p2 + "    - Ingrese la fecha Inicio en formato yyyy-MM-dd: " + p);
        String inputDate = teclado.nextLine();
        System.out.print(p2 + "    - Ingrese la fecha Fin en formato yyyy-MM-dd: " + p);
        String inputDate2 = teclado.nextLine();
        return new String[] { inputDate, inputDate2 };
    }
}