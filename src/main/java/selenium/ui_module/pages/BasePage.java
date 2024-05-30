package selenium.ui_module.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.common_module.driver.hard_initialization.DriverProvider;

import java.time.Duration;

// от этого класса должны наследоваться другие классы описывающие страницы
public abstract class BasePage {

    protected final Logger logger = LogManager.getRootLogger();

    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT),   Duration.ofSeconds(POLLING));
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }
}
