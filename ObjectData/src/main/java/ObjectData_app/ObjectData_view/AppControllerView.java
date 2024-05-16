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
    //Estos metodos cargan los FXML cuyo controlador es SocioControllerView (Gestión Socios)
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
        SocioControllerView.mostrarTodosLosSocios();
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
    //Estos metodos cargan los FXML cuyo controlador es InscripcionControllerView (Gestión Inscripciones)
    @FXML
    public void nuevaInscripcionFXMLLoader() {
        FXMLLoader("/InscripcionView/nuevaInscripcionFXMLLoader.fxml");
    }
    @FXML
    public void mostrarInscripcionFechaFXMLLoader() {
        FXMLLoader("/InscripcionView/mostrarInscripcionFechaFXMLLoader.fxml");
    }
    @FXML
    public void mostrarInscripcionSocioFXMLLoader() {
        FXMLLoader("/InscripcionView/mostrarInscripcionSocioFXMLLoader.fxml");
    }
    @FXML
    public void eliminarInscripcionFXMLLoader() {
        FXMLLoader("/InscripcionView/eliminarInscripcionFXMLLoader.fxml");
    }
    //Estos metodos cargan los FXML cuyo controlador es ExcursionControllerView (Gestión Excursiones)
    @FXML
    public void nuevaExcursionFXMLLoader() {
        FXMLLoader("/ExcursionView/nuevaExcursionFXMLLoader.fxml");
    }
    @FXML
    public void eliminarExcursionFXMLLoader() {
        FXMLLoader("/ExcursionView/eliminarExcursionFXMLLoader.fxml");
    }
    @FXML
    public void mostrarExcursionFechaFXMLLoader() {
        FXMLLoader("/ExcursionView/mostrarExcursionFechaFXMLLoader.fxml");
    }
    // Cierre de la app usando el menu Salir
    @FXML
    public void exitApp() {
        System.exit(0);
    }
}
