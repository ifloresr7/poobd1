package ObjectData_app.ObjectData_controller;
import ObjectData_app.ObjectData_model.SocioEstandarModel;
import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.SeguroModel;

//Se añade la vista socio
import ObjectData_app.ObjectData_view.SocioView;

public class SocioController{
    static SocioView View = new SocioView();

    //Metodo para añadir un socio estandar
    public static void crearSocioEstandar(Datos BBDD) {
        String[] retorno = View.formCrearSocioEstandarView(); //Se llama a la vista para pedir el nombre y el DNI del usuario
        String nombre = retorno[0]; //El primer parametro del array sera el nombre
        String NIF = retorno[1]; //El segundo parametro del array es el DNI

        //Se debe implementar la logia para generar el numero de socio de forma dinamica.
        int numeroSocio = 3; //Número de socio

        //Se debera crear una vista para poder añadir un seguro.
        SeguroModel seguro = new SeguroModel(null, numeroSocio);

        //Se crea el objeto y se le manda al modelo SocioEstandarModel
        SocioEstandarModel model = new SocioEstandarModel(numeroSocio, nombre, NIF, seguro);

        // Suponiendo que existe una instancia de alguna clase que gestione una colección de socios
        model.addSocioEstandar(model);
    }
    public static void modificarSeguroSocioEstandar(Datos BBDD) {
    }
    public static void crearSocioFederado(Datos BBDD) {
    }
    public static void crearSocioInfantil(Datos BBDD) {
    }
    public static void eliminarSocio(Datos BBDD) {
    }
    public static void mostrarSocio(Datos BBDD) {
    }
    public static void facturaMensualSocio(Datos BBDD) {
    }
}