package selenide.common_module.driver.hard_initialization;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import selenide.common_module.Global;
import selenide.common_module.utils.TimeOuts;

public class SelenideConfig {
    public static void setup() {
        String browserType = Global.WEBDRIVER_TYPE;

        if (browserType.equalsIgnoreCase(Global.CHROME_NAME)) {
            Configuration.browser = "chrome";
        } else if (browserType.equalsIgnoreCase(Global.FIREFOX_NAME)) {
            Configuration.browser = "firefox";
        } else if (browserType.equalsIgnoreCase(Global.SAFARI_NAME)) {
            Configuration.browser = "safari";
        } else {
            Configuration.browser = "chrome"; // Default browser
        }

        Configuration.pageLoadTimeout = TimeOuts.PAGE_LOAD_TIMEOUT * 1000L;
        Configuration.timeout = TimeOuts.ELEMENT_TIMEOUT * 1000L;
        Configuration.browserSize = "1920x1080";
        //headless - без отрисовки интерфейса, по этому false
        Configuration.headless = false;
    }

    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
