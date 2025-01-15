package selenium.common_module.driver.hard_initialization;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//8 создаём механизм для получения управления и завершения работы
// с единственным экземпляром веб-драйвера в приложении
public class DriverProvider {

    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DriverProvider.class);

    // Потокобезопасный доступ к драйверу
    public static synchronized WebDriver getDriver() {
        if (driver == null) {
            logger.info("Creating a new WebDriver instance.");
            driver = DriverFactory.launchDriver();
        }
        return driver;
    }

    //Использовано ключевое слово synchronized для методов, чтобы избежать конфликтов при многопоточном доступе.
    public static synchronized void closeDriver() {
        if (driver != null) {
            try {
                logger.info("Closing WebDriver instance.");
                //Закрывает текущее окно браузера, над которым управляет WebDriver.
                //driver.close();
                //Закрывает все окна, открытые WebDriver, и завершает работу драйвера.
                driver.quit();
                //он автоматически закрывает все окна, поэтому вызов driver.close() перед quit() не имеет смысла.
            } catch (Exception e) {
                logger.error("Error occurred while closing the driver: ", e);
            } finally {
                driver = null;
            }
        }
    }
}
