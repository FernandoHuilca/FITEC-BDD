package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.SuplementoDAO;
import ModuloFITEC.logic.Models.Suplemento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControladorSuplementoActualizacion {

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
    private DatePicker datePickerFechaVencimiento;

    @FXML
    private SplitMenuButton splitMenuButtonCategoria;

    @FXML
    private SplitMenuButton splitMenuButtonSucursal;

    @FXML
    private TextField textFieldCantidad;

    @FXML
    private TextField textFieldCodigoSuplemento;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldPrecio;

    @FXML
    private TextField textFieldCodigo;

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
    private Text textNombreServidor;

    private ObservableList<Suplemento> suplementos;

    private final SuplementoDAO suplementoDAO = new SuplementoDAO();

    @FXML
    public void initialize() {
        textFieldCodigo.setDisable(true);

        deshabilitarCampos();

        for (MenuItem item : splitMenuButtonSucursal.getItems()) {
            item.setOnAction(event -> {
                splitMenuButtonSucursal.setText(item.getText());
                splitMenuButtonSucursal.setStyle("-fx-text-fill: black;");
            });
        }

        for (MenuItem item : splitMenuButtonCategoria.getItems()) {
            item.setOnAction(event -> {
                splitMenuButtonCategoria.setText(item.getText());
                splitMenuButtonCategoria.setStyle("-fx-text-fill: black;");
            });
        }
        
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

        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte()? "Nodo Norte" : "Nodo Sur");
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
    void consultarSuplemento(ActionEvent event) {
        limpiarFormulario();
        splitMenuButtonSucursal.setStyle("-fx-text-fill: black;");
        splitMenuButtonCategoria.setStyle("-fx-text-fill: black;");
        habilitarCampos();
        try {
            int id = Integer.parseInt(textFieldCodigoSuplemento.getText().trim());
            Suplemento suplemento = suplementoDAO.buscarPorId(id);

            if (suplemento != null) {
                textFieldCodigo.setText(String.valueOf(suplemento.getIdSuplemento()));
                splitMenuButtonSucursal.setText(suplemento.getIdSucursal());
                textFieldNombre.setText(suplemento.getNombre());
                splitMenuButtonCategoria.setText(suplemento.getCategoria());
                textFieldPrecio.setText(String.valueOf(suplemento.getPrecio()));
                textFieldCantidad.setText(String.valueOf(suplemento.getCantidadDisponible()));
                datePickerFechaVencimiento.setValue(suplemento.getFechaVencimiento());
            } else {
                mostrarAlerta("Suplemento no encontrado", "No se encontró un suplemento con ese ID.");
            }
        } catch (NumberFormatException e) {
            deshabilitarCampos();            
            mostrarAlerta("ID inválido", "Por favor, ingrese un número entero válido como ID.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al buscar el suplemento.");
            e.printStackTrace();
        }

        //textFieldCodigoSuplemento.clear();
    }

    @FXML
    void seleccionarSuplemento(MouseEvent event) {
        textFieldCodigoSuplemento.clear();
        limpiarFormulario();
        splitMenuButtonSucursal.setStyle("-fx-text-fill: black;");
        splitMenuButtonCategoria.setStyle("-fx-text-fill: black;");
        habilitarCampos();
        Suplemento suplemento = this.tableSuplementos.getSelectionModel().getSelectedItem();
        if (suplemento != null) {
            textFieldCodigo.setText(String.valueOf(suplemento.getIdSuplemento()));
            splitMenuButtonSucursal.setText(suplemento.getIdSucursal());
            textFieldNombre.setText(suplemento.getNombre());
            splitMenuButtonCategoria.setText(suplemento.getCategoria());
            textFieldPrecio.setText(String.valueOf(suplemento.getPrecio()));
            textFieldCantidad.setText(String.valueOf(suplemento.getCantidadDisponible()));
            datePickerFechaVencimiento.setValue(suplemento.getFechaVencimiento());
        }
    }

    @FXML
    void actualizarSuplemento(ActionEvent event) {
        try {
        // Validar campos
            if (textFieldCodigo.getText().isBlank() || 
                textFieldNombre.getText().isBlank() || 
                splitMenuButtonCategoria.getText().equals("Escoja la categoría") ||
                splitMenuButtonSucursal.getText().equals("Escoja la sucursal") ||
                textFieldPrecio.getText().isBlank() || 
                textFieldCantidad.getText().isBlank() || 
                datePickerFechaVencimiento.getValue() == null) {
                
                mostrarAlerta("Campos incompletos", "Por favor, completa todos los campos.");
            }
            else {
                // Validar existencia del suplemento
                if (suplementoDAO.existeSuplementoEnSucursal(textFieldNombre.getText(), splitMenuButtonSucursal.getText())) {
                    mostrarAlerta("Duplicado", "Ya existe un suplemento con ese nombre en esa sucursal.");
                    return;
                }
                // Actualizar objeto suplemento
                Suplemento suplemento = new Suplemento(
                    Integer.parseInt(textFieldCodigo.getText()),
                    splitMenuButtonSucursal.getText(),
                    textFieldNombre.getText(),
                    splitMenuButtonCategoria.getText(),  // Usamos el texto seleccionado
                    Double.parseDouble(textFieldPrecio.getText()),
                    Integer.parseInt(textFieldCantidad.getText()),
                    datePickerFechaVencimiento.getValue()
                );

                // Insertar en la base de datos
                suplementoDAO.actualizarSuplemento(suplemento);
                mostrarAlerta("Éxito", "Suplemento actualizado correctamente.");
                limpiarFormulario();
                textFieldCodigoSuplemento.clear();
                deshabilitarCampos();

                suplementos.setAll(suplementoDAO.listarSuplementos());
                tableSuplementos.setItems(suplementos);
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Verifica que el código, el precio y la cantidad sean números válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al registrar el suplemento:\n" + e.getMessage());
            e.printStackTrace();
        }

    }

    private void limpiarFormulario() {
        textFieldCodigo.clear();
        splitMenuButtonSucursal.setText("Escoja la sucursal"); 
        textFieldNombre.clear();
        splitMenuButtonCategoria.setText("Escoja la categoría"); 
        textFieldPrecio.clear();
        textFieldCantidad.clear();
        datePickerFechaVencimiento.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void deshabilitarCampos() {
        splitMenuButtonSucursal.setDisable(true);
        textFieldNombre.setDisable(true);
        splitMenuButtonCategoria.setDisable(true);
        textFieldPrecio.setDisable(true);
        textFieldCantidad.setDisable(true);
        datePickerFechaVencimiento.setDisable(true);
    }

    private void habilitarCampos() {
        splitMenuButtonSucursal.setDisable(false);
        textFieldNombre.setDisable(false);
        splitMenuButtonCategoria.setDisable(false);
        textFieldPrecio.setDisable(false);
        textFieldCantidad.setDisable(false);
        datePickerFechaVencimiento.setDisable(false);
    }
}
