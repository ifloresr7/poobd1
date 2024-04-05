package ObjectData_app.ObjectData_view;

import java.util.Scanner;

public class MensajeControllerView {
    // Atributos
    static Scanner teclado = new Scanner(System.in);
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";

    // Este metodo se usa para devolver respuestas generadas por la aplicación
    public void respuestaControllerView(String respuesta) {
        System.out.println(respuesta);
        System.out.println(p2 + "\nPulse una tecla para continuar..." + p);
        teclado.nextLine();
    }

    // Este metodo se usa para devolver errores generados en la aplicación
    public void excepcionesControllerView(String respuesta) {
        System.out.println(error + "¡ERROR! - " + respuesta + p);
        System.out.println("\nPulse una tecla para continuar...");
        teclado.nextLine();
    }
}
