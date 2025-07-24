package ModuloFITEC.logic.Models;

import java.time.LocalDate;

public class Suplemento {
    private int idSuplemento;
    private String idSucursal;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidadDisponible;
    private LocalDate fechaVencimiento;

    public Suplemento(int idSuplemento, String idSucursal, String nombre, String categoria, double precio,
            int cantidadDisponible, LocalDate fechaVencimiento) {
        this.idSuplemento = idSuplemento;
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaVencimiento = fechaVencimiento;
    }
    
    public int getIdSuplemento() {
        return idSuplemento;
    }
    public void setIdSuplemento(int idSuplemento) {
        this.idSuplemento = idSuplemento;
    }
    public String getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


    
}
