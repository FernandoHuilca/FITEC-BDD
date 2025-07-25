package ModuloFITEC.logic.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.Compra;
import ModuloFITEC.logic.Models.Suplemento;

public class CompraDAO {
    private final ConexionBaseSingleton db;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CompraDAO() {
        this.db = ConexionBaseSingleton.getInstancia();
    }

    public void crearCompra(Compra compra) throws Exception {
        // Obtener el ID del suplemento desde su nombre
        Suplemento suplemento = new SuplementoDAO().buscarPorNombre(compra.getNombreSuplemento());
        if (suplemento == null) {
            throw new Exception("No se encontró el suplemento con nombre: " + compra.getNombreSuplemento());
        }

        int idSuplemento = suplemento.getIdSuplemento();

        String sql = """
            SET XACT_ABORT ON;
            INSERT INTO COMPRA (IDCOMPRA, CEDULACLIENTE, IDSUPLEMENTO, CANTIDADCOMPRADA,
                                FECHACOMPRA, PRECIOCOMPRA, IDSUCURSAL)
            VALUES (%d, '%s', %d, %d, '%s', %.2f, '%s')
            """.formatted(
                compra.getIdCompra(),
                compra.getCedulaCliente(),
                idSuplemento,
                compra.getCantidadComprada(),
                compra.getFechaCompra().format(formatter),
                compra.getPrecioCompra(),
                compra.getIdSucursal()
            );

        db.ejecutarActualizacion(sql);
    }

    public void actualizarCompra(Compra c) throws Exception {
        Suplemento suplemento = new SuplementoDAO().buscarPorNombre(c.getNombreSuplemento());
        if (suplemento == null) {
            throw new Exception("No se encontró el suplemento con nombre: " + c.getNombreSuplemento());
        }

        int idSuplemento = suplemento.getIdSuplemento();

        String sql = """
            SET XACT_ABORT ON;
            UPDATE COMPRA SET
                CEDULACLIENTE = '%s',
                IDSUPLEMENTO = %d,
                CANTIDADCOMPRADA = %d,
                FECHACOMPRA = '%s',
                PRECIOCOMPRA = %.2f,
                IDSUCURSAL = '%s'
            WHERE IDCOMPRA = %d
            """.formatted(
                c.getCedulaCliente(),
                idSuplemento,
                c.getCantidadComprada(),
                c.getFechaCompra().format(formatter),
                c.getPrecioCompra(),
                c.getIdSucursal(),
                c.getIdCompra()
            );

        db.ejecutarActualizacion(sql);
    }

