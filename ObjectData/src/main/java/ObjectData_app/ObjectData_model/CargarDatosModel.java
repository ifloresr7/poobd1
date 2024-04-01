package ObjectData_app.ObjectData_model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import ObjectData_app.ObjectData_model.SeguroModel.TipoSeguro; (Sin uso)

public class CargarDatosModel {
    public void cargarDatos(Datos BBDD) {
        FileReader archivo;
        BufferedReader lector;
        try {
            File fichero = new File("ObjectData\\src\\main\\java\\ObjectData_app\\ObjectData_model\\federaciones.txt");
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
            File fichero = new File("ObjectData\\src\\main\\java\\ObjectData_app\\ObjectData_model\\excursiones.txt");
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
                        ExcursionModel excursion = new ExcursionModel(Integer.parseInt(codigo), descripcion, fecha,
                                numeroDias, precioInscripcion);
                        BBDD.excursion.add(excursion);

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
        try {
            File fichero = new File("ObjectData\\src\\main\\java\\ObjectData_app\\ObjectData_model\\socioEstandar.txt");
            String ruta = fichero.getAbsolutePath();
            archivo = new FileReader(ruta);
            if (archivo.ready()) {
                lector = new BufferedReader(archivo);
                String cadena;
                while ((cadena = lector.readLine()) != null) {
                    String[] partes = cadena.split(","); // Separar la cadena por comas
                    if (partes.length == 4) {
                        String codigo = partes[0].trim();
                        String nombre = partes[1].trim();
                        String DNI = partes[2].trim();
                        String seguro = partes[3].trim();
                        // Convertir el nombre del tipo de seguro en un valor del enumerador TipoSeguro
                        SeguroModel.TipoSeguro tipoSeguro;
                        if (seguro.equals("BASICO")) {
                            tipoSeguro = SeguroModel.TipoSeguro.BASICO;
                        } else if (seguro.equals("COMPLETO")) {
                            tipoSeguro = SeguroModel.TipoSeguro.COMPLETO;
                        } else {
                            // Manejar el caso donde el tipo de seguro no es válido
                            System.out.println("Tipo de seguro no válido en la línea: " + cadena);
                            continue; // Saltar al siguiente ciclo
                        }
                        // Crear el objeto SocioEstandarModel con el tipo de seguro correspondiente
                        SocioEstandarModel socio = new SocioEstandarModel(Integer.parseInt(codigo), nombre, DNI,
                                new SeguroModel(tipoSeguro));
                        BBDD.socioEstandar.add(socio);

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