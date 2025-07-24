package ModuloFITEC.logic.DAOs;

import ModuloFITEC.DataBase.ConexionBaseSingleton;

import java.time.format.DateTimeFormatter;

public class ClienteDAO {
    private final ConexionBaseSingleton baseDeDatos;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ClienteDAO() {
        this.baseDeDatos = ConexionBaseSingleton.getInstancia();
    }

    //public void

}
