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
    public String pedirDescripcionExcursion(){
        System.out.print(p2 + "   - Ingrese el nombre de la excursión: " + p);
        return teclado.nextLine();
    }
    public String pedirFechaExcursion(){
        System.out.print(p2 + "   - Ingrese la fecha de la excursión en formato yyyy-MM-dd HH:mm: " + p);
        return teclado.nextLine();       
    }
    public String pedirNumeroDiasExcursion(){
        System.out.print(p2 + "   - Ingrese el número de días de la excursión: " + p);
        return teclado.nextLine();
    }
    public String pedirPrecioExcursion(){
        System.out.print(p2 + "   - Ingrese el precio de inscripción de la excursión: " + p);
        return teclado.nextLine();
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