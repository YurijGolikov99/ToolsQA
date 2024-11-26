package selenide.common_module.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

//Методы, связанные с чтением и записью JSON-файлов и объектов,
public class JsonUtils {
    private ObjectMapper objectMapper;

    @SneakyThrows
    public Map<String, Object> getMapFromFile(String filePath) {
        return objectMapper.readValue(new File(filePath), HashMap.class);
    }

    @SneakyThrows
    public String getStringFromFile(String filePath) {
        return objectMapper.writeValueAsString(objectMapper.readValue(new File(filePath), HashMap.class));
    }

    @SneakyThrows
    public String getStringFromMap(Map<String, Object> json) {
        return objectMapper.writeValueAsString(json);
    }

    @SneakyThrows
    public JsonNode getJsonNodeFromFile(String filePath) {
        return objectMapper.readTree(new File(filePath));
    }
}
