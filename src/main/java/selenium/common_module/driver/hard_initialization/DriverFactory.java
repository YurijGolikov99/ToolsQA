package selenium.common_module.driver.hard_initialization;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static selenium.common_module.Global.CHROME_NAME;
import static selenium.common_module.Global.FIREFOX_NAME;
import static selenium.common_module.Global.SAFARI_NAME;
import static selenium.common_module.Global.WEBDRIVER_TYPE;
import static selenium.common_module.utils.TimeOuts.ELEMENT_TIMEOUT;
import static selenium.common_module.utils.TimeOuts.PAGE_LOAD_TIMEOUT;

//7 создали порядок и настройки объявления веб-драйвера
public class DriverFactory {

    public static WebDriver launchDriver() {
        // Создаем драйвер через выбор нужного браузера
        WebDriver driver = getDriverByType();

        // Настройка драйвера
        configureDriver(driver);

        return driver;
    }

    private static WebDriver getDriverByType() {
        BrowserLauncher launcher;

        if (WEBDRIVER_TYPE.equalsIgnoreCase(CHROME_NAME)) {
            launcher = new ChromeLauncher();
        } else if (WEBDRIVER_TYPE.equalsIgnoreCase(FIREFOX_NAME)) {
            launcher = new FirefoxLauncher();
        } else if (WEBDRIVER_TYPE.equalsIgnoreCase(SAFARI_NAME)) {
            launcher = new SafariLauncher();
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + WEBDRIVER_TYPE);
        }

        return launcher.createDriver();
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}

