package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorClienteEliminacion {

    // 🧭 Navegación lateral
    @FXML private Button buttonInicio;
    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;

    // 🧾 Botones principales (registro, consulta, etc.)
    @FXML private Button buttonRegistrarCliente;
    @FXML private Button buttonConsultarCliente;
    @FXML private Button buttonActualizarCliente;
    @FXML private Button buttonEliminarCliente;

    // 📄 Botones del formulario
    @FXML private Button buttonConsultarFormulario;
    @FXML private Button buttonEliminarFormulario;

    // 🧠 Métodos de navegación lateral
    @FXML
    private void cambiarVentanaInicio() {
        System.out.println("➡️ Cambiar a vista Inicio");
    }

    @FXML
    private void cambiarVentanaClientes() {
        System.out.println("➡️ Cambiar a vista Clientes");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Cliente");
    }

    @FXML
    private void cambiarVentanaInstructores() {
        System.out.println("➡️ Cambiar a vista Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML
    private void cambiarVentanaSuplementos() {
        System.out.println("➡️ Cambiar a vista Suplementos");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoActualizacion.fxml", "Compra");
    }

    @FXML
    private void cambiarVentanaHistorialDeCompras() {
        System.out.println("➡️ Cambiar a vista Historial de Compras");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraActualizacion.fxml", "Compra");
    }

    @FXML
    private void cambiarVentanaNominaInstructores() {
        System.out.println("➡️ Cambiar a vista Nómina Instructores");
    }

    @FXML
    private void cambiarVentanaSuscrpciones() {
        System.out.println("➡️ Cambiar a vista Suscripciones");
    }

    // 👥 Acciones con cliente
    @FXML
    private void registrarCliente() {
        System.out.println("📋 Registrando cliente...");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml");
    }

    @FXML
    private void consultarCliente() {
        System.out.println("🔍 Consultando cliente...");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteBusqueda.fxml");
    }

    @FXML
    private void actualizarCliente() {
        System.out.println("🛠️ Actualizando cliente...");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteActualizacion.fxml");
    }

    @FXML
    private void eliminarCliente() {
        System.out.println("🗑️ Eliminando cliente...");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteEliminacion.fxml");
    }

    // 🔎 Funciones del formulario
    @FXML
    private void ConsultarClienteFormulario() {
        System.out.println("📄 Consultar cliente desde formulario...");
    }

    @FXML
    private void eliminarClienteFormulario() {
        System.out.println("🗑️ Eliminar cliente desde formulario...");
    }
}
