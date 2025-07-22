package ModuloFITEC.logic.DAOs;

import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ModuloFITEC.DataBase.ConexionBaseSingleton;

public class NominaDeInstructorDAO {

    private final ConexionBaseSingleton conexionBaseSingleton;

    public NominaDeInstructorDAO() {
        this.conexionBaseSingleton = ConexionBaseSingleton.getInstancia();
    }

    public ResultSet buscarNominaPorCedula(String cedula) {
        
        String consulta = "SELECT CEDULAINSTRUCTOR, SALARIO, FECHACONTRATACION FROM dbo.NOMINA_INSTRUCTOR WHERE CEDULAINSTRUCTOR = ?";

        try {
            PreparedStatement statement = conexionBaseSingleton.getConexion().prepareStatement(consulta);
            statement.setString(1, cedula);
            return statement.executeQuery(); // No cierres el ResultSet aquí
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

}
