package selenide_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import selenide.common_module.driver.hard_initialization.DriverProvider;

// от этого класса должны наследоваться другие тестовые классы
public class BaseTest {

    protected static final Logger logger = LogManager.getRootLogger();

    private static final DriverProvider driverProvider = new DriverProvider();

    @BeforeAll
    public static void init(){
        logger.info("Setting up Selenide driver");
        driverProvider.setupDriver();
        logger.info("Selenide driver setup complete");
    }

    @AfterAll
    public static void tearDown(){
        logger.info("Closing Selenide driver");
        driverProvider.closeDriver();
        logger.info("Selenide driver closed");
    }
}
