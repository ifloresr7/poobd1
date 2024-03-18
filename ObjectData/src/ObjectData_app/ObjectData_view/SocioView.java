package ObjectData_app.ObjectData_view;
import java.util.Scanner;
public class SocioView {
    //Teclado, colores de texto y limpieza de consola.
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[33m";
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";
    //Propiedades de clase
    public void formCrearSocioEstandarView() {
        System.out.println(limpiezaConsola + "  - Formulario Añadir Socio Estándar");
    }
    public void formModificarTipoSeguroView() {
        System.out.println(limpiezaConsola + "  - Formulario para Modificar tipo de seguro de un socio estándar");
    }
    public void formCrearSocioFederadoView() {
        System.out.println(limpiezaConsola + "  - Formulario para Añadir Socio Federado");
    }
    public void formCrearSocioInfantilView() {
        System.out.println(limpiezaConsola + "  - Formulario para Añadir Socio Infantil");
    }
    public void formEliminarSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Eliminar socio");
    }
    public void formMostrarSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Mostrar Socios (Todos o por tipo de socio)");
    }
    public void formMostrarFacturaMensualSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Mostrar Factura mensual por socios");
    }
}