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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    private Button buttonConsultar;

    @FXML
    private Button buttonConsultarSuscripcion;

    @FXML
    private Button buttonEliminarSuscripcion;


    @FXML
    private Button buttonRegistrarSuscripcion;

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

    //@FXML
    //private ImageView imageViewNomina;

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

        //buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        //imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        tableViewSuscripcion.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

         try {
            suscripcionesList.addAll(SuscripcionDAO.getInstancia().listar("SUSCRIPCION"));
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudieron cargar las suscripciones: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        tableViewSuscripcion.setItems(suscripcionesList);
        buttonActualizarFormulario.setDisable(true);

        tableViewSuscripcion.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            Suscripcion suscripcion = newSel;
            buttonActualizarFormulario.setDisable(newSel == null);
            if (newSel != null) {
                cargarDatosEnFormulario(newSel);
            }
        });
    }

    

    private void cargarDatosEnFormulario(Suscripcion newSel) {
        textFieldCodigoAConsultar.setText(String.valueOf(newSel.getIdSuscripcion()));
        textFieldTipo.setText(newSel.getTipo().strip().toLowerCase());
        textFieldDescripcion.setText(newSel.getDescripcion());
        textFieldPrecio.setText(String.valueOf(newSel.getPrecio()));
        textFieldDuracion.setText(String.valueOf(newSel.getDuracionMeses()));
        codigoSuscripcionPorActualizar = newSel.getIdSuscripcion(); // Importante: actualizar variable de control
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
            textFieldTipo.getText().strip().toLowerCase(),
            textFieldDescripcion.getText().strip().toLowerCase(),
            Double.parseDouble(textFieldPrecio.getText().strip()),
            Integer.parseInt(textFieldDuracion.getText().strip())
            );
            if(suscripcion.getIdSuscripcion() <= 0 || suscripcion.getDuracionMeses() <= 0 || suscripcion.getPrecio() <= 0) {
                MetodosFrecuentes.mostrarError("Error", "No se pueden ingresar valores negativos o cero.");
                suscripcion = null;
                return;
            }

            if(SuscripcionDAO.getInstancia().buscarPorString(textFieldTipo.getText().strip().toLowerCase(), "SUSCRIPCION", "TIPO", "IDSUSCRIPCION", codigoSuscripcionPorActualizar) != null) {
                MetodosFrecuentes.mostrarError("Error", "Ya existe una suscripción con el tipo " + textFieldTipo.getText() + ".");
                return;
            }

            suscripcion = SuscripcionDAO.getInstancia().actualizar(suscripcion);
            if (suscripcion == null) {
                MetodosFrecuentes.mostrarError("Error", "No se pudo actualizar la suscripción.");
                return;
            }
            MetodosFrecuentes.mostrarInfo("Éxito", "Suscripción actualizada correctamente.");
            colocarObjetoEnTabla(suscripcion, suscripcionesList, tableViewSuscripcion);
            buttonActualizarFormulario.setDisable(true);

        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo actualizar la suscripción: " + e.getMessage());
            e.printStackTrace();
            return;
        }
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
        
        cargarDatosEnFormulario(suscripcion);
        tableColumnDescripcion.setPrefWidth(600.0);
        tableColumnDescripcion.setResizable(true);   // Permite redimensionar
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
