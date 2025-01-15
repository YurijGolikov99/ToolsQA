package selenium.ui_module.steps;

import org.openqa.selenium.WebDriver;
import selenium.ui_module.pages.MainPage;

public class MainPageSteps {

    private MainPage mainPage;

    public MainPageSteps(WebDriver driver) {
        this.mainPage = new MainPage(driver);
    }

    // Методы для взаимодействия с элементами страницы
    public void clickElementsButton(){
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
