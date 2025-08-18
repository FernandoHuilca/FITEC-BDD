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

import java.time.LocalDate;

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

    // Constantes para validación
    private static final int MAX_LONGITUD_CEDULA = 15;
    private static final int MIN_LONGITUD_CEDULA = 8;
    private static final double SALARIO_MINIMO = 300.0;
    private static final double SALARIO_MAXIMO = 50000.0;
    private static final int ANIO_MINIMO_CONTRATACION = 2000;

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
            // Sanitizar y cargar datos
            String cedulaSanitizada = nomina.getCedulaInstructor() != null ? nomina.getCedulaInstructor().strip() : "";
            
            // No actualizar textFieldCedula ya que se usa para búsqueda
            // Solo actualizar los campos editables del formulario
            textFieldSalario.setText(String.valueOf(nomina.getSalario()));
            datePickerFechaContratacion.setValue(
                nomina.getFechaContratacion() != null ? nomina.getFechaContratacion().toLocalDate() : null
            );
            cedulaInstructorPorActualizar = cedulaSanitizada;
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

    /**
     * Sanitiza y valida los campos de entrada eliminando espacios en blanco y validando longitudes
     */
    private boolean validarYSanitizarCampos() {
        // Sanitizar campos de texto
        textFieldCedula.setText(textFieldCedula.getText().strip());
        textFieldSalario.setText(textFieldSalario.getText().strip());

        // Validar que la cédula no esté vacía
        if(cedulaInstructorPorActualizar == null || cedulaInstructorPorActualizar.strip().isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, seleccione una nómina de la tabla antes de actualizar.");
            return false;
        }

        // Validar que los campos obligatorios no estén vacíos
        if(textFieldSalario.getText().isEmpty() || datePickerFechaContratacion.getValue() == null) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, complete todos los campos (salario y fecha de contratación).");
            return false;
        }

        // Validar longitud de cédula para prevenir overflow
        String cedulaSanitizada = cedulaInstructorPorActualizar.strip();
        if(cedulaSanitizada.length() < MIN_LONGITUD_CEDULA || cedulaSanitizada.length() > MAX_LONGITUD_CEDULA) {
            MetodosFrecuentes.mostrarError("Error", "La cédula debe tener entre " + MIN_LONGITUD_CEDULA + " y " + MAX_LONGITUD_CEDULA + " caracteres.");
            return false;
        }

        // Validar formato de cédula (solo números)
        if (!cedulaSanitizada.matches("^[0-9]+$")) {
            MetodosFrecuentes.mostrarError("Error", "La cédula solo puede contener números.");
            return false;
        }

        cedulaInstructorPorActualizar = cedulaSanitizada;
        return true;
    }

    /**
     * Valida los valores de salario y fecha
     */
    private boolean validarSalarioYFecha() {
        try {
            // Validar salario
            double salario = Double.parseDouble(textFieldSalario.getText());
            
            if(salario < SALARIO_MINIMO || salario > SALARIO_MAXIMO) {
                MetodosFrecuentes.mostrarError("Error", "El salario debe estar entre $" + SALARIO_MINIMO + " y $" + SALARIO_MAXIMO + ".");
                return false;
            }

            // Verificar máximo 2 decimales en salario
            if (textFieldSalario.getText().matches(".*\\.\\d{3,}")) {
                MetodosFrecuentes.mostrarError("Error", "El salario no puede tener más de 2 decimales.");
                return false;
            }

            // Validar fecha de contratación
            LocalDate fechaContratacion = datePickerFechaContratacion.getValue();
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaMinima = LocalDate.of(ANIO_MINIMO_CONTRATACION, 1, 1);

            if (fechaContratacion.isAfter(fechaActual)) {
                MetodosFrecuentes.mostrarError("Error", "La fecha de contratación no puede ser futura.");
                return false;
            }

            if (fechaContratacion.isBefore(fechaMinima)) {
                MetodosFrecuentes.mostrarError("Error", "La fecha de contratación no puede ser anterior al año " + ANIO_MINIMO_CONTRATACION + ".");
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, ingrese un valor numérico válido para el salario.");
            return false;
        }
    }

    @FXML
    void actualizarNominaInstructor(ActionEvent event) {
        
        // Validar y sanitizar campos
        if(!validarYSanitizarCampos()) {
            return;
        }

        // Validar salario y fecha
        if(!validarSalarioYFecha()) {
            return;
        }

        NominaInstructor nominaInstructor = null;

        try {
            nominaInstructor = new NominaInstructor(
                    cedulaInstructorPorActualizar,
                    Double.parseDouble(textFieldSalario.getText()),
                    datePickerFechaContratacion.getValue().atStartOfDay()
            );

            NominaInstructorDAO.getInstancia().actualizar(nominaInstructor);

            // Actualizar la nomina en la lista original
            nominaList.clear();
            nominaList.addAll(NominaInstructorDAO.getInstancia().listar("NOMINA_INSTRUCTOR"));
            //tableViewNomina.refresh();

            MetodosFrecuentes.mostrarInfo("Éxito", "Nómina actualizada correctamente.");

            //limpiarCamposFormulario();
            //cedulaInstructorPorActualizar = null; // Limpiar la variable para evitar actualizaciones accidentales
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "Error al actualizar la nómina: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}