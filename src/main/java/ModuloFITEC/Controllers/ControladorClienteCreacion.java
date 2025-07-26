package ModuloFITEC.Controllers;

import java.time.LocalDate;
import java.util.List;


import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.ClienteDAO;
import ModuloFITEC.logic.DAOs.SuscripcionesDAO;
import ModuloFITEC.logic.Models.Cliente;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ControladorClienteCreacion {

    @FXML private TextField textFieldCedula;
    @FXML private SplitMenuButton splitMenuSuscripcion;
    @FXML private SplitMenuButton splitMenuSucursal;
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldApellido;
    @FXML private TextField textFieldTelefono;
    @FXML private TextField textFieldEmail;
    @FXML private DatePicker datePickerFechaNacimiento;
    @FXML private TextField textFieldDireccion;



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
    @FXML private Button buttonRegistrarFormularioCliente;


    // Variable para guardar el idSuscripcion seleccionado
    private Integer idSuscripcionSeleccionada = null;



    @FXML
    public void initialize() {
        //configurarSplitMenuSuscripcion();
        cargarSuscripciones();
        configurarSplitMenuSucursal();
    }

    private void cargarSuscripciones() {
        splitMenuSuscripcion.getItems().clear();
        try {
            List<Suscripcion> suscripciones = new SuscripcionesDAO().listarSuscripciones();
            for (Suscripcion s : suscripciones) {
                MenuItem item = new MenuItem(s.getTipo());
                // Al seleccionar, guarda el id en la variable y actualiza el texto
                item.setOnAction(e -> {
                    splitMenuSuscripcion.setText(s.getTipo());
                    idSuscripcionSeleccionada = s.getIdSuscripcion();
                });
                splitMenuSuscripcion.getItems().add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "No se pudieron cargar las suscripciones.");
        }
    }
    private void configurarSplitMenuSuscripcion() {
        splitMenuSuscripcion.getItems().forEach(item -> {
            item.setOnAction(e -> splitMenuSuscripcion.setText(item.getText()));
        });
    }

    private void configurarSplitMenuSucursal() {
        splitMenuSucursal.getItems().forEach(item -> {
            item.setOnAction(e -> splitMenuSucursal.setText(item.getText()));
        });
    }


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
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Compra");
    }

    @FXML
    private void cambiarVentanaHistorialDeCompras() {
        System.out.println("🔄 Cambiando a pestaña: Historial de compras");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Compra");
    }

    @FXML
    private void cambiarVentanaInicio() {
        System.out.println("🔄 Cambiando a pestaña: Inicio");
        MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");

    }

    @FXML
    private void cambiarVentanaNominaInstructores() {
        System.out.println("🔄 Cambiando a pestaña: Nómina de instructores");
    }

    @FXML
    private void cambiarVentanaSuscrpciones() {
        System.out.println("🔄 Cambiando a pestaña: Suscripciones");
    }

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
    private void registrarFormularioCliente() {
        try {
            // Validación 
            if (textFieldCedula.getText().trim().isEmpty() ||
                textFieldNombre.getText().trim().isEmpty() ||
                textFieldApellido.getText().trim().isEmpty() ||
                textFieldTelefono.getText().trim().isEmpty() ||
                textFieldEmail.getText().trim().isEmpty() ||
                datePickerFechaNacimiento.getValue() == null ||
                textFieldDireccion.getText().trim().isEmpty() ||
                idSuscripcionSeleccionada == null ||
                splitMenuSucursal.getText().equals("Escoja la sucursal")) {
                MetodosFrecuentes.mostrarError("Campos obligatorios", "Por favor, complete todos los campos.");
                return;
            }

            String cedula = textFieldCedula.getText().trim();
            int idSuscripcion = idSuscripcionSeleccionada;
            String idSucursal = obtenerIdSucursal(splitMenuSucursal.getText());
            String nombre = textFieldNombre.getText().trim();
            String apellido = textFieldApellido.getText().trim();
            String telefono = textFieldTelefono.getText().trim();
            String email = textFieldEmail.getText().trim();
            LocalDate fechaNacimiento = datePickerFechaNacimiento.getValue();
            LocalDate fechaRegistro = LocalDate.now();
            String direccion = textFieldDireccion.getText().trim();

            Cliente nuevoCliente = new Cliente(
                cedula,
                idSuscripcion,
                idSucursal,
                nombre,
                apellido,
                telefono,
                email,
                fechaNacimiento,
                fechaRegistro,
                direccion
            );

            ClienteDAO.getInstancia().registrarNuevoCliente(nuevoCliente);
            MetodosFrecuentes.mostrarInfo("Registro exitoso", "El cliente ha sido registrado correctamente.");

            limpiarCampos();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar cliente: " + e.getMessage());
            MetodosFrecuentes.mostrarError("Error", "No se pudo registrar el cliente: " + e.getMessage());
        }
    }

        public String obtenerIdSucursal(String texto) {
        if (texto.equals("Quito Sur")) return "QUITO_SUR";
        if (texto.equals("Quito Norte")) return "QUITO_NORTE";
        // Puedes agregar más sucursales aquí si lo necesitas
        return null;
    }

    private void limpiarCampos() {
        textFieldCedula.clear();
        splitMenuSuscripcion.setText("Escoja el tipo de suscripción");
        splitMenuSucursal.setText("Escoja la sucursal");
        textFieldNombre.clear();
        textFieldApellido.clear();
        textFieldTelefono.clear();
        textFieldEmail.clear();
        datePickerFechaNacimiento.setValue(null);
        textFieldDireccion.clear();
    }
}
