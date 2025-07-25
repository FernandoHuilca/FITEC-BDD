package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.CompraDAO;
import ModuloFITEC.logic.Models.Compra;
import ModuloFITEC.logic.Models.Suplemento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorCompraBusqueda {

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultarCompra;

    @FXML
    private Button buttonEliminarCompra;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private Button buttonRegistrarCompra;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

    @FXML
    private Button buttonConsultar;

    @FXML
    private SplitMenuButton splitMenuButtonSeleccion;

    @FXML
    private TextField textFieldValorConsulta;

    @FXML
    private Text textSeleccion;

    @FXML
    private TableColumn columnCantidadComprada;

    @FXML
    private TableColumn columnCedulaCliente;

    @FXML
    private TableColumn columnCodigo;

    @FXML
    private TableColumn columnFechaCompra;

    @FXML
    private TableColumn columnNombreSuplemento;

    @FXML
    private TableColumn columnPrecioCompra;

    @FXML
    private TableColumn columnSucursal;

    @FXML
    private TableView<Compra> tableCompras;

    private ObservableList<Compra> compras;

    private final CompraDAO compraDAO = new CompraDAO();

    @FXML
    public void initialize() {

        deshabilitarCamposConsulta();

        compras = FXCollections.observableArrayList();

        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("idCompra"));
        columnCedulaCliente.setCellValueFactory(new PropertyValueFactory<>("cedulaCliente"));
        columnNombreSuplemento.setCellValueFactory(new PropertyValueFactory<>("nombreSuplemento"));
        columnCantidadComprada.setCellValueFactory(new PropertyValueFactory<>("cantidadComprada"));
        columnFechaCompra.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        columnPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
        columnSucursal.setCellValueFactory(new PropertyValueFactory<>("idSucursal"));

        try {
            compras.setAll(compraDAO.listarCompras());
            tableCompras.setItems(compras);
        } catch (Exception e) {
            mostrarAlerta("Error al cargar compras", "No se pudieron obtener los datos de la base.");
            e.printStackTrace();
        }

        for (MenuItem item : splitMenuButtonSeleccion.getItems()) {
            item.setOnAction(event -> {
                String seleccion = item.getText();
                splitMenuButtonSeleccion.setText(seleccion);
                splitMenuButtonSeleccion.setStyle("-fx-text-fill: black;");

                // Cambiar texto y placeholder según la opción
                switch (seleccion) {
                    case "Todo" -> {
                        deshabilitarCamposConsulta();

                        textSeleccion.setText("");
                        textFieldValorConsulta.clear();
                        textFieldValorConsulta.setPromptText("");

                        try {
                            compras.setAll(compraDAO.listarCompras());
                        } catch (Exception e) {
                            mostrarAlerta("Error al cargar compras", "No se pudieron obtener los datos de la base.");
                            e.printStackTrace();
                        }
                        tableCompras.setItems(compras);
                    }
                    case "Cliente" -> {
                        habilitarCamposConsulta();
                        textSeleccion.setText("Cliente:");
                        textFieldValorConsulta.clear();
                        textFieldValorConsulta.setPromptText("Escriba el número de cédula del cliente");
                    }
                    case "Suplemento" -> {
                        habilitarCamposConsulta();
                        textSeleccion.setText("Suplemento:");
                        textFieldValorConsulta.clear();
                        textFieldValorConsulta.setPromptText("Escriba el nombre del suplemento");
                    }
                    case "Sucursal" -> {
                        habilitarCamposConsulta();
                        textSeleccion.setText("Sucursal:");
                        textFieldValorConsulta.clear();
                        textFieldValorConsulta.setPromptText("Escriba el código de la sucursal");
                    }
                    default -> {
                        textSeleccion.setText("");
                        textFieldValorConsulta.setPromptText("");
                    }
                }
            });
        }
    }

    @FXML
    void cambiarVentanaClientes(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonClientes.getScene().getWindow(), "/ModuloFITEC/views/VistaClienteCreacion.fxml", "Registrar Cliente");
    }

    @FXML
    void cambiarVentanaConsultarCompra(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarCompra.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraBusqueda.fxml", "Consultar Compra");
    }

    @FXML
    void cambiarVentanaEliminarCompra(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarCompra.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraEliminacion.fxml", "Eliminar Compra");
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
        MetodosFrecuentes.cambiarVentana((Stage) buttonInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaInstructorCreacion.fxml", "Registrar Instructor");
    }

    @FXML
    void cambiarVentanaNominaInstructores(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructorBusqueda.fxml", "Consultar Nómina");
    }

    @FXML
    void cambiarVentanaRegistrarCompra(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarCompra.getScene().getWindow(), "/ModuloFITEC/views/VistaCompraCreacion.fxml", "Registrar Compra");
    }

    @FXML
    void cambiarVentanaSuplementos(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Registrar Suplemento");
    }

    @FXML
    void cambiarVentanaSuscripciones(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Registrar Suscripción");
    }

    @FXML
    void consultarCompras(ActionEvent event) {
        String opcion = splitMenuButtonSeleccion.getText();
        String valor = textFieldValorConsulta.getText().trim();

        try {
            switch (opcion) {
                // case "Todo" -> {
                //     compras.setAll(compraDAO.listarCompras());
                //     tableCompras.setItems(compras);
                // }
                case "Cliente" -> {
                    if (valor.isEmpty()) {
                        mostrarAlerta("Campo vacío", "Ingrese una cédula para consultar.");
                        return;
                    }

                    // Consulta por cédula
                    compras.setAll(compraDAO.buscarPorCedulaCliente(valor));
                    if (compras.isEmpty()) {
                        mostrarAlerta("Sin resultados", "No se encontraron compras para la cédula ingresada.");
                        compras.setAll(compraDAO.listarCompras());
                    }

                    tableCompras.setItems(compras);
                }

                case "Suplemento" -> {
                    if (valor.isEmpty()) {
                        mostrarAlerta("Campo vacío", "Ingrese un nombre de suplemento para consultar.");
                        return;
                    }

                    // Consulta por nombre de suplemento
                    compras.setAll(compraDAO.buscarPorNombreSuplemento(valor));
                    if (compras.isEmpty()) {
                        mostrarAlerta("Sin resultados", "No se encontraron compras para ese suplemento.");
                        compras.setAll(compraDAO.listarCompras());
                    }

                    tableCompras.setItems(compras);
                }

                case "Sucursal" -> {
                    if (valor.isEmpty()) {
                        mostrarAlerta("Campo vacío", "Ingrese un código de sucursal para consultar.");
                        return;
                    }

                    // Consulta por ID de sucursal
                    compras.setAll(compraDAO.buscarPorIdSucursal(valor));
                    if (compras.isEmpty()) {
                        mostrarAlerta("Sin resultados", "No se encontraron compras para esa sucursal.");
                        compras.setAll(compraDAO.listarCompras());
                    }

                    tableCompras.setItems(compras);
                }

                default -> {
                    mostrarAlerta("Selección inválida", "Por favor, seleccione una opción válida para consultar.");
                }
            }

        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al consultar las compras:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void habilitarCamposConsulta() {
        textFieldValorConsulta.setDisable(false);
        buttonConsultar.setDisable(false);
    }

    private void deshabilitarCamposConsulta() {
        textFieldValorConsulta.setDisable(true);
        buttonConsultar.setDisable(true);
    }
}
