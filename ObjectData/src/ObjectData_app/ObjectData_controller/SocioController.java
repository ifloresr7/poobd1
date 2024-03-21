package ObjectData_app.ObjectData_controller;

import ObjectData_app.ObjectData_model.SocioEstandarModel;
import ObjectData_app.ObjectData_model.SocioFederadoModel;
import ObjectData_app.ObjectData_model.SocioInfantilModel;

import java.util.Random;

import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.SeguroModel;
import ObjectData_app.ObjectData_model.SeguroModel.TipoSeguro;
//Se añade la vista socio
import ObjectData_app.ObjectData_view.SocioView;

public class SocioController {
    static SocioView View = new SocioView();

    // Método para generar un número de socio aleatorio
    public static int generarID() {
        Random rand = new Random();
        int id = 0;
        for (int i = 0; i < 10; i++) {
            id = id * 10 + rand.nextInt(9) + 1;
        }
        if (id < 0) {
            return id * -1;
        }
        return id;
    }

    // Metodo para añadir un socio estandar
    public static void crearSocioEstandar(Datos BBDD) {
        //Se llama a la vista para pedir el nombre y el DNI del usuario
        String[] retorno = View.formCrearSocioEstandarView();
        String nombre = retorno[0]; // El primer parametro del array sera el nombre
        String NIF = retorno[1]; // El segundo parametro del array es el DNI
        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); // Número de socio
        //Mandamos el numero de socio a la pantalla:
        View.respuestaControllerView("   - Numero de socio generado: " + numeroSocio);
        // Tratamiento del seguro
        String[] retornoSeguro;
        TipoSeguro tipoSeguro = null;
        Double precioSeguro = null;
        boolean comprobarTipoSeguro = false;
        boolean comprobarPrecioSeguro = false;
        //Excepciones de tipo y precio
        do {
            // Se piden los datos del seguro mediante la vista seleccionarSeguroView
            retornoSeguro = View.seleccionarSeguroView();
            // Se hace el tratamiento de los datos retornados desde la vista.
            if (retornoSeguro[0].equals("1")) {
                tipoSeguro = TipoSeguro.BASICO;
                comprobarTipoSeguro = true;
            } else if (retornoSeguro[0].equals("2")) {
                tipoSeguro = TipoSeguro.COMPLETO;
                comprobarTipoSeguro = true;
            } else {
                View.respuestaControllerView("No se ha podido comprobar el tipo de seguro.");
                continue;
            }
            try {
                precioSeguro = Double.parseDouble(retornoSeguro[1]);
                comprobarPrecioSeguro = true;
            } catch (NumberFormatException error) {
                View.respuestaControllerView("El precio del seguro no es válido. Por favor, ingrese un valor numérico. Error: " + error);
                continue;
            }
        } while (!comprobarTipoSeguro || !comprobarPrecioSeguro);
        // Creamos el objeto de tipo llamado seguroModel
        SeguroModel seguroModel = new SeguroModel(tipoSeguro, precioSeguro);
        // Creamos un objeto de tipo llamado socioModel con los datos correctos
        SocioEstandarModel socioModel = new SocioEstandarModel(numeroSocio, nombre, NIF, seguroModel);
        // Enviamos la información al modelo para que añada el socio a la BBDD
        String respuesta = socioModel.crearSocioEstandar(BBDD, socioModel);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
    }

    public static void modificarSeguroSocioEstandar(Datos BBDD) {
    }

    public static void crearSocioFederado(Datos BBDD) {
        //Se llama a la vista para pedir el nombre y el DNI del usuario
        String[] retorno = View.formCrearSocioFederadoView();
        String nombre = retorno[0]; // El primer parametro del array sera el nombre
        String NIF = retorno[1]; // El segundo parametro del array es el DNI
        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); // Número de socio
        //Mandamos el numero de socio a la pantalla:
        View.respuestaControllerView("   - Numero de socio generado: " + numeroSocio);  
        // Creamos un objeto de tipo llamado socioModel con los datos correctos
        SocioFederadoModel socioModel = new SocioFederadoModel(numeroSocio, nombre, NIF);
        // Enviamos la información al modelo para que añada el socio a la BBDD
        String respuesta = socioModel.crearSocioFederado(BBDD, socioModel);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
    }

    public static void crearSocioInfantil(Datos BBDD) {
         int numeroSocioPadreOMadre = generarID();
         View.respuestaControllerView("   - Numero de socio generado: " + numeroSocioPadreOMadre);  
         SocioInfantilModel socioModel = new SocioInfantilModel(numeroSocioPadreOMadre, "Nombre del Socio Infantil", true, 0);
         String respuesta = socioModel.crearSocioInfantil(BBDD, socioModel);
         System.out.println("Socio infantil creado con éxito.");
    }

    public static void eliminarSocio(Datos BBDD, int numeroSocio) {
        // Llamamos al método en el modelo para eliminar al socio de la base de datos
        boolean eliminado = BBDD.eliminarSocio(numeroSocio);
        // Verificamos si el socio fue eliminado correctamente
        if (eliminado) {
            System.out.println("El socio con número de socio " + numeroSocio + " ha sido eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el socio con número de socio " + numeroSocio + ". El socio no existe en la base de datos.");
        }
    }
    

    public static void mostrarSocio(Datos BBDD) {
    }

    public static void facturaMensualSocio(Datos BBDD) {
    }
}