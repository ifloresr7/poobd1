package ObjectData_app.ObjectData_view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuView {

    @FXML
    private BorderPane mainContainer;

    private Stage stage = new Stage();

    @FXML
    public int menuInicioView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Gestion.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("ObjectData Aplicación V1.0");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @FXML
    public void nuevoSocioEstandar(MouseEvent event) {
        System.out.println("null");
        System.out.println("null");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/HelloWorld.fxml"));
            mainContainer.setCenter(root); // Establecer la nueva vista en el contenedor principal
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goodByeWorld(MouseEvent event) {
        // Your event handling logic for "Good Bye World" button
    }
}
