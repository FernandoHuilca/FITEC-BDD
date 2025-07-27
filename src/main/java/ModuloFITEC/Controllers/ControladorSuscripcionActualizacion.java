package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.DAOGeneral;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladorSuscripcionActualizacion extends ControladorGeneral<Suscripcion> {

    @FXML
    private TableColumn<?, ?> tableColumnPrecio;

    @FXML
    private TableColumn<?, ?> tableColumnCodigo;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcion;

    @FXML
    private TableColumn<?, ?> tableColumnDuracion;

    @FXML
    private TableColumn<?, ?> tableColumnTipo;

    @FXML
    private Button buttonActualizarFormulario;

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
    private TableView<Suscripcion> tableViewSuscripcion;

    @FXML
    private TextField textFieldCodigoAConsultar;

    @FXML
    private TextField textFieldDescripcion;

    @FXML
    private TextField textFieldDuracion;

    @FXML
    private TextField textFieldPrecio;

    @FXML
    private TextField textFieldTipo;
    
    private ObservableList<Suscripcion> suscripcionesList;

    private int codigoSuscripcionPorActualizar;

    @FXML
    private ImageView imageViewNomina;

    public ControladorSuscripcionActualizacion() {
        codigoSuscripcionPorActualizar = 0;
    }

    @FXML
    void initialize() {
                
        this.suscripcionesList = FXCollections.observableArrayList();
        
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory("idSuscripcion"));
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tableColumnPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        tableColumnDuracion.setCellValueFactory(new PropertyValueFactory("duracionMeses"));

        buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
    }

    @FXML
    void actualizarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionActualizacion.fxml", "Actualizar Suscripción");

    }

    @FXML
    void buttonActualizarFormulario(ActionEvent event) {

        if (codigoSuscripcionPorActualizar == 0) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, consulte una suscripción antes de actualizar.");
            return;
        }

        if(textFieldCodigoAConsultar.getText().isEmpty() || textFieldTipo.getText().isEmpty() || textFieldDescripcion.getText().isEmpty() ||
           textFieldPrecio.getText().isEmpty() || textFieldDuracion.getText().isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, complete todos los campos.");
            return;
        }

        Suscripcion suscripcion = null;
        try {
            suscripcion = new Suscripcion(
            codigoSuscripcionPorActualizar,
            textFieldTipo.getText(),
            textFieldDescripcion.getText(),
            Double.parseDouble(textFieldPrecio.getText()),
            Integer.parseInt(textFieldDuracion.getText())
            );
            if(suscripcion.getIdSuscripcion() <= 0 || suscripcion.getDuracionMeses() <= 0 || suscripcion.getPrecio() <= 0) {
                MetodosFrecuentes.mostrarError("Error", "No se pueden ingresar valores negativos o cero.");
                suscripcion = null;
                return;
            }

            suscripcion = SuscripcionDAO.getInstancia().actualizar(suscripcion);
            if (suscripcion == null) {
                MetodosFrecuentes.mostrarError("Error", "No se pudo actualizar la suscripción.");
                return;
            }
            MetodosFrecuentes.mostrarInfo("Éxito", "Suscripción actualizada correctamente.");
            colocarObjetoEnTabla(suscripcion, suscripcionesList, tableViewSuscripcion);

        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo actualizar la suscripción: " + e.getMessage());
            e.printStackTrace();
            return;
        }
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
    void consultarFormulario(ActionEvent event) {
        /*int codigo = obtenerCodigoDeTextField(textFieldCodigoAConsultar.getText());
        
        if (codigo <= 0) {
            return;
        }
        Suscripcion suscripcion = obtenerObjetoPorCodigo(codigo, SuscripcionDAO.getInstancia(), "SUSCRIPCION", "IDSUSCRIPCION");
        if (suscripcion == null) {
            return;
        }

        colocarObjetoEnTabla(suscripcion, suscripcionesList, tableViewSuscripcion);*/
        Suscripcion suscripcion = mostrarEnTabla(textFieldCodigoAConsultar, SuscripcionDAO.getInstancia(), "SUSCRIPCION", "IDSUSCRIPCION", suscripcionesList, tableViewSuscripcion);
        
        if (suscripcion == null) {
            return;
        }
        
        colocarVariablesEnCampos(suscripcion);
        codigoSuscripcionPorActualizar = suscripcion.getIdSuscripcion();
    }

    private void colocarVariablesEnCampos(Suscripcion suscripcion) {
        textFieldTipo.setText(suscripcion.getTipo());
        textFieldDescripcion.setText(suscripcion.getDescripcion());
        textFieldPrecio.setText(String.valueOf(suscripcion.getPrecio()));
        textFieldDuracion.setText(String.valueOf(suscripcion.getDuracionMeses()));
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
