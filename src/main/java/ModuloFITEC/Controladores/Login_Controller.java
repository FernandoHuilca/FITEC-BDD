package ModuloFITEC.Controladores;


import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Login_Controller {
    @FXML
    private Button buttonIngresar;

    @FXML
    private void ingresar(){
        MetodosFrecuentes.cambiarVentana((Stage) buttonIngresar.getScene().getWindow(), "/ModuloFITEC/views/Cliente.fxml", "Cliente");
    }

}
