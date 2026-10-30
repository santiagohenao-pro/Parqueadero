import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Parqueadero {

    static Scanner sc = new Scanner(System.in);

    static final int CAPACIDAD_TOTAL = 10;

    static final double TARIFA_HORA_CARRO = 4000;
    static final double TARIFA_HORA_MOTO = 2000;

    static String[] tipoVehiculo = new String[CAPACIDAD_TOTAL];
    static LocalDateTime[] fechaIngreso = new LocalDateTime[CAPACIDAD_TOTAL];

    static double recaudoCarros = 0;
    static double recaudoMotos = 0;

    static DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {

        int opcion;

        do {

            System.out.println("\nPARQUEADERO");
            System.out.println("1. Entrada");
            System.out.println("2. Salida");
            System.out.println("3. Ocupacion");
            System.out.println("4. Financiero");
            System.out.println("5. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    entrada();
                    break;

                case 2:
                    salida();
                    break;

                case 3:
                    ocupacion();
                    break;

                case 4:
                    financiero();
                    break;

            }

        } while (opcion != 5);

        sc.close();
    }

    static void entrada() {

        int posicion = -1;

        for (int i = 0; i < CAPACIDAD_TOTAL; i++) {

            if (tipoVehiculo[i] == null) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {

            System.out.println("Parqueadero lleno");
            return;
        }

        System.out.print("Tipo (CARRO/MOTO): ");
        String tipo = sc.nextLine().toUpperCase();

        System.out.print("Fecha ingreso (yyyy-MM-dd HH:mm): ");
        String fecha = sc.nextLine();

        tipoVehiculo[posicion] = tipo;
        fechaIngreso[posicion] =
                LocalDateTime.parse(fecha, formato);

        System.out.println(
                "Asignado a la celda "
                        + (posicion + 1));
    }

    static void salida() {

        System.out.print("Numero de celda: ");
        int celda = sc.nextInt();
        sc.nextLine();

        int posicion = celda - 1;

        if (tipoVehiculo[posicion] == null) {

            System.out.println("Celda vacia");
            return;
        }

        System.out.print("Fecha salida (yyyy-MM-dd HH:mm): ");
        String fecha = sc.nextLine();

        LocalDateTime salida =
                LocalDateTime.parse(fecha, formato);

        LocalDateTime ingreso =
                fechaIngreso[posicion];

        long minutos =
                Duration.between(ingreso, salida)
                        .toMinutes();

        long horas =
                (minutos + 59) / 60;

        double total;

        if (tipoVehiculo[posicion].equals("CARRO")) {

            total = horas * TARIFA_HORA_CARRO;
            recaudoCarros += total;

        } else {

            total = horas * TARIFA_HORA_MOTO;
            recaudoMotos += total;
        }

        System.out.println("Total a pagar: $" + total);

        tipoVehiculo[posicion] = null;
        fechaIngreso[posicion] = null;
    }

    static void ocupacion() {

        int ocupadas = 0;

        for (int i = 0; i < CAPACIDAD_TOTAL; i++) {

            if (tipoVehiculo[i] != null) {
                ocupadas++;
            }
        }

        System.out.println("Ocupadas: " + ocupadas);
        System.out.println("Libres: " +
                (CAPACIDAD_TOTAL - ocupadas));
    }

    static void financiero() {

        System.out.println(
                "Carros: $" + recaudoCarros);

        System.out.println(
                "Motos: $" + recaudoMotos);

        System.out.println(
                "Total: $"
                        + (recaudoCarros + recaudoMotos));
    }
}