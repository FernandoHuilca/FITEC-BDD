package ModuloFITEC.logic.Models;
import java.net.FileNameMap;

public class Suscripcion {
    private final String nombre = "Suscripción";

    private int idSuscripcion;
    private String tipo;
    private String descripcion;
    private double precio;
    private int duracionMeses;

    public Suscripcion(int idSuscripcion, String tipo, String descripcion, double precio, int duracionMeses) {
        this.idSuscripcion = idSuscripcion;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionMeses = duracionMeses;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public String getNombre() {
        return nombre;
    }
}
