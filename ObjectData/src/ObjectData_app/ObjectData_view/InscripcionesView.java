package ObjectData_app.ObjectData_view;
import java.util.Scanner;
public class InscripcionesView {
    //Teclado, colores de texto y limpieza de consola.
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[33m";
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";
    //Metodos de clase
    public void formCrearInscripcionView() {
        System.out.println(limpiezaConsola + "  - Formulario para Crear inscripcion");
    }
    public void formEliminarInscripcionView() {
        System.out.println(limpiezaConsola + "  - Formulario para Eliminar inscripcion");
    }
    public void formMostrarInscripcionView() {
        System.out.println(limpiezaConsola + "  - Formulario para Mostrar inscripciones con las opciones de filtrar por socio y/o fechas");
    }
}