package ModuloFITEC.logic.DAOs;

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
}
