package aplicacion;

// Librerías y clases que voy a utilizar en el programa
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Empleado;
import modelo.EmpleadoFijo;
import modelo.EmpleadoPorHora;

public class Main {

    // Lista de los meses que se pueden seleccionar
    private static String[] meses = {
        "Enero", "Febrero", "Marzo", "Abril",
        "Mayo", "Junio", "Julio", "Agosto",
        "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    // Aquí voy guardando los empleados que registro
    private static ArrayList<Empleado> empleados = new ArrayList<>();

    // Sirve para leer los datos que ingreso por teclado
    private static Scanner teclado = new Scanner(System.in);

    // Guarda el número del mes seleccionado
    private static int numeroMes;

    // Método principal, desde aquí comienza el programa
    public static void main(String[] args) {
        seleccionarMes();

        int opcion = 0;

        while (opcion != 3) {
            limpiarPantalla();
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ", 1, 3);

            switch (opcion) {
                case 1:
                    ingresoNomina();
                    break;
                case 2:
                    imprimirNomina();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, adiós!");
                    break;
            }
        }
    }

    // Permite elegir el mes en el que se trabajará
    public static void seleccionarMes() {
        limpiarPantalla();
        System.out.println("CÁLCULO DE NÓMINA");

        // Solo permite ingresar un número del 1 al 12
        numeroMes = leerEntero("Ingrese el mes a trabajar (1-12): ", 1, 12);
    }

    // Muestra el nombre del mes seleccionado
    public static void mostrarTitulo() {

        // Se resta uno porque las posiciones del arreglo empiezan en cero
        System.out.println("NÓMINA DEL MES: " + meses[numeroMes - 1].toUpperCase());
    }

    // Presenta las opciones principales del programa
    public static void mostrarMenuPrincipal() {
        mostrarTitulo();
        System.out.println("1 - Ingreso de nómina");
        System.out.println("2 - Impresión de nómina");
        System.out.println("3 - Salir");
    }

    // Muestra las opciones para registrar un tipo de empleado
    public static void mostrarMenuTipoEmpleado() {
        mostrarTitulo();
        System.out.println("INGRESO DE EMPLEADO A NÓMINA");

        // Muestra cuántos empleados se han registrado
        System.out.println("Trabajadores actuales = " + empleados.size());

        System.out.println("1 - Empleado fijo");
        System.out.println("2 - Empleado por hora");
    }

    // Permite registrar uno o varios empleados
    public static void ingresoNomina() {
        int otro = 1;

        while (otro == 1) {
            limpiarPantalla();
            mostrarMenuTipoEmpleado();
            int tipo = leerEntero("Seleccione el tipo de empleado: ", 1, 2);

            limpiarPantalla();
            mostrarTitulo();

            Empleado emp;

            // Según la opción, se solicitan los datos del empleado correspondiente
            if (tipo == 1) {
                emp = capturarEmpleadoFijo();
            } else {
                emp = capturarEmpleadoPorHora();
            }

            System.out.printf("Pago de %s = Q %.2f\n",
                    emp.getNombre(), emp.calcularPago());

            // Agrega el empleado después de ingresar sus datos
            agregarEmpleado(emp);

            System.out.println();
            System.out.println("Empleado agregado correctamente.");
            System.out.println("Trabajadores actuales = " + empleados.size());
            System.out.println("¿Desea ingresar otro empleado?");
            System.out.println("1 - Sí, ingresar otro empleado");
            System.out.println("2 - Volver al menú principal");

            otro = leerEntero("Seleccione una opción: ", 1, 2);
        }
    }

    // Solicita los datos necesarios para un empleado fijo
    public static Empleado capturarEmpleadoFijo() {
        System.out.println("INGRESO DE EMPLEADO FIJO");

        String dpi = leerDpi();
        String nombre = leerNombre();
        double sueldo = leerNumero("Sueldo mensual (Q): ");
        double bono = leerNumero("Bonificación de este mes (Q): ");

        System.out.println("Pago del mes = sueldo mensual + bonificación");

        // Crea el empleado con los datos que se ingresaron
        return new EmpleadoFijo(dpi, nombre, sueldo, bono);
    }

    // Solicita los datos de una persona que trabaja por hora
    public static Empleado capturarEmpleadoPorHora() {
        System.out.println("INGRESO DE EMPLEADO POR HORA");

        String dpi = leerDpi();
        String nombre = leerNombre();
        double pagoHora = leerNumero("Pago por hora (Q): ");
        double horas = leerNumero("Horas trabajadas este mes: ");

        System.out.println("Pago del mes = pago por hora x horas trabajadas");

        // Crea el empleado usando el pago por hora y las horas trabajadas
        return new EmpleadoPorHora(dpi, nombre, pagoHora, horas);
    }

    // Guarda el empleado en la lista
    public static void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    // Muestra los empleados registrados y el total que se debe pagar
    public static void imprimirNomina() {
        limpiarPantalla();
        mostrarTitulo();

        // Acumula el pago de todos los empleados
        double total = 0;

        if (empleados.size() == 0) {
            System.out.println("No hay empleados registrados en la nómina de este mes");
        } else {
            System.out.printf("%-15s %-25s %-10s %10s\n",
                    "DPI", "Nombre", "Tipo", "Pago (Q)");

            // Recorre la lista para mostrar los datos de cada empleado
            for (Empleado e : empleados) {
                System.out.printf("%-15s %-25s %-10s %10.2f\n",
                        e.getDpi(), e.getNombre(),
                        e.obtenerTipo(), e.calcularPago());

                // Suma el pago de cada empleado al total
                total = total + e.calcularPago();
            }
        }

        System.out.println();
        System.out.println("Total de trabajadores: " + empleados.size());
        System.out.printf("Total de nómina: Q %.2f\n", total);
        System.out.println("Presione Enter para volver al menú principal...");

        teclado.nextLine();
    }

    // Lee una opción numérica y comprueba que esté dentro del rango permitido
    public static int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String dato = teclado.nextLine();

            try {
                int num = Integer.parseInt(dato.trim());

                if (num >= min && num <= max) {
                    return num;
                }

                System.out.println("Opción inválida, debe ser un número entre "
                        + min + " y " + max);

            } catch (NumberFormatException ex) {
                System.out.println("Error: debe ingresar un número entero");
            }
        }
    }

    // Comprueba que el dato ingresado sea un número válido y no negativo
    public static double leerNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String dato = teclado.nextLine();

            try {
                double num = Double.parseDouble(dato.trim());

                if (Double.isNaN(num) || Double.isInfinite(num)) {
                    System.out.println("Error: ingrese un número válido");
                } else if (num < 0) {
                    System.out.println("Error: el valor no puede ser negativo");
                } else {
                    return num;
                }

            } catch (NumberFormatException ex) {
                System.out.println("Error: debe ingresar un valor numérico");
            }
        }
    }

    // Solicita el DPI y verifica que tenga exactamente 13 números
    public static String leerDpi() {
        while (true) {
            System.out.print("DPI: ");
            String dpi = teclado.nextLine().trim();
            boolean valido = true;

            if (dpi.length() != 13) {
                valido = false;
            } else {
                // Revisa cada carácter para comprobar que sea un dígito
                for (int i = 0; i < dpi.length(); i++) {
                    if (!Character.isDigit(dpi.charAt(i))) {
                        valido = false;
                    }
                }
            }

            if (valido) {
                return dpi;
            }

            System.out.println("Error: el DPI debe tener exactamente 13 dígitos");
        }
    }

    // Pide el nombre y no permite dejar el espacio vacío
    public static String leerNombre() {
        while (true) {
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine().trim();

            if (!nombre.isEmpty()) {
                return nombre;
            }

            System.out.println("Error: el nombre no puede estar vacío");
        }
    }

    // Deja varias líneas en blanco para separar las pantallas del menú
    public static void limpiarPantalla() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}

