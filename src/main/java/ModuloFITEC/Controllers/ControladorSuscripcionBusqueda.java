package ModuloFITEC.Controllers;


import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladorSuscripcionBusqueda extends ControladorGeneral<Suscripcion> {

    @FXML
    private Button buttonActualizarSuscripcion;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultar;

    @FXML
    private Button buttonConsultarSuscripcion;

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
    private TableColumn<?, ?> tableColumncodigo;

    @FXML
    private TableView<Suscripcion> tableViewSuscripcion;

    @FXML
    private TextField textFieldCodigo;

    private javafx.collections.ObservableList<Suscripcion> suscripcionesList;

    @FXML
    private ImageView imageViewNomina;

    @FXML
    void initialize() {
        suscripcionesList = FXCollections.observableArrayList();

        tableColumncodigo.setCellValueFactory(new PropertyValueFactory("idSuscripcion"));
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tableColumnPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        tableColumnDuracion.setCellValueFactory(new PropertyValueFactory("duracionMeses"));

        try {
            suscripcionesList.addAll(SuscripcionDAO.getInstancia().listar("SUSCRIPCION"));
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudieron cargar las suscripciones: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        tableViewSuscripcion.setItems(suscripcionesList);

        buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
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
        /*Suscripcion suscripcion = null;

        int codigo = obtenerCodigoDeTextField(textFieldCodigo.getText());
        if(codigo <= 0) {
            return;
        }

        suscripcion = obtenerObjetoPorCodigo(codigo, SuscripcionDAO.getInstancia(), "SUSCRIPCION", "IDSUSCRIPCION");
        if (suscripcion == null) {
            return;
        }
        tableViewSuscripcion.getItems().clear();
        suscripcionesList.add(suscripcion);
        tableViewSuscripcion.setItems(suscripcionesList);*/
        mostrarEnTabla(textFieldCodigo, SuscripcionDAO.getInstancia(), "SUSCRIPCION", "IDSUSCRIPCION", suscripcionesList, tableViewSuscripcion);
    }


    @FXML
    void consultarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionBusqueda.fxml", "Consultar Suscripción");
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
