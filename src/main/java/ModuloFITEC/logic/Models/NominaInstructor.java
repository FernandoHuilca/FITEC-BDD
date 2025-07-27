package ModuloFITEC.logic.Models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class NominaInstructor {

    private static final DateTimeFormatter FORMATO_FECHA = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private String cedulaInstructor;
    private double salario;
    private LocalDateTime  fechaContratacion;

    public NominaInstructor(String cedulaInstructor, double salario, LocalDateTime fechaContratacion) {
        this.cedulaInstructor = cedulaInstructor;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public NominaInstructor(String cedulaInstructor, double salario, String fechaContratacion) throws DateTimeParseException {
        this.cedulaInstructor = cedulaInstructor;
        this.salario = salario;
        this.fechaContratacion = LocalDateTime.parse(fechaContratacion, 
            DateTimeFormatter.ofPattern(FORMATO_FECHA.toString()));
    }

    public String getCedulaInstructor() {
        return cedulaInstructor;
    }

    public void setCedulaInstructor(String cedulaInstructor) {
        this.cedulaInstructor = cedulaInstructor;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDateTime getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDateTime fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getFechaContratacionString() {
        return fechaContratacion.format(FORMATO_FECHA);
    }

    /*
    public static LocalDateTime stringALocalDateTime(String fechaString) throws DateTimeParseException {
        return LocalDateTime.parse(fechaString, FORMATO_FECHA);
    }

    public static String localDateTimeAString(LocalDateTime fecha) {
        return fecha.format(FORMATO_FECHA);
    }
    */

}
