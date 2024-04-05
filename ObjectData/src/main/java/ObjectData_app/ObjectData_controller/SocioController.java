package ObjectData_app.ObjectData_controller;

import ObjectData_app.ObjectData_model.SocioEstandarModel;
import ObjectData_app.ObjectData_model.SocioFederadoModel;
import ObjectData_app.ObjectData_model.SocioInfantilModel;
import ObjectData_app.ObjectData_model.SocioModel;

import java.util.Random;

import ObjectData_app.ObjectData_model.FederacionModel;
import ObjectData_app.ObjectData_model.InscripcionModel;
import ObjectData_app.ObjectData_model.SeguroModel;
import ObjectData_app.ObjectData_model.SeguroModel.TipoSeguro;
import ObjectData_app.ObjectData_view.MensajeControllerView;
//Se añaden las vistas necesarias.
import ObjectData_app.ObjectData_view.SocioView;


public class SocioController {
    // Se inicializan las vistas necasias.
    static MensajeControllerView RespView = new MensajeControllerView();
    static SocioView SociView = new SocioView();

    // Método para generar un número de socio aleatorio
    public static int generarID() {
        Random rand = new Random();
        int id = 0;
        for (int i = 0; i < 8; i++) {
            id = id * 10 + rand.nextInt(9) + 1;
        }
        if (id < 0) {
            return id * -1;
        }
        return id;
    }

    // Metodo para elegir el tipo de socio a crear
    public static void crearNuevoSocio() {
        // Excepcion para la conversion del numero de tipo String a Int
        int opcion = 0;
        boolean opcionValida = false;
        do {
            String retorno = SociView.crearNuevoSocioView();
            try {
                opcion = Integer.parseInt(retorno);
            } catch (NumberFormatException error) {
                RespView.excepcionesControllerView("Debe insertar un valor numerico valido.");
                continue;
            }
            if (opcion >= 1 && opcion <= 4) {
                opcionValida = true;
            } else {
                RespView.excepcionesControllerView("Debe seleccionar una opción valida.");
            }
        } while (!opcionValida);
        // Se carga el formulario de registro de un nuevo socio.
        switch (opcion) {
            case 1:
                crearSocioEstandar();
                break;
            case 2:
                crearSocioFederado();
                break;
            case 3:
                crearSocioInfantil();
                break;
            case 4:
                AppController.gestionSocios();
            default:
                AppController.gestionSocios();
        }
    }

    // Metodo para añadir un socio estandar
    public static void crearSocioEstandar() {
        // Se llama a la vista para pedir el nombre y el DNI del usuario
        String[] retorno = SociView.formCrearSocioEstandarView();
        String nombre = retorno[0]; // El primer parametro del array sera el nombre
        String NIF = retorno[1]; // El segundo parametro del array es el DNI
        if (nombre.isEmpty() || NIF.isEmpty()) {
            RespView.respuestaControllerView("Operación cancelada: El nombre o el NIF no pueden estar vacíos.");
            // No se agrega al socio, así que simplemente retornamos.
            AppController.gestionSocios();
            return;
        } // en otro caso, si las cadenas tienen valor no nulo, entonces generamos el
          // socio.
          // Método para generar un número de socio aleatorio
        int numeroSocio = Integer.parseInt("5" + generarID()); // Número de socio
        // Mandamos el numero de socio a la pantalla:
        RespView.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
        // Pido el seguro
        SeguroModel seguroModel = seguroSocio();
        // Creamos un objeto de tipo llamado socioModel con los datos correctos
        SocioEstandarModel socioModel = new SocioEstandarModel(numeroSocio, nombre, NIF, seguroModel);
        // Enviamos la información al modelo para que añada el socio a la
        String respuesta = socioModel.crearSocioEstandar(socioModel);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta
        // recibida por parte modelo al controlador hacia la vista.
        RespView.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios();
    }

    public static void modificarSeguroSocioEstandar() {
        // Excepcion para la conversion del numero de tipo String a Int
        int numSocio = 0;

        String[] retorno = SociView.formModificarTipoSeguroView();
        // Verifica si el retorno es un número entero
        if (retorno[0].matches("\\d+")) {
            numSocio = Integer.parseInt(retorno[0]);
        } else {
            // Si no es un número entero, muestra un mensaje de error
            RespView.respuestaControllerView("El número de socio debe ser un numero.");
        }
        // Objetemos el objeto socio estandar (si existe)
        SocioEstandarModel socio = SocioEstandarModel.getSocioEstandar(numSocio);
        if (socio != null) {
            RespView.respuestaControllerView("Socio encontrado para modificar el seguro.");
            SeguroModel seguroModel = seguroSocio();
            String respuesta = socio.actualizarSeguroSocioEstandar(seguroModel, socio);
            // Devuelvo la respuespuesta del modelo hacia la vista.
            RespView.respuestaControllerView(respuesta);
        } else {
            RespView.excepcionesControllerView("No se ha podido encontrar el socio.");
        }
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios();
    }

