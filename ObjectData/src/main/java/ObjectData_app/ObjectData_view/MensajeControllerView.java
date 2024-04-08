package ObjectData_app.ObjectData_view;

import java.util.Scanner;

public class MensajeControllerView {
    // Atributos
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[32m";
    static String p = "\u001B[0m";
    static String error = "\033[31m";
    static String out = "\u001B[35m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";
    //Este metodo se usa para imprimir un titulo H1 en pantalla, para titulos de metodo.
    public void tituloDeLaFuncion(String titulo) {
        System.out.println(limpiezaConsola + h1 + titulo);
        System.out.println("NOTA: Puede detener esta operación si omite algún dato durante la recolección de información.");
    }
    // Este metodo se usa para devolver respuestas generadas por la aplicación
    public void respuestaControllerView(String respuesta) {
        System.out.println(respuesta);
        System.out.println(out + "\nPulse ENTER para continuar..." + p);
        teclado.nextLine();
    }

    // Este metodo se usa para devolver errores generados en la aplicación
    public void excepcionesControllerView(String respuesta) {
        System.out.println(error + "¡ERROR! - " + respuesta + p);
        System.out.println(out + "\nPulse ENTER para continuar..." + p);
        teclado.nextLine();
    }
}
