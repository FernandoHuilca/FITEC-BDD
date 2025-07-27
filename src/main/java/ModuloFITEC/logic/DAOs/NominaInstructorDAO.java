package ModuloFITEC.logic.DAOs;
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
        INSERTO INTO NOMINA_INSTRUCTOR (CEDULAINSTRUCTOR, SALARIO, FECHACONTRATACION) VALUES ('%s', %f, '%s')
        """.formatted(
            entidadT.getCedulaInstructor(),
            entidadT.getSalario(),
            entidadT.getFechaContratacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        ConexionBaseSingleton.getInstancia().ejecutarActualizacion(consulta);
    }

}