    public static void crearSocioFederado() {

        // Se llama a la vista para pedir el nombre y el DNI del usuario
        String[] retorno = SociView.formCrearSocioFederadoView();
        String nombre = retorno[0]; // El primer parametro del array sera el nombre
        String NIF = retorno[1]; // El segundo parametro del array es el DNI
        if (nombre.isEmpty() || NIF.isEmpty()) {
            RespView.respuestaControllerView("Operación cancelada.");
            // No se agrega al socio, así que simplemente retornamos.
            AppController.gestionSocios();
            return;
        }
        // Método para generar un número de socio aleatorio
        int numeroSocio = Integer.parseInt("6" + generarID()); // Número de socio
        // Mandamos el numero de socio a la pantalla:
        RespView.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
        // Pido el listado de federaciones y el numero de federaciones disponibles;
        String[] listaFederaciones = FederacionModel.obtenerListadoFederacion();
        int opcionesDiponibles = Integer.parseInt(listaFederaciones[1]);
        // Se genera el control de excepcion para opcion seleccionada no valida.
        int seleccion = 0;
        boolean opcionOk = false;
        do {
            String[] opcion = SociView.selectorFederacionesView(listaFederaciones[0]);
            // Verifica si la opción es un número entero
            if (opcion[0].matches("\\d+")) {
                seleccion = Integer.parseInt(opcion[0]);
            } else {
                // Si no es un número entero, muestra un mensaje de error
                RespView.excepcionesControllerView("Opcion no valida, debe introducir un valor numerico.");
                continue;
            }
            if (seleccion <= 0 || seleccion >= opcionesDiponibles) {
                RespView.excepcionesControllerView("Opcion no valida, seleccione una opcion disponible.");
                continue;
            } else {
                opcionOk = true;
                continue;
            }
        } while (!opcionOk);
        // Con este metodo del modelo obtengo el objeto seleccionado por el usuario
        FederacionModel federacion = FederacionModel.obtenerFederacion(seleccion);
        // Creamos el objeto con los datos recolectados.
        SocioFederadoModel socio = new SocioFederadoModel(numeroSocio, nombre, NIF, federacion);
        // Enviamos la información al modelo para que añada el socio a la
        String respuesta = socio.crearSocioFederado(socio);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta
        // recibida por parte modelo al controlador hacia la vista.
        RespView.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios();
    }

    public static void crearSocioInfantil() {
        // Se llama a la vista para pedir el nombre
        String[] retorno = SociView.formCrearSocioInfantilView();
        String nombre = retorno[0];
        if (nombre.isEmpty()) {
            RespView.respuestaControllerView("Operación cancelada. \n");
            // No se agrega al socio, así que simplemente retornamos.
            AppController.gestionSocios();
            return;
        }
        // Método para generar un número de socio aleatorio
        int numeroSocio = Integer.parseInt("7" + generarID()); // Número de socio
        // Mandamos el numero de socio a la pantalla:

        // Se genera el control de excepcion para opcion seleccionada no valida.

        int numeroParental = 0;

        // Pedimos el codigo del padre
        String[] retornoNun = SociView.numeroSocioParentalView();
        // Creamos la excepción para verificar el tipo de dato introducido.
        if (retornoNun[0].isEmpty()) {
            RespView.respuestaControllerView("Operación cancelada.");
            return;
        }
        // Verifica si la opción es un número entero
        if (retornoNun[0].matches("\\d+")) {
            numeroParental = Integer.parseInt(retornoNun[0]);
        } else {
            // Si no es un número entero, muestra un mensaje de error
            RespView.excepcionesControllerView("Opcion no valida, debe introducir un valor numerico.");
        }
        // Creamos la excepción para verificar que el ID socio introducido existe.
        if (!SocioModel.comprobarSocioPorNumSocio(numeroParental)) {
            RespView.excepcionesControllerView("No se ha encontrado un socio con ese código.");
            return;
        }

        RespView.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
        // Creamos el objeto con los datos recolectados.
        SocioInfantilModel socio = new SocioInfantilModel(numeroSocio, nombre, numeroParental);
        // Enviamos la información al modelo para que añada el socio a la
        String respuesta = socio.crearSocioInfantil(socio);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta
        // recibida por parte modelo al controlador hacia la vista.
        RespView.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios();
    }

