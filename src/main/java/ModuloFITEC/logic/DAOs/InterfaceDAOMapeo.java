package ModuloFITEC.logic.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface InterfaceDAOMapeo<T> {
    T mapear(ResultSet rs) throws SQLException;
}
