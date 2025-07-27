package ModuloFITEC.Controllers;

import java.sql.SQLException;
import java.util.List;
import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.ClienteDAO;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
import ModuloFITEC.logic.Models.Cliente;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorSuscripcionEliminacion extends ControladorGeneral<Suscripcion>{

    @FXML
    private Button buttonActualizarSuscripcion;

    @FXML
    private Button buttonConsultar;

    @FXML
    private Button buttonConsultarSuscripcion;

    @FXML
    private Button buttonEliminar;

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
    private TableColumn<?, ?> tableColumnCodigo;

    @FXML
    private TableView<Suscripcion> tableViewSuscripcion;

    @FXML
    private TextField textFieldCodigoAConsultar;

    ObservableList<Suscripcion> suscripcionesList;


    private int codigoSuscripcionPorEliminar;

    public ControladorSuscripcionEliminacion(){
        codigoSuscripcionPorEliminar = 0;
    }

    @FXML
    void initialize() {
           // Permite redimensionar
                
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
    void consultarCodigo(ActionEvent event) {

        Suscripcion suscripcion = mostrarEnTabla(textFieldCodigoAConsultar, SuscripcionDAO.getInstancia(), "SUSCRIPCION", "IDSUSCRIPCION", suscripcionesList, tableViewSuscripcion);
        codigoSuscripcionPorEliminar = suscripcion != null ? suscripcion.getIdSuscripcion() : 0;
        tableColumnDescripcion.setPrefWidth(600.0);
        tableColumnDescripcion.setResizable(true);
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

        List<Cliente> listaClientes = null;
        try {
            listaClientes = ClienteDAO.getInstancia().getListaClientesDB();
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo obtener la lista de clientes.");
            e.printStackTrace();
            System.out.println("Error al obtener la lista de clientes: " + e.getMessage());
            return;
        }

        for (Cliente cliente : listaClientes) {
            if (cliente.getIdSuscripcion() == codigoSuscripcionPorEliminar) {
                MetodosFrecuentes.mostrarError("Error", "No se puede eliminar la suscripción porque está asociada a un cliente.");
                return;
            }
        }

        try {
            SuscripcionDAO.getInstancia().eliminarPorCodigo(codigoSuscripcionPorEliminar, "SUSCRIPCION", "IDSUSCRIPCION");
            MetodosFrecuentes.mostrarInfo("Éxito", "Suscripción eliminada correctamente.");
            textFieldCodigoAConsultar.clear();
            codigoSuscripcionPorEliminar = 0;
            tableViewSuscripcion.getItems().clear();
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
