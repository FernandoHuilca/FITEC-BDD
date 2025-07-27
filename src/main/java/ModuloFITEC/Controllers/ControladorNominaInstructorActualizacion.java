package ModuloFITEC.Controllers;

import java.util.Observable;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.NominaInstructorDAO;
import ModuloFITEC.logic.Models.NominaInstructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorNominaInstructorActualizacion extends ControladorGeneral<NominaInstructor> {

    @FXML
    private Button buttonActualizarFormulario;

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
    private Button buttonNominaInstructores;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

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
    private DatePicker datePickerFechaContratacion;

    @FXML
    private TextField textFieldSalario;

    @FXML
    private Text textNombreServidor;

    ObservableList<NominaInstructor> listaNominaInstructores;

    private String cedulaInstructorPorActualizar;

    public ControladorNominaInstructorActualizacion() {
        listaNominaInstructores = FXCollections.observableArrayList();
        cedulaInstructorPorActualizar = "0";
    }
    @FXML
    void initialize(){
        tableColumnCedulaInstructor.setCellValueFactory(new PropertyValueFactory("cedulaInstructor"));
        tableColumnFechaContratacion.setCellValueFactory(new PropertyValueFactory("fechaContratacionSimple"));
        tableColumnSalario.setCellValueFactory(new PropertyValueFactory("salario"));
        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte()? "Nodo Norte" : "Nodo Sur");
    }

    @FXML
    void actualizarFormulario(ActionEvent event) {

        if(cedulaInstructorPorActualizar.strip().isEmpty() || cedulaInstructorPorActualizar.strip().isBlank() ||  cedulaInstructorPorActualizar == null || cedulaInstructorPorActualizar.equals("0")) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, consulte un formulario antes de actualizar.");
            return;
        }

        if(textFieldCedula.getText().strip().isEmpty() || datePickerFechaContratacion.getValue() == null || textFieldSalario.getText().strip().isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, complete todos los campos.");
            return;
        }

        NominaInstructor nominaInstructor = null;

        try {
            nominaInstructor = new NominaInstructor(
                textFieldCedula.getText().strip(),
                Double.parseDouble(textFieldSalario.getText().strip()),
                datePickerFechaContratacion.getValue().atStartOfDay()
            );

            if(nominaInstructor.getSalario() <= 0) {
                MetodosFrecuentes.mostrarError("Error", "El salario debe ser mayor a 0.");
                return;
            }

            NominaInstructorDAO.getInstancia().actualizar(nominaInstructor);
            MetodosFrecuentes.mostrarInfo("Éxito", "Nómina actualizada correctamente.");
            colocarObjetoEnTabla(nominaInstructor, listaNominaInstructores, tableViewNomina);
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "Error al crear el objeto: " + e.getMessage());
            return;
        }
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
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Nomina Instructores");
    }

    @FXML
    void cambiarVentanaSuscripciones(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Suscripciones");
    }

    @FXML
    void consultarFormulario(ActionEvent event) {
        
        NominaInstructor nominaInstructor = mostrarEnTabla(textFieldCedula, NominaInstructorDAO.getInstancia(), "NOMINA_INSTRUCTOR", "CEDULAINSTRUCTOR", listaNominaInstructores, tableViewNomina);
        
        if (nominaInstructor == null) {
            return;
        }
        //MetodosFrecuentes.mostrarInfo("Éxito", "Objeto encontrado.");
        colocarVariablesEnCampos(nominaInstructor);
        cedulaInstructorPorActualizar = nominaInstructor.getCedulaInstructor();
    }

    private void colocarVariablesEnCampos(NominaInstructor nominaInstructor) {
        textFieldCedula.setText(String.valueOf(nominaInstructor.getCedulaInstructor()));
        datePickerFechaContratacion.setValue(nominaInstructor.getFechaContratacion().toLocalDate());
        textFieldSalario.setText(String.valueOf(nominaInstructor.getSalario()));
    }
        
}
