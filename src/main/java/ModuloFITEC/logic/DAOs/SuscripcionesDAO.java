package ModuloFITEC.logic.DAOs;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.Suscripcion;

public class SuscripcionesDAO {

    private final ConexionBaseSingleton db;

    public SuscripcionesDAO() {
        this.db = ConexionBaseSingleton.getInstancia();
    }

    public void crearSuscripcion(Suscripcion suscripcion) throws Exception {

        String consulta = 
        """
        SET XACT_ABORT ON;
        INSERT INTO SUSCRIPCION (IDSUSCRIPCION, TIPO, DESCRIPCION, PRECIO, DURACIONMESES) 
        VALUES (%d, '%s', '%s', %.2f, %d)
        """.formatted(
            suscripcion.getIdSuscripcion(),
            suscripcion.getTipo(),
            suscripcion.getDescripcion(),
            suscripcion.getPrecio(),
            suscripcion.getDuracionMeses()
        );

        db.ejecutarActualizacion(consulta);
    }

    public Suscripcion buscarPorCodigo(int idSuscripcion) throws Exception {
        String consulta =
        """
        SET XACT_ABORT ON;
        SELECT * FROM SUSCRIPCION WHERE IDSUSCRIPCION = %d
        """.formatted(idSuscripcion);

        ResultSet rs = null;
        Statement st = null;

        try {
            rs = db.ejecutarConsulta(consulta);
            return rs.next() ? mapear(rs) : null;
        } catch (SQLException e) {
            throw new Exception("Error al buscar suscripción por ID: " + e.getMessage(), e);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public List<Suscripcion> listarSuscripciones() throws Exception {
        String consulta = """
        SET XACT_ABORT ON;
        SELECT * FROM SUSCRIPCION""";
        ResultSet rs = null;
        Statement st = null;

        List<Suscripcion> suscripcionesList = new ArrayList<>();

        try {
            rs = db.ejecutarConsulta(consulta);
            while (rs.next()) {
                Suscripcion suscripcion = mapear(rs);
                suscripcionesList.add(suscripcion);
            }
            return suscripcionesList;
        } catch (SQLException e) {
            throw new Exception("Error al listar suscripciones: " + e.getMessage(), e);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    private Suscripcion mapear(ResultSet rs) throws SQLException {
        Suscripcion suscripcion = new Suscripcion(
            rs.getInt("IDSUSCRIPCION"),
            rs.getString("TIPO"),
            rs.getString("DESCRIPCION"),
            rs.getDouble("PRECIO"),
            rs.getInt("DURACIONMESES")
        );
        return suscripcion;
    }

    public Suscripcion actualizarSuscripción(Suscripcion suscripcion) throws Exception {
        String consulta = 
        """
        SET XACT_ABORT ON;
        UPDATE SUSCRIPCION
        SET TIPO = '%S', DESCRIPCION = '%s', PRECIO = %.2f, DURACIONMESES = %d
        WHERE IDSUSCRIPCION = %d
        """.formatted(
            suscripcion.getTipo(),
            suscripcion.getDescripcion(),
            suscripcion.getPrecio(),
            suscripcion.getDuracionMeses(),
            suscripcion.getIdSuscripcion()
        );

        db.ejecutarActualizacion(consulta); 

        return buscarPorCodigo(suscripcion.getIdSuscripcion());
    }

    public void eliminarPorCodigo(int codigoSuscripcionPorEliminar) throws SQLException {
        String consulta =
        """ 
        DELETE SUSCRIPCION WHERE IDSUSCRIPCION = %d
        """.formatted(codigoSuscripcionPorEliminar);
        db.ejecutarActualizacion(consulta);
    }
    
}
