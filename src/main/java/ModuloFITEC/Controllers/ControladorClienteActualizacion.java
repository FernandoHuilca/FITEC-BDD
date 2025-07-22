package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ControladorClienteActualizacion {

    // 🧭 Navegación lateral
    @FXML private Button buttonInicio;
    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;

    // 🔁 Botones de operaciones principales
    @FXML private Button buttonRegistrarCliente;
    @FXML private Button buttonConsultarCliente;
    @FXML private Button buttonActualizarCliente;
    @FXML private Button buttonEliminarCliente;

    // 🧾 Formulario de búsqueda
    @FXML private TextField codigoClienteField;
    @FXML private Button buttonConsultarFormulario;

    // 📋 Tabla de datos
    @FXML private TableView<?> tableClientes;
    @FXML private TableColumn<?, ?> columnaCedula;
    @FXML private TableColumn<?, ?> columnaSuscripcion;
    @FXML private TableColumn<?, ?> columnaNombre;
    @FXML private TableColumn<?, ?> columnaApellido;

    // 📝 Campos de edición
    @FXML private SplitMenuButton tipoSuscripcionMenu;
    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField telefonoField;
    @FXML private TextField emailField;
    @FXML private TextField direccionField;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private Button buttonActualizarFormulario;

    // ------------------ MÉTODOS ------------------

    // 🔁 Acciones de navegación
    @FXML private void cambiarVentanaInicio() {
        System.out.println("➡️ Cambio a ventana Inicio");
    }

    @FXML private void cambiarVentanaClientes() {
        System.out.println("➡️ Cambio a ventana Clientes");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Cliente");
    }

    @FXML private void cambiarVentanaInstructores() {
        System.out.println("➡️ Cambio a ventana Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void cambiarVentanaSuplementos() {
        System.out.println("➡️ Cambio a ventana Suplementos");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoActualizacion.fxml", "Compra");
    }

    @FXML private void cambiarVentanaHistorialDeCompras() {
        System.out.println("➡️ Cambio a ventana Historial de Compras");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraActualizacion.fxml", "Compra");
    }

    @FXML private void cambiarVentanaNominaInstructores() {
        System.out.println("➡️ Cambio a ventana Nómina Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void cambiarVentanaSuscrpciones() {
        System.out.println("➡️ Cambio a ventana Suscripciones");
    }

    // 🧾 Acciones del menú lateral
    @FXML private void registrarCliente() {
        System.out.println("📋 Registrando cliente...");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml");
    }

    @FXML private void consultarCliente() {
        System.out.println("🔍 Consultando cliente...");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteBusqueda.fxml");
    }

    @FXML private void actualizarCliente() {
        System.out.println("🔧 Actualizando cliente...");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteActualizacion.fxml");
    }

    @FXML private void eliminarCliente() {
        System.out.println("🗑️ Eliminando cliente...");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteEliminacion.fxml");
    }

    // 🔍 Formulario
    @FXML private void consultarFormulario() {
        System.out.println("🔎 Consultando datos desde formulario...");
    }

    @FXML private void actualizarFormulario() {
        System.out.println("📦 Enviando datos actualizados...");
    }
}
