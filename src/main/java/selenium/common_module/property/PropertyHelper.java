package selenium.common_module.property;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//4 Создали класс для чтения значений из файла свойств в формате key-value.
// В данном случае файл свойств называется "project_data.properties"
public class PropertyHelper {

    private static final String PROPERTIES_PATH = "src/main/resources/project_data.properties";

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        File propertyFile = null;
        try {
            propertyFile = new File(PROPERTIES_PATH);
            properties.load(new FileReader(propertyFile));
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read property file: " + propertyFile.getAbsolutePath());
        }
    }
}
