package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorCompraCreacion {

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultarCompra;

    @FXML
    private Button buttonEliminarCompra;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private Button buttonRegistrarCompra;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

@FXML
    void cambiarVentanaClientes(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Registrar Cliente");
    }

    @FXML
    void cambiarVentanaConsultarCompra(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCompra.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraBusqueda.fxml", "Consultar Compra");
    }

    @FXML
    void cambiarVentanaEliminarCompra(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarCompra.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraEliminacion.fxml", "Eliminar Compra");
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
        MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Registrar Instructor");
    }

    @FXML
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Consultar Nómina");
    }

    @FXML
    void cambiarVentanaRegistrarCompra(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarCompra.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Registrar Compra");
    }

    @FXML
    void cambiarVentanaSuplementos(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Registrar Suplemento");
    }

    @FXML
    void cambiarVentanaSuscripciones(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Registrar Suscripción");
    }

}
