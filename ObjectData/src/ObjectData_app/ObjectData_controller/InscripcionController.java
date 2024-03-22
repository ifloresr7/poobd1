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
            } else if (!SocioModel.comprobarSocioPorNumSocio(BBDD, numeroExcursion)) {
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
                numeroExcursion = Integer.parseInt(retorno);
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
            if(opcion == 0){
                AppController.gestionInscripciones(BBDD);
                break;
            }else if (opcion == 1 || opcion == 2) {
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

    }

    public static void mostrarInscripcionPorFecha() {
        String[] retorno = View.formFiltrarPorFechas();
    }

}