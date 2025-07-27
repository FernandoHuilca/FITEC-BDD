package ModuloFITEC.Controllers;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladorSuscripcionCreacion {

    @FXML
    private Button buttonActualizarSuscripcion;

    @FXML
    private Button buttonConsultarSuscripcion;

    @FXML
    private Button buttonEliminarSuscripcion;


    @FXML
    private Button buttonRegistrarFormularioSuscripcion;

    @FXML
    private Button buttonRegistrarSuscripcion;

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
    private ImageView imageViewNomina;

    @FXML
    void initialize() {
        // No es necesario volver a inicializar el DAO aquí
        //buttonNominaInstructores.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
        //imageViewNomina.setVisible(ConexionBaseSingleton.getInstancia().isNodoNorte());
    }

    @FXML
    void actualizarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonActualizarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionActualizacion.fxml", "Actualizar Suscripción");
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
    void registrarFormularioSuscripcion(ActionEvent event) {

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
        limpiarCampos();
        
    }

    @FXML
    void registrarSuscripcion(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) buttonRegistrarSuscripcion.getScene().getWindow(), "/ModuloFITEC/views/VistaSuscripcionCreacion.fxml", "Registrar Suscripción");
    }

    private void limpiarCampos() {
        textFieldCodigo.clear();
        textFieldTipo.clear();
        textFieldDescripcion.clear();
        textFieldPrecio.clear();
        textFieldDuracion.clear();
    }

}
