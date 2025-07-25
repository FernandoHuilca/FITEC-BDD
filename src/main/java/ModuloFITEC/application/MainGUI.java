package ModuloFITEC.application;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModuloFITEC/views/VistaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Cerrando conexion");
        ConexionBaseSingleton.getInstancia().cerrar();
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try {
                ConexionBaseSingleton.getInstancia().establecerURL(false); // Establece la URL para QUITO_SUR
                ConexionBaseSingleton.getInstancia().getConexion();
            } catch (Exception e) {
                try {
                    ConexionBaseSingleton.getInstancia().establecerURL(true); // Establece la URL para QUITO_NORTE
                    ConexionBaseSingleton.getInstancia().getConexion();
                } catch (Exception ex) {
                    System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
                    return; // Termina la ejecución si no se puede conectar
                }
            }

            System.out.println("¡Conexión exitosa a SQL Server!");
        } catch (Exception e) {
            System.err.println("No se encontró el driver SQL Server: " + e.getMessage());
            return; // Termina la ejecución si no se encuentra el driver
        }
        launch();
    }
}