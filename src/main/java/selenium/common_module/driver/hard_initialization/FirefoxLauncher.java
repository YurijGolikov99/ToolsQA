package selenium.common_module.driver.hard_initialization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//2 создали и предоставили веб-драйвер для firefox
public class FirefoxLauncher implements BrowserLauncher {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}