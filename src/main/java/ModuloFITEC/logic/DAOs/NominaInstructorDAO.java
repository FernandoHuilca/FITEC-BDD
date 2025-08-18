package ModuloFITEC.logic.DAOs;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                INSERT INTO NOMINA_INSTRUCTOR (CEDULAINSTRUCTOR, SALARIO, FECHACONTRATACION) VALUES ('%s', %f, '%s')
                """.formatted(
                entidadT.getCedulaInstructor(),
                entidadT.getSalario(),
                entidadT.getFechaContratacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);
    }


    //NECESITO OBTENER EL SALARIO PARA PODER MOSTRARLO: ___________________________________________
    public double obtenerSalarioPorCedula(String cedula) throws Exception {
        String sql = String.format("""
                    SET XACT_ABORT ON;
                    SELECT SALARIO FROM [DESKTOP-HSCPRKC].QUITO_NORTE.dbo.NOMINA_INSTRUCTOR WHERE CEDULAINSTRUCTOR = '%s'
                """, cedula);
        ResultSet rs = null;
        Statement st = null;
        try {
            st = ConexionBaseSingleton.getInstancia().getConexion().createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble("SALARIO");
            }
            return 0.0;
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public void actualizarSalarioInstructor(String cedula, double salario) throws Exception {
        String sql = String.format("""
                    SET XACT_ABORT ON;
                    UPDATE [DESKTOP-HSCPRKC].QUITO_NORTE.dbo.NOMINA_INSTRUCTOR
                    SET SALARIO = %f WHERE CEDULAINSTRUCTOR = '%s'
                """, salario, cedula);
        Statement st = null;
        try {
            st = ConexionBaseSingleton.getInstancia().getConexion().createStatement();
            st.executeUpdate(sql);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, st);
        }
    }

    public ResultSet buscarNominaPorCedula(String cedula) {
        String sql = String.format("""
                    SET XACT_ABORT ON;
                    SELECT CEDULAINSTRUCTOR, SALARIO, FECHACONTRATACION
                    FROM [DESKTOP-HSCPRKC].QUITO_NORTE.dbo.NOMINA_INSTRUCTOR
                    WHERE CEDULAINSTRUCTOR = '%s'
                """, cedula);
        try {
            Statement st = ConexionBaseSingleton.getInstancia().getConexion().createStatement();
            return st.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registrarNominaInstructor(String cedula, double salario, LocalDate fechaContratacion) throws Exception {
        String fecha = fechaContratacion.toString(); // formato yyyy-MM-dd
        String sql = String.format("""
                    SET XACT_ABORT ON;
                    INSERT INTO [DESKTOP-HSCPRKC].QUITO_NORTE.dbo.NOMINA_INSTRUCTOR
                    (CEDULAINSTRUCTOR, SALARIO, FECHACONTRATACION)
                    VALUES ('%s', %f, '%s')
                """, cedula, salario, fecha);
        Statement st = null;
        try {
            st = ConexionBaseSingleton.getInstancia().getConexion().createStatement();
            st.executeUpdate(sql);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, st);
        }
    }

    public void eliminarPorCedula(String cedula) throws Exception {
        String sql = String.format("""
                    SET XACT_ABORT ON;
                    DELETE FROM [DESKTOP-HSCPRKC].QUITO_NORTE.dbo.NOMINA_INSTRUCTOR
                    WHERE CEDULAINSTRUCTOR = '%s'
                """, cedula);
        Statement st = null;
        try {
            st = ConexionBaseSingleton.getInstancia().getConexion().createStatement();
            st.executeUpdate(sql);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, st);
        }
    }
}