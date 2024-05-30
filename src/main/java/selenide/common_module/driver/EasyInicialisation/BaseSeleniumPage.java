package selenide.common_module.driver.EasyInicialisation;

import org.openqa.selenium.WebDriver;

//Простой вариант: 1 настройка selenium page
abstract public class BaseSeleniumPage {

    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }
}
