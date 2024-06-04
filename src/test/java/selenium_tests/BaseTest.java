package selenium_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import selenium.common_module.driver.hard_initialization.DriverProvider;
import selenium.common_module.property.PropertyHelper;

//9 создаем базовый тестовый класс, для порядка работы драйвера
public class BaseTest {

    protected final Logger logger = LogManager.getRootLogger();
    protected WebDriver driver;
    private final String BASE_URL = PropertyHelper.getProperty("base.url");

    @BeforeSuite
    public void beforeSuit() {
        System.setProperty("log4j.configurationFile", PropertyHelper.getProperty("log.config.file"));
    }

    @BeforeClass
    public void beforeClass() {
        driver = DriverProvider.getDriver();
        logger.info("Open url: " + BASE_URL);
        driver.get(BASE_URL);
    }

    @AfterClass
    public void afterClass() {
        logger.info("Tear down driver");
        DriverProvider.closeDriver();
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("Tear down driver");
        DriverProvider.closeDriver();
    }
}
