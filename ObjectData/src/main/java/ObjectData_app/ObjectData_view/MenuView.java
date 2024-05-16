package ObjectData_app.ObjectData_view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuView {
    private Stage stage;
    private Scene scene;
    private VBox layout;

    public MenuView() {
        layout = new VBox();
        scene = new Scene(layout, 720, 480);

        // Establecer imagen de fondo
        Image backgroundImage = new Image("image.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        layout.getChildren().add(backgroundImageView);

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
    }

    public int menuInicioView_() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/HelloWorld.fxml"));
            stage.setTitle("Hello World");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            return 1;
        }catch(Exception e){
            System.out.println("Error");
        }
        return 0;
    }

    // Método para mostrar el menú principal
    public int menuInicioView() {
        layout.getChildren().clear();

        Button enviar = new Button("Gestionar Excursiones");

        // Arreglo para almacenar la opción seleccionada
        final int[] opcionSeleccionada = { 0 };

        // Asignar acciones a los botones
        enviar.setOnAction(e -> {
            opcionSeleccionada[0] = 1;
        });

        layout.getChildren().addAll(enviar);
        // Hacer que la ventana sea modal
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Menú Principal");

        // Mostrar la ventana
        stage.showAndWait();

        // Devolver la opción seleccionada
        return opcionSeleccionada[0];
    }

    public int menuGestionExcursionesView() {
        layout.getChildren().clear();

        Button excursionesButton = new Button("Añadir Excursión");
        Button sociosButton = new Button("Mostrar Excursiones con filtro entre fechas");
        Button inscripcionesButton = new Button(" Volver al menu principal");

        // Arreglo para almacenar la opción seleccionada
        final int[] opcionSeleccionada = { 0 };

        // Asignar acciones a los botones
        excursionesButton.setOnAction(e -> {
            opcionSeleccionada[0] = 1;
        });
        sociosButton.setOnAction(e -> {
            opcionSeleccionada[0] = 2;
        });
        inscripcionesButton.setOnAction(e -> {
            opcionSeleccionada[0] = 3;
        });

        layout.getChildren().addAll(excursionesButton, sociosButton, inscripcionesButton);

        // Hacer que la ventana sea modal
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Menú Principal");

        // Mostrar la ventana y esperar hasta que se cierre
        stage.showAndWait();

        // Devolver la opción seleccionada
        return opcionSeleccionada[0];
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
}
