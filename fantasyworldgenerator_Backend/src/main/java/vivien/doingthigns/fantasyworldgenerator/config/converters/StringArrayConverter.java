package vivien.doingthigns.fantasyworldgenerator.config.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;  


@Converter
public class StringArrayConverter implements AttributeConverter<String[], String> {

    private static  
 final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException  
 e) {
            // Handle conversion exception (e.g., log or throw)
            throw new RuntimeException("Error converting String array to JSON", e);
        }
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, String[].class);
        } catch (JsonProcessingException  
 e) {
            // Handle conversion exception (e.g., log or throw)
            throw new RuntimeException("Error converting JSON to String array", e);
        }
    }
}
