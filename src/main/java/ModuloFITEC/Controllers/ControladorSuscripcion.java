package ModuloFITEC.Controllers;

import java.sql.SQLException;
import java.util.List;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.ClienteDAO;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
import ModuloFITEC.logic.Models.Cliente;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControladorSuscripcion extends ControladorGeneral<Suscripcion>{

    @FXML
    private Button botonActualizarSuscripcion;

    @FXML
    private Button botonCrearSuscripcion;

    @FXML
    private Button botonEliminarSuscripcion;

    @FXML
    private TableColumn<?, ?> tableColumnCodigo;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcion;

    @FXML
    private TableColumn<?, ?> tableColumnDuracion;

    @FXML
    private TableColumn<?, ?> tableColumnPrecio;

    @FXML
    private TableColumn<?, ?> tableColumnTipo;

    @FXML
    private TableView<Suscripcion> tableViewSuscripcion;

    @FXML
    private TextField textFieldCodigo;

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
    private FilteredList<Suscripcion> suscripcionesFiltradas;

    //private int codigoSuscripcionPorActualizar = 0;

    @FXML
    private ControladorMenuIzquierdo vistaMenuIzquierdoController;

    @FXML
    void initialize() {
                
        this.suscripcionesList = FXCollections.observableArrayList();
        
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("idSuscripcion"));
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableColumnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tableColumnDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionMeses"));

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

        // Crear la lista filtrada basada en la lista original
        suscripcionesFiltradas = new FilteredList<>(suscripcionesList, p -> true);
        
        // Configurar la tabla con la lista filtrada
        tableViewSuscripcion.setItems(suscripcionesFiltradas);

        // Agregar listener al campo de texto para filtrado dinámico
        textFieldCodigoAConsultar.textProperty().addListener((observable, oldValue, newValue) -> {
            suscripcionesFiltradas.setPredicate(suscripcion -> {
                // Si el campo está vacío, mostrar todas las suscripciones
                if (newValue == null || newValue.isEmpty()) {
                    // Limpiar los campos del formulario cuando se borre el texto de búsqueda
                    limpiarCamposFormulario();
                    return true;
                }
                
                // Convertir el ID de la suscripción a String para la comparación
                String codigoSuscripcion = String.valueOf(suscripcion.getIdSuscripcion());
                
                // Filtrar por coincidencias parciales del código (case insensitive)
                return codigoSuscripcion.toLowerCase().contains(newValue.toLowerCase());
            });
        });
        //botonActualizarSuscripcion.setDisable(true);

        // Listener para el evento Enter en textFieldCodigoAConsultar
        textFieldCodigoAConsultar.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Quitar el foco del TextField
                tableViewSuscripcion.requestFocus();
                event.consume();
            }
        });

        tableViewSuscripcion.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            //botonActualizarSuscripcion.setDisable(newSel == null);
            if (newSel != null) {
                cargarDatosEnFormulario(newSel);
            }
        });

        // Configurar el estilo del botón de suscripciones en el menú lateral
        if (vistaMenuIzquierdoController != null) {
            vistaMenuIzquierdoController.getButtonSuscripciones().setStyle("-fx-background-color: #ff5f5f");
        }

        //botonCrearSuscripcion.setDisable(true);
        //botonEliminarSuscripcion.setDisable(true);
    }

    private void cargarDatosEnFormulario(Suscripcion newSel) {
        //textFieldCodigoAConsultar.setText(String.valueOf(newSel.getIdSuscripcion()));
        textFieldCodigo.setText(String.valueOf(newSel.getIdSuscripcion()));
        textFieldTipo.setText(newSel.getTipo().strip().toLowerCase());
        textFieldDescripcion.setText(newSel.getDescripcion());
        textFieldPrecio.setText(String.valueOf(newSel.getPrecio()));
        textFieldDuracion.setText(String.valueOf(newSel.getDuracionMeses()));
        //codigoSuscripcionPorActualizar = newSel.getIdSuscripcion(); // Importante: actualizar variable de control
    }

    private void limpiarCamposFormulario() {
        textFieldTipo.clear();
        textFieldDescripcion.clear();
        textFieldPrecio.clear();
        textFieldDuracion.clear();
        textFieldCodigo.clear();
        //codigoSuscripcionPorActualizar = 0;
        //botonActualizarSuscripcion.setDisable(true);
    }

    @FXML
    void actualizarSuscripcion(ActionEvent event) {

        if(textFieldCodigo.getText().isEmpty() || textFieldTipo.getText().isEmpty() || textFieldDescripcion.getText().isEmpty() ||
           textFieldPrecio.getText().isEmpty() || textFieldDuracion.getText().isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, complete todos los campos.");
            return;
        }

        Suscripcion suscripcion = null;
        try {
            suscripcion = new Suscripcion(
            Integer.parseInt(textFieldCodigo.getText().strip()),
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

            if(SuscripcionDAO.getInstancia().buscarPorString(textFieldTipo.getText().strip().toLowerCase(), "SUSCRIPCION", "TIPO", "IDSUSCRIPCION", Integer.parseInt(textFieldCodigo.getText().strip())) != null) {
                MetodosFrecuentes.mostrarError("Error", "Ya existe una suscripción con el tipo " + textFieldTipo.getText() + ".");
                return;
            }

            suscripcion = SuscripcionDAO.getInstancia().actualizar(suscripcion);
            if (suscripcion == null) {
                MetodosFrecuentes.mostrarError("Error", "No se pudo actualizar la suscripción.");
                return;
            }
            MetodosFrecuentes.mostrarInfo("Éxito", "Suscripción actualizada correctamente.");
            
            // Actualizar la suscripción en la lista original
            for (int i = 0; i < suscripcionesList.size(); i++) {
                if (suscripcionesList.get(i).getIdSuscripcion() == suscripcion.getIdSuscripcion()) {
                    suscripcionesList.set(i, suscripcion);
                    break;
                }
            }

            //limpiarCamposFormulario();
            
            //botonActualizarSuscripcion.setDisable(true);

        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo actualizar la suscripción: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    @FXML
    void crearSuscripcion(ActionEvent event) {

        if(textFieldCodigo.getText().isEmpty() || textFieldTipo.getText().isEmpty() || textFieldDescripcion.getText().isEmpty() ||
           textFieldPrecio.getText().isEmpty() || textFieldDuracion.getText().isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, complete todos los campos.");
            return;
        }
        
        try {
            int codigo = Integer.parseInt(textFieldCodigo.getText());

            if (SuscripcionDAO.getInstancia().buscarPorCodigo(codigo, "SUSCRIPCION", "IDSUSCRIPCION") != null) {
                MetodosFrecuentes.mostrarError("Error", "La suscripción con el código " + codigo + " ya existe.");
                return;
            }

            if(SuscripcionDAO.getInstancia().buscarPorString(textFieldTipo.getText().strip().toLowerCase(), "SUSCRIPCION", "TIPO", "IDSUSCRIPCION", codigo) != null) {
                MetodosFrecuentes.mostrarError("Error", "Ya existe una suscripción con el tipo " + textFieldTipo.getText().toLowerCase() + ".");
                return;
            }

            String tipo = textFieldTipo.getText().strip().toLowerCase();
            String descripcion = textFieldDescripcion.getText().strip().toLowerCase();
            double precio = Double.parseDouble(textFieldPrecio.getText());
            int duracion = Integer.parseInt(textFieldDuracion.getText());

            Suscripcion suscripcion = new Suscripcion(codigo, tipo, descripcion, precio, duracion);
            SuscripcionDAO.getInstancia().crear(suscripcion);

        } catch (NumberFormatException e) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, ingrese valores numéricos válidos");
            return;

        }catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo registrar la suscripción: " + e.getMessage());
            System.out.println("Error al registrar la suscripción: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        MetodosFrecuentes.mostrarInfo("Éxito", "La suscripción "+textFieldTipo.getText()+" se ha registrado correctamente.");
        limpiarCamposFormulario();

    }

    @FXML
    void eliminarSuscripcion(ActionEvent event) {

        if(textFieldCodigo.getText().isEmpty() || Integer.parseInt(textFieldCodigo.getText()) <= 0) {
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
            if (cliente.getIdSuscripcion() == Integer.parseInt(textFieldCodigo.getText())) {
                MetodosFrecuentes.mostrarError("Error", "No se puede eliminar la suscripción porque está asociada a un cliente.");
                return;
            }
        }

        try {
            SuscripcionDAO.getInstancia().eliminarPorCodigo(Integer.parseInt(textFieldCodigo.getText()), "SUSCRIPCION", "IDSUSCRIPCION");
            MetodosFrecuentes.mostrarInfo("Éxito", "Suscripción eliminada correctamente.");
            textFieldCodigoAConsultar.clear();
            textFieldCodigo.clear();
            tableViewSuscripcion.getItems().clear();
        } catch (SQLException e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo eliminar la suscripción.");
        }

    }

}
