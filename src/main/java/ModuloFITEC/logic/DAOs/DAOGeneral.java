package ModuloFITEC.logic.DAOs;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ModuloFITEC.DataBase.ConexionBaseSingleton;;


public abstract class DAOGeneral<T> implements InterfaceDAOBusqueda<T>, InterfaceDAOEliminacion<T> {

    @Override
    public <K extends Serializable & Comparable<K>> void eliminarPorCodigo(K codigo, String nombreTabla, String nombreColumna) throws SQLException {
        String consulta =
        """
        SET XACT_ABORT ON;
        DELETE FROM %s WHERE %s = %d
        """.formatted(nombreTabla, nombreColumna, codigo);
        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);
    }

    @Override
    public <K extends Serializable & Comparable<K>> T buscarPorCodigo(K codigo, String nombreTabla, String nombreColumna) throws Exception {
        String consulta =
        """
        SET XACT_ABORT ON;
        SELECT * FROM %s WHERE %s = %d
        """.formatted(nombreTabla, nombreColumna, codigo);

        ResultSet rs = null;
        Statement st = null;

        try {
            rs = ConexionBaseSingleton.getInstancia().ejecutarConsulta(consulta);
            return rs.next() ? mapear(rs) : null;
        } catch (Exception e) {
            throw new Exception("Error al buscar objeto por ID: " + e.getMessage(), e);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public <K extends Serializable & Comparable<K>> T buscarPorString(K valorAComparar, String nombreTabla, String nombreColumna, String columnaID, int id) throws Exception {
        String consulta =
        """
        SET XACT_ABORT ON;
        SELECT * FROM %s WHERE %s = '%s' AND %s <> '%s'
        """.formatted(nombreTabla, nombreColumna, valorAComparar, columnaID, id);

        ResultSet rs = null;
        Statement st = null;

        try {
            rs = ConexionBaseSingleton.getInstancia().ejecutarConsulta(consulta);
            return rs.next() ? mapear(rs) : null;
        } catch (Exception e) {
            throw new Exception("Error al buscar objeto por ID: " + e.getMessage(), e);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public abstract T mapear(ResultSet rs) throws SQLException;

    @Override
    public List<T> listar(String nombreTabla) throws Exception {
        String consulta = """
        SET XACT_ABORT ON;
        SELECT * FROM %s""".formatted(nombreTabla);
        ResultSet rs = null;
        Statement st = null;

        List<T> objetosList = new ArrayList<>();

        try {
            rs = ConexionBaseSingleton.getInstancia().ejecutarConsulta(consulta);
            while (rs.next()) {
                T objeto = mapear(rs);
                objetosList.add(objeto);
            }
            return objetosList;
        } catch (Exception e) {
            throw new Exception("Error al listar objetos: " + e.getMessage(), e);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

}
