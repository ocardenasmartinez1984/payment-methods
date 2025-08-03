package cl.billy;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJsonFromString {

    public Invoice read(String jsonString) {
        Invoice invoice = null;
        try {
            var objectMapper = new ObjectMapper();
            invoice = objectMapper.readValue(jsonString, Invoice.class);
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
        return invoice;
    }

}
