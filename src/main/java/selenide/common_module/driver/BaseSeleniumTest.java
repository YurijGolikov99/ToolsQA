package selenide.common_module.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

//Простой вариант: 2 настройка selenium test
abstract public class BaseSeleniumTest {

    protected WebDriver driver;

    @BeforeMethod
    public void launchDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage()
                .window().maximize(); //открываем во весь экран браузер
        driver.manage()
                .timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //ожидание загрузки страницы до 10 сек
        driver.manage()
                .timeouts().implicitlyWait(10, TimeUnit.SECONDS); //ожидание загрузки эллемента, зависит от скорости элемента
        BaseSeleniumPage.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            // Закрывает текущее активное окно браузера, в котором выполняется WebDriver.
            driver.quit();
            //Закрывает все окна браузера, которые были открыты WebDriver'ом в течение сессии.
            //Завершает работу WebDriver и освобождает все используемые ресурсы.
        }
    }
}
