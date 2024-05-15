package ObjectData_app.ObjectData_view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuView extends Application{
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("file:image.jpg");
        ImageView mv = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(mv);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("ObjectData App");
        primaryStage.show();
    }
    
    // Propiedades de clase
    public void menuInicioView(Stage primaryStage) {
        System.out.println("---MENU PRINCIPAL DE LA APLICACION---");
        System.out.println("    1. Gestión Excursiones");
        System.out.println("    2. Gestión de Socios");
        System.out.println("    3. Gestión de Inscripciones");
        System.out.println("    4. Salir de la aplicación");
    }

    public void menuGestionExcursionesView() {
        System.out.println("---GESTIÓN DE EXCURSIONES---");
        System.out.println("    1. Añadir Excursión");
        System.out.println("    2. Mostrar Excursiones con filtro entre fechas");
        System.out.println("    3. Volver al menu principal");
    }

    public void menuGestionSociosView() {
        System.out.println("---GESTIÓN DE SOCIOS---");
        System.out.println("    1. Añadir nuevo socio.");
        System.out.println("    2. Modificar tipo de seguro de un socio estándar.");
        System.out.println("    3. Eliminar socio.");
        System.out.println("    4. Mostrar Socios.");
        System.out.println("    5. Mostrar Factura mensual por socio.");
        System.out.println("    6. Volver al menu principal");
    }

    public void menuGestionInscripcionesView() {
        System.out.println("---GESTIÓN DE INSCRIPCIONES---");
        System.out.println("    1. Añadir Inscripción");
        System.out.println("    2. Eliminar Inscripción");
        System.out.println("    3. Mostrar inscripciones con las opciones de filtrar por socio y/o fechas");
        System.out.println("    4. Volver al menu principal");
    }

    public int getOpcionView(int value) {
        try {
            System.out.print("Elije una opción válida (1 - " + value + "): ");
            int opcion = 2;
            if (opcion < 1 || opcion > value) {
                System.out.println("Opción no válida.");
                return getOpcionView(value);
            }
            return opcion;
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida. Debe ingresar un número.");
            return getOpcionView(value);
        }
    }
}
