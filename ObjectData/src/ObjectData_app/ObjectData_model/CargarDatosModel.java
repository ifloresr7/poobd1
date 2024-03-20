package ObjectData_app.ObjectData_model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}