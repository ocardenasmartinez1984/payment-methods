package cl.billy.utils;

import cl.billy.entities.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJsonFromStringUtil {

    public static Invoice read(String jsonString) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Invoice.class);
    }

}
