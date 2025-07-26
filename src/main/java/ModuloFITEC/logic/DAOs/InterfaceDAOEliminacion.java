package ModuloFITEC.logic.DAOs;
import java.io.Serializable;
import java.sql.SQLException;

public interface InterfaceDAOEliminacion<T> {
    <K extends Serializable & Comparable<K>> void eliminarPorCodigo(K codigo, String nombreTabla, String nombreColumna) throws SQLException;
}
