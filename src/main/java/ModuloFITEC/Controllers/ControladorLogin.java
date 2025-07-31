package ModuloFITEC.Controllers;


import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorLogin {
    @FXML
    private Button buttonIngresar;

    @FXML
    private PasswordField textFieldContrasena;

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private void ingresar(){
        if (textFieldUsuario.getText().isEmpty() || textFieldContrasena.getText().isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, complete todos los campos.");
            return;
        }

        if (!textFieldUsuario.getText().equals(ConexionBaseSingleton.getUser()) || !textFieldContrasena.getText().equals(ConexionBaseSingleton.getPassword())) {
            MetodosFrecuentes.mostrarError("Error", "Usuario o contraseña incorrectos.");
            return;
        }

        MetodosFrecuentes.cambiarVentana((Stage) buttonIngresar.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
        //MetodosFrecuentes.mostrarError("Error" , "Este es un mensaje de error.");
        //MetodosFrecuentes.abrirNuevaVentana("/ModuloFITEC/views/VistaInicio.fxml", "Prueba");
        //MetodosFrecuentes.abrirVentanaModal("/ModuloFITEC/views/VistaInicio.fxml", "Prueba",(Stage) buttonIngresar.getScene().getWindow());
        //MetodosFrecuentes.mostrarInfo("Informativo" , "Este es un mensaje informativo.");
    }

}
