package modelo;

// Hereda de Empleado
public class EmpleadoFijo extends Empleado {

    // ATRIBUTO: sueldoMensual se puede usar en toda esta clase
    private double sueldoMensual;
    private double bonificacion;

    // PARAMETROS: dpi, nombre, sueldoMensual y bonificacion (los declarados aqui).
    // ARGUMENTOS: los valores que se mandan al llamarlo, por ejemplo
    // new EmpleadoFijo("1234567890101", "Ana López", 4000, 300)
    public EmpleadoFijo(String dpi, String nombre, double sueldoMensual, double bonificacion) {
        super(dpi, nombre); // manda el dpi y nombre a la clase padre
        this.sueldoMensual = sueldoMensual; // this.sueldoMensual = atributo, sueldoMensual = parametro
        this.bonificacion = bonificacion;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    // pago = sueldo + bonificacion
    @Override
    public double calcularPago() {
        return sueldoMensual + bonificacion;
    }

    @Override
    public String obtenerTipo() {
        return "Fijo";
    }
}