    public static void eliminarSocio() {
        try {
            String[] retorno = SociView.formEliminarSocioView();
            String numeroSocio = retorno[0];

            // Verificar si la cadena numeroSocio está vacía o nula
            if (numeroSocio == null || numeroSocio.isEmpty()) {
                RespView.respuestaControllerView("Operación cancelada.");
                AppController.gestionSocios();
                return;
            }

            int numeroSocioInt = Integer.parseInt(numeroSocio);

            // Verificar si el socio está inscrito en alguna excursión
            boolean inscritoEnExcursion = InscripcionModel.comprobarSocioInscrito(numeroSocioInt);
            if (inscritoEnExcursion) {
                // Mostrar mensaje de que el socio está inscrito en una excursión y no puede ser
                // eliminado
                RespView.respuestaControllerView("El socio con número de socio " + numeroSocio
                        + " está inscrito en una excursión y no puede ser eliminado.");
                AppController.gestionSocios();
                return;
            }

            // Verificar el tipo de socio y llamar al método eliminar correspondiente
            String tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(numeroSocioInt);
            boolean eliminado = false;

            if (tipoSocio == "Estandar") {
                eliminado = SocioEstandarModel.eliminarSocioModel(numeroSocioInt);
            } else if (tipoSocio.equals("Federado")) {
                eliminado = SocioFederadoModel.eliminarSocioModel(numeroSocioInt);
            } else if (tipoSocio.equals("Infantil")) {
                eliminado = SocioInfantilModel.eliminarSocioModel(numeroSocioInt);
            }

            if (eliminado) {
                RespView.respuestaControllerView("El socio con número de socio " + numeroSocio + " ha sido eliminado correctamente.");
            } else {
                RespView.excepcionesControllerView("No se pudo eliminar el socio con número de socio " + numeroSocio
                        + ". El socio no existe en la base de datos.");
            }
        } catch (NumberFormatException e) {
            RespView.excepcionesControllerView("Ingrese un número válido para el número de socio.");
        } catch (Exception e) {
            RespView.excepcionesControllerView("Problema al eliminar el socio.");
            e.printStackTrace();
        }
    }

    public static void mostrarSocio() {
        // Excepcion para la conversion del numero de tipo String a Int y opcion
        // seleccionada valida
        int opcion = 0;
        boolean opcionValida = false;
        do {
            String retorno = SociView.listadoSociosView();
            // Verifica si el retorno es un número entero
            if (retorno.matches("\\d+")) {
                opcion = Integer.parseInt(retorno);
            } else {
                // Si no es un número entero, muestra un mensaje de error
                RespView.excepcionesControllerView("Debe insertar un valor numerico valido.");
                continue;
            }
            if (opcion >= 1 && opcion <= 5) {
                opcionValida = true;
            } else {
                RespView.excepcionesControllerView("Debe seleccionar una opción valida.");
            }
        } while (!opcionValida);
        // Se carga el formulario de registro de un nuevo socio.
        switch (opcion) {
            case 1:
                RespView.respuestaControllerView("Listado de todos los socios: " + SocioModel.listarSociosModel()[0]);
                break;
            case 2:
                RespView.respuestaControllerView(
                        "\nListado de socios estandar: " + SocioModel.listarSociosEstandarModel()[0]);
                break;
            case 3:
                RespView.respuestaControllerView(
                        "\nListado de socios federados: " + SocioModel.listarSociosFederadosModel()[0]);
                break;
            case 4:
                RespView.respuestaControllerView(
                        "\nListado de socios infantiles: " + SocioModel.listarSociosInfantilesModel()[0]);
                break;
            case 5:
                AppController.gestionSocios();
                break;
            default:
                AppController.gestionSocios();
        }
    }

