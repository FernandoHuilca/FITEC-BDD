package ModuloFITEC.logic.DAOs;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.Instructor;

    public class InstructorDAO {

        private static InstructorDAO instancia;
        private final ConexionBaseSingleton baseDeDatos;


        private InstructorDAO() {
            this.baseDeDatos = ConexionBaseSingleton.getInstancia();
        }

        public static InstructorDAO getInstancia() {
            if (instancia == null) {
                instancia = new InstructorDAO();
            }
            return instancia;
        }



        public boolean existeInstructorPorCedula(String cedula) throws Exception {
        String sql = "SELECT COUNT(*) FROM INSTRUCTOR WHERE CEDULAINSTRUCTOR = ?";
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = baseDeDatos.getConexion().prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, ps);
        }
    }

    public void registrarNuevoInstructor(Instructor instructor) throws Exception {
        String sql = "INSERT INTO INSTRUCTOR (CEDULAINSTRUCTOR, IDSUCURSAL, NOMBRE, APELLIDO, TELEFONO, EMAIL, FECHANACIMIENTO, DIRECCION) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.PreparedStatement ps = null;
        try {
            ps = baseDeDatos.getConexion().prepareStatement(sql);
            ps.setString(1, instructor.getCedulaInstructor());
            ps.setString(2, instructor.getIdSucursal());
            ps.setString(3, instructor.getNombre());
            ps.setString(4, instructor.getApellido());
            ps.setString(5, instructor.getTelefono());
            ps.setString(6, instructor.getEmail());
            ps.setDate(7, java.sql.Date.valueOf(instructor.getFechaNacimiento()));
            ps.setString(8, instructor.getDireccion());
            ps.executeUpdate();
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, ps);
        }
    }


    public List<Instructor> getListaInstructoresDB() throws Exception {
        String sql = "SELECT * FROM INSTRUCTOR ORDER BY CEDULAINSTRUCTOR";
        ResultSet rs = null;
        Statement st = null;
        try {
            rs = baseDeDatos.ejecutarConsulta(sql);
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }

    public List<Instructor> getInstructoresPorNombre(String nombre) throws Exception {
        String sql = "SELECT * FROM INSTRUCTOR WHERE NOMBRE LIKE ? ORDER BY NOMBRE";
        ResultSet rs = null;
        java.sql.PreparedStatement ps = null;
        try {
            ps = baseDeDatos.getConexion().prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, ps);
        }
    }

    public List<Instructor> getInstructoresPorCedula(String cedula) throws Exception {
        String sql = "SELECT * FROM INSTRUCTOR WHERE CEDULAINSTRUCTOR LIKE ? ORDER BY CEDULAINSTRUCTOR";
        ResultSet rs = null;
        java.sql.PreparedStatement ps = null;
        try {
            ps = baseDeDatos.getConexion().prepareStatement(sql);
            ps.setString(1, "%" + cedula + "%");
            rs = ps.executeQuery();
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, ps);
        }
    }
    private List<Instructor> mapearLista(ResultSet rs) throws Exception {
    List<Instructor> instructores = new ArrayList<>();
        while (rs.next()) {
            Instructor instructor = new Instructor(
                rs.getString("CEDULAINSTRUCTOR"),
                rs.getString("IDSUCURSAL"),
                rs.getString("NOMBRE"),
                rs.getString("APELLIDO"),
                rs.getString("TELEFONO"),
                rs.getString("EMAIL"),
                rs.getDate("FECHANACIMIENTO").toLocalDate(),
                rs.getString("DIRECCION"),
                0.0, // Salario (no está en la tabla INSTRUCTOR)
                null // Fecha de contratación (no está en la tabla INSTRUCTOR)
            );
            instructores.add(instructor);
        }
        return instructores;
    }

    public void actualizarInstructor(Instructor instructor) throws Exception {
        String sql = "UPDATE INSTRUCTOR SET IDSUCURSAL = ?, NOMBRE = ?, APELLIDO = ?, TELEFONO = ?, EMAIL = ?, FECHANACIMIENTO = ?, DIRECCION = ? WHERE CEDULAINSTRUCTOR = ?";
        java.sql.PreparedStatement ps = null;
        try {
            ps = baseDeDatos.getConexion().prepareStatement(sql);
            ps.setString(1, instructor.getIdSucursal());
            ps.setString(2, instructor.getNombre());
            ps.setString(3, instructor.getApellido());
            ps.setString(4, instructor.getTelefono());
            ps.setString(5, instructor.getEmail());
            ps.setDate(6, java.sql.Date.valueOf(instructor.getFechaNacimiento()));
            ps.setString(7, instructor.getDireccion());
            ps.setString(8, instructor.getCedulaInstructor());
            ps.executeUpdate();
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, ps);
        }
    }


}
