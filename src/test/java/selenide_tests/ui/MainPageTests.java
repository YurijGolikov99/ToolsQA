package selenide_tests.ui;

import org.junit.Test;
import selenium.ui_module.constants.UiEndpoints;
import selenium.ui_module.steps.MainPageSteps;

// TODO разобраться с DriverManger и DriverProvider
//DriverManager - будет открываться и закрываться каждый раз браузер
//DriverProvider - всё будет прогоняться сплошными тестами в одном окне
public class MainPageTests { //DriverManager или DriverProvider

    public final static String BASE_URL = UiEndpoints.BASE_URL.getUrl();

    //вызывается конструктор, с помощью использования ключевого слова new.
    private final MainPageSteps mainPageSteps = new MainPageSteps();

    @Test
    public void openElementsPage(){
        mainPageSteps.clickElementsButton();
    }

    @Test
    public void openFormsPage(){
        mainPageSteps.clickFormsButton();
    }

    @Test
    public void openAlertFrameWindowsPage(){
        mainPageSteps.clickAlertFrameWindowsButton();
    }

    @Test
    public void openWidgetsPage(){
        mainPageSteps.clickWidgetsButton();
    }

    @Test
    public void openInteractionsPage(){
        mainPageSteps.clickInteractionsButton();
    }

    @Test
    public void openBookStoreApplicationPage(){
        mainPageSteps.clickBookStoreApplication();
    }
}
