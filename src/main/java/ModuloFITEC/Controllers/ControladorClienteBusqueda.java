package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.ClienteDAO;
import ModuloFITEC.logic.Models.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorClienteBusqueda implements Initializable {

    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonInicio;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;
    @FXML private Button buttonRegistrarCliente;
    @FXML private Button buttonConsultarCliente;
    @FXML private Button buttonActualizarCliente;
    @FXML private Button buttonEliminarCliente;
    @FXML private Button buttonConsultarFormulario;

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

    //Para las consultas:
    ClienteDAO clienteDAO = ClienteDAO.getInstancia();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas
        columnSucursal.setCellValueFactory(new PropertyValueFactory<>("idSucursal"));
        columnCedula.setCellValueFactory(new PropertyValueFactory<>("cedulaCliente"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnIdSuscripcion.setCellValueFactory(new PropertyValueFactory<>("idSuscripcion"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        cargarClientes();

        // Deshabilitar el botón de consultar formulario al inicio si el campo está vacío       
        buttonConsultarFormulario.setDisable(true);
        textFieldNombreCedula.textProperty().addListener((obs, oldText, newText) -> {
            buttonConsultarFormulario.setDisable(newText.trim().isEmpty());
        });
    }

    private void cargarClientes() {
        try {
            List<Cliente> listaClientes = clienteDAO.getListaClientesDB();
            ObservableList<Cliente> clientesObservable = FXCollections.observableArrayList(listaClientes);
            tableViewClientes.setItems(clientesObservable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 📍 Métodos de navegación
    @FXML
    private void cambiarVentanaClientes() {
        System.out.println("🔄 Cambiando a pestaña: Clientes");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Cliente");
    }

    @FXML
    private void cambiarVentanaInstructores() {
        System.out.println("🔄 Cambiando a pestaña: Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
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
        String valorBusqueda = textFieldNombreCedula.getText().trim();
        List<Cliente> clientesConsultados = new ArrayList<>();
        try {
            if (valorBusqueda.isEmpty()) {
                MetodosFrecuentes.mostrarError("Campo vacío", "No ha ingresado texto en el campo.");
                return;
            }

            // Buscar primero por nombre
            clientesConsultados = clienteDAO.getClientesPorNombre(valorBusqueda);

            // Si no hay resultados, buscar por cédula
            if (clientesConsultados.isEmpty()) {
                clientesConsultados = clienteDAO.getClientesPorCedula(valorBusqueda);
            }

            // Mostrar resultados o mensaje de información
            if (clientesConsultados.isEmpty()) {
                MetodosFrecuentes.mostrarInfo("Información", "No se encontraron clientes con: " + valorBusqueda);
               // tableViewClientes.setItems(FXCollections.observableArrayList());
            } else {
                tableViewClientes.setItems(FXCollections.observableArrayList(clientesConsultados));
            }
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "Ocurrió un error: " + e.getMessage());
        }
    }
}
