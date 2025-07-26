package ModuloFITEC.Controllers;

import java.util.List;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.ClienteDAO;
import ModuloFITEC.logic.DAOs.CompraDAO;
import ModuloFITEC.logic.Models.Cliente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorClienteEliminacion {

    @FXML private TableView<Cliente> tableViewClientes;
    @FXML private TableColumn<Cliente, String> columnSucursal;
    @FXML private TableColumn<Cliente, String> columnCedula;
    @FXML private TableColumn<Cliente, String> columnNombre;
    @FXML private TableColumn<Cliente, String> columnApellido;
    @FXML private TableColumn<Cliente, Integer> columnIdSuscripcion;
    @FXML private TableColumn<Cliente, String> columnTelefono;
    @FXML private TableColumn<Cliente, String> columnEmail;
    @FXML private TableColumn<Cliente, String> columnFechaNacimiento;
    @FXML private TableColumn<Cliente, String> columnFechaRegistro;
    @FXML private TableColumn<Cliente, String> columnDireccion;

    @FXML private TextField textFieldNombreCedula;

    
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


    private Cliente clienteSeleccionado = null;
    
    @FXML
    public void initialize() {
        configurarTabla();
        buttonEliminarFormulario.setDisable(true);

        tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            clienteSeleccionado = newSel;
            buttonEliminarFormulario.setDisable(newSel == null);
        });

        cargarClientes();
    }



    // 🧠 Métodos de navegación lateral
    @FXML
    private void cambiarVentanaInicio() {
        System.out.println("➡️ Cambiar a vista Inicio");
        MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
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
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Compra");
    }

    @FXML
    private void cambiarVentanaHistorialDeCompras() {
        System.out.println("➡️ Cambiar a vista Historial de Compras");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Compra");
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


    
    private void configurarTabla() {
        columnSucursal.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getIdSucursal()));
        columnCedula.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCedulaCliente()));
        columnNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        columnApellido.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getApellido()));
        columnIdSuscripcion.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdSuscripcion()).asObject());
        columnTelefono.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTelefono()));
        columnEmail.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));
        columnFechaNacimiento.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getFechaNacimiento().toString()));
        columnFechaRegistro.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getFechaRegistro().toString()));
        columnDireccion.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDireccion()));
    }

    private void cargarClientes() {
        try {
            List<Cliente> listaClientes = ClienteDAO.getInstancia().getListaClientesDB();
            tableViewClientes.setItems(FXCollections.observableArrayList(listaClientes));
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "No se pudieron cargar los clientes.");
        }
    }

    @FXML
    private void ConsultarClienteFormulario() {
        String valorBusqueda = textFieldNombreCedula.getText().trim();
        List<Cliente> clientesConsultados = FXCollections.observableArrayList();
        try {
            if (valorBusqueda.isEmpty()) {
                MetodosFrecuentes.mostrarError("Campo vacío", "No ha ingresado texto en el campo.");
                return;
            }
            clientesConsultados = ClienteDAO.getInstancia().getClientesPorNombre(valorBusqueda);
            if (clientesConsultados.isEmpty()) {
                clientesConsultados = ClienteDAO.getInstancia().getClientesPorCedula(valorBusqueda);
            }
            if (clientesConsultados.isEmpty()) {
                MetodosFrecuentes.mostrarInfo("Información", "No se encontraron clientes con: " + valorBusqueda);
                tableViewClientes.setItems(FXCollections.observableArrayList());
            } else {
                tableViewClientes.setItems(FXCollections.observableArrayList(clientesConsultados));
            }
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "Ocurrió un error: " + e.getMessage());
        }
    }

    @FXML
    private void eliminarClienteFormulario() {
        if (clienteSeleccionado == null) {
            MetodosFrecuentes.mostrarError("Selección requerida", "Debe seleccionar un cliente en la tabla.");
            return;
        }
        try {
            // Verifica si el cliente tiene compras
            boolean tieneCompras = new CompraDAO().existeCompraPorCedula(clienteSeleccionado.getCedulaCliente());
            if (tieneCompras) {
                MetodosFrecuentes.mostrarError("No permitido", "No se puede eliminar el cliente porque tiene compras registradas.");
                return;
            }
            ClienteDAO.getInstancia().eliminarCliente(clienteSeleccionado.getCedulaCliente());
            MetodosFrecuentes.mostrarInfo("Eliminación exitosa", "El cliente ha sido eliminado correctamente.");
            cargarClientes();
            buttonEliminarFormulario.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "No se pudo eliminar el cliente: " + e.getMessage());
        }
    }
}
