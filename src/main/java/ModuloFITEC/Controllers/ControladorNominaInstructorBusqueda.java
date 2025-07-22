package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorNominaInstructorBusqueda {

    @FXML
    private Button buttonActualizarNomina;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultarFormulario;

    @FXML
    private Button buttonConsultarNomina;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private TableColumn<?, ?> tableColumnCedulaInstructor;

    @FXML
    private TableColumn<?, ?> tableColumnFechaContratacion;

    @FXML
    private TableColumn<?, ?> tableColumnSalario;

    @FXML
    private TableView<?> tableViewNomina;

    @FXML
    private TextField textFieldCedula;

    @FXML
    void actualizarNomina(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarNomina.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorActualizacion.fxml", "Actualizar Nomina");
    }

    @FXML
    void consultarNomina(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarNomina.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Consultar Nomina");

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
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Nomina Instructores");
    }

    @FXML
    void consultarFormulario(ActionEvent event) {

    }

}
