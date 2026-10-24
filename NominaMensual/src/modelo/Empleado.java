package modelo;

// Clase abstracta, no se pueden crear objetos de esta clase directamente
public abstract class Empleado {

    // atributos comunes de todos los empleados
    private String dpi;
    private String nombre;

    // constructor con parametros
    public Empleado(String dpi, String nombre) {
        this.dpi = dpi;
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public String getNombre() {
        return nombre;
    }

    // metodo abstracto, cada clase hija hace su propio calculo
    public abstract double calcularPago();

    // para mostrar el tipo en el reporte
    public abstract String obtenerTipo();
}
