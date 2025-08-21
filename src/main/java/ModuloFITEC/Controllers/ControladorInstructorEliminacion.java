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

public class ControladorInstructorEliminacion {


    @FXML private TableView<Instructor> tableViewInstructores;
    @FXML private TableColumn<Instructor, String> columnSucursal;
    @FXML private TableColumn<Instructor, String> columnCedula;
    @FXML private TableColumn<Instructor, String> columnNombre;
    @FXML private TableColumn<Instructor, String> columnApellido;
    @FXML private TableColumn<Instructor, String> columnTelefono;
    @FXML private TableColumn<Instructor, String> columnEmail;
    @FXML private TableColumn<Instructor, String> columnFechaNacimiento;
    @FXML private TableColumn<Instructor, String> columnDireccion;

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

    // 🧾 Botones del panel derecho
    @FXML private Button buttonRegistrarInstructor;
    @FXML private Button buttonConsultarInstructor;
    @FXML private Button buttonActualizarInstructor;
    @FXML private Button buttonEliminarInstructor;

    // 🔍 Buscador
    @FXML private TextField textFieldCedulaBusqueda;
    @FXML private Button buttonConsultarFormulario;

    // 📋 Tabla de resultados
    @FXML private TableView<?> tablaInstructores;
    @FXML private TableColumn<?, ?> columnaCedula;
    @FXML private TableColumn<?, ?> columnaNombre;
    @FXML private TableColumn<?, ?> columnaApellido;
    @FXML private TableColumn<?, ?> columnaTelefono;

    // ❌ Botón de eliminación
    @FXML private Button buttonEliminarFormulario;

    @FXML private Text textNombreServidor;
    @FXML private ImageView imageViewNomina;

    @FXML
    public void initialize() {
        configurarTabla();
        cargarInstructores();

        buttonEliminarFormulario.setDisable(true);

        tableViewInstructores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            instructorSeleccionado = newSel;
            buttonEliminarFormulario.setDisable(newSel == null);
        });

        buttonConsultarFormulario.setDisable(true);
        textFieldNombreCedula.textProperty().addListener((obs, oldText, newText) -> {
            buttonConsultarFormulario.setDisable(newText.trim().isEmpty());
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


        @FXML
    private void consultarFormulario() {
        System.out.println("🔍 Consultando instructores por nombre o cédula");
        String valorBusqueda = textFieldNombreCedula.getText().trim();
        List<Instructor> instructoresConsultados;
        try {
            if (valorBusqueda.isEmpty()) {
                MetodosFrecuentes.mostrarError("Campo vacío", "No ha ingresado texto en el campo.");
                return;
            }
            instructoresConsultados = InstructorDAO.getInstancia().getInstructoresPorNombre(valorBusqueda);
            if (instructoresConsultados.isEmpty()) {
                instructoresConsultados = InstructorDAO.getInstancia().getInstructoresPorCedula(valorBusqueda);
            }
            if (instructoresConsultados.isEmpty()) {
                MetodosFrecuentes.mostrarInfo("Información", "No se encontraron instructores con: " + valorBusqueda);
            } else {
                tableViewInstructores.setItems(FXCollections.observableArrayList(instructoresConsultados));
            }
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "Ocurrió un error: " + e.getMessage());
        }
    }



    @FXML
    private void eliminarInstructorFormulario() {
        System.out.println("🗑️ Eliminando instructor con cédula: ");
        if (instructorSeleccionado == null) {
            MetodosFrecuentes.mostrarError("Selección requerida", "Debe seleccionar un instructor en la tabla.");
            return;
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro que desea eliminar al instructor?");
        confirmacion.setContentText("Cédula: " + instructorSeleccionado.getCedulaInstructor() + "\nNombre: " + instructorSeleccionado.getNombre());
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    // Eliminar primero de NOMINA_INSTRUCTOR
                    //NominaInstructorDAO.getInstancia().eliminarPorCodigo(instructorSeleccionado.getCedulaInstructor(), "NOMINA_INSTRUCTOR", "CEDULAINSTRUCTOR");
                     NominaInstructorDAO.getInstancia().eliminarPorCedula(instructorSeleccionado.getCedulaInstructor());
                    // Eliminar de INSTRUCTOR
                    InstructorDAO.getInstancia().eliminarPorCedula(instructorSeleccionado.getCedulaInstructor());
                    MetodosFrecuentes.mostrarInfo("Eliminación exitosa", "El instructor ha sido eliminado correctamente.");
                    cargarInstructores();
                    buttonEliminarFormulario.setDisable(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    MetodosFrecuentes.mostrarError("Error", "No se pudo eliminar el instructor: " + e.getMessage());
                }
            }
        });
    }

    // ---------------- MÉTODOS DE NAVEGACIÓN ----------------

    @FXML private void cambiarVentanaInicio() {
        System.out.println("Cambio a ventana: Inicio");
        MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
    }

    @FXML private void cambiarVentanaClientes() {
        System.out.println("Cambio a ventana: Clientes");
        MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Cliente");
    }

    @FXML private void cambiarVentanaInstructores() {
        System.out.println("Cambio a ventana: Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void cambiarVentanaSuplementos() {
        System.out.println("Cambio a ventana: Suplementos");
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Compra");
    }

    @FXML private void cambiarVentanaHistorialDeCompras() {
        System.out.println("Cambio a ventana: Historial de Compras");
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Compra");
    }

    @FXML private void cambiarVentanaNominaInstructores() {
        System.out.println("Cambio a ventana: Nómina de Instructores");
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructor.fxml", "Instructor");
    }

    @FXML private void cambiarVentanaSuscrpciones() {
        System.out.println("Cambio a ventana: Suscripciones");
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "ModuloFITEC/views/VistaSuscripcion.fxml", "Suscripcion" );
    }

    // ---------------- MÉTODOS DE ACCIÓN ----------------

    @FXML private void registrarInstructor() {
        System.out.println("Registro de nuevo instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor");
    }

    @FXML private void consultarInstructor() {
        System.out.println("Consulta de instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorBusqueda.fxml", "Instructor");
    }

    @FXML private void actualizarInstructor() {
        System.out.println("Acceso a actualización de instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorActualizacion.fxml", "Instructor");
    }

    @FXML private void eliminarInstructor() {
        System.out.println("Acceso a eliminación de instructor");
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorEliminacion.fxml", "Instructor");
    }

}
