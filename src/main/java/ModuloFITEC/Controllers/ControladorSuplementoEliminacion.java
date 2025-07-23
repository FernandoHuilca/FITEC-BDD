package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorSuplementoEliminacion {

    @FXML
    private Button buttonActualizarSuplemento;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultarSuplemento;

    @FXML
    private Button buttonEliminarSuplemento;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private Button buttonRegistrarSuplemento;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

        @FXML
    void buttonVentanaActualizarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoActualizacion.fxml", "Actualizar Suplemento");
    }

    @FXML
    void cambiarVentanaClientes(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Registrar Cliente");
    }

    @FXML
    void cambiarVentanaConsultarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoBusqueda.fxml", "Consultar Suplemento");
    }

    @FXML
    void cambiarVentanaEliminarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoEliminacion.fxml", "Eliminar Suplemento");
    }

    @FXML
    void cambiarVentanaHistorialDeCompras(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Crear Compra");
    }

    @FXML
    void cambiarVentanaInicio(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
    }

    @FXML
    void cambiarVentanaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Crear Instructor");
    }

    @FXML
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Consultar Nómina");
    }

    @FXML
    void cambiarVentanaRegistrarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Registrar Suplemento");
    }

    @FXML
    void cambiarVentanaSuplementos(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Crear Suplemento");
    }

    @FXML
    void cambiarVentanaSuscripciones(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Registrar Suscripción");
    }
    
}
