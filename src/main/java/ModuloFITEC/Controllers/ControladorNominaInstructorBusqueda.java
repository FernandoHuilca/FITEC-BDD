package ModuloFITEC.Controllers;

import java.util.List;
import java.util.Observable;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.NominaInstructorDAO;
import ModuloFITEC.logic.Models.NominaInstructor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControladorNominaInstructorBusqueda extends ControladorGeneral<NominaInstructor> {

    @FXML
    private Button buttonActualizarNomina;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultarFormulario;

    @FXML
    private Button buttonConsultarNomina;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private TableColumn<?, ?> tableColumnCedulaInstructor;

    @FXML
    private TableColumn<?, ?> tableColumnFechaContratacion;

    @FXML
    private TableColumn<?, ?> tableColumnSalario;

    @FXML
    private TableView<NominaInstructor> tableViewNomina;

    @FXML
    private TextField textFieldCedula;

    @FXML
    private Text textNombreServidor;

    ObservableList<NominaInstructor> listaNominaInstructores;

    @FXML
    void initialize() {
        listaNominaInstructores = FXCollections.observableArrayList();

        tableColumnCedulaInstructor.setCellValueFactory(new PropertyValueFactory("cedulaInstructor"));
        tableColumnSalario.setCellValueFactory(new PropertyValueFactory("salario"));
        tableColumnFechaContratacion.setCellValueFactory(new PropertyValueFactory("fechaContratacionSimple"));

        try {
            listaNominaInstructores.addAll(NominaInstructorDAO.getInstancia().listar("NOMINA_INSTRUCTOR"));
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo cargar la nómina de instructores: " + e.getMessage());
            e.printStackTrace();
            System.out.println("Error al cargar la nómina de instructores: " + e.getMessage());
            return;
        }

        tableViewNomina.setItems(listaNominaInstructores);
        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte() ? "Nodo Norte" : "Nodo Sur");
    }


    @FXML
    void actualizarNomina(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarNomina.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorActualizacion.fxml", "Actualizar Nómina");
    }

    @FXML
    void consultarNomina(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarNomina.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Consultar Nómina");

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
        MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Instructores");
        System.out.println("Instructores button clicked");
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
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Nomina Instructores");
    }

    @FXML
    void consultarFormulario(ActionEvent event) {
        
        /*String cedulaInstructor = obtenerCodigoDeTextField(textFieldCedula.getText())+"";
        if (cedulaInstructor.equals("0")) {
            return;
        }

        NominaInstructor nominaInstructor = null;
        try {
            nominaInstructor = NominaInstructorDAO.getInstancia().buscarPorCodigo(cedulaInstructor, "NOMINA_INSTRUCTOR", "CEDULAINSTRUCTOR");
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo encontrar la nómina del instructor.");
        }

        tableViewNomina.getItems().clear();
        listaNominaInstructores.add(nominaInstructor);
        tableViewNomina.setItems(listaNominaInstructores);*/

        mostrarEnTabla(textFieldCedula, NominaInstructorDAO.getInstancia(), "NOMINA_INSTRUCTOR", "CEDULAINSTRUCTOR", listaNominaInstructores, tableViewNomina);
        
    }

}
