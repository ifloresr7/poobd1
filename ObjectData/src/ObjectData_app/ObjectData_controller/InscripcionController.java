package ObjectData_app.ObjectData_controller;
//Se añade la vista principal
import ObjectData_app.ObjectData_view.InscripcionesView;
import ObjectData_app.ObjectData_model.Datos;

import java.util.Random;

public class InscripcionController{
    static InscripcionesView View = new InscripcionesView();
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
    public static void crearInscripcion(Datos BBDD)
    {
        String[] retorno = View.formCrearInscripcionView();
        String nombre = retorno[0]; // El primer parametro del array será el nombre
        String id = retorno[1];
        int numeroInscripcion = generarID();
    }

    public static void eliminarInscripcion(Datos BBDD) {

    }

    public static void mostrarInscripcion(Datos BBDD) {
        boolean chequeo = false;
        int opcion = 0;
        do{
            String retorno = View.formMostrarInscripcionView();
            try{
                opcion = Integer.parseInt(retorno);
            }catch(Exception e){
                View.respuestaControllerView("Debes introducir un valor númerico.");
                continue;
            }
            if(opcion == 1 || opcion == 2){
                chequeo = true;
            }else{
                View.respuestaControllerView("Debes selecciona una opcion valida.");
                continue;
            }
        } while(!chequeo);
        switch (opcion) {
            case 1:
                mostrarInscripcionPorSocio();
                break;
            case 2:
                mostrarInscripcionPorFecha();
                break;
        }
    }
    public static void mostrarInscripcionPorSocio(){
        String[] retorno = View.formFiltrarPorSocio();

    }
    public static void mostrarInscripcionPorFecha(){
        String[] retorno = View.formFiltrarPorFechas();
    }

}