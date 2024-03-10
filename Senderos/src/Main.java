//Este archivo es el que busca el programa al iniciar la aplicacion
//por primera vez, en este archivo tenemos la clase Main que contiene
//la llamada al metodo menuInicio del controlador.
public class Main extends AppController {
    public static void main(String[] args) {
        AppController APP = new AppController();
        APP.inicio();
    }
}