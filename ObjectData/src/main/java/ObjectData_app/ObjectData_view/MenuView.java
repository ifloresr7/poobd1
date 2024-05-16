package ObjectData_app.ObjectData_view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuView{

    @FXML
    private BorderPane mainContainer;

    private Stage stage = new Stage();

    @FXML
    public void menuInicioView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/HelloWorld.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Inicio");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void displayHelloWorld(MouseEvent event) {
        System.out.println("null");
    }
    
    @FXML
    public void goodByeWorld(MouseEvent event) {
        // Your event handling logic for "Good Bye World" button
    }
}
