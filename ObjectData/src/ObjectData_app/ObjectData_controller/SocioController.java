package ObjectData_app.ObjectData_controller;

import ObjectData_app.ObjectData_model.SocioEstandarModel;
import ObjectData_app.ObjectData_model.SocioFederadoModel;
import ObjectData_app.ObjectData_model.SocioInfantilModel;
import ObjectData_app.ObjectData_model.SocioModel;

import java.util.Random;

import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.FederacionModel;
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
        // Se llama a la vista para pedir el nombre y el DNI del usuario
        String[] retorno = View.formCrearSocioEstandarView();
        String nombre = retorno[0]; // El primer parametro del array sera el nombre
        String NIF = retorno[1]; // El segundo parametro del array es el DNI
        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); // Número de socio
        // Mandamos el numero de socio a la pantalla:
        View.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
        // Pido el seguro
        SeguroModel seguroModel = obtenerSeguro();
        // Creamos un objeto de tipo llamado socioModel con los datos correctos
        SocioEstandarModel socioModel = new SocioEstandarModel(numeroSocio, nombre, NIF, seguroModel);
        // Enviamos la información al modelo para que añada el socio a la BBDD
        String respuesta = socioModel.crearSocioEstandar(BBDD, socioModel);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta
        // recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
    }

    public static void modificarSeguroSocioEstandar(Datos BBDD){
        //PEdirle el numero de socio:
        String[] retorno = View.formModificarTipoSeguroView();
        String numeroSocio = retorno[0];
        //Excepcion para la conversion del numero de tipo String a Int
        int numSocio = 0;
        try{
            numSocio = Integer.parseInt(numeroSocio);
        }catch (NumberFormatException error){
            View.respuestaControllerView("El numero de socio debe ser un numero: " + error);
        }
        //Objetemos el objeto socio estandar (si existe)
        SocioEstandarModel socio = SocioEstandarModel.getSocioEstandar(BBDD, numSocio);
        if(socio != null){
            View.respuestaControllerView("Socio encontrado para modificar el seguro.");
            SeguroModel seguroModel = obtenerSeguro();
            String respuesta = socio.actualizarSeguroSocioEstandar(BBDD, seguroModel, socio);
            //Devuelvo la respuespuesta del modelo hacia la vista.
            View.respuestaControllerView(respuesta);
        }else{
            View.respuestaControllerView("No se a podido encontrar el socio.");
        }
    }

    public static void crearSocioFederado(Datos BBDD) {
        //Se llama a la vista para pedir el nombre y el DNI del usuario
        String[] retorno = View.formCrearSocioFederadoView();
        String nombre = retorno[0]; // El primer parametro del array sera el nombre
        String NIF = retorno[1]; // El segundo parametro del array es el DNI
        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); // Número de socio
        //Mandamos el numero de socio a la pantalla:
        View.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
        // Pido el listado de federaciones y el numero de federaciones disponibles;
        String[] listaFederaciones = FederacionModel.obtenerListadoFederacion(BBDD);
        String listado = listaFederaciones[0];
        int opcionesDiponibles = Integer.parseInt(listaFederaciones[1]);
        // Se genera el control de excepcion para opcion seleccionada no valida.
        int seleccion = 0;
        boolean opcionOk = false;
        do{
            String[] opcion = View.selectorFederacionesView(listado);
            try{
                seleccion = Integer.parseInt(opcion[0]);
            }catch (Exception e){
                View.respuestaControllerView("Opcion no valida, debes introducir un valor numerico.");
                continue;
            }
            if(seleccion <= 0 || seleccion >= opcionesDiponibles){
                View.respuestaControllerView("Opcion no valida, selecciona una opcion disponible.");
                continue;
            }else{
                opcionOk = true;
                continue;
            }
        }while(!opcionOk);
        //Con este metodo del modelo obtengo el objeto seleccionado por el usuario
        FederacionModel federacion = FederacionModel.obtenerFederacion(BBDD, seleccion);
        //Creamos el objeto con los datos recolectados.
        SocioFederadoModel socio = new SocioFederadoModel(numeroSocio, nombre, NIF, federacion);
        // Enviamos la información al modelo para que añada el socio a la BBDD
        String respuesta = socio.crearSocioFederado(BBDD, socio);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
    }

    public static void crearSocioInfantil(Datos BBDD) {
        //Se llama a la vista para pedir el nombre
        String[] retorno = View.formCrearSocioInfantilView();
        String nombre = retorno[0];
        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); // Número de socio
        //Mandamos el numero de socio a la pantalla:
        View.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
        // Se genera el control de excepcion para opcion seleccionada no valida.
        boolean codigoOk = false;
        int numeroParental = 0;
        do{
            String[] retornoNun = View.numeroSocioParentalView();
            try{
                numeroParental = Integer.parseInt(retornoNun[0]);
            }catch (Exception e){
                View.respuestaControllerView("Opcion no valida, debes introducir un valor numerico.");
                continue;
            }
            if(!SocioModel.comprobarSocioByCodigo(BBDD, numeroParental)){
                View.respuestaControllerView("No se a encontrado un socio con ese codigo.");
                continue;
            }else{
                codigoOk = true;
            }
        }while(!codigoOk);
        //Creamos el objeto con los datos recolectados.
        SocioInfantilModel socio = new SocioInfantilModel(numeroSocio, nombre, numeroParental);
        // Enviamos la información al modelo para que añada el socio a la BBDD
        String respuesta = socio.crearSocioInfantil(BBDD, socio);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
    }

    public static void eliminarSocio(Datos BBDD) {
        // // Llamamos al método en el modelo para eliminar al socio de la base de datos
        // boolean eliminado = BBDD.eliminarSocio(numeroSocio);
        // // Verificamos si el socio fue eliminado correctamente
        // if (eliminado) {
        // System.out.println("El socio con número de socio " + numeroSocio + " ha sido
        // eliminado correctamente.");
        // } else {
        // System.out.println("No se pudo eliminar el socio con número de socio " +
        // numeroSocio + ". El socio no existe en la base de datos.");
        // }
    }

    public static void mostrarSocio(Datos BBDD) {
    }

    public static void facturaMensualSocio(Datos BBDD) {
    }

    public static SeguroModel obtenerSeguro() {
        // Tratamiento del seguro
        String[] retornoSeguro;
        TipoSeguro tipoSeguro = null;
        Double precioSeguro = null;
        boolean comprobarTipoSeguro = false;
        boolean comprobarPrecioSeguro = false;
        // Excepciones de tipo y precio
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
                View.respuestaControllerView(
                        "El precio del seguro no es válido. Por favor, ingrese un valor numérico. Error: " + error);
                continue;
            }
        } while (!comprobarTipoSeguro || !comprobarPrecioSeguro);
        // Genero el objeto de tipo seguro
        SeguroModel seguro = new SeguroModel(tipoSeguro, precioSeguro);
        // Retorno el seguro
        return seguro;
    }
}