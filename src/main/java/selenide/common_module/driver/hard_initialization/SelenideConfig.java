package selenide.common_module.driver.hard_initialization;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import selenide.common_module.Global;
import selenide.common_module.utils.TimeOuts;

public class SelenideConfig {

    protected static final Logger logger = LogManager.getRootLogger();

    public static void setup() {
        logger.info("Configuring Selenide");
        String browserType = Global.WEBDRIVER_TYPE;
        logger.info("Setting up the driver for browser: " + browserType);

        if (browserType.equalsIgnoreCase(Global.CHROME_NAME)) {
            Configuration.browser = "chrome";
        } else if (browserType.equalsIgnoreCase(Global.FIREFOX_NAME)) {
            Configuration.browser = "firefox";
        } else if (browserType.equalsIgnoreCase(Global.SAFARI_NAME)) {
            Configuration.browser = "safari";
        } else {
            Configuration.browser = "chrome"; // Default browser
        }

        Configuration.pageLoadTimeout = TimeOuts.PAGE_LOAD_TIMEOUT.getTimeOut() * 1000L;
        Configuration.timeout = TimeOuts.ELEMENT_TIMEOUT.getTimeOut() * 1000L;
        Configuration.browserSize = "1920x1080";
        //headless - без отрисовки интерфейса, по этому false
        Configuration.headless = false;

        logger.info("Selenide configured with browser: " + Configuration.browser);
    }

    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
