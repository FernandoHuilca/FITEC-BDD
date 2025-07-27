package ModuloFITEC.logic.DAOs;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.almasb.fxgl.scene3d.Cone;

import java.sql.ResultSet;
import java.sql.SQLException;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.NominaInstructor;

public class NominaInstructorDAO extends DAOGeneral<NominaInstructor> implements InterfaceDAOActualizacion<NominaInstructor>, InterfaceDAOCreacion<NominaInstructor> {

    private static NominaInstructorDAO instancia;

    private NominaInstructorDAO() {
        // Constructor privado para evitar instanciación externa
    }

    public static NominaInstructorDAO getInstancia() {
        if (instancia == null) {
            instancia = new NominaInstructorDAO();
        }
        return instancia;
    }

    /*
    public NominaInstructor buscarNominaPorCedula(String cedula) throws Exception {

        String consulta = """
        SET XACT_ABORT ON;
        SELECT * FROM dbo.NOMINA_INSTRUCTOR WHERE CEDULAINSTRUCTOR = '%s';
        """.formatted(cedula);

        ResultSet rs = null;
        Statement st = null;

        try {
            rs = ConexionBaseSingleton.getInstancia().ejecutarConsulta(consulta);
            return rs.next() ? mapear(rs) : null;
        } catch (Exception e) {
            throw new Exception("Error al buscar nómina de instructor por cédula: " + e.getMessage(), e);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public List<NominaInstructor> listarNominaInstructores() throws Exception {

        List<NominaInstructor> nominas = new ArrayList<>();

        String consulta = """
        SET XACT_ABORT ON;
        SELECT * FROM NOMINA_INSTRUCTOR;
        """;

        ResultSet rs = null;
        Statement st = null;

        try {
            rs = ConexionBaseSingleton.getInstancia().ejecutarConsulta(consulta);
            while (rs.next()) {
                NominaInstructor nomina = mapear(rs);
                nominas.add(nomina);
            }
            return nominas;
        } catch (Exception e) {
            throw new Exception("Error al buscar nómina de instructor por cédula: " + e.getMessage(), e);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }
         */

    @Override
    public NominaInstructor mapear(ResultSet rs) throws SQLException {
        java.sql.Timestamp timestamp = rs.getTimestamp("FECHACONTRATACION");
        LocalDateTime fechaContratacion = timestamp != null ? timestamp.toLocalDateTime() : null;
        NominaInstructor nomina = new NominaInstructor(
                rs.getString("CEDULAINSTRUCTOR"),
                rs.getDouble("SALARIO"),
                fechaContratacion
        );
        return nomina;
    }

    @Override
    public NominaInstructor actualizar(NominaInstructor entidadT) throws Exception {
        String consulta = """ 
        SET XACT_ABORT ON;
        UPDATE NOMINA_INSTRUCTOR
        SET SALARIO = %f, FECHACONTRATACION = '%s'
        WHERE CEDULAINSTRUCTOR = '%s'
        """.formatted(
                entidadT.getSalario(),
                entidadT.getFechaContratacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                entidadT.getCedulaInstructor()
        );

        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);

        return entidadT;
    }

    @Override
    public void crear(NominaInstructor entidadT) throws SQLException {
        String consulta = """ 
        SET XACT_ABORT ON;
        INSERTO INTO NOMINA_INSTRUCTOR (CEDULAINSTRUCTOR, SALARIO, FECHACONTRATACION) VALUES ('%s', %f, '%s')
        """.formatted(
                entidadT.getCedulaInstructor(),
                entidadT.getSalario(),
                entidadT.getFechaContratacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);
    }


    //NECESITO OBTENER EL SALARIO PARA PODER MOSTRARLO: ___________________________________________
    public double obtenerSalarioPorCedula(String cedula) throws Exception {
        String sql = "SELECT SALARIO FROM NOMINA_INSTRUCTOR WHERE CEDULAINSTRUCTOR = ?";
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConexionBaseSingleton.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("SALARIO");
            }
            return 0.0;
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, ps);
        }
    }
    public void actualizarSalarioInstructor(String cedula, double salario) throws Exception {
        String sql = "UPDATE NOMINA_INSTRUCTOR SET SALARIO = ? WHERE CEDULAINSTRUCTOR = ?";
        java.sql.PreparedStatement ps = null;
        try {
            ps = ConexionBaseSingleton.getInstancia().getConexion().prepareStatement(sql);
            ps.setDouble(1, salario);
            ps.setString(2, cedula);
            ps.executeUpdate();
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, ps);
        }
    }

    public ResultSet buscarNominaPorCedula(String cedula) {

        String consulta = "SELECT CEDULAINSTRUCTOR, SALARIO, FECHACONTRATACION FROM dbo.NOMINA_INSTRUCTOR WHERE CEDULAINSTRUCTOR = ?";

        try {
            PreparedStatement statement = ConexionBaseSingleton.getInstancia().getConexion().prepareStatement(consulta);
            statement.setString(1, cedula);
            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void registrarNominaInstructor(String cedula, double salario, LocalDate fechaContratacion) throws Exception {
        String sql = "INSERT INTO NOMINA_INSTRUCTOR (CEDULAINSTRUCTOR, SALARIO, FECHACONTRATACION) VALUES (?, ?, ?)";
        java.sql.PreparedStatement ps = null;
        try {
            ps = ConexionBaseSingleton.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, cedula);
            ps.setDouble(2, salario);
            ps.setDate(3, java.sql.Date.valueOf(fechaContratacion));
            ps.executeUpdate();
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, ps);
        }
    }


    public void eliminarPorCedula(String cedula) throws Exception {
        String sql = "DELETE FROM NOMINA_INSTRUCTOR WHERE CEDULAINSTRUCTOR = ?";
        java.sql.PreparedStatement ps = null;
        try {
            ps = ConexionBaseSingleton.getInstancia().getConexion().prepareStatement(sql);
            ps.setString(1, cedula);
            ps.executeUpdate();
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, ps);
        }
    }
    //  ___________________________________________ ___________________________________________
}