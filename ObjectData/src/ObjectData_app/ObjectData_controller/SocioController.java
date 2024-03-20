package ObjectData_app.ObjectData_controller;

import ObjectData_app.ObjectData_model.SocioEstandarModel;

import java.util.Random;
import java.util.UUID;

import ObjectData_app.ObjectData_model.Datos;
import ObjectData_app.ObjectData_model.SeguroModel;

//Se añade la vista socio
import ObjectData_app.ObjectData_view.SocioView;

public class SocioController {
    static SocioView View = new SocioView();

    // Método para generar un número de socio aleatorio
    public static int generarID() {
        Random rand = new Random();
        int id = 0;
        for (int i = 0; i < 10; i++) {
            id = id * 10 + rand.nextInt(9) + 1;
        }
        if(id < 0){
            return id * -1;
        }
        return id;
    }

    // Metodo para añadir un socio estandar
    public static void crearSocioEstandar(Datos BBDD) {
        String[] retorno = View.formCrearSocioEstandarView(); //Se llama a la vista para pedir el nombre y el DNI del usuario
        String nombre = retorno[0]; //El primer parametro del array sera el nombre
        String NIF = retorno[1]; //El segundo parametro del array es el DNI

        //Se pide el seguro mediante la vista
        SeguroModel seguro = View.seleccionarSeguroView();

        // Método para generar un número de socio aleatorio
        int numeroSocio = generarID(); //Número de socio

        // Método para crear un socio con un número de socio generado dinámicamente
        SocioEstandarModel socio = new SocioEstandarModel(numeroSocio, nombre, NIF, seguro);

        // Suponiendo que existe una instancia de alguna clase que gestione una colección de socios
        socio.crearSocioEstandar(BBDD, socio);
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