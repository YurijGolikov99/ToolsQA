package selenium_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import selenium.common_module.driver.hard_initialization.DriverProvider;
import selenium.common_module.property.PropertyHelper;

//9 создаем базовый тестовый класс, для порядка работы драйвера
public class BaseTest {

    protected final Logger logger = LogManager.getRootLogger();
    protected WebDriver driver; // Не используем static, чтобы каждый тест имел свой экземпляр
    private final String BASE_URL = PropertyHelper.getProperty("base.url");

    @BeforeSuite
    public void beforeSuit() {
        logger.info("Setting up suite configuration.");
        System.setProperty("log4j.configurationFile", PropertyHelper.getProperty("log.config.file"));
    }

    //DriverManager - будет открываться и закрываться каждый раз браузер
    @BeforeMethod
    public void beforeMethod() {
        driver = DriverProvider.getDriver(); // Инициализируем драйвер перед каждым тестом
        logger.info("Open url: " + BASE_URL);
        driver.get(BASE_URL); // Переходим на базовый URL
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Closing WebDriver after test method.");
            DriverProvider.closeDriver();
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("All tests completed. Resources cleaned up.");
    }
}
