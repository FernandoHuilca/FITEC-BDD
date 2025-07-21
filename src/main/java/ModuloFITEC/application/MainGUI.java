package ModuloFITEC.application;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModuloFITEC/views/Login.fxml"));
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
                ConexionBaseSingleton.getInstancia().getConexion();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println("¡Conexión exitosa a SQL Server!");
        } catch (Exception e) {
            System.err.println("No se encontró el driver SQL Server: " + e.getMessage());
        }
        launch();
    }
}