package ModuloFITEC.Controllers;

import java.util.List;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.InstructorDAO;
import ModuloFITEC.logic.DAOs.NominaInstructorDAO;
import ModuloFITEC.logic.Models.Instructor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorInstructorActualizacion {
    @FXML private TableView<Instructor> tableViewInstructores;
    @FXML private TableColumn<Instructor, String> columnSucursal;
    @FXML private TableColumn<Instructor, String> columnCedula;
    @FXML private TableColumn<Instructor, String> columnNombre;
    @FXML private TableColumn<Instructor, String> columnApellido;
    @FXML private TableColumn<Instructor, String> columnTelefono;
    @FXML private TableColumn<Instructor, String> columnEmail;
    @FXML private TableColumn<Instructor, String> columnFechaNacimiento;
    @FXML private TableColumn<Instructor, String> columnDireccion;

    @FXML private TextField textFieldCedula;
    @FXML private SplitMenuButton splitMenuSucursal;
    @FXML private TextField textFieldSalario;
    @FXML private Button buttonActualizarFormulario;
    @FXML private TextField textFieldNombreCedula;

    private Instructor instructorSeleccionado = null;


    // 🧭 Botones de navegación lateral
    @FXML private Button buttonInicio;
    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;

    // 🧾 Botones de navegación del panel derecho
    @FXML private Button buttonRegistrarInstructor;
    @FXML private Button buttonConsultarInstructor;
    @FXML private Button buttonActualizarInstructor;
    @FXML private Button buttonEliminarInstructor;

    // 🔍 Buscador por cédula (código)
    @FXML private TextField textFieldCedulaBusqueda;
    @FXML private Button buttonConsultarFormulario;

    // 📊 Tabla de búsqueda rápida
    @FXML private TableView<?> tablaInstructores;
    @FXML private TableColumn<?, ?> columnaCedula;
    @FXML private TableColumn<?, ?> columnaNombre;
    @FXML private TableColumn<?, ?> columnaApellido;
    @FXML private TableColumn<?, ?> columnaTelefono;

    // ✏️ Campos de actualización del formulario
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldApellido;
    @FXML private TextField textFieldTelefono;
    @FXML private TextField textFieldEmail;
    @FXML private TextField textFieldDireccion;
    @FXML private TextField textFieldTelefonoSecundario;
    @FXML private DatePicker datePickerFechaNacimiento;
    @FXML private Button buttonActualizarFormularioInstructor;
    @FXML private Text textNombreServidor;
    @FXML private ImageView imageViewNomina;

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
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void cambiarVentanaSuscrpciones() {
        System.out.println("🔁 Cambio a ventana: Suscripciones");
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml");
    }

    // ---------------- BOTONES DEL PANEL DERECHO ----------------

    @FXML private void registrarInstructor() {
        System.out.println("📝 Registro de nuevo instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void consultarInstructor() {
        System.out.println("🔍 Consulta de instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorBusqueda.fxml", "Instructor");
    }

    @FXML private void actualizarInstructor() {
        System.out.println("🔄 Acceso a actualización de instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorActualizacion.fxml", "Instructor");
    }

    @FXML private void eliminarInstructor() {
        System.out.println("🗑️ Eliminación de instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorEliminacion.fxml", "Instructor");
    }

    // ---------------- CONSULTA DE FORMULARIO ----------------

    @FXML private void consultarFormulario() {
        System.out.println("🔎 Consultando instructor con cédula");
    }

  

    @FXML
    public void initialize() {
        configurarTabla();
        cargarInstructores();

        buttonActualizarFormulario.setDisable(true);

        tableViewInstructores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            instructorSeleccionado = newSel;
            buttonActualizarFormulario.setDisable(newSel == null);
            if (newSel != null) {
                cargarDatosEnFormulario(newSel);
            }
        });

        textFieldNombreCedula.textProperty().addListener((obs, oldText, newText) -> {
            // Puedes agregar lógica para búsqueda dinámica si lo deseas
        });

        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte()? "Nodo Norte" : "Nodo Sur");
        buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
    }

    private void configurarTabla() {
        columnSucursal.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getIdSucursal()));
        columnCedula.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCedulaInstructor()));
        columnNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        columnApellido.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getApellido()));
        columnTelefono.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTelefono()));
        columnEmail.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));
        columnFechaNacimiento.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getFechaNacimiento().toString()));
        columnDireccion.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDireccion()));
    }

    private void cargarInstructores() {
        try {
            List<Instructor> listaInstructores = InstructorDAO.getInstancia().getListaInstructoresDB();
            tableViewInstructores.setItems(FXCollections.observableArrayList(listaInstructores));
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "No se pudieron cargar los instructores.");
        }
    }

    private void cargarDatosEnFormulario(Instructor instructor) {
        //textFieldCedula.setText(instructor.getCedulaInstructor());
        splitMenuSucursal.setText(instructor.getIdSucursal());
        textFieldNombre.setText(instructor.getNombre());
        textFieldApellido.setText(instructor.getApellido());
        textFieldTelefono.setText(instructor.getTelefono());
        textFieldEmail.setText(instructor.getEmail());
        datePickerFechaNacimiento.setValue(instructor.getFechaNacimiento());
        textFieldDireccion.setText(instructor.getDireccion());
        // Cargar salario desde la nómina
        try {
            double salario = obtenerSalarioInstructor(instructor.getCedulaInstructor());
            textFieldSalario.setText(String.valueOf(salario));
        } catch (Exception e) {
            textFieldSalario.setText("");
        }
    }

    private double obtenerSalarioInstructor(String cedula) throws Exception {
        // Implementa el método en NominaDeInstructorDAO para obtener el salario por cédula
        return NominaInstructorDAO.getInstancia().obtenerSalarioPorCedula(cedula);
    }
  // ---------------- ACTUALIZAR DATOS ----------------
    @FXML
    private void actualizarFormularioInstructor() {
        if (instructorSeleccionado == null) {
            MetodosFrecuentes.mostrarError("Selección requerida", "Debe seleccionar un instructor en la tabla.");
            return;
        }
        try {
            // Validación de campos vacíos
            if (splitMenuSucursal.getText().equals("Escoja la sucursal") ||
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

            Instructor instructorActualizado = new Instructor(
                instructorSeleccionado.getCedulaInstructor(),
                splitMenuSucursal.getText(),
                textFieldNombre.getText().trim(),
                textFieldApellido.getText().trim(),
                textFieldTelefono.getText().trim(),
                textFieldEmail.getText().trim(),
                datePickerFechaNacimiento.getValue(),
                textFieldDireccion.getText().trim(),
                salario,
                null // Fecha de contratación no se actualiza
            );

            // Actualizar en INSTRUCTOR
            InstructorDAO.getInstancia().actualizarInstructor(instructorActualizado);

            // Actualizar salario en NOMINA_INSTRUCTOR
            NominaInstructorDAO.getInstancia().actualizarSalarioInstructor(instructorActualizado.getCedulaInstructor(), salario);

            MetodosFrecuentes.mostrarInfo("Actualización exitosa", "El instructor ha sido actualizado correctamente.");
            cargarInstructores();
            buttonActualizarFormulario.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "No se pudo actualizar el instructor: " + e.getMessage());
        }
    }

}
