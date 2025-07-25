package ModuloFITEC.logic.DAOs;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.Cliente;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static ClienteDAO instancia;
    private final ConexionBaseSingleton baseDeDatos;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


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

    }
}
