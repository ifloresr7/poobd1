package ObjectData_app.ObjectData_view;

import java.util.Scanner;

public class SocioView {
    // Teclado, colores de texto y limpieza de consola.
    static Scanner teclado = new Scanner(System.in);
    static String h1 = "\033[33m";
    static String h2 = "\033[32m";
    static String p = "\u001B[0m";
    static String p2 = "\033[36m";
    static String error = "\033[31m";
    static String limpiezaConsola = "\n\n\n\n\n\n\n\n\n\n\n";

    // Metodos de la clase
    public String crearNuevoSocioView() {
        System.out.println(h2 + "--- CREAR SOCIO POR TIPO ---" + p);
        System.out.println("    1. Añadir Socio Estándar.");
        System.out.println("    2. Añadir Socio Federado.");
        System.out.println("    3. Añadir Socio Infantil.");
        System.out.println("    4. Volver al menú de gestión de socios.");
        System.out.println(p2 + "Seleccione una opcion: " + p);
        return teclado.nextLine();
    }

    public String[] formCrearSocioEstandarView() {
        System.out.println(limpiezaConsola + h2 + "- Formulario añadir socio estándar ---" + p);
        System.out.println("NOTA: Puede cancelar la búsqueda de excursiones al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
        System.out.print(p2 + "- Nombre: " + p);
        String nombre = teclado.nextLine();
        System.out.print(p2 + "- DNI: " + p);
        String DNI = teclado.nextLine();
        return new String[] { nombre, DNI };
    }

    public String[] formModificarTipoSeguroView() {
        System.out.println(limpiezaConsola + h2 + "- Formulario para Modificar tipo de seguro de un socio estándar" + p);
        System.out.println("NOTA: Puede cancelar la búsqueda de excursiones al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
        System.out.print(p2 + "- Numero de Socio: " + p);
        String numeroSocio = teclado.nextLine();
        return new String[] { numeroSocio };
    }

    public String[] formCrearSocioFederadoView() {
        System.out.println(limpiezaConsola + h2 + "- Formulario para añadir socio federado" + p);
        System.out.println("NOTA: Puede cancelar la búsqueda de excursiones al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
        System.out.print(p2 + "- Nombre: " + p);
        String nombre = teclado.nextLine();
        System.out.print(p2 + "- NIF: " + p);
        String NIF = teclado.nextLine();
        return new String[] { nombre, NIF };
    }

    public String[] selectorFederacionesView(String listado) {
        System.out.println(limpiezaConsola + h2 + "- Selección de Federación" + p);
        System.out.println(listado);
        System.out.print(p2 + "Ingrese el número ordinal de la federación que quieres seleccionar: " + p);
        String federacionSeleccionada = teclado.nextLine();
        return new String[] { federacionSeleccionada };
    }

    public String[] formCrearSocioInfantilView() {
        System.out.println(limpiezaConsola + h2 + "- Formulario para Añadir Socio Infantil" + p);
        System.out.println("NOTA: Puede cancelar la busqueda de excursiones al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
        System.out.print(p2 + "- Nombre del niño: " + p);
        String nombre = teclado.nextLine();
        return new String[] { nombre };
    }

    public String[] numeroSocioParentalView() {
        System.out.println(limpiezaConsola + h2 + "- Indica el numero de Socio Parental" + p);
        System.out.print(p2 + "- Número de socio del padre o madre: " + p);
        String numeroParental = teclado.nextLine();
        return new String[] { numeroParental };
    }

    public String listadoSociosView(){
        System.out.println(limpiezaConsola + h2 + "- Indica el tipo de socio que quieres mostrar: " + p);
        System.out.println("    1. Todos los socios.");
        System.out.println("    2. Socios Estándar.");
        System.out.println("    3. Socios Federados.");
        System.out.println("    4. Socio Infantiles.");
        System.out.println("    5. Volver al menú de gestión de socios.");
        System.out.println(p2 + "Seleccione una opcion: " + p);
        return teclado.nextLine();
    }

    public String[] formEliminarSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Eliminar socio");
        System.out.println("NOTA: Puede cancelar la busqueda de excursiones al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
        System.out.print(p2 + "- Numero de socio: " + p);
        String numeroSocio = teclado.nextLine();
        return new String[] { numeroSocio };
    }

    public String formMostrarFacturaMensualSocioView() {
        System.out.println(limpiezaConsola + h2 + "--- Mostrar facturación mensual ---" + p);
        System.out.println("NOTA: Puede cancelar la busqueda de excursiones al finalizar la recolección de datos si durante la recopilación de estos usted deja un dato en blanco.");
        System.out.print(p2 + "- Numero de socio: " + p);
        return teclado.nextLine();
    }

    public String[] seleccionarSeguroView() {
        System.out.println(h2 + "-- Tipos de seguros --" + p);
        System.out.println("    1. Basico");
        System.out.println("    2. Completo");
        System.out.print(p2 + "- Elige el tipo de seguro: " + p);
        String tipo = teclado.nextLine();
        return new String[] { tipo };
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