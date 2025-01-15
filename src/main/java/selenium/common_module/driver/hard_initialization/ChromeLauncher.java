package selenium.common_module.driver.hard_initialization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//1 Создали веб-драйвер для chrome
public class ChromeLauncher implements BrowserLauncher {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}