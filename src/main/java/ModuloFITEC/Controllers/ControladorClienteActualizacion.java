package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.ClienteDAO;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
import ModuloFITEC.logic.Models.Cliente;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ControladorClienteActualizacion {

    // Navegación lateral
    @FXML private Button buttonInicio;
    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;

    // Botones de operaciones principales
    @FXML private Button buttonRegistrarCliente;
    @FXML private Button buttonConsultarCliente;
    @FXML private Button buttonActualizarCliente;
    @FXML private Button buttonEliminarCliente;

    //  Formulario de búsqueda
    @FXML private TextField textFieldNombreCedula;
    @FXML private Button buttonConsultarFormulario;

    // Tabla de datos
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

    // Campos de edición
    @FXML private SplitMenuButton tipoSuscripcionMenu;
    @FXML private SplitMenuButton splitMenuSucursal;
    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField telefonoField;
    @FXML private TextField emailField;
    @FXML private TextField direccionField;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private Button buttonActualizarFormulario;

    @FXML private Text textNombreServidor;
    @FXML private ImageView imageViewNomina;

    private Cliente clienteSeleccionado = null;
    private Integer idSuscripcionSeleccionada = null;

    @FXML
    public void initialize() {
        configurarTabla();
        cargarSuscripciones();
        configurarSplitMenuSucursal();
        buttonActualizarFormulario.setDisable(true);

        // Listener para habilitar el botón de actualizar solo si hay cliente seleccionado
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            clienteSeleccionado = newSel;
            buttonActualizarFormulario.setDisable(newSel == null);
            if (newSel != null) {
                cargarDatosClienteSeleccionado(newSel);
            } else {
                limpiarCampos();
            }
        });

        cargarClientes();
        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte()? "Nodo Norte" : "Nodo Sur");
        buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
    }

    private void configurarTabla() {
        columnSucursal.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getIdSucursal()));
        columnCedula.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCedulaCliente()));
        columnNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        columnApellido.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getApellido()));
        columnTelefono.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTelefono()));
        columnEmail.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));
        columnFechaNacimiento.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getFechaNacimiento().toString()));
        columnFechaRegistro.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getFechaRegistro().toString()));
        columnDireccion.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDireccion()));
        columnIdSuscripcion.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdSuscripcion()).asObject());
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

    private void cargarSuscripciones() {
        tipoSuscripcionMenu.getItems().clear();
        try {
            List<Suscripcion> suscripciones = SuscripcionDAO.getInstancia().listar("SUSCRIPCION");
            for (Suscripcion s : suscripciones) {
                MenuItem item = new MenuItem(s.getTipo());
                item.setOnAction(e -> {
                    tipoSuscripcionMenu.setText(s.getTipo());
                    idSuscripcionSeleccionada = s.getIdSuscripcion();
                });
                tipoSuscripcionMenu.getItems().add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "No se pudieron cargar las suscripciones.");
        }
    }

    private void configurarSplitMenuSucursal() {
        splitMenuSucursal.getItems().forEach(item -> {
            item.setOnAction(e -> splitMenuSucursal.setText(item.getText()));
        });
    }

    private void cargarDatosClienteSeleccionado(Cliente cliente) {
        nombreField.setText(cliente.getNombre());
        apellidoField.setText(cliente.getApellido());
        telefonoField.setText(cliente.getTelefono());
        emailField.setText(cliente.getEmail());
        direccionField.setText(cliente.getDireccion());
        fechaNacimientoPicker.setValue(cliente.getFechaNacimiento());
        tipoSuscripcionMenu.setText(obtenerTipoSuscripcionPorId(cliente.getIdSuscripcion()));
        idSuscripcionSeleccionada = cliente.getIdSuscripcion();
        splitMenuSucursal.setText(cliente.getIdSucursal());
    }

    private String obtenerTipoSuscripcionPorId(int idSuscripcion) {
        try {
            List<Suscripcion> suscripciones = SuscripcionDAO.getInstancia().listar("SUSCRIPCION");
            for (Suscripcion s : suscripciones) {
                if (s.getIdSuscripcion() == idSuscripcion) {
                    return s.getTipo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Escoja el tipo de suscripción";
    }

    private void limpiarCampos() {
        nombreField.clear();
        apellidoField.clear();
        telefonoField.clear();
        emailField.clear();
        direccionField.clear();
        fechaNacimientoPicker.setValue(null);
        tipoSuscripcionMenu.setText("Escoja el tipo de suscripción");
        splitMenuSucursal.setText("Escoja la sucursal");
        idSuscripcionSeleccionada = null;
    }

    @FXML
    private void consultarFormulario() {
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
    private void actualizarFormulario() {
        if (clienteSeleccionado == null) {
            MetodosFrecuentes.mostrarError("Selección requerida", "Debe seleccionar un cliente en la tabla.");
            return;
        }
        try {
            // Solo actualiza los campos que han cambiado
            String nuevoNombre = nombreField.getText().trim();
            String nuevoApellido = apellidoField.getText().trim();
            String nuevoTelefono = telefonoField.getText().trim();
            String nuevoEmail = emailField.getText().trim();
            String nuevaDireccion = direccionField.getText().trim();
            LocalDate nuevaFechaNacimiento = fechaNacimientoPicker.getValue();
            int nuevoIdSuscripcion = idSuscripcionSeleccionada != null ? idSuscripcionSeleccionada : clienteSeleccionado.getIdSuscripcion();
            String nuevaSucursal = splitMenuSucursal.getText().equals("Escoja la sucursal") ? clienteSeleccionado.getIdSucursal() : splitMenuSucursal.getText();

            Cliente clienteActualizado = new Cliente(
                clienteSeleccionado.getCedulaCliente(),
                nuevoIdSuscripcion,
                nuevaSucursal,
                nuevoNombre.isEmpty() ? clienteSeleccionado.getNombre() : nuevoNombre,
                nuevoApellido.isEmpty() ? clienteSeleccionado.getApellido() : nuevoApellido,
                nuevoTelefono.isEmpty() ? clienteSeleccionado.getTelefono() : nuevoTelefono,
                nuevoEmail.isEmpty() ? clienteSeleccionado.getEmail() : nuevoEmail,
                nuevaFechaNacimiento == null ? clienteSeleccionado.getFechaNacimiento() : nuevaFechaNacimiento,
                clienteSeleccionado.getFechaRegistro(),
                nuevaDireccion.isEmpty() ? clienteSeleccionado.getDireccion() : nuevaDireccion
            );

            ClienteDAO.getInstancia().actualizarCliente(clienteActualizado);
            MetodosFrecuentes.mostrarInfo("Actualización exitosa", "El cliente ha sido actualizado correctamente.");
            cargarClientes();
            limpiarCampos();
            buttonActualizarFormulario.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "Mensaje: " + e.getMessage());
        }
    }

    @FXML private void cambiarVentanaInicio() { MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio"); }
    @FXML private void cambiarVentanaClientes() { MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Cliente"); }
    @FXML private void cambiarVentanaInstructores() { MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor"); }
    @FXML private void cambiarVentanaSuplementos() { MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Suplemento"); }
    @FXML private void cambiarVentanaHistorialDeCompras() { MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Compra"); }
    @FXML private void cambiarVentanaNominaInstructores() { MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructor.fxml", "Nomina"); }
    @FXML private void cambiarVentanaSuscrpciones() { MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcion.fxml", "Suscripcion");}


    @FXML private void registrarCliente() { MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml"); }
    @FXML private void consultarCliente() { MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteBusqueda.fxml"); }
    @FXML private void actualizarCliente() { MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteActualizacion.fxml"); }
    @FXML private void eliminarCliente() { MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCliente.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteEliminacion.fxml"); }
}