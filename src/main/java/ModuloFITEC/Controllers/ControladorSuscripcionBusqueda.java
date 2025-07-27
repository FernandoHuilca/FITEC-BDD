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
    private Button buttonConsultar;

    @FXML
    private Button buttonConsultarSuscripcion;

    @FXML
    private Button buttonEliminarSuscripcion;


   

    @FXML
    private Button buttonRegistrarSuscripcion;



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

        //buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        //imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
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

    @FXML
    void actualizarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionActualizacion.fxml", "Actualizar Suscripción");
    }

}
