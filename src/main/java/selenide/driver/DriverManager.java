package selenide.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

/**
 *1.настройка, инициализация и установка браузера
 */


public class DriverManager {

    @Before
    public void init(){
        setUp();
    }

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
