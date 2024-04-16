package ObjectData_app.ObjectData_controller;

import ObjectData_app.ObjectData_model.ExcursionModel;
import ObjectData_app.ObjectData_view.ExcursionesView;
import ObjectData_app.ObjectData_view.MensajeControllerView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ExcursionController {
    // Se inicializan las vistas necesarias.
    static MensajeControllerView RespView = new MensajeControllerView();
    static ExcursionesView ExcuView = new ExcursionesView();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // Metodo para crear una ID random de 8 digitos
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

    // Esta función sirve para crear una nueva excursión (Debemos importar , que
    // se inicializó al arranque de APP en main)
    public static void crearExcursion() {
        String respuesta = null;
        boolean finalizar = false;
        do {
            // Se lanza la vista del menu de crear Excursion
            String[] retorno = ExcuView.menuCrearExcursionView();
            // Se comprueba si el usuario quiere salir
            if (retorno[0].equals("") || retorno[1].equals("") || retorno[2].equals("") || retorno[3].equals("")) {
                RespView.respuestaControllerView("Operación cancelada.");
                AppController.gestionExcursiones();
                continue;
            }
            String descripcion = retorno[0];
            // Se genera la variable para almacenar la fecha y se crea la excepción
            Date date = null;
            try {
                if (retorno[1].matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")) {
                    date = sdf.parse(retorno[1]);
                } else {
                    RespView.excepcionesControllerView("Las fechas y horas introducidas no son válidas. Formato esperado: yyyy-MM-dd HH:mm");
                    continue;
                }
            } catch (ParseException e) {
                RespView.excepcionesControllerView("Error al parsear la fecha: " + e.getMessage());
                continue;
            }
            // Convertir la cadena del número de días a un entero
            int num = 0;
            if (retorno[2].matches("\\d+")) {
                num = Integer.parseInt(retorno[2]);
            } else {
                RespView.excepcionesControllerView("El número de días debe ser un entero válido.");
                continue;
            }
            // Convertir la cadena del precio a un número de punto flotante (double)
            double coste = 0;
            if (retorno[3].matches("\\d+(\\.\\d+)?")) {
                // Verifica si la cadena es un número con o sin parte decimal
                coste = Double.parseDouble(retorno[3]);
            } else {
                // Si no es un número válido, muestra un mensaje de error
                RespView.excepcionesControllerView("El precio debe ser un número válido.");
                continue;
            }
            // Método para generar un numeroExcursion aleatorio
            int numeroExcursion = Integer.parseInt("1" + generarID()); // numeroExcursion
            // Mandamos el numeroExcursion a la pantalla:
            RespView.respuestaControllerView("- Número de excursion generada: " + numeroExcursion);
            // Se genera el conjunto de en la variable excursion
            ExcursionModel excursion = new ExcursionModel(numeroExcursion, descripcion, date, num, coste);
            // Se llama al metodo crearExcursion del modelo ExcursionModel, se pasa tanto la
            // instancia como el objeto creado
            try {
                respuesta = excursion.crearExcursionModel(excursion);
            }catch (SQLException e){
                RespView.excepcionesControllerView(e.getMessage());
            }
            // Devuelvo la respuesta del modelo y la imprimo en la vista
            RespView.respuestaControllerView(respuesta);

            finalizar = true;
        } while (!finalizar);
        // Al finalizar vuelvo al menu de excursiones
        AppController.gestionExcursiones();
    }

    public static void mostrarExcursionFecha() {
        //No se añaden horas aqui porque es mas facil filtrar solo por fechas
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean finalizar = false;
        do {
            String[] retorno = ExcuView.menuMostarExcursionFechaView();
            // Se comprueba si el usuario queire salir
            if (retorno[0].equals("") || retorno[1].equals("")) {
                RespView.respuestaControllerView("Operación cancelada.");
                AppController.gestionExcursiones();
                break;
            }
            // Se intenta transformar las fechas y se printan.
            try {
                Date fechaInicio = sdf.parse(retorno[0]);
                Date fechaFin = sdf.parse(retorno[1]);
                String respuesta = ExcursionModel.mostrarExcursiones(fechaInicio, fechaFin);
                RespView.respuestaControllerView(respuesta);
                finalizar = true;
            } catch (Exception e) {
                RespView.excepcionesControllerView("Formato de fecha y hora incorrectos. Debe ser yyyy-MM-dd");
            }
        } while (!finalizar);
        // Al finalizar vuelvo al menu de excursiones
        AppController.gestionExcursiones();
    }
}