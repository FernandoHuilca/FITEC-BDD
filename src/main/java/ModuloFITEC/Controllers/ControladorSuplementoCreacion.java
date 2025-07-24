package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.SuplementoDAO;
import ModuloFITEC.logic.Models.Suplemento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorSuplementoCreacion {

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
    private DatePicker datePickerFechaVencimiento;

    @FXML
    private SplitMenuButton splitMenuButtonCategoria;

    @FXML
    private SplitMenuButton splitMenuButtonSucursal;

    @FXML
    private TextField textFieldCantidad;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldPrecio;

    @FXML
    public void initialize() {
        for (MenuItem item : splitMenuButtonSucursal.getItems()) {
            item.setOnAction(event -> {
                splitMenuButtonSucursal.setText(item.getText());
                splitMenuButtonSucursal.setStyle("-fx-text-fill: black;");
            });
        }

        for (MenuItem item : splitMenuButtonCategoria.getItems()) {
            item.setOnAction(event -> {
                splitMenuButtonCategoria.setText(item.getText());
                splitMenuButtonCategoria.setStyle("-fx-text-fill: black;");
            });
        }
    }

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

        @FXML
    private void cambiarVentanaActualizarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoActualizacion.fxml", "Actualizar Suplemento");
    }

    @FXML
    void registrarSuplemento(ActionEvent event) {
        try {
        // Validar campos
            if (textFieldCodigo.getText().isBlank() || 
                textFieldNombre.getText().isBlank() || 
                splitMenuButtonCategoria.getText().equals("Escoja la categoría") ||
                splitMenuButtonSucursal.getText().equals("Escoja la sucursal") ||
                textFieldPrecio.getText().isBlank() || 
                textFieldCantidad.getText().isBlank() || 
                datePickerFechaVencimiento.getValue() == null) {
                
                mostrarAlerta("Campos incompletos", "Por favor, completa todos los campos.");
            }
            else {
                // Crear objeto suplemento
                Suplemento suplemento = new Suplemento(
                    Integer.parseInt(textFieldCodigo.getText()),
                    splitMenuButtonSucursal.getText(),
                    textFieldNombre.getText(),
                    splitMenuButtonCategoria.getText(),  // Usamos el texto seleccionado
                    Double.parseDouble(textFieldPrecio.getText()),
                    Integer.parseInt(textFieldCantidad.getText()),
                    datePickerFechaVencimiento.getValue()
                );

                // Insertar en la base de datos
                new SuplementoDAO().crearSuplemento(suplemento);

                mostrarAlerta("Éxito", "Suplemento registrado correctamente.");

                limpiarFormulario();
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Verifica que el código, el precio y la cantidad sean números válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al registrar el suplemento:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarFormulario() {
        textFieldCodigo.clear();
        textFieldNombre.clear();
        textFieldPrecio.clear();
        textFieldCantidad.clear();
        datePickerFechaVencimiento.setValue(null);
        splitMenuButtonCategoria.setText("Escoja la categoría"); 
        splitMenuButtonSucursal.setText("Escoja la sucursal"); 
    }

    // TO DO: no se puede insertar un suplemento con el mismo ID en diferentes sucursales
}
