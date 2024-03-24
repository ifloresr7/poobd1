package ObjectData_app.ObjectData_controller;

import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.ExcursionModel;
import ObjectData_app.ObjectData_view.ExcursionesView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ExcursionController {
    // Se inicializa una vista de ExcursionesView
    static ExcursionesView View = new ExcursionesView();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // Metodo para crear una ID ramdon de 10 digitos
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
    // Esta función sirve para crear una nueva excursión (Debemos importar BBDD, que
    // se inicializó al arranque de APP en main)
    public static void crearExcursion(Datos BBDD) {
        boolean finalizar = false;
        do {
            // Se lanza la vista del menu de crear Excursion
            String[] retorno = View.menuCrearExcursionView();
            // Se comprueba si el usuario quiere salir
            if (retorno[0] == "" || retorno[1] == "" || retorno[2] == "" || retorno[3] == "") {
                View.respuestaControllerView("Operación cancelada.");
                AppController.gestionExcursiones(BBDD);
                continue;
            }
            String descripcion = retorno[0];
            // Se genera la variable para almacenar la fecha y se crea la excepción
            Date date = null;
            try {
                date = sdf.parse(retorno[1]);
            } catch (ParseException e) {
                View.respuestaControllerView("Error: Las fechas introducidas no son validas.");
                continue;
            }
            // Convertir la cadena del número de días a un entero
            int num = 0;
            try {
                num = Integer.parseInt(retorno[2]);
            } catch (NumberFormatException e) {
                // En caso de que el número de días no sea un entero válido
                View.respuestaControllerView("Error: El número de días debe ser un entero válido.");
                continue;
            }
            // Convertir la cadena del precio a un número de punto flotante (double)
            double coste = 0;
            try {
                coste = Double.parseDouble(retorno[3]);
            } catch (NumberFormatException e) {
                // En caso de que el precio no sea un número de punto flotante válido
                View.respuestaControllerView("Error: El precio debe ser un número válido.");
                continue;
            }
            // Método para generar un número de excursión aleatorio
            int numeroExcursion = generarID(); // Número de excursión
            // Mandamos el numero de excursión a la pantalla:
            View.respuestaControllerView("- Número de excursión generado: " + numeroExcursion);
            // Se genera el conjunto de BBDD en la variable excursion
            ExcursionModel excursion = new ExcursionModel(numeroExcursion, descripcion, date, num, coste);
            // Se llama al metodo crearExcursion del modelo ExcursionModel, se pasa tanto la
            // instancia BBDD como el objeto creado
            String respuesta = excursion.crearExcursionModel(BBDD, excursion);
            // Devuelvo la respuesta del modelo y la imprimo en la vista
            View.respuestaControllerView(respuesta);
            finalizar = true;
        } while (!finalizar);
        // Al finalizar vuelvo al menu de excursiones
        AppController.gestionExcursiones(BBDD);
    }

    public static void mostrarExcursionFecha(Datos BBDD) {
        boolean finalizar = false;
        do {
            String[] retorno = View.menuMostarExcursionFechaView();
            // Se comprueba si el usuario queire salir
            if (retorno[0] == "" || retorno[1] == "") {
                View.respuestaControllerView("Operación cancelada.");
                AppController.gestionExcursiones(BBDD);
                break;
            }
            // Se comprueba que existen datos
            if (retorno[0].isEmpty() || retorno[1].isEmpty()) {
                throw new IllegalArgumentException("Error: Debe proporcionar ambas fechas.");
            }
            // Se intenta transformar las fechas y se printan.
            try {
                Date fechaInicio = sdf.parse(retorno[0]);
                Date fechaFin = sdf.parse(retorno[1]);
                String respuesta = ExcursionModel.mostrarExcursiones(BBDD, fechaInicio, fechaFin);
                View.respuestaControllerView(respuesta);
                finalizar = true;
            } catch (Exception e) {
                View.respuestaControllerView("Error: Formato de fecha incorrecto. Debe ser yyyy-MM-dd HH:mm");
            }
        } while (!finalizar);
        // Al finalizar vuelvo al menu de excursiones
        AppController.gestionExcursiones(BBDD);
    }
}