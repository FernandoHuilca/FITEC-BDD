package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.InstructorDAO;
import ModuloFITEC.logic.Models.Instructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorInstructorBusqueda implements Initializable {

    @FXML private Button buttonClientes;
    @FXML private Button buttonInstructores;
    @FXML private Button buttonSuplementos;
    @FXML private Button buttonHistorialDeCompras;
    @FXML private Button buttonInicio;
    @FXML private Button buttonNominaInstructores;
    @FXML private Button buttonSuscripciones;
    @FXML private Button buttonRegistrarInstructor;
    @FXML private Button buttonConsultarInstructor;
    @FXML private Button buttonActualizarInstructor;
    @FXML private Button buttonEliminarInstructor;
    @FXML private Button buttonConsultarFormulario;

    @FXML private TableView<Instructor> tableViewClientes;
    @FXML private TableColumn<Instructor, String> columnSucursal;
    @FXML private TableColumn<Instructor, String> columnCedula;
    @FXML private TableColumn<Instructor, String> columnNombre;
    @FXML private TableColumn<Instructor, String> columnApellido;
    @FXML private TableColumn<Instructor, String> columnTelefono;
    @FXML private TableColumn<Instructor, String> columnEmail;
    @FXML private TableColumn<Instructor, String> columnFechaNacimiento;
    @FXML private TableColumn<Instructor, String> columnDireccion;

    @FXML private TextField textFieldNombreCedula;
    @FXML private Text textNombreServidor;
    @FXML private ImageView imageViewNomina;

    private final InstructorDAO instructorDAO = InstructorDAO.getInstancia();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas
        columnSucursal.setCellValueFactory(new PropertyValueFactory<>("idSucursal"));
        columnCedula.setCellValueFactory(new PropertyValueFactory<>("cedulaInstructor"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        cargarInstructores();

        // Deshabilitar el botón de consultar formulario al inicio si el campo está vacío
        buttonConsultarFormulario.setDisable(true);
        textFieldNombreCedula.textProperty().addListener((obs, oldText, newText) -> {
            buttonConsultarFormulario.setDisable(newText.trim().isEmpty());
        });

        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte()? "Nodo Norte" : "Nodo Sur");
        buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
    }

    private void cargarInstructores() {
        try {
            List<Instructor> listaInstructores = instructorDAO.getListaInstructoresDB();
            ObservableList<Instructor> instructoresObservable = FXCollections.observableArrayList(listaInstructores);
            tableViewClientes.setItems(instructoresObservable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Métodos de navegación (igual que en tu código actual)
    @FXML private void cambiarVentanaInicio() { MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio"); }
    @FXML private void cambiarVentanaClientes() { MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Cliente"); }
    @FXML private void cambiarVentanaInstructores() { MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor"); }
    @FXML private void cambiarVentanaSuplementos() { MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Suplemento"); }
    @FXML private void cambiarVentanaHistorialDeCompras() { MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Compra"); }
    @FXML private void cambiarVentanaNominaInstructores() { MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructor.fxml", "Nomina instructor"); }
    @FXML private void cambiarVentanaSuscrpciones() { MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcion.fxml", "Suscripcion");}

    @FXML private void registrarInstructor() { MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructor"); }
    @FXML private void consultarInstructor() { MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorBusqueda.fxml", "Instructor"); }
    @FXML private void actualizarInstructor() { MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorActualizacion.fxml", "Instructor"); }
    @FXML private void eliminarInstructor() { MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarInstructor.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorEliminacion.fxml", "Instructor"); }

    // Consulta por nombre o cédula
    @FXML
    private void consultarFormulario() {
        System.out.println("🧾 Acción: Consultar formulario de instructor");
        String valorBusqueda = textFieldNombreCedula.getText().trim();
        List<Instructor> instructoresConsultados;
        try {
            if (valorBusqueda.isEmpty()) {
                MetodosFrecuentes.mostrarError("Campo vacío", "No ha ingresado texto en el campo.");
                return;
            }

            // Buscar primero por nombre
            instructoresConsultados = instructorDAO.getInstructoresPorNombre(valorBusqueda);

            // Si no hay resultados, buscar por cédula
            if (instructoresConsultados.isEmpty()) {
                instructoresConsultados = instructorDAO.getInstructoresPorCedula(valorBusqueda);
            }

            // Mostrar resultados o mensaje de información
            if (instructoresConsultados.isEmpty()) {
                MetodosFrecuentes.mostrarInfo("Información", "No se encontraron instructores con: " + valorBusqueda);
            } else {
                tableViewClientes.setItems(FXCollections.observableArrayList(instructoresConsultados));
            }
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "Ocurrió un error: " + e.getMessage());
        }
    }
}