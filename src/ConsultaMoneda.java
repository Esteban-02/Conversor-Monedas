import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    public CambioMoneda cambio(String monedaBase, String monedaCambio, double monto){

        //Crea la direccion con la clave de acceso a la API para hacer la solicitud
        String direccion = "https://v6.exchangerate-api.com/v6/6fbb9a4377567026d2c1aa70/pair/"+monedaBase+"/"+monedaCambio+"/"+monto+"/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();

        //Guarda la peticion y la retorna un JSON
        try {
            HttpResponse <String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), CambioMoneda.class);
        }catch (Exception e){
            throw new RuntimeException("No se logro realizar la conversion de la moneda");
        }

    }
}
