package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorClienteBusqueda {

    // 🔘 Navegación lateral
    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonInicio;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;

    // 📋 Acciones principales
    @FXML private Button buttonRegistrarCliente;
    @FXML private Button buttonConsultarCliente;
    @FXML private Button buttonActualizarCliente;
    @FXML private Button buttonEliminarCliente;

    // 📄 Acción sobre el formulario
    @FXML private Button buttonConsultarFormulario;

    // 📍 Métodos de navegación
    @FXML
    private void cambiarVentanaClientes() {
        System.out.println("🔄 Cambiando a pestaña: Clientes");
    }

    @FXML
    private void cambiarVentanaInstructores() {
        System.out.println("🔄 Cambiando a pestaña: Instructores");
    }

    @FXML
    private void cambiarVentanaSuplementos() {
        System.out.println("🔄 Cambiando a pestaña: Suplementos");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoActualizacion.fxml", "Compra");
    }

    @FXML
    private void cambiarVentanaHistorialDeCompras() {
        System.out.println("🔄 Cambiando a pestaña: Historial de compras");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraActualizacion.fxml", "Compra");
    }

    @FXML
    private void cambiarVentanaInicio() {
        System.out.println("🔄 Cambiando a pestaña: Inicio");
    }

    @FXML
    private void cambiarVentanaNominaInstructores() {
        System.out.println("🔄 Cambiando a pestaña: Nómina de instructores");
    }

    @FXML
    private void cambiarVentanaSuscrpciones() {
        System.out.println("🔄 Cambiando a pestaña: Suscripciones");
    }

    // 📋 Métodos de acción
    @FXML
    private void registrarCliente() {
        System.out.println("✅ Acción: Registrar cliente");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml");
    }

    @FXML
    private void consultarCliente() {
        System.out.println("🔍 Acción: Consultar cliente");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteBusqueda.fxml");
    }

    @FXML
    private void actualizarCliente() {
        System.out.println("✏️ Acción: Actualizar cliente");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteActualizacion.fxml");
    }

    @FXML
    private void eliminarCliente() {
        System.out.println("🗑️ Acción: Eliminar cliente");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteEliminacion.fxml");
    }

    @FXML
    private void consultarFormulario() {
        System.out.println("🧾 Acción: Consultar formulario de cliente");
    }
}
