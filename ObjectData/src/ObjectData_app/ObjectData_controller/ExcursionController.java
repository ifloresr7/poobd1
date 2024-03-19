package ObjectData_app.ObjectData_controller;
//Se añade la vista principal
import java.util.UUID;


import ObjectData_app.ObjectData_model.ExcursionModel;
import ObjectData_app.ObjectData_view.ExcursionesView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ObjectData_app.ObjectData_controller.AppController;

import static ObjectData_app.ObjectData_controller.AppController.datos;

public class ExcursionController{
    static ExcursionesView View = new ExcursionesView();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    //Inicio del menu de la APP.
    public static String obtenerIdExcursion() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 10); // Tomar los primeros 10 caracteres
    }


    public static void addExcursion(String descripcion,String fecha,String numDias,String precio) {
        // Convertir la cadena de fecha a un objeto Date
        String numeroExcursion = obtenerIdExcursion();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente
        }

        // Convertir la cadena del número de días a un entero
        int num = Integer.parseInt(numDias);

        // Convertir la cadena del precio a un número de punto flotante (double)
        double coste = Double.parseDouble(precio);

        ExcursionModel excursion = new ExcursionModel(numeroExcursion, descripcion, data, num, coste);
        datos.getExcursiones().add(excursion);

    }
    public static void mostrarExcursionFecha(String fechaIni, String fechaFin) {
        Date fechaI = null;
        Date fechaF = null;
        try {
            fechaI = sdf.parse(fechaIni);
            System.out.println("Fecha ingresada: " + sdf.format(fechaI));
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Utilice el formato yyyy-MM-dd.");
        }
        try {
            fechaF = sdf.parse(fechaFin);
            System.out.println("Fecha ingresada: " + sdf.format(fechaF));
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Utilice el formato yyyy-MM-dd.");
        }
        for (ExcursionModel excursion : datos.getExcursiones()) {
            Date fechaExcursion = excursion.getFecha();
            if (fechaExcursion.after(fechaF) && fechaExcursion.before(fechaI)) {
                // La excursión está dentro del rango de fechas, así que la mostramos usando su método toString
                System.out.println(excursion);

                // Indicamos que se encontró al menos una excursión dentro del rango
            }
        }

    }
}