package ModuloFITEC.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ControladorInicio {
    @FXML
    private ImageView imageViewNomina;

    @FXML
    private ControladorMenuIzquierdo vistaMenuIzquierdoController;

    @FXML
    void initialize() {
        // Configurar el estilo del botón de inicio en el menú lateral
        if (vistaMenuIzquierdoController != null) {
            vistaMenuIzquierdoController.getButtonInicio().setStyle("-fx-background-color: #ff5f5f");
        }
    }
}
