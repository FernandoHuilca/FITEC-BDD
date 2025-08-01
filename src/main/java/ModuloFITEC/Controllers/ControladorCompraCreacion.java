package ModuloFITEC.Controllers;

import java.util.List;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.ClienteDAO;
import ModuloFITEC.logic.DAOs.CompraDAO;
import ModuloFITEC.logic.DAOs.SuplementoDAO;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
import ModuloFITEC.logic.Models.Cliente;
import ModuloFITEC.logic.Models.Compra;
import ModuloFITEC.logic.Models.Suplemento;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorCompraCreacion {

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
    private SplitMenuButton splitMenuButtonSucursal;

    @FXML
    private TextField textFieldCantidad;

    @FXML
    private TextField textFieldCedulaCliente;

    @FXML
    private TextField textFieldCodigo;

    // @FXML
    // private TextField textFieldCodigoSuplemento;

    @FXML
    private SplitMenuButton splitMenuButtonNombreSuplemento;

    @FXML
    private Text textNombreServidor;

    @FXML
    private ImageView imageViewNomina;

    private SuplementoDAO suplementoDAO = new SuplementoDAO();
    private CompraDAO compraDAO = new CompraDAO();

    @FXML
    public void initialize() {
        splitMenuButtonNombreSuplemento.setDisable(true);
        for (MenuItem item : splitMenuButtonSucursal.getItems()) {
            item.setOnAction(event -> {
                String seleccion = item.getText();
                splitMenuButtonSucursal.setText(seleccion);
                splitMenuButtonSucursal.setStyle("-fx-text-fill: black;");

                switch (seleccion) {
                    case "QUITO_NORTE" -> {
                        splitMenuButtonNombreSuplemento.setText("Escoja un suplemento");
                        splitMenuButtonNombreSuplemento.setStyle("-fx-text-fill: gray;");
                        splitMenuButtonNombreSuplemento.setDisable(false);
                        cargarSuplementos("QUITO_NORTE");
                    }
                    case "QUITO_SUR" -> {
                        splitMenuButtonNombreSuplemento.setText("Escoja un suplemento");
                        splitMenuButtonNombreSuplemento.setStyle("-fx-text-fill: gray;");
                        splitMenuButtonNombreSuplemento.setDisable(false);
                        cargarSuplementos("QUITO_SUR");
                    }
                    default -> {
                        splitMenuButtonNombreSuplemento.getItems().clear();
                        splitMenuButtonNombreSuplemento.setText("Escoja un suplemento");
                        splitMenuButtonNombreSuplemento.setStyle("-fx-text-fill: gray;");
                    }
                }
            });
        }

        textNombreServidor.setText(ConexionBaseSingleton.getInstancia().isNodoNorte()? "Nodo Norte" : "Nodo Sur");
        buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
    }

    private void cargarSuplementos(String sucursal) {
        splitMenuButtonNombreSuplemento.getItems().clear();
        try {
            List<Suplemento> suplementos = suplementoDAO.buscarPorSucursal(sucursal);
            for (Suplemento s : suplementos) {
                MenuItem item = new MenuItem(s.getNombre());
                // Al seleccionar, guarda el id en la variable y actualiza el texto
                item.setOnAction(e -> {
                    splitMenuButtonNombreSuplemento.setText(s.getNombre());
                    //idSuplementoSeleccionado = s.getIdSuplemento();
                });
                splitMenuButtonNombreSuplemento.getItems().add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MetodosFrecuentes.mostrarError("Error", "No se pudieron cargar los suplementos.");
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
    void registrarCompra(ActionEvent event) {
        try {
            if (!validarCampos()) return;

            if (compraDAO.existeCompraConId(Integer.parseInt(textFieldCodigo.getText()))) {
                mostrarAlerta("ID duplicado", "Ya existe una compra con ese código (ID), aunque sea en otra sucursal.");
                return;
            }

            Cliente cliente = obtenerCliente();
            Suplemento suplemento = suplementoDAO.buscarPorId(obtenerSuplemento());
            if (suplemento == null || cliente == null) return;

            int cantidadPedida = Integer.parseInt(textFieldCantidad.getText());

            if (!validarCantidadDisponible(suplemento, cantidadPedida)) return;

            Compra compra = new Compra(
                Integer.parseInt(textFieldCodigo.getText()),
                textFieldCedulaCliente.getText(),
                suplemento.getIdSuplemento(),
                cantidadPedida,
                java.time.LocalDateTime.now(),
                suplemento.getPrecio(),
                splitMenuButtonSucursal.getText()
            );

            compraDAO.crearCompra(compra);

            suplemento.setCantidadDisponible(suplemento.getCantidadDisponible() - cantidadPedida);
            suplementoDAO.actualizarSuplemento(suplemento);

            mostrarAlerta("Éxito", "Compra registrada correctamente.");
            limpiarFormulario();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Verifica que el código y la cantidad sean números válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al registrar la compra:\n" + e.getMessage());
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

    private void limpiarFormulario() {
        textFieldCodigo.clear();
        splitMenuButtonSucursal.setText("Escoja la sucursal"); 
        textFieldCedulaCliente.clear();
        splitMenuButtonNombreSuplemento.setText("Escoja un suplemento");
        textFieldCantidad.clear();
    }

    private boolean validarCampos() {
        if (textFieldCodigo.getText().isBlank() ||
            textFieldCedulaCliente.getText().isBlank() ||
            splitMenuButtonSucursal.getText().equals("Escoja la sucursal") ||
            textFieldCantidad.getText().isBlank() ||
            splitMenuButtonNombreSuplemento.getText().equals("Escoja un suplemento")) {

            mostrarAlerta("Campos incompletos", "Por favor, completa todos los campos.");
            return false;
        }
        return true;
    }

    private Cliente obtenerCliente() throws Exception {

        String cedula = textFieldCedulaCliente.getText().trim();

        List<Cliente> cliente = ClienteDAO.getInstancia().getClientesPorCedula(cedula);

        if (cliente.isEmpty()) {
            mostrarAlerta("Cliente no encontrado", "No existe un cliente con cédula: " + cedula);
            return null;
        }

        return cliente.get(0);
    }

    private int obtenerSuplemento() throws Exception {
        SuplementoDAO suplementoDAO = new SuplementoDAO();

        String nombreSuplemento = splitMenuButtonNombreSuplemento.getText();
        String sucursal = splitMenuButtonSucursal.getText();

        return suplementoDAO.obtenerIdSuplementoPorNombreYSucursal(nombreSuplemento, sucursal);
    }

    private boolean validarCantidadDisponible(Suplemento suplemento, int cantidadPedida) {
        if (cantidadPedida > suplemento.getCantidadDisponible()) {
            mostrarAlerta("Cantidad excedida", "No hay suficiente cantidad disponible del suplemento.");
            return false;
        }
        return true;
    }
}
