package selenium.ui_module.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import selenium.ui_module.pages.MainPage;

public class MainPageSteps {

    protected final Logger logger = LogManager.getRootLogger();
    private MainPage mainPage;

    public MainPageSteps(WebDriver driver) {
        this.mainPage = new MainPage(driver);
    }

    // Методы для взаимодействия с элементами страницы
    public void clickElementsButton(){
        logger.info("Clicking on Elements button");
        mainPage.getElementsButton().click();
    }

    public void clickFormsButton(){
        mainPage.getFormsButton().click();
    }

    public void clickAlertFrameWindowsButton(){
        mainPage.getAlertFrameWindowsButton().click();
    }

    public void clickWidgetsButton(){
        mainPage.getWidgetsButton().click();
    }

    public void clickInteractionsButton(){
        mainPage.getInteractionsButton().click();
    }

    public void clickBookStoreApplication(){
        mainPage.getBookStoreApplicationButton().click();
    }
}
