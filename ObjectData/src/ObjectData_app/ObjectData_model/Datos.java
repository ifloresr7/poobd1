package ObjectData_app.ObjectData_model;
import java.util.ArrayList;

import java.io.IOException;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Datos {
    private ArrayList<ExcursionModel> excursiones;
    private ArrayList<SocioModel> socios;
    private ArrayList<InscripcionModel> inscripciones;
    private static ArrayList<FederacionModel> federaciones;
    public Datos() {
        socios = new ArrayList<>();
        excursiones = new ArrayList<>();
        inscripciones = new ArrayList<>();
        federaciones = new ArrayList<>();
    }
    public void eliminarInscripcionPorIndice(int indice) {
        if (indice >= 0 && indice < inscripciones.size()) {
            inscripciones.remove(indice);
            System.out.println("Inscripción eliminada correctamente.");
        } else {
            System.out.println("El índice proporcionado está fuera de rango.");
        }
    }

    public ArrayList<ExcursionModel> getExcursiones() {
        return excursiones;
    }
    public ArrayList<FederacionModel> getFederaciones() {
        return federaciones;
    }
    public ArrayList<SocioModel> getSocios() {
        return socios;
    }
    public void setInscripciones(ArrayList<InscripcionModel> inscripciones) {
        this.inscripciones = inscripciones;
    }
    public void setFederaciones(ArrayList<FederacionModel> federaciones) {
        this.federaciones = federaciones;
    }
    public ArrayList<InscripcionModel> getInscripciones() {
        return inscripciones;
    }
    public static void cargarDatos() {
        FileReader archivo;
        BufferedReader lector;

        try {
            File fichero = new File("federaciones.txt");
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
                        // Aquí puedes hacer lo que necesites con el código y el nombre de la federación,
                        // como crear un objeto Federacion y almacenarlo en una lista, por ejemplo.
                        // Por ejemplo:
                        FederacionModel federacion = new FederacionModel(codigo, nombre);
                        federaciones.add(federacion);
                        System.out.println("Federación cargada: " + federacion.getNombre());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
