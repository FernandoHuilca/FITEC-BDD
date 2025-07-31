package ModuloFITEC.logic.DAOs;
import java.sql.ResultSet;
import java.sql.SQLException;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.Suscripcion;

public class SuscripcionDAO extends DAOGeneral<Suscripcion> implements InterfaceDAOCreacion<Suscripcion>, InterfaceDAOActualizacion<Suscripcion> {

    private static SuscripcionDAO instancia;

    private SuscripcionDAO() {
    }

    public static SuscripcionDAO getInstancia() {
        if (instancia == null) {
            instancia = new SuscripcionDAO();
        }
        return instancia;
    }

    /*
    public void crearSuscripcion(Suscripcion suscripcion) throws SQLException {

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

        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);
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
            rs = ConexionBaseSingleton.getInstancia().ejecutarConsulta(consulta);
            return rs.next() ? mapear(rs) : null;
        } catch (Exception e) {
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
            rs = ConexionBaseSingleton.getInstancia().ejecutarConsulta(consulta);
            while (rs.next()) {
                Suscripcion suscripcion = mapear(rs);
                suscripcionesList.add(suscripcion);
            }
            return suscripcionesList;
        } catch (Exception e) {
            throw new Exception("Error al listar suscripciones: " + e.getMessage(), e);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }
        */


    /* 
    public Suscripcion actualizarSuscripcion(Suscripcion suscripcion) throws Exception {
        String consulta = 
        """
        SET XACT_ABORT ON;
        UPDATE SUSCRIPCION
        SET TIPO = '%S', DESCRIPCION = '%s', PRECIO = %.2f, DURACIONMESES = %d
        WHERE IDSUSCRIPCION = %d
        """.formatted(
            entidadT.getTipo(),
            entidadT.getDescripcion(),
            entidadT.getPrecio(),
            entidadT.getDuracionMeses(),
            entidadT.getIdSuscripcion()
        );

        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);

        return buscarPorCodigo(entidadT.getIdSuscripcion());
        
    }*/

    /* 
    public void eliminarPorCodigo(int codigoSuscripcionPorEliminar) throws SQLException {
        String consulta =
        """ 
        DELETE SUSCRIPCION WHERE IDSUSCRIPCION = %d
        """.formatted(codigoSuscripcionPorEliminar);
        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);
    }*/

    @Override
    public Suscripcion actualizar(Suscripcion entidadT) throws Exception {
        String consulta = 
        """
        SET XACT_ABORT ON;
        UPDATE SUSCRIPCION
        SET TIPO = '%s', DESCRIPCION = '%s', PRECIO = %.2f, DURACIONMESES = %d
        WHERE IDSUSCRIPCION = %d
        """.formatted(
            entidadT.getTipo(),
            entidadT.getDescripcion(),
            entidadT.getPrecio(),
            entidadT.getDuracionMeses(),
            entidadT.getIdSuscripcion()
        );

        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);

        return entidadT;
    }

    @Override
    public void crear(Suscripcion entidadT) throws SQLException {
        String consulta = 
        """
        SET XACT_ABORT ON;
        INSERT INTO SUSCRIPCION (IDSUSCRIPCION, TIPO, DESCRIPCION, PRECIO, DURACIONMESES) 
        VALUES (%d, '%s', '%s', %.2f, %d)
        """.formatted(
            entidadT.getIdSuscripcion(),
            entidadT.getTipo(),
            entidadT.getDescripcion(),
            entidadT.getPrecio(),
            entidadT.getDuracionMeses()
        );

        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);
    }

    @Override
    public Suscripcion mapear(ResultSet rs) throws SQLException {
        Suscripcion suscripcion = new Suscripcion(
            rs.getInt("IDSUSCRIPCION"),
            rs.getString("TIPO"),
            rs.getString("DESCRIPCION"),
            rs.getDouble("PRECIO"),
            rs.getInt("DURACIONMESES")
        );
        return suscripcion;
    }
    
}
