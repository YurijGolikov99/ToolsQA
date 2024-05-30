package selenide_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import selenide.common_module.driver.HardInicialisation.DriverProvider;
import selenide.common_module.property.PropertyHelper;

public class BaseTest {

    protected final Logger logger = LogManager.getRootLogger();

    private final String START_URL = PropertyHelper.getProperty("start.url");

    @BeforeSuite
    public void beforeSuit() {
        System.setProperty("log4j.configurationFile", PropertyHelper.getProperty("log.config.file"));
    }

    @BeforeClass
    public void beforeClass() {
        WebDriver driver = DriverProvider.getDriver();
        logger.info("Open url: " + START_URL);
        driver.get(START_URL);
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("Tear down driver");
        DriverProvider.tearDown();
    }
}
