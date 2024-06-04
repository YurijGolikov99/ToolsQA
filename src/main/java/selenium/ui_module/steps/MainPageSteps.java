package selenium.ui_module.steps;

import selenium.common_module.driver.hard_initialization.DriverProvider;
import selenium.ui_module.pages.MainPage;

public class MainPageSteps {

    private final MainPage mainPage = new MainPage(DriverProvider.getDriver());

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
