package ObjectData_app.ObjectData_view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class AppControllerView {
    
    @FXML
    private BorderPane mainContainer;
    private Stage stage;

    @FXML
    public void AppWindowsView() {
        try {
            this.stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/AppWindowsView.fxml"));
            this.stage.setScene(new Scene(root));
            this.stage.setTitle("ObjectData Aplicación V1.0");
            this.stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void FXMLLoader(String file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Parent formulario = loader.load();
            mainContainer.setCenter(formulario);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //Estos metodos cargan FXML cuyo controlador es SocioControllerView
    @FXML
    public void nuevoSocioEstandarFXMLLoader() {
        FXMLLoader("/SocioView/nuevoSocioEstandarFXMLLoader.fxml");
    }
    @FXML
    public void nuevoSocioFederadoFXMLLoader() {
        FXMLLoader("/SocioView/nuevoSocioFederadoFXMLLoader.fxml");
    }
    @FXML
    public void nuevoSocioInfantilFXMLLoader() {
        FXMLLoader("/SocioView/nuevoSocioInfantilFXMLLoader.fxml");
    }
    @FXML
    public void mostrarSociosFXMLLoader() {
        FXMLLoader("/SocioView/mostrarSociosFXMLLoader.fxml");
    }
    @FXML
    public void mostrarSocioEstandarFXMLLoader() {
        FXMLLoader("/SocioView/mostrarSocioEstandarFXMLLoader.fxml");
    }
    @FXML
    public void mostrarSocioFederadoFXMLLoader() {
        FXMLLoader("/SocioView/mostrarSocioFederadoFXMLLoader.fxml");
    }
    @FXML
    public void mostrarSocioInfantilFXMLLoader() {
        FXMLLoader("/SocioView/mostrarSocioInfantilFXMLLoader.fxml");
    }
    @FXML
    public void eliminarSocioFXMLLoader() {
        FXMLLoader("/SocioView/eliminarSocioFXMLLoader.fxml");
    }
    @FXML
    public void modificarSeguroFXMLLoader() {
        FXMLLoader("/SocioView/modificarSeguroFXMLLoader.fxml");
    }
    @FXML
    public void mostrarFacturacionSocioFXMLLoader() {
        FXMLLoader("/SocioView/mostrarFacturacionSocioFXMLLoader.fxml");
    }
    // Cierre de la app usando el menu Salir
    @FXML
    public void nuevoSocioEstandar() {
        System.exit(0);
    }
    @FXML
    public void exitApp() {
        System.exit(0);
    }
}