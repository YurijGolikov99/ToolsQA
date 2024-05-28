package selenium.common_module.driver.HardInicialisation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//2 создали и предоставили веб-драйвер для firefox
public class FirefoxLauncher {

    public static WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}