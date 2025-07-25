package MetodosGlobales;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MetodosFrecuentes {


    public static void cambiarVentana(Stage currentStage, String rutaFXML, String titulo) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(MetodosFrecuentes.class.getResource(rutaFXML));
            Parent root = loader.load();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle(titulo);

        } catch (IOException e) {
            mostrarError("Error", "No se pudo cargar la interfaz de usuario.");
            e.printStackTrace();
        }
    }

    public static void cambiarVentana(Stage currentStage, String rutaFXML) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(MetodosFrecuentes.class.getResource(rutaFXML));
            Parent root = loader.load();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));

        } catch (IOException e) {
            mostrarError("Error", "No se pudo cargar la interfaz de usuario.");
            e.printStackTrace();
        }
    }


    public static void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // NUEVO: Abrir una nueva ventana independiente
    public static void abrirNuevaVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(MetodosFrecuentes.class.getResource(rutaFXML));
            Parent root = loader.load();

            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.setTitle(titulo);
            nuevaVentana.show();

        } catch (IOException e) {
            mostrarError("Error", "No se pudo abrir la nueva ventana.");
            e.printStackTrace();
        }
    }

    // NUEVO: Abrir una ventana modal (bloqueante)
    public static void abrirVentanaModal(String rutaFXML, String titulo, Stage owner) {
        try {
            FXMLLoader loader = new FXMLLoader(MetodosFrecuentes.class.getResource(rutaFXML));
            Parent root = loader.load();

            Stage modal = new Stage();
            modal.initOwner(owner); // Ventana principal
            modal.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal
            modal.setTitle(titulo);
            modal.setScene(new Scene(root));
            modal.showAndWait(); // Espera a que se cierre

        } catch (IOException e) {
            mostrarError("Error", "No se pudo abrir la ventana modal.");
            e.printStackTrace();
        }
    }
    public static void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}