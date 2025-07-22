package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ControladorInstructorBusqueda {

    // 📋 Campos del formulario
    @FXML private TextField cedulaBusquedaField;
    @FXML private Button buttonConsultarFormularioInstructor;

    // 📊 Tabla de resultados
    @FXML private TableView<?> tablaInstructores;
    @FXML private TableColumn<?, ?> columnaCedula;
    @FXML private TableColumn<?, ?> columnaNombre;
    @FXML private TableColumn<?, ?> columnaApellido;
    @FXML private TableColumn<?, ?> columnaTelefono;

    // 🔀 Botones de navegación lateral
    @FXML private Button buttonInicio;
    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;

    // ⚙️ Botones del panel derecho
    @FXML private Button buttonRegistrarInstructor;
    @FXML private Button buttonConsultarInstructor;
    @FXML private Button buttonActualizarInstructor;
    @FXML private Button buttonEliminarInstructor;

    // ---------------- MÉTODOS DE NAVEGACIÓN ----------------

    @FXML private void cambiarVentanaInicio() {
        System.out.println("🔁 Cambio a ventana: Inicio");
    }

    @FXML private void cambiarVentanaClientes() {
        System.out.println("🔁 Cambio a ventana: Clientes");
        MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Cliente");
    }

    @FXML private void cambiarVentanaInstructores() {
        System.out.println("🔁 Cambio a ventana: Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void cambiarVentanaSuplementos() {
        System.out.println("🔁 Cambio a ventana: Suplementos");
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoActualizacion.fxml", "Compra");
    }

    @FXML private void cambiarVentanaHistorialDeCompras() {
        System.out.println("🔁 Cambio a ventana: Historial de Compras");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraActualizacion.fxml", "Compra");
    }

    @FXML private void cambiarVentanaNominaInstructores() {
        System.out.println("🔁 Cambio a ventana: Nómina de Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void cambiarVentanaSuscrpciones() {
        System.out.println("🔁 Cambio a ventana: Suscripciones");
    }

    // ---------------- MÉTODOS DEL PANEL DERECHO ----------------

    @FXML private void registrarInstructor() {
        System.out.println("✅ Acción: Registrar instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void consultarInstructor() {
        System.out.println("🔍 Acción: Consultar instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorBusqueda.fxml", "Instructor");
    }

    @FXML private void actualizarInstructor() {
        System.out.println("✏️ Acción: Actualizar instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorActualizacion.fxml", "Instructor");
    }

    @FXML private void eliminarInstructor() {
        System.out.println("🗑️ Acción: Eliminar instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorEliminacion.fxml", "Instructor");
    }

    // ---------------- CONSULTA DEL FORMULARIO ----------------

    @FXML private void consultarFormularioInstructor() {
        System.out.println("🔎 Consultando instructor con cédula");

    }
}
