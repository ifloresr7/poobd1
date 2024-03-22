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

    // Metodo para elegir el tipo de socio a crear
    public static void crearNuevoSocio(Datos BBDD) {
        // Excepcion para la conversion del numero de tipo String a Int
        int opcion = 0;
        boolean opcionValida = false;
        do {
            String retorno = View.crearNuevoSocioView();
            try {
                opcion = Integer.parseInt(retorno);
            } catch (NumberFormatException error) {
                View.respuestaControllerView("ERROR! Debes insertar un valor numerico valido.");
                continue;
            }
            if (opcion >= 1 && opcion <= 4) {
                opcionValida = true;
            } else {
                View.respuestaControllerView("ERROR! Debes seleccionar una opción valida.");
            }
        } while (!opcionValida);
        // Se carga el formulario de registro de un nuevo socio.
        switch (opcion) {
            case 1:
                crearSocioEstandar(BBDD);
                break;
            case 2:
                crearSocioFederado(BBDD);
                break;
            case 3:
                crearSocioInfantil(BBDD);
                break;
            case 4:
                AppController.gestionSocios(BBDD);
            default:
                AppController.gestionSocios(BBDD);
        }
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
        SeguroModel seguroModel = seguroSocio();
        // Creamos un objeto de tipo llamado socioModel con los datos correctos
        SocioEstandarModel socioModel = new SocioEstandarModel(numeroSocio, nombre, NIF, seguroModel);
        // Enviamos la información al modelo para que añada el socio a la BBDD
        String respuesta = socioModel.crearSocioEstandar(BBDD, socioModel);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta
        // recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios(BBDD);
    }

    public static void modificarSeguroSocioEstandar(Datos BBDD) {
        // Excepcion para la conversion del numero de tipo String a Int
        int numSocio = 0;
        boolean toNumero = false;
        do {
            String[] retorno = View.formModificarTipoSeguroView();
            try {
                numSocio = Integer.parseInt(retorno[0]);
                toNumero = true;
            } catch (NumberFormatException error) {
                View.respuestaControllerView("El numero de socio debe ser un numero: " + error);
            }
        } while (!toNumero);
        // Objetemos el objeto socio estandar (si existe)
        SocioEstandarModel socio = SocioEstandarModel.getSocioEstandar(BBDD, numSocio);
        if (socio != null) {
            View.respuestaControllerView("Socio encontrado para modificar el seguro.");
            SeguroModel seguroModel = seguroSocio();
            String respuesta = socio.actualizarSeguroSocioEstandar(BBDD, seguroModel, socio);
            // Devuelvo la respuespuesta del modelo hacia la vista.
            View.respuestaControllerView(respuesta);
        } else {
            View.respuestaControllerView("No se a podido encontrar el socio.");
        }
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios(BBDD);
    }

    public static void crearSocioFederado(Datos BBDD) {
        // Se llama a la vista para pedir el nombre y el DNI del usuario
        String[] retorno = View.formCrearSocioFederadoView();
        String nombre = retorno[0]; // El primer parametro del array sera el nombre
        String NIF = retorno[1]; // El segundo parametro del array es el DNI
        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); // Número de socio
        // Mandamos el numero de socio a la pantalla:
        View.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
        // Pido el listado de federaciones y el numero de federaciones disponibles;
        String[] listaFederaciones = FederacionModel.obtenerListadoFederacion(BBDD);
        // String[] listado = listaFederaciones[]{};
        int opcionesDiponibles = Integer.parseInt(listaFederaciones[1]);
        // Se genera el control de excepcion para opcion seleccionada no valida.
        int seleccion = 0;
        boolean opcionOk = false;
        do {
            String[] opcion = View.selectorFederacionesView(listaFederaciones[0]);
            try {
                seleccion = Integer.parseInt(opcion[0]);
            } catch (Exception e) {
                View.respuestaControllerView("Opcion no valida, debes introducir un valor numerico.");
                continue;
            }
            if (seleccion <= 0 || seleccion >= opcionesDiponibles) {
                View.respuestaControllerView("Opcion no valida, selecciona una opcion disponible.");
                continue;
            } else {
                opcionOk = true;
                continue;
            }
        } while (!opcionOk);
        // Con este metodo del modelo obtengo el objeto seleccionado por el usuario
        FederacionModel federacion = FederacionModel.obtenerFederacion(BBDD, seleccion);
        // Creamos el objeto con los datos recolectados.
        SocioFederadoModel socio = new SocioFederadoModel(numeroSocio, nombre, NIF, federacion);
        // Enviamos la información al modelo para que añada el socio a la BBDD
        String respuesta = socio.crearSocioFederado(BBDD, socio);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta
        // recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios(BBDD);
    }

    public static void crearSocioInfantil(Datos BBDD) {
        // Se llama a la vista para pedir el nombre
        String[] retorno = View.formCrearSocioInfantilView();
        String nombre = retorno[0];
        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); // Número de socio
        // Mandamos el numero de socio a la pantalla:
        View.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
        // Se genera el control de excepcion para opcion seleccionada no valida.
        boolean codigoOk = false;
        int numeroParental = 0;
        do {
            // Pedimos el codigo del padre
            String[] retornoNun = View.numeroSocioParentalView();
            // Creamos la excepción para verificar el tipo de dato introducido.
            try {
                numeroParental = Integer.parseInt(retornoNun[0]);
            } catch (Exception e) {
                View.respuestaControllerView("Opcion no valida, debes introducir un valor numerico.");
                continue;
            }
            // Creamos la excepción para verificar que el ID socio introducido existe.
            if (!SocioModel.comprobarSocioByCodigo(BBDD, numeroParental)) {
                View.respuestaControllerView("No se a encontrado un socio con ese codigo.");
                continue;
            } else {
                codigoOk = true;
            }
        } while (!codigoOk);
        // Creamos el objeto con los datos recolectados.
        SocioInfantilModel socio = new SocioInfantilModel(numeroSocio, nombre, numeroParental);
        // Enviamos la información al modelo para que añada el socio a la BBDD
        String respuesta = socio.crearSocioInfantil(BBDD, socio);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta
        // recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios(BBDD);
    }

    public static void eliminarSocio(Datos BBDD) {
        String[] listado = SocioModel.listarSociosModel(BBDD);

        View.listadoSociosView(listado[0]);
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
        // Excepcion para la conversion del numero de tipo String a Int y opcion
        // seleccionada valida
        int opcion = 0;
        boolean opcionValida = false;
        do {
            String retorno = View.listadoSociosView();
            try {
                opcion = Integer.parseInt(retorno);
            } catch (NumberFormatException error) {
                View.respuestaControllerView("ERROR! Debes insertar un valor numerico valido.");
                continue;
            }
            if (opcion >= 1 && opcion <= 5) {
                opcionValida = true;
            } else {
                View.respuestaControllerView("ERROR! Debes seleccionar una opción valida.");
            }
        } while (!opcionValida);
        // Se carga el formulario de registro de un nuevo socio.
        switch (opcion) {
            case 1:
                View.respuestaControllerView("\nListado de todos los socios: " + SocioModel.listarSociosModel(BBDD)[0]);
                break;
            case 2:
                View.respuestaControllerView(
                        "\nListado de socios estandar: " + SocioModel.listarSociosEstandarModel(BBDD)[0]);
                break;
            case 3:
                View.respuestaControllerView(
                        "\nListado de socios federados: " + SocioModel.listarSociosFederadosModel(BBDD)[0]);
                break;
            case 4:
                View.respuestaControllerView(
                        "\nListado de socios infantiles: " + SocioModel.listarSociosInfantilesModel(BBDD)[0]);
                break;
            case 5:
                AppController.gestionSocios(BBDD);
                break;
            default:
                AppController.gestionSocios(BBDD);
        }
    }

    public static void facturaMensualSocio(Datos BBDD) {
        int numSocio = 0;
        boolean valoresComprobados = false;
        String tipoSocio = "";
        Double facturacion = 0.0;
        final Double cuotaMensual = 30.00;
        String respuesta = "\nFacturacion del socio: ";
        // Excepciones
        do {
            // Se muestran la vista y se piden datos.
            String retorno = View.formMostrarFacturaMensualSocioView();
            // Se hace el parse de tipo de dato de String a Int.
            try {
                numSocio = Integer.parseInt(retorno);
            } catch (NumberFormatException error) {
                View.respuestaControllerView("Debes insertar un valor númerico valido.");
                continue;
            }
            // Se comprueba que el usuario no quiere salir del metodo y se comprueban otros datos
            if (numSocio == 0) {
                AppController.gestionSocios(BBDD);
                break;
            } else if (SocioModel.comprobarSocioPorNumSocio(BBDD, numSocio)) {
                tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD, numSocio);
                valoresComprobados = true;
            } else {
                View.respuestaControllerView("No se a podido encontrar el socio.");
                continue;
            }
        } while (!valoresComprobados);
        if (tipoSocio == "estandar") {
            // Obtenemos el precio del seguro contratado.
            Double precioSeguro = SocioEstandarModel.getSocioEstandar(BBDD, numSocio).getSeguro().getPrecio();
            respuesta = "\n    - Coste del seguro: " + precioSeguro;
            
            //Se genera el precio final de facturación
            facturacion = cuotaMensual + precioSeguro;
            //Se manda el resultado a la vista
            respuesta = "\n El socio factura "+facturacion+"Euros mensuales.";
        } else if (tipoSocio == "federado") {
            //Aplicamos un despues de la cuota mensual 5%
            Double precioCuotaDescuento = cuotaMensual - (cuotaMensual * 5 / 100);

            //Se genera el precio final de facturación
            facturacion = precioCuotaDescuento;
            //Se manda el resultado a la vista
            respuesta = "El socio factura "+facturacion+"Euros mensuales.";
        } else if (tipoSocio == "infantil") {
            //Aplicamos un descuento de la cuota mensual 50%
            Double precioCuotaDescuento = cuotaMensual - (cuotaMensual * 50 / 100);
            //Se genera el precio final de facturación
            facturacion = precioCuotaDescuento;
            //Se manda el resultado a la vista
            respuesta = "El socio factura "+facturacion+"Euros mensuales.";
        }
        View.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios(BBDD);
    }

    public static SeguroModel seguroSocio() {
        // Tratamiento del seguro
        TipoSeguro tipoSeguro = null;
        Double precioSeguro = null;
        boolean comprobarTipoSeguro = false;
        final Double basico = 25.50, completo = 45.00;
        // Excepciones de tipo de seguro
        do {
            // Se piden los datos del seguro mediante la vista seleccionarSeguroView
            String[] retornoSeguro = View.seleccionarSeguroView();
            // Se hace el tratamiento de los datos retornados desde la vista.
            if (retornoSeguro[0].equals("1")) {
                tipoSeguro = TipoSeguro.BASICO;
                precioSeguro = basico;
                comprobarTipoSeguro = true;
            } else if (retornoSeguro[0].equals("2")) {
                tipoSeguro = TipoSeguro.COMPLETO;
                precioSeguro = completo;
                comprobarTipoSeguro = true;
            } else {
                View.respuestaControllerView("No se ha podido comprobar el tipo de seguro.");
                continue;
            }
        } while (!comprobarTipoSeguro);
        // Genero el objeto de tipo seguro
        SeguroModel seguro = new SeguroModel(tipoSeguro, precioSeguro);
        // Retorno el seguro
        return seguro;
    }
}