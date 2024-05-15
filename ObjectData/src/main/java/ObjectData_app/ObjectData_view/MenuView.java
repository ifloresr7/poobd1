package ObjectData_app.ObjectData_view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuView extends Application{
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }

    @Override
public void start(Stage primaryStage) {
    // Crear la imagen
    Image image = new Image("file:image.jpg");
    
    // Crear el ImageView y configurarlo con la imagen
    ImageView mv = new ImageView(image);
    
    // Crear los botones con un tamaño de fuente más grande
    Button socioButton = new Button("Socio");
    socioButton.setStyle("-fx-font-size: 20px;");
    Button excursionesButton = new Button("Excursiones");
    excursionesButton.setStyle("-fx-font-size: 20px;");
    Button inscripcionesButton = new Button("Inscripciones");
    inscripcionesButton.setStyle("-fx-font-size: 20px;");
    Button salirButton = new Button("Salir");
    salirButton.setStyle("-fx-font-size: 20px;");
    
    // Establecer el tamaño de los botones
    socioButton.setPrefSize(150, 50);
    excursionesButton.setPrefSize(150, 50);
    inscripcionesButton.setPrefSize(150, 50);
    salirButton.setPrefSize(150, 50);
    
    // Distribuir los botones horizontalmente
    HBox buttonBox = new HBox(20); // Espacio entre los botones
    buttonBox.getChildren().addAll(socioButton, excursionesButton, inscripcionesButton, salirButton);
    buttonBox.setPadding(new Insets(20)); // Espacio alrededor de los botones
    
    // Crear el contenedor para los elementos (imagen y botones)
    VBox root = new VBox(); // Contenedor vertical
    root.getChildren().addAll(buttonBox, mv);
    
    // Crear la escena
    Scene scene = new Scene(root, 1280, 720);
    
    // Configurar la escena en el escenario
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.setTitle("ObjectData App");
    primaryStage.show();
}
    // Propiedades de clase
    public void menuInicioView() {
        Image image = new Image("file:image.jpg");
    
    // Crear el ImageView y configurarlo con la imagen
    ImageView mv = new ImageView(image);
    
    // Crear los botones con un tamaño de fuente más grande
    Button socioButton = new Button("Socio");
    socioButton.setStyle("-fx-font-size: 20px;");
    Button excursionesButton = new Button("Excursiones");
    excursionesButton.setStyle("-fx-font-size: 20px;");
    Button inscripcionesButton = new Button("Inscripciones");
    inscripcionesButton.setStyle("-fx-font-size: 20px;");
    Button salirButton = new Button("Salir");
    salirButton.setStyle("-fx-font-size: 20px;");
    
    // Establecer el tamaño de los botones
    socioButton.setPrefSize(150, 50);
    excursionesButton.setPrefSize(150, 50);
    inscripcionesButton.setPrefSize(150, 50);
    salirButton.setPrefSize(150, 50);
    
    // Distribuir los botones horizontalmente
    HBox buttonBox = new HBox(20); // Espacio entre los botones
    buttonBox.getChildren().addAll(socioButton, excursionesButton, inscripcionesButton, salirButton);
    buttonBox.setPadding(new Insets(20)); // Espacio alrededor de los botones
    
    // Crear el contenedor para los elementos (imagen y botones)
    VBox root = new VBox(); // Contenedor vertical
    root.getChildren().addAll(buttonBox, mv);
    
    // Crear la escena
    Scene scene = new Scene(root, 1280, 720);
    
    // Configurar la escena en el escenario
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.setTitle("ObjectData App");
    primaryStage.show();
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
