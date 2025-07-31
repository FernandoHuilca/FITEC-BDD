package ModuloFITEC.logic.DAOs;

import ModuloFITEC.DataBase.ConexionBaseSingleton;
import ModuloFITEC.logic.Models.Cliente;

import java.sql.*;
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

    /*
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
    }*/

    /*
    public void registrarNuevoCliente(Cliente cliente) throws Exception {
        String sqlInsertCliente = "INSERT INTO CLIENTE (CEDULACLIENTE, IDSUSCRIPCION, IDSUCURSAL, NOMBRE, APELLLIDO, TELEFONO, EMAIL, FECHANACIMIENTO, FECHAREGISTRO, DIRECCION) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlInsertCedula = "INSERT INTO CEDULA_CLIENTE (CEDULACLIENTE) VALUES (?)";

        Connection conn = baseDeDatos.getConexion();
        PreparedStatement psCliente = null;
        PreparedStatement psCedula = null;

        try {
            conn.setAutoCommit(false); // Inicia transacción
            conn.prepareStatement("SET XACT_ABORT ON").execute(); // Aborta si hay error

            // Inserta en vista CLIENTE
            psCliente = conn.prepareStatement(sqlInsertCliente);
            psCliente.setString(1, cliente.getCedulaCliente());
            psCliente.setInt(2, cliente.getIdSuscripcion());
            psCliente.setString(3, cliente.getIdSucursal());
            psCliente.setString(4, cliente.getNombre());
            psCliente.setString(5, cliente.getApellido());
            psCliente.setString(6, cliente.getTelefono());
            psCliente.setString(7, cliente.getEmail());
            psCliente.setDate(8, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            psCliente.setDate(9, java.sql.Date.valueOf(cliente.getFechaRegistro()));
            psCliente.setString(10, cliente.getDireccion());
            psCliente.executeUpdate();

            // Inserta en tabla replicada CEDULA_CLIENTE
            psCedula = conn.prepareStatement(sqlInsertCedula);
            psCedula.setString(1, cliente.getCedulaCliente());
            psCedula.executeUpdate();

            conn.commit(); // Confirmar si todo sale bien
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback(); // Revertir si hay error
            }
            throw e;
        } finally {
            ConexionBaseSingleton.cerrarRecursos(null, psCedula);
            ConexionBaseSingleton.cerrarRecursos(null, psCliente);
            if (conn != null) {
                conn.setAutoCommit(true); // Volver al modo automático
            }
        }
    } */

    public void registrarNuevoCliente(Cliente cliente) throws Exception {
        Connection conexion = null;
        PreparedStatement psCliente = null;
        PreparedStatement psCedula = null;

        try {
            conexion = baseDeDatos.getConexion();
            conexion.setAutoCommit(false);  // Iniciar transacción

            // 1. Insertar en la vista CLIENTE (distribuída)
            String sqlCliente = "INSERT INTO CLIENTE (CEDULACLIENTE, IDSUSCRIPCION, IDSUCURSAL, NOMBRE, APELLLIDO, TELEFONO, EMAIL, FECHANACIMIENTO, FECHAREGISTRO, DIRECCION) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psCliente = conexion.prepareStatement(sqlCliente);
            psCliente.setString(1, cliente.getCedulaCliente());
            psCliente.setInt(2, cliente.getIdSuscripcion());
            psCliente.setString(3, cliente.getIdSucursal());
            psCliente.setString(4, cliente.getNombre());
            psCliente.setString(5, cliente.getApellido());
            psCliente.setString(6, cliente.getTelefono());
            psCliente.setString(7, cliente.getEmail());
            psCliente.setDate(8, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            psCliente.setDate(9, java.sql.Date.valueOf(cliente.getFechaRegistro()));
            psCliente.setString(10, cliente.getDireccion());
            psCliente.executeUpdate();

            // 2. Insertar en la tabla replicada CEDULA_CLIENTE
            String sqlCedula = "INSERT INTO CEDULA_CLIENTE (CEDULACLIENTE) VALUES (?)";
            psCedula = conexion.prepareStatement(sqlCedula);
            psCedula.setString(1, cliente.getCedulaCliente());
            psCedula.executeUpdate();

            conexion.commit();  // Confirmar ambas operaciones

        } catch (SQLException e) {
            // Revertir en caso de error
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException ex) {
                    throw new Exception("Error al revertir transacción: " + ex.getMessage(), ex);
                }
            }
            throw new Exception("Error registrando cliente: " + e.getMessage(), e);
        } finally {
            // Restaurar estado de la conexión y cerrar recursos
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Error restaurando autocommit: " + e.getMessage());
                }
            }
            // Cerrar statements individualmente
            cerrarStatement(psCliente);
            cerrarStatement(psCedula);
        }
    }

    // Método auxiliar para cerrar statements
    private void cerrarStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando statement: " + e.getMessage());
            }
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
        Connection conexion = null;
        PreparedStatement psCliente = null;
        PreparedStatement psCedula = null;

        try {
            conexion = baseDeDatos.getConexion();
            conexion.setAutoCommit(false);  // Iniciar transacción

            // 1. Eliminar de la vista CLIENTE (distribuída)
            String sqlCliente = "DELETE FROM CLIENTE WHERE CEDULACLIENTE = ?";
            psCliente = conexion.prepareStatement(sqlCliente);
            psCliente.setString(1, cedulaCliente);
            int filasAfectadas = psCliente.executeUpdate();

            // Verificar si existía el cliente
            if (filasAfectadas == 0) {
                throw new SQLException("Cliente no encontrado con cédula: " + cedulaCliente);
            }

            // 2. Eliminar de la tabla replicada CEDULA_CLIENTE
            String sqlCedula = "DELETE FROM CEDULA_CLIENTE WHERE CEDULACLIENTE = ?";
            psCedula = conexion.prepareStatement(sqlCedula);
            psCedula.setString(1, cedulaCliente);
            psCedula.executeUpdate();

            conexion.commit();  // Confirmar ambas operaciones

        } catch (SQLException e) {
            // Revertir en caso de error
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException ex) {
                    throw new Exception("Error al revertir transacción: " + ex.getMessage(), ex);
                }
            }
            throw new Exception("Error eliminando cliente: " + e.getMessage(), e);
        } finally {
            // Restaurar estado de la conexión y cerrar recursos
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Error restaurando autocommit: " + e.getMessage());
                }
            }
            // Cerrar statements individualmente
            if (psCliente != null) {
                try {
                    psCliente.close();
                } catch (SQLException e) {
                    System.err.println("Error cerrando statement cliente: " + e.getMessage());
                }
            }
            if (psCedula != null) {
                try {
                    psCedula.close();
                } catch (SQLException e) {
                    System.err.println("Error cerrando statement cédula: " + e.getMessage());
                }
            }
        }
    }
}
