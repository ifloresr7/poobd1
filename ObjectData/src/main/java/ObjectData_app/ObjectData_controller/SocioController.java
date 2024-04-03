package ObjectData_app.ObjectData_controller;

import ObjectData_app.ObjectData_model.SocioEstandarModel;
import ObjectData_app.ObjectData_model.SocioFederadoModel;
import ObjectData_app.ObjectData_model.SocioInfantilModel;
import ObjectData_app.ObjectData_model.SocioModel;

import java.util.Random;

import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.FederacionModel;
import ObjectData_app.ObjectData_model.InscripcionModel;
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
                View.respuestaControllerView("¡ERROR! Debe insertar un valor numerico valido.");
                continue;
            }
            if (opcion >= 1 && opcion <= 4) {
                opcionValida = true;
            } else {
                View.respuestaControllerView("¡ERROR! Debe seleccionar una opción valida.");
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
        if (nombre.isEmpty() || NIF.isEmpty()) {
            View.respuestaControllerView("Operación cancelada: El nombre o el NIF no pueden estar vacíos. \n");
            // No se agrega al socio, así que simplemente retornamos.
            AppController.gestionSocios(BBDD);
            return;
        } // en otro caso, si las cadenas tienen valor no nulo, entonces generamos el
          // socio.
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

        String[] retorno = View.formModificarTipoSeguroView();
        try {
            numSocio = Integer.parseInt(retorno[0]);

        } catch (NumberFormatException error) {
            View.respuestaControllerView("El numero de socio debe ser un numero.\n");
            return;
        }

        // Objetemos el objeto socio estandar (si existe)
        SocioEstandarModel socio = SocioEstandarModel.getSocioEstandar(BBDD, numSocio);
        if (socio != null) {
            View.respuestaControllerView("Socio encontrado para modificar el seguro.");
            SeguroModel seguroModel = seguroSocio();
            String respuesta = socio.actualizarSeguroSocioEstandar(BBDD, seguroModel, socio);
            // Devuelvo la respuespuesta del modelo hacia la vista.
            View.respuestaControllerView(respuesta);
        } else {
            View.respuestaControllerView("No se ha podido encontrar el socio.");
        }
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios(BBDD);
    }

    public static void crearSocioFederado(Datos BBDD) {

        // Se llama a la vista para pedir el nombre y el DNI del usuario
        String[] retorno = View.formCrearSocioFederadoView();
        String nombre = retorno[0]; // El primer parametro del array sera el nombre
        String NIF = retorno[1]; // El segundo parametro del array es el DNI
        if (nombre.isEmpty() || NIF.isEmpty()) {
            View.respuestaControllerView("Operación cancelada: El nombre o el NIF no pueden estar vacíos. \n");
            // No se agrega al socio, así que simplemente retornamos.
            AppController.gestionSocios(BBDD);
            return;
        }
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
                View.respuestaControllerView("Opcion no valida, debe introducir un valor numerico.");
                continue;
            }
            if (seleccion <= 0 || seleccion >= opcionesDiponibles) {
                View.respuestaControllerView("Opcion no valida, seleccione una opcion disponible.");
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
        if (nombre.isEmpty()) {
            View.respuestaControllerView("Operación cancelada: El nombre no puede estar vacío. \n");
            // No se agrega al socio, así que simplemente retornamos.
            AppController.gestionSocios(BBDD);
            return;
        }
        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); // Número de socio
        // Mandamos el numero de socio a la pantalla:

        // Se genera el control de excepcion para opcion seleccionada no valida.

        int numeroParental = 0;

        // Pedimos el codigo del padre
        String[] retornoNun = View.numeroSocioParentalView();
        // Creamos la excepción para verificar el tipo de dato introducido.
        if (retornoNun[0].isEmpty()) {
            View.respuestaControllerView("Operación cancelada.");
            return;
        }
        try {
            numeroParental = Integer.parseInt(retornoNun[0]);
        } catch (Exception e) {
            View.respuestaControllerView("Opcion no valida, debe introducir un valor numerico.");

        }

        // Creamos la excepción para verificar que el ID socio introducido existe.
        if (!SocioModel.comprobarSocioPorNumSocio(BBDD, numeroParental)) {
            View.respuestaControllerView("No se ha encontrado un socio con ese código.");
            return;
        }

        View.respuestaControllerView("- Numero de socio generado: " + numeroSocio);
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
        try {
            String[] retorno = View.formEliminarSocioView();
            String numeroSocio = retorno[0];

                // Verificar si la cadena numeroSocio está vacía o nula
            if (numeroSocio == null || numeroSocio.isEmpty()) {
                View.respuestaControllerView("Operación cancelada: El numero de socio no puede estar vacío. \n");
                AppController.gestionSocios(BBDD);
                return;
            }

            int numeroSocioInt = Integer.parseInt(numeroSocio);

            // Verificar si el socio está inscrito en alguna excursión
            boolean inscritoEnExcursion = InscripcionModel.comprobarSocioInscrito(BBDD, numeroSocioInt);
            if (inscritoEnExcursion) {
                // Mostrar mensaje de que el socio está inscrito en una excursión y no puede ser
                // eliminado
                View.respuestaControllerView("El socio con número de socio " + numeroSocio
                        + " está inscrito en una excursión y no puede ser eliminado.");
                AppController.gestionSocios(BBDD);        
                return;
            }

            // Verificar el tipo de socio y llamar al método eliminar correspondiente
            String tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD, numeroSocioInt);
            boolean eliminado = false;

            if (tipoSocio.equals("Estandar")) {
                eliminado = SocioEstandarModel.eliminarSocioModel(BBDD, numeroSocioInt);
            } else if (tipoSocio.equals("Federado")) {
                eliminado = SocioFederadoModel.eliminarSocioModel(BBDD, numeroSocioInt);
            } else if (tipoSocio.equals("Infantil")) {
                eliminado = SocioInfantilModel.eliminarSocioModel(BBDD, numeroSocioInt);
            }

            if (eliminado) {
                View.respuestaControllerView(
                        "El socio con número de socio " + numeroSocio + " ha sido eliminado correctamente.");
            } else {
                View.respuestaControllerView("No se pudo eliminar el socio con número de socio " + numeroSocio
                        + ". El socio no existe en la base de datos.");
            }
        } catch (NumberFormatException e) {
            View.respuestaControllerView("Error: Ingrese un número válido para el número de socio.");
        } catch (Exception e) {
            View.respuestaControllerView("Error al eliminar el socio.");
            e.printStackTrace();
        }
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
                View.respuestaControllerView("¡ERROR! Debe insertar un valor numerico valido.");
                continue;
            }
            if (opcion >= 1 && opcion <= 5) {
                opcionValida = true;
            } else {
                View.respuestaControllerView("¡ERROR! Debe seleccionar una opción valida.");
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
        final Double cuotaMensual = 10.00;
        String respuesta = "\nFacturación del socio: ";
        // Excepciones
        do {
            // Se muestran la vista y se piden datos.
            String retorno = View.formMostrarFacturaMensualSocioView();

            // Verifica si el retorno es nulo o vacío (usuario presionó solo Enter)
            if (retorno == null || retorno.trim().isEmpty()) {
                View.respuestaControllerView("Operación cancelada.");
                AppController.gestionSocios(BBDD);
                return;
            }
            
            // Se hace el parse de tipo de dato de String a Int.
            try {
                numSocio = Integer.parseInt(retorno);
            } catch (NumberFormatException error) {
                View.respuestaControllerView("Debe insertar un valor númerico válido.");
                continue;
            }
            // Se comprueba que el usuario no quiere salir del método y se comprueban otros
            // datos
            if (numSocio == 0) {
                AppController.gestionSocios(BBDD);
                break;
            } else if (SocioModel.comprobarSocioPorNumSocio(BBDD, numSocio)) {
                tipoSocio = SocioModel.obtenerTipoSocioPorNumSocio(BBDD, numSocio);
                valoresComprobados = true;
            } else {
                View.respuestaControllerView("No se ha podido encontrar el socio.");
                continue;
            }
        } while (!valoresComprobados);
        if (tipoSocio.equals("Estandar")) {
            // Coste de la cuota
            respuesta += "\n    - Coste de la cuota: " + cuotaMensual + " euros.";
            // Obtenemos el precio del seguro contratado.
            Double precioSeguro = SocioEstandarModel.getSocioEstandar(BBDD, numSocio).getSeguro().getPrecio();
            // Obtener listado de escursiones y precio:
            String[] retorno = InscripcionModel.obtenerInscripcionesByNumSocio(BBDD, numSocio);
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
            String[] retorno = InscripcionModel.obtenerInscripcionesByNumSocio(BBDD, numSocio);
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
            String[] retorno = InscripcionModel.obtenerInscripcionesByNumSocio(BBDD, numSocio);
            respuesta += retorno[0];
            // Se genera el precio final de facturación
            facturacion = precioCuotaDescuento + Double.parseDouble(retorno[1]);
            // Se manda el resultado a la vista
            respuesta += "\n El socio factura " + facturacion + " euros mensuales.";
        }
        View.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionSocios(BBDD);
    }

    public static SeguroModel seguroSocio() {
        // Tratamiento del seguro
        TipoSeguro tipoSeguro = null;
        boolean comprobarTipoSeguro = false;
        // Excepciones de tipo de seguro
        do {
            // Se piden los datos del seguro mediante la vista seleccionarSeguroView
            String[] retornoSeguro = View.seleccionarSeguroView();
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
        } while (!comprobarTipoSeguro);
        // Genero el objeto de tipo seguro
        SeguroModel seguro = new SeguroModel(tipoSeguro);
        // Retorno el seguro
        return seguro;
    }
}