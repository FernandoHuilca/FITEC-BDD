package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.SuplementoDAO;
import ModuloFITEC.logic.Models.Suplemento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControladorSuplementoBusqueda {

    @FXML
    private Button buttonActualizarSuplemento;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultarSuplemento;

    @FXML
    private Button buttonEliminarSuplemento;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private Button buttonRegistrarSuplemento;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

     @FXML
    private TableColumn columnCantidadDisponible;

    @FXML
    private TableColumn columnCategoria;

    @FXML
    private TableColumn columnCodigo;

    @FXML
    private TableColumn columnFechaVencimiento;

    @FXML
    private TableColumn columnNombre;

    @FXML
    private TableColumn columnPrecio;

    @FXML
    private TableColumn columnSucursal;

    @FXML
    private TableView<Suplemento> tableSuplementos;

    @FXML
    private TextField textFieldNombreSuplemento;

    private ObservableList<Suplemento> suplementos;

    private final SuplementoDAO suplementoDAO = new SuplementoDAO();

    @FXML
    public void initialize() {
        suplementos = FXCollections.observableArrayList();

        this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("idSuplemento"));
        this.columnSucursal.setCellValueFactory(new PropertyValueFactory<>("idSucursal"));
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        this.columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.columnCantidadDisponible.setCellValueFactory(new PropertyValueFactory<>("cantidadDisponible"));
        this.columnFechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));

        try {
            suplementos.setAll(suplementoDAO.listarSuplementos()); 
            tableSuplementos.setItems(suplementos);                
        } catch (Exception e) {
            mostrarAlerta("Error al cargar suplementos", "No se pudieron obtener los datos de la base.");
            e.printStackTrace();
        }
    }

    @FXML
    void buttonVentanaActualizarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoActualizacion.fxml", "Actualizar Suplemento");
    }

    @FXML
    void cambiarVentanaClientes(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Registrar Cliente");
    }

    @FXML
    void cambiarVentanaConsultarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoBusqueda.fxml", "Consultar Suplemento");
    }

    @FXML
    void cambiarVentanaEliminarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoEliminacion.fxml", "Eliminar Suplemento");
    }

    @FXML
    void cambiarVentanaHistorialDeCompras(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Crear Compra");
    }

    @FXML
    void cambiarVentanaInicio(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
    }

    @FXML
    void cambiarVentanaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Crear Instructor");
    }

    @FXML
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Consultar Nómina");
    }

    @FXML
    void cambiarVentanaRegistrarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Registrar Suplemento");
    }

    @FXML
    void cambiarVentanaSuplementos(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Crear Suplemento");
    }

    @FXML
    void cambiarVentanaSuscripciones(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Registrar Suscripción");
    }

    @FXML
    private void cambiarVentanaActualizarSuplemento(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuplemento.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoActualizacion.fxml", "Actualizar Suplemento");
    }

    @FXML
    void consultarSuplemento(ActionEvent event) {
        try {
            Suplemento suplemento = suplementoDAO.buscarPorNombre(textFieldNombreSuplemento.getText());
            if (suplemento != null) {
                suplementos.clear();
                suplementos.add(suplemento);
                tableSuplementos.setItems(suplementos);
            } else {
                mostrarAlerta("Suplemento no encontrado", "No se encontró un suplemento con ese nombre.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al buscar el suplemento.");
            e.printStackTrace();
        }

        textFieldNombreSuplemento.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
