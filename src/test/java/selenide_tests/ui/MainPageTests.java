package selenide_tests.ui;

import org.junit.Test;
import selenide.api_module.driver.DriverProvider;
import selenide.api_module.pages.MainPage;

/**
 *3.Тесты главной страницы
 */

//DriverManager - будет открываться и закрываться каждый раз браузер
//DriverProvider - всё будет прогоняться сплошными тестами в одном окне
public class MainPageTests extends DriverProvider { //DriverManager или DriverProvider
    public final static String BASE_URL = "https://demoqa.com";
    private final MainPage mainPage = new MainPage(BASE_URL);

    @Test
    public void openElementsPage(){
        mainPage.clickOnElementsButton();
    }

    @Test
    public void openFormsPage(){
        mainPage.clickOnFormsButton();
    }

    @Test
    public void openAlertFrameWindowsPage(){
        mainPage.clickOnAlertFrameWindowsButton();
    }

    @Test
    public void openWidgetsPage(){
        mainPage.clickOnWidgetsButton();
    }

    @Test
    public void openInteractionsPage(){
        mainPage.clickOnInteractionsButton();
    }

    @Test
    public void openBookStoreApplicationPage(){
        mainPage.clickOnBookStoreApplication();
    }
}