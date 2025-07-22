package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorSuscripcionCreacion {

    @FXML
    private Button buttonActualizarSuscripción;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultarSuscripción;

    @FXML
    private Button buttonEliminarSuscripción;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private Button buttonRegistrarFormularioSuscripción;

    @FXML
    private Button buttonRegistrarSuscripción;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

    @FXML
    private TextField textFieldDescripcion;

    @FXML
    private TextField textFieldDuracion;

    @FXML
    private TextField textFieldPrecio;

    @FXML
    private TextField textFieldTipo;

    @FXML
    void actualizarSuscripción(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuscripción.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionActualizacion.fxml", "Actualizar Suscripción");
    }

    @FXML
    void cambiarVentanaClientes(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Clientes");
    }

    @FXML
    void cambiarVentanaHistorialDeCompras(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Historial de Compras");
    }

    @FXML
    void cambiarVentanaInicio(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
    }

    @FXML
    void cambiarVentanaInstructores(ActionEvent event) {
        System.out.println("Instructores button clicked");
        //MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/In.fxml", "Instructores");
    }

    @FXML
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Nómina de Instructores");
    }

    @FXML
    void cambiarVentanaSuplementos(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Suplementos");
    }

    @FXML
    void cambiarVentanaSuscripciones(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Suscripciones");
    }

    @FXML
    void consultarSuscripción(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarSuscripción.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionBusqueda.fxml", "Consultar Suscripción");
    }

    @FXML
    void eliminarSuscripción(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarSuscripción.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionEliminacion.fxml", "Eliminar Suscripción");
    }

    @FXML
    void registrarFormularioSuscripción(ActionEvent event) {
    }

    @FXML
    void registrarSuscripción(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarSuscripción.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Registrar Suscripción");
    }

}
