package ModuloFITEC.Controllers;
import MetodosGlobales.MetodosFrecuentes;
import ModuloFITEC.logic.DAOs.DAOGeneral;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControladorGeneral<T> {

    public T obtenerObjetoPorCodigo(int codigo, DAOGeneral<T> dao, String nombreTabla, String nombreColumna) {
        T objeto;
        try {
            objeto = dao.buscarPorCodigo(codigo, nombreTabla, nombreColumna);
        } catch (Exception e) {
            MetodosFrecuentes.mostrarError("Error", "No se pudo realizar la consulta: " + e.getMessage());
            System.out.println("Error al realizar la consulta: " + e.getMessage());
            return null;
        }

        if (objeto == null) {
            MetodosFrecuentes.mostrarError("Error", "Información no encontrada");
            return null;
        }

        //MetodosFrecuentes.mostrarInfo("Información", "Información encontrada");
        return objeto;
    }

    public int obtenerCodigoDeTextField(String textFieldCodigo) {
        textFieldCodigo = textFieldCodigo.strip();
        if (textFieldCodigo.strip().isEmpty() || textFieldCodigo.strip().isBlank() || textFieldCodigo == null) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, ingrese un código.");
            return 0;
        }

        try {
            int codigo = Integer.parseInt(textFieldCodigo.strip());
            if (codigo <= 0) {
                MetodosFrecuentes.mostrarError("Error", "El código debe ser un número positivo.");
                return 0;
            }
            //MetodosFrecuentes.mostrarInfo("Información", "Código obtenido correctamente.");
            return codigo;
            
        } catch (NumberFormatException e) {
            MetodosFrecuentes.mostrarError("Error", "Por favor, ingrese un código válido.");
            return 0;
        }
    }

    public void colocarObjetoEnTabla(T objetoT, ObservableList<T> lista, TableView<T> tabla) {
        tabla.getItems().clear();
        lista.add(objetoT);
        tabla.setItems(lista);
        MetodosFrecuentes.mostrarInfo("Éxito", "Información actualizada correctamente.");

    }

    T mostrarEnTabla(TextField textFieldCodigo, DAOGeneral<T> dao, String nombreTabla, String nombreColumna, ObservableList<T> lista, TableView<T> tableView) {
        int codigo = obtenerCodigoDeTextField(textFieldCodigo.getText().strip());
        if (codigo <= 0) {
            return null;
        }

        T objeto = obtenerObjetoPorCodigo(codigo, dao, nombreTabla, nombreColumna);
        if (objeto == null) {
            return null;
        }

        colocarObjetoEnTabla(objeto, lista, tableView);

        return objeto;
        
    }
}
