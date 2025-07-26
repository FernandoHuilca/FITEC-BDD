package ModuloFITEC.Controllers;

import java.util.Observable;

import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.SuscripcionDAO;
import ModuloFITEC.logic.Models.Suscripcion;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControladorGeneral {

    public static Suscripcion obtenerSuscripcionPorCodigo(int codigo) {
        Suscripcion suscripcion;
        try {
            suscripcion = SuscripcionDAO.getInstancia().buscarPorCodigo(codigo);
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo consultar la suscripción: " + e.getMessage());
            System.out.println("Error al consultar la suscripción: " + e.getMessage());
            return null;
        }

        if (suscripcion == null) {
            MetodosFrecuentes.mostrarError("Error", "Suscripción no encontrada");
            return null;
        }
        
        MetodosFrecuentes.mostrarInfo("Información", "Suscripción encontrada: " + suscripcion.getTipo());
        return suscripcion;
    }

    public static int obtenerCodigo(String textFieldCodigo) {
        if (textFieldCodigo.isEmpty()) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, ingrese un código.");
            return 0;
        }

        try {
            int codigo = Integer.parseInt(textFieldCodigo);
            if (codigo <= 0) {
                MetodosFrecuentes.mostrarError("Error", "El código debe ser un número positivo.");
                return 0;
            }
            return codigo;
            
        } catch (NumberFormatException e) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, ingrese un código válido.");
            return 0;
        }
    }

}
