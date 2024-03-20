package ObjectData_app.ObjectData_view;

import java.text.SimpleDateFormat;
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
    public String[] menuCrearExcursionView() {
        System.out.println(limpiezaConsola + "  - Formulario para crear excursiones");
        System.out.println("Ingrese el nombre de la excursión:");
        String descripcion = teclado.nextLine();
        System.out.println("Ingrese la fecha de la excursión (formato dd/mm/yyyy):");
        String fecha = teclado.nextLine();
        System.out.println("Ingrese el número de días de la excursión:");
        String numDias = teclado.nextLine();
        System.out.println("Ingrese el precio de inscripción de la excursión:");
        String precio = teclado.nextLine();
        return new String[] {descripcion,fecha,numDias,precio};
    }

   public String[] menuMostarExcursionFechaView() {
        System.out.println(limpiezaConsola + "  - Formulario para mostrar excursiones segun fecha");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Ingrese la fecha Inicio en formato yyyy-MM-dd: ");
        String inputDate = teclado.nextLine();
        System.out.println("Ingrese la fecha Fin en formato yyyy-MM-dd: ");
        String inputDate1 = teclado.nextLine();
        return new String [] {inputDate,inputDate1};
        
        // ExcursionController.mostrarExcursionFecha(inputDate1, inputDate);
    }
    //Este metodo se usa para devolver respuestas del controlador, tipo: "Fallo al guardar, Guardado Correcto, No se encontro fecha, etc"
    public void estadoFinal(String respuesta){
        System.out.println(respuesta);
    }
}