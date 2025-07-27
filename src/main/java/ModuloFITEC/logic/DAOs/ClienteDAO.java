package ModuloFITEC.logic.DAOs;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.Cliente;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static ClienteDAO instancia;
    private final ConexionBaseSingleton baseDeDatos;


    private ClienteDAO() {
        this.baseDeDatos = ConexionBaseSingleton.getInstancia();
    }

    public static ClienteDAO getInstancia() {
        if (instancia == null) {
            instancia = new ClienteDAO();
        }
        return instancia;
    }

    public List<Cliente> getListaClientesDB() throws Exception {
        String sql = "SELECT * FROM CLIENTE ORDER BY CEDULACLIENTE";
        ResultSet rs = null;
        Statement st = null;
        try {
            rs = baseDeDatos.ejecutarConsulta(sql);
            return mapearLista(rs);
        } finally {
            ConexionBaseSingleton.cerrarRecursos(rs, st);
        }
    }


    private List<Cliente> mapearLista(ResultSet rs) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente cliente = new Cliente(
                    rs.getString("CEDULACLIENTE"),
                    rs.getInt("IDSUSCRIPCION"),
                    rs.getString("IDSUCURSAL"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLLIDO"),
                    rs.getString("TELEFONO"),
                    rs.getString("EMAIL"),
                    rs.getDate("FECHANACIMIENTO").toLocalDate(),
                    rs.getDate("FECHAREGISTRO").toLocalDate(),
                    rs.getString("DIRECCION")
            );
            clientes.add(cliente);
        }
        return clientes;
    }

    public List<Cliente> getClientesPorNombre(String nombre) throws Exception {
        String sql = "SELECT * FROM CLIENTE WHERE NOMBRE LIKE ? ORDER BY CEDULACLIENTE";
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

    public List<Cliente> getClientesPorCedula(String cedula) throws Exception {
        String sql = "SELECT * FROM CLIENTE WHERE CEDULACLIENTE LIKE ? ORDER BY CEDULACLIENTE";
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


    public void registrarNuevoCliente(Cliente cliente) throws Exception {
        String sql = "SET XACT_ABORT ON; " +
                "INSERT INTO CLIENTE (CEDULACLIENTE, IDSUSCRIPCION, IDSUCURSAL, NOMBRE, APELLLIDO, TELEFONO, EMAIL, FECHANACIMIENTO, FECHAREGISTRO, DIRECCION) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.PreparedStatement ps = null;
        try {
            ps = baseDeDatos.getConexion().prepareStatement(sql);
            ps.setString(1, cliente.getCedulaCliente());
            ps.setInt(2, cliente.getIdSuscripcion());
            ps.setString(3, cliente.getIdSucursal());
            ps.setString(4, cliente.getNombre());
            ps.setString(5, cliente.getApellido());
            ps.setString(6, cliente.getTelefono());
            ps.setString(7, cliente.getEmail());
            ps.setDate(8, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            ps.setDate(9, java.sql.Date.valueOf(cliente.getFechaRegistro()));
            ps.setString(10, cliente.getDireccion());
            ps.executeUpdate();
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, ps);
        }
    }


    public void actualizarCliente(Cliente cliente) throws Exception {
        String sql = "UPDATE CLIENTE SET IDSUSCRIPCION = ?, IDSUCURSAL = ?, NOMBRE = ?, APELLLIDO = ?, TELEFONO = ?, EMAIL = ?, FECHANACIMIENTO = ?, DIRECCION = ? WHERE CEDULACLIENTE = ?";
        java.sql.PreparedStatement ps = null;
        try {
            ps = baseDeDatos.getConexion().prepareStatement(sql);
            ps.setInt(1, cliente.getIdSuscripcion());
            ps.setString(2, cliente.getIdSucursal());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getEmail());
            ps.setDate(7, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            ps.setString(8, cliente.getDireccion());
            ps.setString(9, cliente.getCedulaCliente());
            ps.executeUpdate();
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, ps);
        }
    }

    public void eliminarCliente(String cedulaCliente) throws Exception {
        String sql = "DELETE FROM CLIENTE WHERE CEDULACLIENTE = ?";
        java.sql.PreparedStatement ps = null;
        try {
            ps = baseDeDatos.getConexion().prepareStatement(sql);
            ps.setString(1, cedulaCliente);
            ps.executeUpdate();
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, ps);
        }
    }
}
