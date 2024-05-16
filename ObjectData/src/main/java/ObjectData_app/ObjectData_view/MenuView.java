package ObjectData_app.ObjectData_view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuView {
    // Método para mostrar el menú principal
    public int menuInicioView(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
    
        Label menuTitle = new Label("Menú Principal");
        menuTitle.setFont(new Font("Arial", 20));
        menuTitle.setLayoutX(250);
        menuTitle.setLayoutY(50);
    
        Button button = new Button("Click me!");
        button.setLayoutX(250);
        button.setLayoutY(200);
        button.setOnAction(event -> {
            return 1;
        });
    
        root.getChildren().addAll(menuTitle, button);
    
        stage.setScene(scene);
        stage.setTitle("Menú Principal");
        stage.show();
    
        return 0;
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
            int opcion = 0;
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
