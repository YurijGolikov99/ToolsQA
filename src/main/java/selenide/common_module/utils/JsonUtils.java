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

    //Читает JSON-файл по указанному пути и преобразует его в объект Map.
    @SneakyThrows
    public Map<String, Object> getMapFromFile(String filePath) {
        return objectMapper.readValue(new File(filePath), HashMap.class);
    }

    //Читает JSON-файл и преобразует его содержимое в строку (JSON-строку)
    @SneakyThrows
    public String getStringFromFile(String filePath) {
        return objectMapper.writeValueAsString(objectMapper.readValue(new File(filePath), HashMap.class));
    }

    //Преобразует карту (Map) в JSON-строку.
    @SneakyThrows
    public String getStringFromMap(Map<String, Object> json) {
        return objectMapper.writeValueAsString(json);
    }

    //Читает JSON-файл и преобразует его в объект типа JsonNode.
    @SneakyThrows
    public JsonNode getJsonNodeFromFile(String filePath) {
        return objectMapper.readTree(new File(filePath));
    }

    /**
     * Рекурсивный поиск поля на любом уровне вложенности в JSON.
     *   Рекурсивно - означает, что метод или функция вызывает сам себя для решения более мелкой части той же задачи.
     * @param node         текущий узел JSON
     * @param fieldName    имя искомого поля
     * @param expectedValue ожидаемое значение поля
     * @return true, если поле с заданным значением найдено
     */
    public boolean containsFieldWithValue(JsonNode node, String fieldName, String expectedValue) {
        //Проверяет, содержит ли текущий узел указанное поле и его значение.
        if (node.has(fieldName) && node.get(fieldName).asText().equals(expectedValue)) {
            return true;
        }
        //Если текущий узел является объектом (node.isObject()),
        // метод обходит все дочерние узлы (ключи-значения) и рекурсивно вызывает сам себя.
        if (node.isObject()) {
            for (JsonNode child : node) {
                if (containsFieldWithValue(child, fieldName, expectedValue)) {
                    return true;
                }
            }
            //Если текущий узел является массивом (node.isArray()),
            // метод обходит все элементы массива и также рекурсивно вызывает сам себя.
        } else if (node.isArray()) {
            for (JsonNode arrayElement : node) {
                if (containsFieldWithValue(arrayElement, fieldName, expectedValue)) {
                    return true;
                }
            }
        }
        return false;
    }

}
