package selenide.common_module.driver.easy_initialization;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

//более простой вариант объявления вебдрайвера

/**
 *1.настройка, инициализация и установка браузера
 */

//DriverManager - будет открываться и закрываться каждый раз браузер
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