    public static void facturaMensualSocio() {
        int numSocio = 0;
        boolean valoresComprobados = false;
        String tipoSocio = "";
        Double facturacion = 0.0;
        final Double cuotaMensual = 10.00;
        String respuesta = "\nFacturación del socio: ";
        //Comprobación de datos
        do {
            // Se muestran la vista y se piden datos.
            String retorno = SociView.formMostrarFacturaMensualSocioView();
            // Verifica si el retorno es un número entero
            if (retorno.matches("\\d+")) {
                numSocio = Integer.parseInt(retorno);
            } else {
                // Si no es un número entero, muestra un mensaje de error
                RespView.excepcionesControllerView("Debe insertar un valor númerico válido.");
                continue;
            }
            // Se comprueba que el usuario no quiere salir del método y se comprueban datos
            if (numSocio == 0) {
                AppController.gestionSocios();
                break;
            } else if (SocioModel.comprobarSocioPorNumSocio(numSocio)) {
                tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(numSocio);
                valoresComprobados = true;
            } else {
                RespView.excepcionesControllerView("No se ha podido encontrar el socio.");
                continue;
            }
        } while (!valoresComprobados);
        if (tipoSocio.equals("Estandar")) {
            // Coste de la cuota
            respuesta += "\n    - Coste de la cuota: " + cuotaMensual + " euros.";
            // Obtenemos el precio del seguro contratado.
            Double precioSeguro = SocioEstandarModel.getSocioEstandar(numSocio).getSeguro().getPrecio();
            // Obtener listado de escursiones y precio:
            String[] retorno = InscripcionModel.obtenerInscripcionesByNumSocio(numSocio);
            respuesta += retorno[0];
            // Precio del seguro.
            respuesta += "\n    - Coste del seguro: " + precioSeguro + " euros.";
            // Se genera el precio final de facturación
            facturacion = cuotaMensual + precioSeguro + Double.parseDouble(retorno[1]);
            // Se manda el resultado a la vista
            respuesta += "\n El socio factura " + facturacion + " euros mensuales.";
        } else if (tipoSocio.equals("Federado")) {
            // Aplicamos un despues de la cuota mensual 5%
            Double precioCuotaDescuento = cuotaMensual - (cuotaMensual * 5 / 100);
            // Obtener listado de escursiones y precio:
            String[] retorno = InscripcionModel.obtenerInscripcionesByNumSocio(numSocio);
            respuesta += retorno[0];
            // Se calcula el descuento de las escursiones de este socio. 10%
            Double descuentoExcursiones = Double.parseDouble(retorno[1]) - (Double.parseDouble(retorno[1]) * 10 / 100);
            // Se genera el precio final de facturación
            facturacion = precioCuotaDescuento + descuentoExcursiones;
            // Se manda el resultado a la vista
            respuesta += "\n El socio factura " + facturacion
                    + " euros mensuales. (10% de dto. incluido en el precio final.)";
        } else if (tipoSocio.equals("Infantil")) {
            // Aplicamos un descuento de la cuota mensual 50%
            Double precioCuotaDescuento = cuotaMensual - (cuotaMensual * 50 / 100);
            respuesta += "\n    - Coste de la cuota: " + precioCuotaDescuento + " euros.";
            // Obtener listado de escursiones y precio:
            String[] retorno = InscripcionModel.obtenerInscripcionesByNumSocio(numSocio);
            respuesta += retorno[0];
            // Se genera el precio final de facturación
            facturacion = precioCuotaDescuento + Double.parseDouble(retorno[1]);
            // Se manda el resultado a la vista
            respuesta += "\n El socio factura " + facturacion + " euros mensuales.";
        }
        RespView.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios();
    }

    public static SeguroModel seguroSocio() {
        // Tratamiento del seguro
        TipoSeguro tipoSeguro = null;
        boolean comprobarTipoSeguro = false;
        // Excepciones de tipo de seguro
        do {
            // Se piden los datos del seguro mediante la vista seleccionarSeguroView
            String[] retornoSeguro = SociView.seleccionarSeguroView();
            // Se hace el tratamiento de los datos retornados desde la vista.
            if (retornoSeguro[0].isEmpty()){
                AppController.gestionSocios();
                break;
            }else if (retornoSeguro[0].equals("1")) {
                tipoSeguro = TipoSeguro.BASICO;
                comprobarTipoSeguro = true;
            } else if (retornoSeguro[0].equals("2")) {
                tipoSeguro = TipoSeguro.COMPLETO;
                comprobarTipoSeguro = true;
            } else {
                RespView.excepcionesControllerView("No se ha podido comprobar el tipo de seguro.");
                continue;
            }
        } while (!comprobarTipoSeguro);
        // Genero el objeto de tipo seguro
        SeguroModel seguro = new SeguroModel(tipoSeguro);
        // Retorno el seguro
        return seguro;
    }
}