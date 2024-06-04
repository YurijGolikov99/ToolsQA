package selenide.ui_module.steps;

import selenide.ui_module.constants.UiEndpoints;
import selenide.ui_module.pages.MainPage;

public class MainPageSteps {

    private final MainPage mainPage;

    // Конструктор по умолчанию
    public MainPageSteps() {
        this.mainPage = new MainPage(UiEndpoints.BASE_URL.getUrl());
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
