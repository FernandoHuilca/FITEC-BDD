package ModuloFITEC.logic.DAOs;
import java.sql.SQLException;

public interface InterfaceDAOCreacion<T> {
    void crear(T entidadT) throws SQLException;
}
