package ModuloFITEC.logic.DAOs;
import java.io.Serializable;
import java.util.List;

public interface InterfaceDAOBusqueda<T> {
    <K extends Serializable & Comparable<K>> T buscarPorCodigo(K codigo, String nombreTabla, String nombreColumna) throws Exception;
    List<T> listar(String nombreTabla) throws Exception;
}
