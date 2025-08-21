package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.CompraDAO;
import ModuloFITEC.logic.Models.Compra;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorCompraEliminacion {

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

    @FXML
    private Text textNombreServidor;

    @FXML
    private ImageView imageViewNomina;

    private ObservableList<Compra> compras;

    private final CompraDAO compraDAO = new CompraDAO();

    //private Compra compraSeleccionada = null;

    @FXML
    public void initialize() {
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
                    case "Código Compra" -> {
                        textSeleccion.setText("Código:");
                        textFieldValorConsulta.clear();
                        textFieldValorConsulta.setPromptText("Ingrese el código de compra");
                        try {
                            compras.setAll(compraDAO.listarCompras());
                        } catch (Exception e) {
                            mostrarAlerta("Error al cargar compras", "No se pudieron obtener los datos de la base.");
                            e.printStackTrace();
                        }
                        tableCompras.setItems(compras);
                    }
                    case "Cliente" -> {
                        textSeleccion.setText("Cliente:");
                        textFieldValorConsulta.clear();
                        textFieldValorConsulta.setPromptText("Ingrese el número de cédula del cliente");

                        try {
                            compras.setAll(compraDAO.listarCompras());
                        } catch (Exception e) {
                            mostrarAlerta("Error al cargar compras", "No se pudieron obtener los datos de la base.");
                            e.printStackTrace();
                        }
                        tableCompras.setItems(compras);
                    }
                    case "Suplemento" -> {
                        textSeleccion.setText("Suplemento:");
                        textFieldValorConsulta.clear();
                        textFieldValorConsulta.setPromptText("Ingrese el nombre del suplemento");

                    try {
                            compras.setAll(compraDAO.listarCompras());
                        } catch (Exception e) {
                            mostrarAlerta("Error al cargar compras", "No se pudieron obtener los datos de la base.");
                            e.printStackTrace();
                        }
                        tableCompras.setItems(compras);
                    }
                    case "Sucursal" -> {
                        textSeleccion.setText("Sucursal:");
                        textFieldValorConsulta.clear();
                        textFieldValorConsulta.setPromptText("Ingrese el código de la sucursal");

                        try {
                            compras.setAll(compraDAO.listarCompras());
                        } catch (Exception e) {
                            mostrarAlerta("Error al cargar compras", "No se pudieron obtener los datos de la base.");
                            e.printStackTrace();
                        }
                        tableCompras.setItems(compras);
                    }
                    default -> {
                        textSeleccion.setText("");
                        textFieldValorConsulta.setPromptText("");
                    }
                }
            });
        }

        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte()? "Nodo Norte" : "Nodo Sur");
        buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
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
        MetodosFrecuentes.cambiarVentana((Stage) buttonNominaInstructores.getScene().getWindow(), "/ModuloFITEC/views/VistaNominaInstructor.fxml", "Consultar Nómina");
    }

    @FXML
    void cambiarVentanaRegistrarCompra(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarCompra.getScene().getWindow(), "/ModuloFITEC/views/VistaCompra.fxml", "Registrar Compra");
    }

    @FXML
    void cambiarVentanaSuplementos(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuplementos.getScene().getWindow(), "/ModuloFITEC/views/VistaSuplementoCreacion.fxml", "Registrar Suplemento");
    }

    @FXML
    void cambiarVentanaSuscripciones(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonSuscripciones.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcion.fxml", "Registrar Suscripción");
    }

    @FXML
    void seleccionarCompra(MouseEvent event) {
        Compra compraSeleccionada = tableCompras.getSelectionModel().getSelectedItem();
        
        if (compraSeleccionada != null && splitMenuButtonSeleccion.getText().equals("Código Compra")) {
            textFieldValorConsulta.setText(String.valueOf(compraSeleccionada.getIdCompra()));
        }
    }

    @FXML
    void consultarCompras(ActionEvent event) {
        String opcion = splitMenuButtonSeleccion.getText();
        String valor = textFieldValorConsulta.getText().trim();

        try {
            switch (opcion) {
                case "Código Compra" -> {
                    if (valor.isEmpty()) {
                        mostrarAlerta("Campo vacío", "Ingrese código de compra para consultar.");
                        return;
                    }

                    // Consulta por código de compra
                    compras.clear();
                    compras.add(compraDAO.buscarPorIdCompra(Integer.parseInt(valor)));
                    tableCompras.setItems(compras);

                    if (compras.isEmpty()) {
                        mostrarAlerta("Sin resultados", "No se encontraron compras para la cédula ingresada.");
                        compras.setAll(compraDAO.listarCompras());
                    }

                    tableCompras.setItems(compras);
                }
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

    @FXML
    void eliminarCompras(ActionEvent event) {
        String opcion = splitMenuButtonSeleccion.getText();
        String valor = textFieldValorConsulta.getText().trim();

        try {
            if (valor.isEmpty()) {
                mostrarAlerta("Campo vacío", "Ingrese un valor para eliminar.");
                return;
            }

            switch (opcion) {
                case "Código Compra" -> {
                    int id = Integer.parseInt(valor);
                    compraDAO.eliminarPorCodigoCompra(id);
                    mostrarAlerta("Éxito", "Compra eliminada correctamente.");
                }

                case "Cliente" -> {
                    compraDAO.eliminarPorCliente(valor);
                    mostrarAlerta("Éxito", "Compras del cliente eliminadas correctamente.");
                }

                case "Suplemento" -> {
                    compraDAO.eliminarPorSuplemento(valor);
                    mostrarAlerta("Éxito", "Compras del suplemento eliminadas correctamente.");
                }

                case "Sucursal" -> {
                    compraDAO.eliminarPorIdSucursal(valor);
                    mostrarAlerta("Éxito", "Compras de la sucursal eliminadas correctamente.");
                }

                default -> {
                    mostrarAlerta("Selección inválida", "Seleccione una opción válida para eliminar.");
                    return;
                }
            }

            // Refrescar la tabla
            textFieldValorConsulta.clear();
            compras.setAll(compraDAO.listarCompras());
            tableCompras.setItems(compras);
            textFieldValorConsulta.clear();

        } catch (NumberFormatException e) {
            mostrarAlerta("Formato inválido", "El código de compra debe ser numérico.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al eliminar la(s) compra(s):\n" + e.getMessage());
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
}
