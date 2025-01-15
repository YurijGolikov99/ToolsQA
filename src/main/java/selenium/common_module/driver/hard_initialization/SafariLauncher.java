package selenium.common_module.driver.hard_initialization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

//3 создали и предоставили веб-драйвер для safari
public class SafariLauncher implements BrowserLauncher {

    @Override
    public  WebDriver createDriver() {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }
}
