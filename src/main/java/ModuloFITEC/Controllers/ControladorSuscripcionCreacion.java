package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorSuscripcionCreacion {

    @FXML
    private Button buttonActualizarSuscripción;

    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonConsultarSuscripción;

    @FXML
    private Button buttonEliminarSuscripción;

    @FXML
    private Button buttonHistorialDeCompras;

    @FXML
    private Button buttonInicio;

    @FXML
    private Button buttonInstructores;

    @FXML
    private Button buttonNominaInstructores;

    @FXML
    private Button buttonRegistrarFormularioSuscripción;

    @FXML
    private Button buttonRegistrarSuscripción;

    @FXML
    private Button buttonSuplementos;

    @FXML
    private Button buttonSuscripciones;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldDescripcion;

    @FXML
    private TextField textFieldDuracion;

    @FXML
    private TextField textFieldPrecio;

    @FXML
    private TextField textFieldTipo;

    @FXML
    void initialize() {
        // No es necesario volver a inicializar el DAO aquí
    }

    @FXML
    void actualizarSuscripción(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuscripción.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionActualizacion.fxml", "Actualizar Suscripción");
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
    void consultarSuscripción(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonConsultarSuscripción.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionBusqueda.fxml", "Consultar Suscripción");
    }

    @FXML
    void eliminarSuscripción(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonEliminarSuscripción.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionEliminacion.fxml", "Eliminar Suscripción");
    }

    @FXML
    void registrarFormularioSuscripción(ActionEvent event) {

        if(textFieldCodigo.getText().isEmpty() || textFieldTipo.getText().isEmpty() || textFieldDescripcion.getText().isEmpty() ||
           textFieldPrecio.getText().isEmpty() || textFieldDuracion.getText().isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, complete todos los campos.");
            return;
        }
        
        try {
            int codigo = Integer.parseInt(textFieldCodigo.getText());

            if (SuscripcionDAO.getInstancia().buscarPorCodigo(codigo) != null) {
                MetodosFrecuentes.mostrarError("Error", "La suscripción con el código " + codigo + " ya existe.");
                return;
            }

            String tipo = textFieldTipo.getText();
            String descripcion = textFieldDescripcion.getText();
            double precio = Double.parseDouble(textFieldPrecio.getText());
            int duracion = Integer.parseInt(textFieldDuracion.getText());

            Suscripcion suscripcion = new Suscripcion(codigo, tipo, descripcion, precio, duracion);
            SuscripcionDAO.getInstancia().crearSuscripcion(suscripcion);

        } catch (NumberFormatException e) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, ingrese valores numéricos válidos para precio y duración.");
            return;

        }catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo registrar la suscripción: " + e.getMessage());
            System.out.println("Error al registrar la suscripción: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        MetodosFrecuentes.mostrarInfo("Éxito", "La suscripción "+textFieldTipo.getText()+" se ha registrado correctamente.");
    }

    @FXML
    void registrarSuscripción(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarSuscripción.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Registrar Suscripción");
    }

}
