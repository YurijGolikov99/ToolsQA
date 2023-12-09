package selenide.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

//3
public class SafariLauncher {
    public static WebDriver createDriver() {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }
}
