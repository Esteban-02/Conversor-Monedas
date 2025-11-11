import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    static Scanner lectura = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\tCasa de Cambio");
        //Decalracion de la moneda base a la moneda de cambio
        String monedaBase = "";
        String monedaCambio = "";

        do{
            menu();
            int opcionMenu = lectura.nextInt();
            // Opciones para el cambio de monedas segun la moneda base con la moneda a la que se va a cambiar
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
                    // Con return sale del bucle para terminar la ejecucion del codigo
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

                //Con este codigo se toma solamente el resultado para mostrarlo en consola, con subString e indexOf se ubica el = y el . como referencia para
                //obtener solo el resultado
                System.out.println("\nCambio de moneda: "+ String.valueOf(moneda).
                        substring(String.valueOf(moneda).indexOf("=")+1,
                                String.valueOf(moneda).indexOf(".")+3) + monedaCambio);

                GeneradorJson generador = new GeneradorJson();
                generador.guardarConversion(moneda, monedaBase, monedaCambio);
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }while (true); //Repite infinitas veces el menu hasta seleccionar el numero 7 que sale del bucle
    }

    /**
     * Este metodo contiene el menu que se mostrara para el cambio de monedas
     */
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