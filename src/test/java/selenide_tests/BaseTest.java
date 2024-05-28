package selenide_tests;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import selenide.common_module.Specifications;
import selenide.common_module.constants.PropertyPath;
import selenide.common_module.property.PropertyHelper;

import java.util.List;

import static selenide.common_module.property.PropertyHelper.getProperty;

// от этого класса должны наследоваться другие тестовые классы
public class BaseTest {

    protected final Logger logger = LogManager.getRootLogger();

    private final String BASE_URL = PropertyHelper.getProperty("base.url");

    private final Specifications specifications = new Specifications();

    //для параметризованных тестов
    protected Response sendGetRequest(String paramName, String booksName) {
        try {
            return specifications.httpGetRequestWithQueryParameters(getProperty(PropertyPath.BASE_URL.getPath()), paramName, List.of(booksName));
        } catch (Exception e) {
            logger.error("Failed to send GET request: {}", e.getMessage());
            throw new RuntimeException("Failed to send GET request", e);
        }
    }
}
