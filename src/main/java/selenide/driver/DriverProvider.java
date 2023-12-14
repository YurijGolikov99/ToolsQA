package selenide.driver;

import org.openqa.selenium.WebDriver;

//5 создаём механизм для получения управления и завершения работы
// с единственным экземпляром веб-драйвера в приложении
public class DriverProvider {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = DriverFactory.launchDriver();
        }
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}