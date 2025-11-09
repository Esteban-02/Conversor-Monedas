import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    static Scanner lectura = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\tCasa de Cambio");

        String monedaBase = "";
        String monedaCambio = "";

        do{
            menu();
            int opcionMenu = lectura.nextInt();
            switch (opcionMenu){
                case 1:
                    monedaBase = "USD";
                    monedaCambio = "ARS";
                    break;
                case 2:
                    monedaBase = "ARS";
                    monedaCambio = "USD";
                    break;
                case 3:
                    monedaBase = "USD";
                    monedaCambio = "BRL";
                    break;
                case 4:
                    monedaBase = "BRL";
                    monedaCambio = "USD";
                    break;
                case 5:
                    monedaBase = "USD";
                    monedaCambio = "COP";
                    break;
                case 6:
                    monedaBase = "COP";
                    monedaCambio = "USD";
                    break;
                case 7:
                    System.out.println("Regresa pronto!!");
                    return;

                default:
                    System.out.println("Selecciona una opcion correcta");
                    break;
            }

            System.out.print("Ingresa el valor en "+ monedaBase + " para cambiar a "+ monedaCambio + " >> ");
            double valor = lectura.nextInt();

            try {
                ConsultaMoneda consultaMoneda = new ConsultaMoneda();
                CambioMoneda moneda = consultaMoneda.cambio(monedaBase, monedaCambio, valor);
                System.out.println("\nCambio de moneda: "+ String.valueOf(moneda).
                        substring(String.valueOf(moneda).indexOf("=")+1,
                                String.valueOf(moneda).indexOf(".")+3));
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public static void menu(){
        System.out.print("""
                            
                            MENU
                1. Dolar -> Peso Argentino
                2. Peso Argentino -> Dolar
                3. Dolar -> Real Brasileño
                4. Real Brasileño -> Dolar
                5. Dolar -> Peso Colombiano
                6. Peso Colombiano -> Dolar
                7. Salir
                >> """);

    }
}