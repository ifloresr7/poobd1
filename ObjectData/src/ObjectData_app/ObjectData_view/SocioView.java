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

    // Propiedades de clase
    public String[] formCrearSocioEstandarView() {
        System.out.println(limpiezaConsola + h1 + "--- Formulario Añadir Socio Estándar ---" + p);
        System.out.print(p2 + "- Nombre: " + p);
        String nombre = teclado.nextLine();
        System.out.print(p2 + "- DNI: " + p);
        String DNI = teclado.nextLine();
        return new String[] { nombre, DNI };
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

    public String[] seleccionarSeguroView() {
        System.out.println(h2 + "-- Tipos de seguros --" + p);
        System.out.println("    1. Basico");
        System.out.println("    2. Completo");
        System.out.print(p2 + "- Elige el tipo de seguro: " + p);
        String tipo = teclado.nextLine();
        System.out.print(p2 + "- Ingrese el precio de inscripción de la excursión (Ejem: 25.75): " + p);
        String precio = teclado.nextLine();
        return new String[] {tipo, precio};
    }

    // Este metodo se usa para devolver respuestas del controlador, tipo: "Fallo al guardar, Guardado Correcto, los objetos o lo que sea..., es decir los datos almacenados entre otros mensajes."
    public void respuestaControllerView(String respuesta) {
        System.out.println(limpiezaConsola + respuesta);
        System.out.println("\nPulsa un tecla para continuar...");
        teclado.nextLine();
    }
}