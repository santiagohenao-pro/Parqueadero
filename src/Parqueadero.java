import java.util.Scanner;
import java.time.LocalDateTime;

public class Parqueadero {

    static Scanner sc = new Scanner(System.in);

    static final int CAPACIDAD_TOTAL = 10;

    static final double TARIFA_HORA_CARRO = 4000;
    static final double TARIFA_DIA_CARRO = 30000;

    static final double TARIFA_HORA_MOTO = 2000;
    static final double TARIFA_DIA_MOTO = 15000;

    static final int UMBRAL_HORAS_DIA = 8;

    static final int HORA_APERTURA = 8;
    static final int HORA_CIERRE = 22;

    static String[] tipoVehiculo =
            new String[CAPACIDAD_TOTAL];

    static LocalDateTime[] fechaIngreso =
            new LocalDateTime[CAPACIDAD_TOTAL];

    static double recaudoCarros = 0;
    static double recaudoMotos = 0;

    static int ocupadas = 0;
    static int libres = CAPACIDAD_TOTAL;

    public static void main(String[] args) {

        System.out.println("Sistema de Parqueadero");

    }

    static int celdaDisponible() {

        for(int i = 0; i < CAPACIDAD_TOTAL; i++) {

            if(tipoVehiculo[i] == null) {

                return i;

            }

        }

        return -1;
    }
}