package ModuloFITEC.Controllers;


import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorLogin {
    @FXML
    private Button buttonIngresar;

    @FXML
    private void ingresar(){
        MetodosFrecuentes.cambiarVentana((Stage) buttonIngresar.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
        //MetodosFrecuentes.mostrarError("Error" , "Este es un mensaje de error.");
        //MetodosFrecuentes.abrirNuevaVentana("/ModuloFITEC/views/VistaInicio.fxml", "Prueba");
        //MetodosFrecuentes.abrirVentanaModal("/ModuloFITEC/views/VistaInicio.fxml", "Prueba",(Stage) buttonIngresar.getScene().getWindow());
        //MetodosFrecuentes.mostrarInfo("Informativo" , "Este es un mensaje informativo.");
    }

}
