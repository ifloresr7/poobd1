package ObjectData_app.ObjectData_controller;
//Se añade la vista principal
import java.util.UUID;

import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.ExcursionModel;
import ObjectData_app.ObjectData_view.ExcursionesView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcursionController{
    //Se inicializa una vista de ExcursionesView
    static ExcursionesView View = new ExcursionesView();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    //Metodo para crear una ID ramdon
    public static String obtenerIdExcursion() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 10); // Tomar los primeros 10 caracteres
    }
    //Esta función sirve para crear una nueva excursión (Debemos importar BBDD, que se inicializó al arranque de APP en main)
    public static void crearExcursion(Datos BBDD) {
        //Se lanza la vista del menu de crear Excursion
        String[] retorno = View.menuCrearExcursionView();
        //El retorno desde la vista: return new String[] {descripcion,fecha,numDias,precio}; // Lo pasamos a variables 
        String descripcion = retorno[0];
        String fecha = retorno[1];
        String numDias = retorno[2];
        String precio = retorno[3];
        //Se usa para obtener un ID dinamico para la excursion
        String numeroExcursion = obtenerIdExcursion();
        //Se genera la variable para almacenar la fecha y se crea la excepción
        Date date = null;
        try {
            date = sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente
        }
        // Convertir la cadena del número de días a un entero
        int num = Integer.parseInt(numDias);
        // Convertir la cadena del precio a un número de punto flotante (double)
        double coste = Double.parseDouble(precio);
        //Se genera el conjunto de BBDD en la variable excursion
        ExcursionModel excursion = new ExcursionModel(numeroExcursion, descripcion, date, num, coste);
        //Se llama al metodo crearExcursion del modelo ExcursionModel, se pasa tanto la instancia BBDD como el objeto creado
        excursion.crearExcursionModel(BBDD, excursion);
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
        for (ExcursionModel excursion : BBDD.getExcursiones()) {
            Date fechaExcursion = excursion.getFecha();
            if (fechaExcursion.after(fechaF) && fechaExcursion.before(fechaI)) {
                // La excursión está dentro del rango de fechas, así que la mostramos usando su método toString
                System.out.println(excursion);

                // Indicamos que se encontró al menos una excursión dentro del rango
            }
        }

    }
}