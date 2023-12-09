package selenide.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
