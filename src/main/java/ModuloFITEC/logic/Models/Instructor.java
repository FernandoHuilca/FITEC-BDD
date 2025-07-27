package ModuloFITEC.logic.Models;

import java.time.LocalDate;

public class Instructor {
    private String cedulaInstructor;
    private String idSucursal;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private String direccion;
    private double salario;
    private LocalDate fechaContratacion;

    public Instructor(String cedulaInstructor, String idSucursal, String nombre, String apellido,
                      String telefono, String email, LocalDate fechaNacimiento, String direccion,
                      double salario, LocalDate fechaContratacion) {
        this.cedulaInstructor = cedulaInstructor;
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public String getCedulaInstructor() {
        return cedulaInstructor;
    }

    public void setCedulaInstructor(String cedulaInstructor) {
        this.cedulaInstructor = cedulaInstructor;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion){
         this.fechaContratacion = fechaContratacion;
    }
}