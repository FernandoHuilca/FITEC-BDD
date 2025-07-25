package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.CompraDAO;
import ModuloFITEC.logic.DAOs.SuplementoDAO;
import ModuloFITEC.logic.Models.Compra;
import ModuloFITEC.logic.Models.Suplemento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
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
    private SplitMenuButton splitMenuButtonSucursal;

    @FXML
    private TextField textFieldCantidad;

    @FXML
    private TextField textFieldCedulaCliente;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldCodigoSuplemento;

    @FXML
    public void initialize() {
        for (MenuItem item : splitMenuButtonSucursal.getItems()) {
            item.setOnAction(event -> {
                splitMenuButtonSucursal.setText(item.getText());
                splitMenuButtonSucursal.setStyle("-fx-text-fill: black;");
            });
        }
    }

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

    @FXML
    void registrarCompra(ActionEvent event) {
            try {
            if (!validarCampos()) return;

            Suplemento suplemento = obtenerSuplemento();
            if (suplemento == null) return;

            int cantidadPedida = Integer.parseInt(textFieldCantidad.getText());

            if (!validarCantidadDisponible(suplemento, cantidadPedida)) return;

            Compra compra = new Compra(
                Integer.parseInt(textFieldCodigo.getText()),
                textFieldCedulaCliente.getText(),
                suplemento.getIdSuplemento(),
                cantidadPedida,
                java.time.LocalDateTime.now(),
                suplemento.getPrecio(),
                splitMenuButtonSucursal.getText()
            );

            new CompraDAO().crearCompra(compra);

            suplemento.setCantidadDisponible(suplemento.getCantidadDisponible() - cantidadPedida);
            new SuplementoDAO().actualizarSuplemento(suplemento);

            mostrarAlerta("Éxito", "Compra registrada correctamente.");
            limpiarFormulario();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Verifica que el código y la cantidad sean números válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al registrar la compra:\n" + e.getMessage());
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
        splitMenuButtonSucursal.setText("Escoja la sucursal"); 
        textFieldCedulaCliente.clear();
        textFieldCodigoSuplemento.clear();
        textFieldCantidad.clear();
    }

    private boolean validarCampos() {
        if (textFieldCodigo.getText().isBlank() ||
            textFieldCedulaCliente.getText().isBlank() ||
            textFieldCodigoSuplemento.getText().isBlank() ||
            textFieldCantidad.getText().isBlank() ||
            splitMenuButtonSucursal.getText().equals("Escoja la sucursal")) {
            
            mostrarAlerta("Campos incompletos", "Por favor, completa todos los campos.");
            return false;
        }
        return true;
    }

    private Suplemento obtenerSuplemento() throws Exception {
        SuplementoDAO suplementoDAO = new SuplementoDAO();

        int idSuplemento;
        try {
            idSuplemento = Integer.parseInt(textFieldCodigoSuplemento.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta("ID inválido", "El ID del suplemento debe ser un número entero.");
            return null;
        }

        Suplemento suplemento = suplementoDAO.buscarPorId(idSuplemento);

        if (suplemento == null) {
            mostrarAlerta("Suplemento no encontrado", "No existe un suplemento con ID: " + idSuplemento);
            return null;
        }

        return suplemento;
    }

    private boolean validarCantidadDisponible(Suplemento suplemento, int cantidadPedida) {
        if (cantidadPedida > suplemento.getCantidadDisponible()) {
            mostrarAlerta("Cantidad excedida", "No hay suficiente cantidad disponible del suplemento.");
            return false;
        }
        return true;
    }
}
