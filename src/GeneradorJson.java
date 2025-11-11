import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneradorJson {

    public void guardarConversion(CambioMoneda cambioMoneda, String monedaBase, String monedaCambio) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File archivo = new File("ConversionMonedas.json");

            // Lista para almacenar todas las conversiones de las monedas
            List<Map<String, Object>> historialConversiones = new ArrayList<>();

            // Leer conversiones existentes si el archivo ya existe
            if (archivo.exists() && archivo.length() > 0) {
                try (FileReader reader = new FileReader(archivo)) {
                    Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
                    List<Map<String, Object>> conversionesExistentes = gson.fromJson(reader, listType);

                    if (conversionesExistentes != null) {
                        historialConversiones = conversionesExistentes;
                    }
                } catch (Exception e) {
                    System.out.println("Error al leer archivo existente, se creará uno nuevo: " + e.getMessage());
                }
            }

            // Crear nuevo registro de conversión
            Map<String, Object> nuevaConversion = new HashMap<>();
            nuevaConversion.put("Moneda Base", monedaBase);
            nuevaConversion.put("Moneda Cambio", monedaCambio);
            nuevaConversion.put("Resultado Conversion", cambioMoneda.conversion_result());
            nuevaConversion.put("Fecha", LocalDateTime.now().toString());

            // Agregar la nueva conversión al historial de conversiones
            historialConversiones.add(nuevaConversion);

            // Guarda las conversiones realizadas en un archivo JSON
            try (FileWriter escritura = new FileWriter(archivo)) {
                gson.toJson(historialConversiones, escritura);
            }

        } catch (IOException e) {
            System.out.println("Error al guardar en el JSON: " + e.getMessage());
        }
    }
}