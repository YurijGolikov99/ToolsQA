package selenide.common_module.driver.tg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//более простой вариант объявления вебдрайвера

/**
 *1.настройка, инициализация и установка браузера
 */

public class DriverManagerTG {

    @BeforeMethod
    public void init(){
        setUp();
    }

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
