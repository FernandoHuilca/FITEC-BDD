package ModuloFITEC.logic.Models;

import java.time.LocalDateTime;

public class Compra {
    private int idCompra;
    private String cedulaCliente;
    private int idSuplemento;
    private String nombreSuplemento;
    private int cantidadComprada;
    private LocalDateTime fechaCompra;
    private double precioCompra;
    private String idSucursal;

    public Compra(int idCompra, String cedulaCliente, int idSuplemento, int cantidadComprada, LocalDateTime fechaCompra,
            double precioCompra, String idSucursal) {
        this.idCompra = idCompra;
        this.cedulaCliente = cedulaCliente;
        this.idSuplemento = idSuplemento;
        this.cantidadComprada = cantidadComprada;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.idSucursal = idSucursal;
    }

    public Compra(int idCompra, String cedulaCliente, String nombreSuplemento, int cantidadComprada,
            LocalDateTime fechaCompra, double precioCompra, String idSucursal) {
        this.idCompra = idCompra;
        this.cedulaCliente = cedulaCliente;
        this.nombreSuplemento = nombreSuplemento;
        this.cantidadComprada = cantidadComprada;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.idSucursal = idSucursal;
    }
    
    public int getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
    public String getCedulaCliente() {
        return cedulaCliente;
    }
    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }
    public String getNombreSuplemento() {
        return nombreSuplemento;
    }
    public void setNombreSuplemento(String nombreSuplemento) {
        this.nombreSuplemento = nombreSuplemento;
    }
    public int getCantidadComprada() {
        return cantidadComprada;
    }
    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }
    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public double getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
    public String getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdSuplemento() {
        return idSuplemento;
    }

    public void setIdSuplemento(int idSuplemento) {
        this.idSuplemento = idSuplemento;
    }
}
