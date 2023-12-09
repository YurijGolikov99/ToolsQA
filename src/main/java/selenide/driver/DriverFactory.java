package selenide.driver;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static selenide.data.Global.CHROME_NAME;
import static selenide.data.Global.FIREFOX_NAME;
import static selenide.data.Global.SAFARI_NAME;
import static selenide.data.Global.WEBDRIVER_TYPE;
import static selenide.utils.TimeOuts.ELEMENT_TIMEOUT;
import static selenide.utils.TimeOuts.PAGE_LOAD_TIMEOUT;

//4
public class DriverFactory {

    static WebDriver launchDriver() {
        WebDriver driver;
        if (WEBDRIVER_TYPE.equalsIgnoreCase(CHROME_NAME)) {
            driver = ChromeLauncher.createDriver();
        } else if (WEBDRIVER_TYPE.equalsIgnoreCase(FIREFOX_NAME)) {
            driver = FirefoxLauncher.createDriver();
        } else if (WEBDRIVER_TYPE.equalsIgnoreCase(SAFARI_NAME)) {
            driver = SafariLauncher.createDriver();
        } else {
            // Если не указан Chrome, Firefox или Safari, можно выбрать другой драйвер по умолчанию
            // Например, можно использовать Chrome
            driver = ChromeLauncher.createDriver();
        }

        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}