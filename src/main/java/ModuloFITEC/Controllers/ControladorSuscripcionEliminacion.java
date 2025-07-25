package ModuloFITEC.Controllers;

import java.sql.SQLException;
import java.util.Observable;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.SuscripcionesDAO;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControladorSuscripcionEliminacion {

    @FXML
    private Button buttonActualizarSuscripcion;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultar;

    @FXML
    private Button buttonConsultarSuscripcion;

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonEliminarSuscripcion;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private Button buttonRegistrarSuscripcion;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcion;

    @FXML
    private TableColumn<?, ?> tableColumnDuracion;

    @FXML
    private TableColumn<?, ?> tableColumnPrecio;

    @FXML
    private TableColumn<?, ?> tableColumnTipo;

    @FXML
    private TableColumn<?, ?> tableColumnCodigo;

    @FXML
    private TableView<Suscripcion> tableViewSuscripcion;

    @FXML
    private TextField textFieldCodigoAConsultar;

    ObservableList<Suscripcion> suscripcionesList;

    private SuscripcionesDAO suscripcionesDAO;

    private int codigoSuscripcionPorEliminar;

    public ControladorSuscripcionEliminacion() {
        // Constructor vacío
        this.suscripcionesDAO = new SuscripcionesDAO();
        codigoSuscripcionPorEliminar = 0;
    }

    @FXML
    void initialize() {
                
        this.suscripcionesList = FXCollections.observableArrayList();
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory("idSuscripcion"));
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tableColumnPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        tableColumnDuracion.setCellValueFactory(new PropertyValueFactory("duracionMeses"));
    }


    @FXML
    void actualizarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionActualizacion.fxml", "Actualizar Suscripción");
    }

     @FXML
    void cambiarVentanaClientes(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Clientes");
    }

    @FXML
    void cambiarVentanaHistorialDeCompras(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonHistorialDeCompras.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Historial de Compras");
    }

    @FXML
    void cambiarVentanaInicio(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonInicio.getScene().getWindow(), "/ModuloFITEC/views/VistaInicio.fxml", "Inicio");
    }

    @FXML
    void cambiarVentanaInstructores(ActionEvent event) {
        System.out.println("Instructores button clicked");
        //MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/In.fxml", "Instructores");
    }

    @FXML
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Nómina de Instructores");
    }

    @FXML
    void cambiarVentanaSuplementos(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Suplementos");
    }

    @FXML
    void cambiarVentanaSuscripciones(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Suscripciones");
    }

    @FXML
    void consultarCodigo(ActionEvent event) {

        int codigo = ControladorGeneral.obtenerCodigo(textFieldCodigoAConsultar.getText());
        if (codigo <= 0) {
            return;
        }
        Suscripcion suscripcion = ControladorGeneral.obtenerSuscripcionPorCodigo(codigo);
        if (suscripcion == null) {
            return;
        }
        tableViewSuscripcion.getItems().clear();
        suscripcionesList.add(suscripcion);
        tableViewSuscripcion.setItems(suscripcionesList);

        codigoSuscripcionPorEliminar = codigo;
    }

     @FXML
    void consultarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionBusqueda.fxml", "Consultar Suscripción");
    }

    @FXML
    void eliminarSuscripcionFormulario(ActionEvent event) {
        if(codigoSuscripcionPorEliminar <= 0) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, consulte una suscripción antes de eliminar.");
            return;
        }

        try {
            suscripcionesDAO.eliminarPorCodigo(codigoSuscripcionPorEliminar);
            MetodosFrecuentes.mostrarInfo("Éxito", "Suscripción eliminada correctamente.");
        } catch (SQLException e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo eliminar la suscripción.");
        }
    }

    @FXML
    void eliminarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionEliminacion.fxml", "Eliminar Suscripción");
    }

    @FXML
    void registrarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Registrar Suscripción");
    }

}
