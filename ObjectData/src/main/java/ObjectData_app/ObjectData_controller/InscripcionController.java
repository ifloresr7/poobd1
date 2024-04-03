package ObjectData_app.ObjectData_controller;

//Se añade la vista principal
import ObjectData_app.ObjectData_model.InscripcionModel;
import ObjectData_app.ObjectData_model.SocioModel;
import ObjectData_app.ObjectData_view.*;

import ObjectData_app.ObjectData_model.ExcursionModel;

import java.util.Date;
import java.util.Random;

public class InscripcionController {
    static InscripcionesView View = new InscripcionesView();

    // Metodo para crear ID de inscripcion dinamicos
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

    // Metodo para crear una Inscripcion
    public static void crearInscripcion() {

        int numeroSocio = 0;
        int numeroExcursion = 0;
        String retorno = View.formCrearInscripcionView();
        // Comprueba si se cancela la operación
        if (retorno == null || retorno.isEmpty()) {
            View.respuestaControllerView("Operación cancelada.");
            return;
        }

        // Si se solicita crear un nuevo socio
        if (retorno.equals("0")) {
            SocioController.crearNuevoSocio();
            AppController.gestionInscripciones();
            return;
        }

        // Si el retorno no es "N", se supone que contiene el número de socio
        try {
            numeroSocio = Integer.parseInt(retorno);
        } catch (NumberFormatException e) {
            View.respuestaControllerView("Debes introducir un valor númerico.");
            return;
        }

        // Comprueba si el socio existe
        if (!SocioModel.comprobarSocioPorNumSocio(numeroSocio)) {
            View.respuestaControllerView("Socio no encontrado.");
            return;
        }

        // Obtiene y muestra el listado de excursiones
        String[] listadoExcursiones = ExcursionModel.obtenerListadoExcursiones();
        String retornoExcursion = View.formListadoExcursionesView(listadoExcursiones[0]);

        try {
            int opcion = Integer.parseInt(retornoExcursion);
            numeroExcursion = ExcursionModel.obtenerExcursionDesdeLista(opcion).getNumeroExcursion();
        } catch (NumberFormatException e) {
            View.respuestaControllerView("Debes introducir un valor númerico.");
            return;
        }

        // Comprueba si la excursión existe
        if (!ExcursionModel.comprobarExcursionPorNumExcursion(numeroExcursion)) {
            View.respuestaControllerView("Excursión no encontrada.");
            return;
        }

        // Genera un número de inscripción aleatorio
        int numeroInscripcion = Integer.parseInt("9" + generarID());
        View.respuestaControllerView("- Número de inscripción generado: " + numeroInscripcion);

        // Crea la inscripción
        InscripcionModel inscripcion = new InscripcionModel(numeroInscripcion, numeroSocio, numeroExcursion,
                new Date());
        String respuesta = InscripcionModel.crearInscripcion(inscripcion);

        // Muestra la respuesta del modelo
        View.respuestaControllerView(respuesta);
    }

    public static void mostrarInscripcion() {
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
                AppController.gestionInscripciones();
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
                mostrarInscripcionPorSocio();
                break;
            case 2:
                mostrarInscripcionPorFecha();
                break;
        }
    }

    public static void mostrarInscripcionPorSocio() {
        String[] retorno = View.formFiltrarPorSocio();
        if (retorno != null && retorno.length > 0) {
            String numSocio = retorno[0];
            int numeroSocio = Integer.parseInt(numSocio);
            View.respuestaControllerView("\nListado de todas las inscripciones para el socio seleccionado: "
                    + InscripcionModel.obtenerInscripcionesByNumSocio(numeroSocio)[0]);
        } else {
            // Si el usuario no ingresó ningún valor, imprimir un mensaje apropiado
            View.respuestaControllerView("\nNo se ha ingresado ningún número de socio para filtrar.");
        }
    }

    public static void eliminarInscripcion() {

        String[] listadoInscripciones = InscripcionModel.obtenerListadoInscripciones();
        String listado = String.join("\n", listadoInscripciones);
        View.respuestaControllerView("\nListado de todas las inscripciones \n" + listado);
        String retorno = View.formEliminarInscripcionView(listadoInscripciones);
        int num;
        try {
            num = Integer.parseInt(retorno);
        } catch (NumberFormatException e) {
            // Manejar el caso en que el usuario no ingrese un número válido
            View.respuestaControllerView("El número de inscripción ingresado no es válido.");
            return;
        }
        boolean inscripcionEliminada = InscripcionModel.eliminarInscripcionNumero(num);
        if (inscripcionEliminada) {
            View.respuestaControllerView("La inscripción ha sido eliminada exitosamente.");
        } else {
            View.respuestaControllerView("No se pudo eliminar la inscripción. Verifique el número de inscripción y asegúrese de que la fecha de inscripción sea anterior a la fecha de la excursión.");
        }
    }

    public static void mostrarInscripcionPorFecha() {
        String[] retorno = View.formFiltrarPorFechas();
        if (retorno != null && retorno.length == 2) {
            String fechaInicio = retorno[0];
            String fechaFin = retorno[1];
            String[] inscripciones = InscripcionModel.listarInscripcionesFecha(fechaInicio, fechaFin);
            if (inscripciones.length > 0) {
                View.respuestaControllerView("\n Listado de inscripciones por rango de fechas: " + inscripciones[0]);
            } else {
                View.respuestaControllerView(
                        "\n No se encontraron inscripciones para el rango de fechas especificado.");
            }
        } else {
            View.respuestaControllerView("\n Error al obtener las fechas de filtrado.");
        }
    }

}