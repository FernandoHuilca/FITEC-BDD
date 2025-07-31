package ModuloFITEC.Controllers;

import java.time.LocalDate;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.InstructorDAO;
import ModuloFITEC.logic.DAOs.NominaInstructorDAO;
import ModuloFITEC.logic.Models.Instructor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorInstructorCreacion {

    @FXML private TextField textFieldCedula;
    @FXML private SplitMenuButton splitMenuSucursal;
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldApellido;
    @FXML private TextField textFieldTelefono;
    @FXML private TextField textFieldEmail;
    @FXML private DatePicker datePickerFechaNacimiento;
    @FXML private TextField textFieldDireccion;
    @FXML private TextField textFieldSalario;


    // 🧭 Botones de navegación lateral
    @FXML private Button buttonInicio;
    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;

    // 📋 Campos del formulario principal
    @FXML private TextField cedulaField;
    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField telefonoField;
    @FXML private TextField emailField;
    @FXML private TextField direccionField;
    @FXML private TextField salarioField;
    @FXML private DatePicker fechaNacimientoPicker;

    @FXML private Button buttonRegistrarFormularioInstructor;

    // ⚙️ Botones del panel derecho
    @FXML private Button buttonRegistrarInstructor;
    @FXML private Button buttonConsultarInstructor;
    @FXML private Button buttonActualizarInstructor;
    @FXML private Button buttonEliminarInstructor;

    @FXML private Text textNombreServidor;
    @FXML private ImageView imageViewNomina;

    @FXML
    public void initialize() {
        configurarSplitMenuSucursal();
        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte()? "Nodo Norte" : "Nodo Sur");
        buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
    }
    
    private void configurarSplitMenuSucursal() {
        splitMenuSucursal.getItems().forEach(item -> {
            item.setOnAction(e -> splitMenuSucursal.setText(item.getText()));
        });
    }

    // ---------------- MÉTODOS DE NAVEGACIÓN ----------------

    @FXML private void cambiarVentanaInicio() {
        System.out.println("🔁 Cambio a ventana: Inicio");
        MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
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
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Compra");
    }

    @FXML private void cambiarVentanaHistorialDeCompras() {
        System.out.println("🔁 Cambio a ventana: Historial de Compras");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Compra");
    }

    @FXML private void cambiarVentanaNominaInstructores() {
        System.out.println("🔁 Cambio a ventana: Nómina de Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Instructor");
    }

    @FXML private void cambiarVentanaSuscrpciones() {
        System.out.println("🔁 Cambio a ventana: Suscripciones");
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml","Suscripcion");
    }

    // ---------------- MÉTODOS DE FORMULARIO ----------------


    // ---------------- CRUD (Panel derecho) ----------------

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

    @FXML
    private void registrarFormularioInstructor() {
        System.out.println("Registrando Instructor...");
        try {
            // Validación de campos vacíos
            if (textFieldCedula.getText().trim().isEmpty() ||
                splitMenuSucursal.getText().equals("Escoja la sucursal") ||
                textFieldNombre.getText().trim().isEmpty() ||
                textFieldApellido.getText().trim().isEmpty() ||
                textFieldTelefono.getText().trim().isEmpty() ||
                textFieldEmail.getText().trim().isEmpty() ||
                datePickerFechaNacimiento.getValue() == null ||
                textFieldDireccion.getText().trim().isEmpty() ||
                textFieldSalario.getText().trim().isEmpty()) {
                MetodosFrecuentes.mostrarError("Campos obligatorios", "Por favor, complete todos los campos.");
                return;
            }

            String cedula = textFieldCedula.getText().trim();

            // Validación de salario
            double salario;
            try {
                salario = Double.parseDouble(textFieldSalario.getText().trim());
                if (salario <= 0) {
                    MetodosFrecuentes.mostrarError("Salario inválido", "El salario debe ser un número positivo.");
                    return;
                }
            } catch (NumberFormatException ex) {
                MetodosFrecuentes.mostrarError("Salario inválido", "El salario debe ser un número válido.");
                return;
            }

            // Validación de cédula única en ambas tablas
            boolean existeInstructor = InstructorDAO.getInstancia().existeInstructorPorCedula(cedula);
            boolean existeNomina = NominaInstructorDAO.getInstancia().buscarNominaPorCedula(cedula).next();
            if (existeInstructor || existeNomina) {
                MetodosFrecuentes.mostrarError("Cédula duplicada", "Ya existe un instructor con esa cédula.");
                return;
            }

            String idSucursal = splitMenuSucursal.getText();
            String nombre = textFieldNombre.getText().trim();
            String apellido = textFieldApellido.getText().trim();
            String telefono = textFieldTelefono.getText().trim();
            String email = textFieldEmail.getText().trim();
            LocalDate fechaNacimiento = datePickerFechaNacimiento.getValue();
            String direccion = textFieldDireccion.getText().trim();
            LocalDate fechaContratacion = LocalDate.now();

            Instructor nuevoInstructor = new Instructor(
                cedula,
                idSucursal,
                nombre,
                apellido,
                telefono,
                email,
                fechaNacimiento,
                direccion,
                salario,
                fechaContratacion
            );

            // Registrar en INSTRUCTOR
            InstructorDAO.getInstancia().registrarNuevoInstructor(nuevoInstructor);

            // Registrar en NOMINA_INSTRUCTOR
            NominaInstructorDAO.getInstancia().registrarNominaInstructor(cedula, salario, fechaContratacion);
            MetodosFrecuentes.mostrarInfo("Registro exitoso", "El instructor ha sido registrado correctamente.");
            limpiarCampos();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error " +  "No se pudo registrar el instructor: " + e.getMessage());
            MetodosFrecuentes.mostrarError("Error", "No se pudo registrar el instructor: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        textFieldCedula.clear();
        splitMenuSucursal.setText("Escoja la sucursal");
        textFieldNombre.clear();
        textFieldApellido.clear();
        textFieldTelefono.clear();
        textFieldEmail.clear();
        datePickerFechaNacimiento.setValue(null);
        textFieldDireccion.clear();
        textFieldSalario.clear();
    }


}
