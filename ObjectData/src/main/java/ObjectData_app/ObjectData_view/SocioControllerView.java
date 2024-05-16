package ObjectData_app.ObjectData_view;

import java.util.Scanner;

public class SocioControllerView {

    // Teclado, colores de texto y limpieza de consola.
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[33m";
    static String h2 = "\033[32m";
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";

    // Metodos de la clase
    //Menu de creacion de socios
    public String crearNuevoSocioView() {
        System.out.println(limpiezaConsola + h2 + "--- CREAR SOCIO POR TIPO ---" + p);
        System.out.println("    1. Añadir Socio Estándar.");
        System.out.println("    2. Añadir Socio Federado.");
        System.out.println("    3. Añadir Socio Infantil.");
        System.out.println("    4. Volver al menú de gestión de socios.");
        System.out.println(p2 + "Seleccione una opcion: " + p);
        return teclado.nextLine();
    }
    //Menu de listado de socios por tipo
    public String listadoSociosView(){
        System.out.println(limpiezaConsola + h2 + "- Indique el tipo de socio que quiere mostrar: " + p);
        System.out.println("    1. Todos los socios.");
        System.out.println("    2. Socios Estándar.");
        System.out.println("    3. Socios Federados.");
        System.out.println("    4. Socio Infantiles.");
        System.out.println("    5. Volver al menú de gestión de socios.");
        System.out.println(p2 + "Seleccione una opcion: " + p);
        return teclado.nextLine();
    }
    //Metodo para pedir al usuario el nombre de socio.
    public String obtenerNombreSocio() {
        System.out.print(p2 + "- Introduce el nombre del socio: " + p);
        return teclado.nextLine();
    }
    //Metodo para pedir al usuario el DNI del socio.
    public String obtenerDNISocio() {
        System.out.print(p2 + "- Introduce el DNI del socio: " + p);
        return teclado.nextLine();
    }
    //Metodo para pedir al usuario el número de socio.
    public String obtenerNumeroSocio() {
        System.out.print(p2 + "- Introduce el número del socio: " + p);
        return teclado.nextLine();
    }
    //Metodo para pedir al usuario el número de socio tutor legal.
    public String numeroSocioParentalView() {
        System.out.print(p2 + "- Introduce el número del socio tutor legal: " + p);
        return teclado.nextLine();
    }
    //Metodo para pedir el seguro del socio.
    public String seleccionarSeguroView() {
        System.out.println(h2 + "-- Tipos de seguros --" + p);
        System.out.println("    1. Basico");
        System.out.println("    2. Completo");
        System.out.print(p2 + "- Elija el tipo de seguro: " + p);
        return teclado.nextLine();
    }
    //Metodo para obtener la federacion del socio.
    public String selectorFederacionesView(String listado) {
        System.out.println(limpiezaConsola + h2 + "- Selección de Federación" + p);
        System.out.println(listado);
        System.out.print(p2 + "Ingrese el número ordinal de la federación que quieres seleccionar: " + p);
        return teclado.nextLine();
    }
}