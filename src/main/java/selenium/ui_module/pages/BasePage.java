package selenium.ui_module.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.common_module.utils.TimeOuts;

import java.time.Duration;

//10 от этого класса должны наследоваться другие классы описывающие страницы
public abstract class BasePage {

    protected final Logger logger = LogManager.getRootLogger();
    protected WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOuts.ELEMENT_TIMEOUT),   Duration.ofSeconds(TimeOuts.POLLING));
        PageFactory.initElements(driver, this);
    }

    //Общие методы для взаимодействия с элементами
    //Ожидаем, что элемент доступен для клика и кликаем.
    protected void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        logger.info("Clicked on element: " + element);
    }

    //Ожидаем, что элемент видим, очищаем и вводим текст в элемент.
    protected void setInputText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
        logger.info("Set text '" + text + "' to element: " + element);
    }

    //Ожидаем, что элемент видим и текст можно извлечь.
    protected String getElementText(WebElement element) {
        String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
        logger.info("Got text '" + text + "' from element: " + element);
        return text;
    }
}
