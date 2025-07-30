package ModuloFITEC.logic.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.Suplemento;

public class SuplementoDAO {
    private final ConexionBaseSingleton db;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SuplementoDAO() {
        this.db = ConexionBaseSingleton.getInstancia();
    }

    public void crearSuplemento(Suplemento suplemento) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            INSERT INTO SUPLEMENTO (IDSUPLEMENTO, IDSUCURSAL, NOMBRE, CATEGORIA,
                                    PRECIO, CANTIDADDISPONIBLE, FECHAVENCIMIENTO)
            VALUES (%d, '%s', '%s', '%s', %.2f, %d, '%s')
            """.formatted(
                suplemento.getIdSuplemento(),
                suplemento.getIdSucursal(),
                suplemento.getNombre(),
                suplemento.getCategoria(),
                suplemento.getPrecio(),
                suplemento.getCantidadDisponible(),
                suplemento.getFechaVencimiento().format(formatter)
            );

        db.ejecutarActualizacion(sql);
    }

    public void actualizarSuplemento(Suplemento s) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            UPDATE SUPLEMENTO SET
                IDSUCURSAL = '%s',
                NOMBRE = '%s',
                CATEGORIA = '%s',
                PRECIO = %.2f,
                CANTIDADDISPONIBLE = %d,
                FECHAVENCIMIENTO = '%s'
            WHERE IDSUPLEMENTO = %d
            """.formatted(
                s.getIdSucursal(),
                s.getNombre(),
                s.getCategoria(),
                s.getPrecio(),
                s.getCantidadDisponible(),
                s.getFechaVencimiento().format(formatter),
                s.getIdSuplemento()
            );

        db.ejecutarActualizacion(sql);
    }   

    public List<Suplemento> buscarPorNombre(String nombre) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            SELECT * FROM SUPLEMENTO
            WHERE NOMBRE = '%s'
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

    public List<Suplemento> listarSuplementos() throws Exception {
        String sql = "SELECT * FROM SUPLEMENTO ORDER BY IDSUPLEMENTO";
        ResultSet rs = null;
        Statement st = null;
        try {
            rs = db.ejecutarConsulta(sql);
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    private Suplemento mapear(ResultSet rs) throws SQLException {
        Suplemento suplemento = new Suplemento(
            rs.getInt("IDSUPLEMENTO"),
            rs.getString("IDSUCURSAL"),
            rs.getString("NOMBRE"),
            rs.getString("CATEGORIA"),
            rs.getDouble("PRECIO"),
            rs.getInt("CANTIDADDISPONIBLE"),
            rs.getDate("FECHAVENCIMIENTO").toLocalDate()
        );
        suplemento.setIdSucursal(rs.getString("IDSUCURSAL"));
        return suplemento;
    }

    private List<Suplemento> mapearLista(ResultSet rs) throws SQLException {
        List<Suplemento> listaSuplementos = new ArrayList<>();
        while (rs.next()) listaSuplementos.add(mapear(rs));
        return listaSuplementos;
    }

    public void eliminarSuplemento(int id) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            DELETE FROM SUPLEMENTO WHERE IDSUPLEMENTO = %d
            """.formatted(id);

        db.ejecutarActualizacion(sql);
    }

    public void eliminarPorNombre(String nombre) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            DELETE FROM SUPLEMENTO WHERE NOMBRE = '%s'
            """.formatted(nombre);
        db.ejecutarActualizacion(sql);
    }

    public Suplemento buscarPorId(int id) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            SELECT * FROM SUPLEMENTO
            WHERE IDSUPLEMENTO = %d
            """.formatted(id);

        ResultSet rs = null;
        Statement st = null;
        try {
            rs = db.ejecutarConsulta(sql);
            return rs.next() ? mapear(rs) : null;
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public Suplemento buscarPorNombreEId(String nombre, int id) throws Exception {
        String sql = """
            SET XACT_ABORT ON;
            SELECT * FROM SUPLEMENTO
            WHERE NOMBRE = '%s' AND IDSUPLEMENTO = %d
            """.formatted(nombre, id);

        ResultSet rs = null;
        Statement st = null;
        try {
            rs = db.ejecutarConsulta(sql);
            return rs.next() ? mapear(rs) : null;
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public boolean existeSuplementoEnSucursal(String nombre, String sucursal) throws Exception {
        String sql = """
            SELECT COUNT(*) 
            FROM SUPLEMENTO 
            WHERE LOWER(NOMBRE) = LOWER(?) AND IDSUCURSAL = ?
        """;

        try (Connection conn = db.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setString(2, sucursal);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (SQLException e) {
            throw new Exception("Error al verificar existencia del suplemento: " + e.getMessage(), e);
        }
    }


}
