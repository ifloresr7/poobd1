package ObjectData_app.ObjectData_controller;

//Se añade la vista principal
import ObjectData_app.ObjectData_model.InscripcionModel;
import ObjectData_app.ObjectData_model.SocioModel;
import ObjectData_app.ObjectData_view.InscripcionesView;
import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.ExcursionModel;

import java.util.Date;
import java.util.Random;

public class InscripcionController {
    static InscripcionesView View = new InscripcionesView();

    // Metodo para crear ID de inscripcion dinamicos
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

    // Metodo para crear una Inscripcion
    public static void crearInscripcion(Datos BBDD) {
        boolean valoresComprobados = false;
        int numeroSocio = 0;
        int numeroExcursion = 0;
        do {
            String retorno = View.formCrearInscripcionView();
            try {
                numeroSocio = Integer.parseInt(retorno);
            } catch (Exception e) {
                View.respuestaControllerView("Debes introducir un valor númerico.");
                continue;
            }
            // Compruebo que el usuario de la app no quiera Cancelar y salir.
            if (numeroSocio == 0) {
                AppController.gestionInscripciones(BBDD);
                break;
            } else if (!SocioModel.comprobarSocioPorNumSocio(BBDD, numeroSocio)) {
                View.respuestaControllerView("Socio no encontrado.");
                continue;
            } else {
                valoresComprobados = true;
            }
        } while (!valoresComprobados);
        // Reseteo el estado de valoresComprobados para realizar otra excepcion
        valoresComprobados = false;
        String[] listadoExcursiones = ExcursionModel.obtenerListadoExcursiones(BBDD);
        do {
            String retorno = View.formListadoExcursionesView(listadoExcursiones[0]);
            try {
                int opcion = Integer.parseInt(retorno);
                numeroExcursion = ExcursionModel.obtenerExcursion(BBDD, opcion).getNumeroExcursion();
            } catch (Exception e) {
                View.respuestaControllerView("Debes introducir un valor númerico.");
                continue;
            }
            // Compruebo que el usuario de la app no quiera Cancelar y salir.
            if (numeroExcursion == 0) {
                AppController.gestionInscripciones(BBDD);
                break;
            } else if (!ExcursionModel.comprobarExcursionPorNumExcursion(BBDD, numeroExcursion)) {
                View.respuestaControllerView("Excursión no encontrada.");
                continue;
            } else {
                valoresComprobados = true;
            }
        } while (!valoresComprobados);
        // Método para generar un número de socio aleatorio
        int numeroInscripcion = generarID(); // Número de socio
        // Mandamos el numero de inscripcion generado a la pantalla:
        View.respuestaControllerView("- Número de inscripción generado: " + numeroInscripcion);
        // Creamos un objeto de tipo llamado inscripcion con los datos correctos
        InscripcionModel inscripcion = new InscripcionModel(numeroInscripcion, numeroSocio, numeroExcursion, new Date());
        // Enviamos la información al modelo para que añada la inscripción a la BBDD
        String respuesta = InscripcionModel.crearInscripcion(BBDD, inscripcion);
        // Una vez que el modelo responde confirmando la acción, enviamos la respuesta
        // recibida por parte modelo al controlador hacia la vista.
        View.respuestaControllerView(respuesta);
        // Volvemos al menu principal de la gestión de los socios.
        AppController.gestionInscripciones(BBDD);
    }

    public static void eliminarInscripcion(Datos BBDD) {

    }

    public static void mostrarInscripcion(Datos BBDD) {
        boolean valoresComprobados = false;
        int opcion = 0;
        do {
            String retorno = View.formMostrarInscripcionView();
            try {
                opcion = Integer.parseInt(retorno);
            } catch (Exception e) {
                View.respuestaControllerView("Debes introducir un valor númerico.");
                continue;
            }
            if (opcion == 0) {
                AppController.gestionInscripciones(BBDD);
                break;
            } else if (opcion == 1 || opcion == 2) {
                valoresComprobados = true;
            } else {
                View.respuestaControllerView("Debes selecciona una opcion valida.");
                continue;
            }
        } while (!valoresComprobados);
        switch (opcion) {
            case 1:
                mostrarInscripcionPorSocio(BBDD);
                break;
            case 2:
                mostrarInscripcionPorFecha(BBDD);
                break;
        }
    }

    public static void mostrarInscripcionPorSocio(Datos BBDD) {
        String[] retorno = View.formFiltrarPorSocio();
        View.respuestaControllerView("\nListado de todas las inscripciones para el socio seleccionado: " + InscripcionModel.listarInscripciones(BBDD)[0]);
    }

    public static void mostrarInscripcionPorFecha(Datos BBDD) {
        String[] retorno = View.formFiltrarPorFechas();
        if (retorno != null && retorno.length == 2) {
            String fechaInicio = retorno[0];
            String fechaFin = retorno[1];
            String[] inscripciones = InscripcionModel.listarInscripcionesFecha(BBDD, fechaInicio, fechaFin);
            if (inscripciones != null && inscripciones.length > 0) {
                View.respuestaControllerView("\n Listado de inscripciones por rango de fechas: " + inscripciones[0]);
            } else {
                View.respuestaControllerView("\n No se encontraron inscripciones para el rango de fechas especificado.");
            }
        } else {
            View.respuestaControllerView("\n Error al obtener las fechas de filtrado.");
        }
    }

}