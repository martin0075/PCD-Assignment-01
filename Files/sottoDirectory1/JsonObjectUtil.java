package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    //Serializza
    public static String ObjectToJson(Object obj) {
        String json = null;

        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println("Errore serializzazione: " + e.getMessage());
        }
        return json;
    }

    //deserializza
    public static <T> T JsonToObject(Class<T> type, String json) {
        try {
            return (T) objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            System.out.println("Errore deserializzazione: " + e.getMessage());
        }
        return null;
    }
}
