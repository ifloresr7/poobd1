package ObjectData_app.ObjectData_model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CargarDatosModel {
    public void cargarDatos(Datos BBDD){
        FileReader archivo;
        BufferedReader lector;
        try {
            File fichero = new File("ObjectData\\src\\ObjectData_app\\ObjectData_model\\federaciones.txt");
            String ruta = fichero.getAbsolutePath();
            archivo = new FileReader(ruta);
            if (archivo.ready()) {
                lector = new BufferedReader(archivo);
                String cadena;
                while ((cadena = lector.readLine()) != null) {
                    String[] lectura = cadena.split(",");
                    if (lectura.length == 2) {
                        String codigo = lectura[0].trim();
                        String nombre = lectura[1].trim();
                        FederacionModel federacion = new FederacionModel(codigo, nombre);
                        BBDD.federacion.add(federacion);
                        System.out.println("Federación cargada: " + federacion.getNombre());
                    }
                }
                // Cerrar BufferedREader
                lector.close();
            }
            // Cerrar FileReader
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File fichero = new File("ObjectData\\src\\ObjectData_app\\ObjectData_model\\excursiones.txt");
            String ruta = fichero.getAbsolutePath();
            archivo = new FileReader(ruta);

            if (archivo.ready()) {
                lector = new BufferedReader(archivo);
                String cadena;
                while ((cadena = lector.readLine()) != null) {
                    String[] partes = cadena.split(","); // Separar la cadena por comas
                    if (partes.length == 5) { // Verificar que hay 5 partes
                        String codigo = partes[0].trim();
                        String descripcion = partes[1].trim();
                        Date fecha = null;
                        int numeroDias = 0;
                        double precioInscripcion = 0.0;

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            fecha = sdf.parse(partes[2].trim());
                            numeroDias = Integer.parseInt(partes[3].trim());
                            precioInscripcion = Double.parseDouble(partes[4].trim());
                        } catch (ParseException | NumberFormatException e) {
                            System.out.println("Error al parsear la línea: " + cadena);
                            e.printStackTrace();
                        }
                        ExcursionModel excursion = new ExcursionModel(codigo, descripcion, fecha, numeroDias, precioInscripcion);
                        BBDD.excursion.add(excursion);
                        System.out.println("Excursión cargada: " + excursion);
                    } else {
                        System.out.println("Error en el formato de la línea: " + cadena);
                    }
                }
                lector.close(); 
            }
            archivo.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}