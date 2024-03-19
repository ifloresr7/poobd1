package ObjectData_app.ObjectData_view;
import ObjectData_app.ObjectData_controller.ExcursionController;

import java.util.Scanner;
public class ExcursionesView {
    //Teclado, colores de texto y limpieza de consola.
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[33m";
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";
    //Metodos de clase
    public void menuCrearExcursionView() {
        System.out.println(limpiezaConsola + "  - Formulario para crear excursiones");
        System.out.println("Ingrese el nombre de la excursión:");
        String descripcion = teclado.nextLine();
        System.out.println("Ingrese la fecha de la excursión (formato dd/mm/yyyy):");
        String fecha = teclado.nextLine();
        System.out.println("Ingrese el número de días de la excursión:");
        int numDias = teclado.nextInt();
        System.out.println("Ingrese el precio de inscripción de la excursión:");
        double precio = teclado.nextDouble();
        ExcursionController.addExcursion(descripcion,fecha,numDias,precio);
        System.out.println("Excursión registrada con éxito.");
    }
    public void menuMostarExcursionFechaView() {
        System.out.println(limpiezaConsola + "  - Formulario para mostrar excursiones segun fecha");
    }
}