    public Compra buscarPorIdCompra(int idCompra) throws Exception {
        String sql = """
            SELECT C.IDCOMPRA, C.CEDULACLIENTE, S.NOMBRE AS NOMBRESUPLEMENTO,
                C.CANTIDADCOMPRADA, C.FECHACOMPRA, C.PRECIOCOMPRA, C.IDSUCURSAL
            FROM COMPRA C
            JOIN SUPLEMENTO S ON C.IDSUPLEMENTO = S.IDSUPLEMENTO
            WHERE C.IDCOMPRA = %d
            """.formatted(idCompra);

        ResultSet rs = null;
        Statement st = null;
        try {
            rs = db.ejecutarConsulta(sql);
            if (rs.next()) {
                return mapear(rs);
            } else {
                return null;
            }
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public List<Compra> buscarPorCedulaCliente(String cedula) throws Exception {
        String sql = """
            SELECT C.IDCOMPRA, C.CEDULACLIENTE, S.NOMBRE AS NOMBRESUPLEMENTO,
                C.CANTIDADCOMPRADA, C.FECHACOMPRA, C.PRECIOCOMPRA, C.IDSUCURSAL
            FROM COMPRA C
            JOIN SUPLEMENTO S ON C.IDSUPLEMENTO = S.IDSUPLEMENTO
            WHERE C.CEDULACLIENTE = '%s'
            """.formatted(cedula);

        ResultSet rs = null;
        Statement st = null;
        try {
            rs = db.ejecutarConsulta(sql);
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public List<Compra> buscarPorNombreSuplemento(String nombre) throws Exception {
        String sql = """
            SELECT C.IDCOMPRA, C.CEDULACLIENTE, S.NOMBRE AS NOMBRESUPLEMENTO,
                C.CANTIDADCOMPRADA, C.FECHACOMPRA, C.PRECIOCOMPRA, C.IDSUCURSAL
            FROM COMPRA C
            JOIN SUPLEMENTO S ON C.IDSUPLEMENTO = S.IDSUPLEMENTO
            WHERE S.NOMBRE = '%s'
            """.formatted(nombre);

        ResultSet rs = null;
        Statement st = null;
        try {
            rs = db.ejecutarConsulta(sql);
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public List<Compra> buscarPorIdSucursal(String idSucursal) throws Exception {
        String sql = """
            SELECT C.IDCOMPRA, C.CEDULACLIENTE, S.NOMBRE AS NOMBRESUPLEMENTO,
                C.CANTIDADCOMPRADA, C.FECHACOMPRA, C.PRECIOCOMPRA, C.IDSUCURSAL
            FROM COMPRA C
            JOIN SUPLEMENTO S ON C.IDSUPLEMENTO = S.IDSUPLEMENTO
            WHERE C.IDSUCURSAL = '%s'
            """.formatted(idSucursal);

        ResultSet rs = null;
        Statement st = null;
        try {
            rs = db.ejecutarConsulta(sql);
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public List<Compra> listarCompras() throws Exception {
        String sql = """
            SELECT C.IDCOMPRA, C.CEDULACLIENTE, S.NOMBRE AS NOMBRESUPLEMENTO,
                C.CANTIDADCOMPRADA, C.FECHACOMPRA, C.PRECIOCOMPRA, C.IDSUCURSAL
            FROM COMPRA C
            JOIN SUPLEMENTO S ON C.IDSUPLEMENTO = S.IDSUPLEMENTO
            ORDER BY C.IDCOMPRA
            """;

        ResultSet rs = null;
        Statement st = null;
        try {
            rs = db.ejecutarConsulta(sql);
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public void eliminarPorCodigoCompra(int id) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            DELETE FROM COMPRA WHERE IDCOMPRA = %d
            """.formatted(id);

        db.ejecutarActualizacion(sql);
    }

    public void eliminarPorIdSucursal(String idSucursal) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            DELETE FROM COMPRA WHERE IDSUCURSAL = '%s'
            """.formatted(idSucursal);

        db.ejecutarActualizacion(sql);
    }

    public void eliminarPorSuplemento(String nombreSuplemento) throws Exception {
        Suplemento suplemento = new SuplementoDAO().buscarPorNombre(nombreSuplemento);
        if (suplemento == null) {
            throw new Exception("No se encontró el suplemento con nombre: " + nombreSuplemento);
        }

        String sql = """
            SET XACT_ABORT ON;
            DELETE FROM COMPRA WHERE IDSUPLEMENTO = %d
            """.formatted(suplemento.getIdSuplemento());

        db.ejecutarActualizacion(sql);
    }

    public void eliminarPorCliente(String cedulaCliente) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            DELETE FROM COMPRA WHERE CEDULACLIENTE = '%s'
            """.formatted(cedulaCliente);

        db.ejecutarActualizacion(sql);
    }

    private Compra mapear(ResultSet rs) throws SQLException {
        return new Compra(
            rs.getInt("IDCOMPRA"),
            rs.getString("CEDULACLIENTE"),
            rs.getString("NOMBRESUPLEMENTO"),
            rs.getInt("CANTIDADCOMPRADA"),
            rs.getTimestamp("FECHACOMPRA").toLocalDateTime(),
            rs.getDouble("PRECIOCOMPRA"),
            rs.getString("IDSUCURSAL")
        );
    }

    private List<Compra> mapearLista(ResultSet rs) throws SQLException {
        List<Compra> lista = new ArrayList<>();
        while (rs.next()) lista.add(mapear(rs));
        return lista;
    }
}
