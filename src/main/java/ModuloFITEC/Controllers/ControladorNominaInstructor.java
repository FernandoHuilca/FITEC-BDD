package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.NominaInstructorDAO;
import ModuloFITEC.logic.Models.NominaInstructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControladorNominaInstructor extends ControladorGeneral<NominaInstructor> {

    @FXML
    private Button botonActualizarNominaInstructor;

    @FXML
    private DatePicker datePickerFechaContratacion;

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
    private TextField textFieldSalario;

    @FXML
    private ControladorMenuIzquierdo vistaMenuIzquierdoController;

    private ObservableList<NominaInstructor> nominaList;
    private FilteredList<NominaInstructor> nominaFiltrada;

    private String cedulaInstructorPorActualizar;

    public ControladorNominaInstructor() {
        this.cedulaInstructorPorActualizar = "";
    }

    @FXML
    void initialize() {

        this.nominaList = FXCollections.observableArrayList();
                
        // Configurar las columnas de la tabla
        tableColumnCedulaInstructor.setCellValueFactory(new PropertyValueFactory<>("cedulaInstructor"));
        tableColumnSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        tableColumnFechaContratacion.setCellValueFactory(new PropertyValueFactory<>("fechaContratacionSimple"));

        // Configurar política de redimensionamiento de la tabla
        tableViewNomina.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        try {
            // Cargar datos iniciales desde la base de datos
            nominaList.addAll(NominaInstructorDAO.getInstancia().listar("NOMINA_INSTRUCTOR"));
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudieron cargar los datos de nómina: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Crear la lista filtrada basada en la lista original
        nominaFiltrada = new FilteredList<>(nominaList, p -> true);
        
        // Configurar la tabla con la lista filtrada
        tableViewNomina.setItems(nominaFiltrada);

        // Listener para filtrado dinámico por cédula
        textFieldCedula.textProperty().addListener((observable, oldValue, newValue) -> {
            nominaFiltrada.setPredicate(nomina -> {
                // Si el campo está vacío, mostrar todas las nóminas
                if (newValue == null || newValue.isEmpty()) {
                    limpiarCamposFormulario();
                    return true;
                }
                
                // Filtrar por coincidencias parciales de la cédula (case insensitive)
                String cedulaInstructor = nomina.getCedulaInstructor();
                return cedulaInstructor != null && cedulaInstructor.toLowerCase().contains(newValue.toLowerCase());
            });
        });

        // Listener para manejar Enter en el campo de cédula
        textFieldCedula.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Quitar el foco del TextField
                tableViewNomina.requestFocus();
                event.consume();
            }
        });

        tableViewNomina.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarDatosEnFormulario(newSel);
            }
        });

        // Configurar estilo del botón en el menú lateral si existe
        if (vistaMenuIzquierdoController != null) {
            // Asumiendo que existe un botón para nómina en el menú
            vistaMenuIzquierdoController.getButtonNomina().setStyle("-fx-background-color: #ff5f5f");
        }
    }

    /**
     * Cargar datos de nómina en los campos del formulario
     */
    private void cargarDatosEnFormulario(NominaInstructor nomina) {
        if (nomina != null) {
            // No actualizar textFieldCedula ya que se usa para búsqueda
            // Solo actualizar los campos editables del formulario
            textFieldSalario.setText(String.valueOf(nomina.getSalario()));
            datePickerFechaContratacion.setValue(
                nomina.getFechaContratacion() != null ? nomina.getFechaContratacion().toLocalDate() : null
            );
            cedulaInstructorPorActualizar = nomina.getCedulaInstructor();
        }
    }

    /**
     * Limpiar todos los campos del formulario
     */
    private void limpiarCamposFormulario() {
        textFieldSalario.clear();
        datePickerFechaContratacion.setValue(null);
        cedulaInstructorPorActualizar = "";
    }

    @FXML
    void actualizarNominaInstructor(ActionEvent event) {
        
        if(cedulaInstructorPorActualizar == null || cedulaInstructorPorActualizar.strip().isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, seleccione una nómina de la tabla antes de actualizar.");
            return;
        }

        if(textFieldSalario.getText().strip().isEmpty() || datePickerFechaContratacion.getValue() == null) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, complete todos los campos (salario y fecha de contratación).");
            return;
        }

        NominaInstructor nominaInstructor = null;

        try {
            nominaInstructor = new NominaInstructor(
                    cedulaInstructorPorActualizar.strip(),
                    Double.parseDouble(textFieldSalario.getText().strip()),
                    datePickerFechaContratacion.getValue().atStartOfDay()
            );

            if(nominaInstructor.getSalario() <= 0) {
                MetodosFrecuentes.mostrarError("Error", "El salario debe ser mayor a 0.");
                return;
            }

            NominaInstructorDAO.getInstancia().actualizar(nominaInstructor);

            // Actualizar la nomina en la lista original
            for (int i = 0; i < nominaList.size(); i++) {
                if (nominaList.get(i).getCedulaInstructor().equals(cedulaInstructorPorActualizar)) {
                    nominaList.set(i, nominaInstructor);
                    break;
                }
            }

            MetodosFrecuentes.mostrarInfo("Éxito", "Nómina actualizada correctamente.");

            //limpiarCamposFormulario();
            //cedulaInstructorPorActualizar = null; // Limpiar la variable para evitar actualizaciones accidentales
        } catch (NumberFormatException e) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, ingrese un valor numérico válido para el salario.");
            return;
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "Error al actualizar la nómina: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}