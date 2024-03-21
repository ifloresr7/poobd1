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

    public String[] formModificarTipoSeguroView() {
        System.out.println(limpiezaConsola + "  - Formulario para Modificar tipo de seguro de un socio estándar");
        System.out.print(p2 + "- Numero de Socio: " + p);
        String numeroSocio = teclado.nextLine();
        return new String[]{ numeroSocio};
    }

    public String[] formCrearSocioFederadoView() {
        System.out.println(limpiezaConsola + "  - Formulario para Añadir Socio Federado");
        System.out.print(p2 + "- Nombre: " + p);
        String nombre = teclado.nextLine();
        System.out.print(p2 + "- Nif: " + p);
        String NIF = teclado.nextLine();
        return new String[]{nombre,NIF};
    }
    
    public String selectorFederacionesView (String listado){
        System.out.println(limpiezaConsola + h1 + "  - Selección de Federación" + p);
        System.out.println(listado);
        System.out.print(p2 + "Seleccione la federación (Ingrese el numero que quieres seleccionar): " + p);
        String federacionSeleccionada = teclado.nextLine();
        return federacionSeleccionada;
    }
    
    public String[] formCrearSocioInfantilView() {
        System.out.println(limpiezaConsola + h1 + "  - Formulario para Añadir Socio Infantil" + p);
        System.out.print(p2 + "- Nombre del niño: " + p);
        String nombre = teclado.nextLine();
        return new String[]{nombre};
    }

    public String[] numeroSocioParentalView (){
        System.out.println(limpiezaConsola + h1 + "  - Indica el numero de Socio Parental" + p);
        System.out.print(p2 + "- Numero de socio del padre: " + p);
        String numeroParental = teclado.nextLine();
        return new String [] {numeroParental}
    }

    public String[] formEliminarSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Eliminar socio");
        System.out.print(p2 + "- Numero de socio: " + p);
        String numeroSocio = teclado.nextLine();
        return new String[]{numeroSocio};
    }

    public String[] formMostrarSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Mostrar Socios (Todos o por tipo de socio)");
        System.out.println(p2 + "Opciones de filtrado:" + p);
        System.out.println("    - 1. Mostrar todos los socios");
        System.out.println("    - 2. Mostrar socios federados");
        System.out.println("    - 3. Mostrar socios infantil");
        System.out.println("    - 4. Mostrar socios estandar");
        System.out.print(p2 + "Seleccione una opción (1,2,3,4): " + p);
        String opcion = teclado.nextLine();
        return opcion;
        return new String[]{};
    }




    public String[] formMostrarFacturaMensualSocioView() {
        System.out.println(limpiezaConsola + "  - Formulario para Mostrar Factura mensual por socios");
        System.out.print(p2 + "- Numero de socio: " + p);
        String numeroSocio = teclado.nextLine();
        return new String[]{};
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
        System.out.println(respuesta);
        System.out.println("\nPulsa un tecla para continuar...");
        teclado.nextLine();
    }
}