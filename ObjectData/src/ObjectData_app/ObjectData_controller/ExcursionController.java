package ObjectData_app.ObjectData_controller;

//Se añade la vista principal
import java.util.UUID;

import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.ExcursionModel;
import ObjectData_app.ObjectData_model.FederacionModel;
import ObjectData_app.ObjectData_model.InscripcionModel;
import ObjectData_app.ObjectData_view.ExcursionesView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ExcursionController {
    // Se inicializa una vista de ExcursionesView
    static ExcursionesView View = new ExcursionesView();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // Metodo para crear una ID ramdon
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
        // Se lanza la vista del menu de crear Excursion
        String[] retorno = View.menuCrearExcursionView();
        // El retorno desde la vista: return new String[]
        // {descripcion,fecha,numDias,precio}; // Lo pasamos a variables
        String descripcion = retorno[0];
        String numDias = retorno[2];
        String precio = retorno[3];

        // Se usa para obtener un ID dinamico para la excursion
        int numeroExcursion = generarID();
        // Se genera la variable para almacenar la fecha y se crea la excepción
        Date date = null;
        try {
            date = sdf.parse(retorno[1]);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente
        }

        // Convertir la cadena del número de días a un entero
        int num = 0;
        try {
            num = Integer.parseInt(numDias);
        } catch (NumberFormatException e) {
            // En caso de que el número de días no sea un entero válido
            View.respuestaControllerView("Error: El número de días debe ser un entero válido.");
        }

        // Convertir la cadena del precio a un número de punto flotante (double)
        double coste = 0;
        try {
            coste = Double.parseDouble(precio);
        } catch (NumberFormatException e) {
            // En caso de que el precio no sea un número de punto flotante válido
            View.respuestaControllerView("Error: El precio debe ser un número válido.");
        }

        // Se genera el conjunto de BBDD en la variable excursion
        ExcursionModel excursion = new ExcursionModel(numeroExcursion, descripcion, date, num, coste);
        // Se llama al metodo crearExcursion del modelo ExcursionModel, se pasa tanto la
        // instancia BBDD como el objeto creado
        String respuesta = excursion.crearExcursionModel(BBDD, excursion);
        // Devuelvo la respuesta del modelo y la imprimo en la vista
        View.respuestaControllerView(respuesta);
    }

    public static void mostrarExcursionFecha(Datos BBDD) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] retorno = View.menuMostarExcursionFechaView();

        try {
            if (retorno[0].isEmpty() || retorno[1].isEmpty()) {
                throw new IllegalArgumentException("Error: Debe proporcionar ambas fechas.");
            }

            Date fechaInicio = sdf.parse(retorno[0]);
            Date fechaFin = sdf.parse(retorno[1]);

            String respuesta = ExcursionModel.mostrarExcursiones(BBDD, fechaInicio, fechaFin);
            View.respuestaControllerView(respuesta);

        } catch (ParseException | IllegalArgumentException e) {
            // En caso de error de formato de fecha o falta de fechas
            if (e instanceof ParseException) {
                View.respuestaControllerView("Error: Formato de fecha incorrecto. Debe ser yyyy-MM-dd");
            } else {
                View.respuestaControllerView("Error: " + e.getMessage());
            }
        }
    }
}