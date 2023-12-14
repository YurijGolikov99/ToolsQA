package selenide.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//1 Создали веб-драйвер для chrome
public class ChromeLauncher {

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}