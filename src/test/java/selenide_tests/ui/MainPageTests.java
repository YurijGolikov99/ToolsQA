package selenide_tests.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selenide.ui_module.constants.BookStoreUiEndpoints;
import selenide.ui_module.pages.MainPage;
import selenide_tests.BaseTest;
import selenide.ui_module.steps.MainPageSteps;

public class MainPageTests extends BaseTest {

    public final static String BASE_URL = BookStoreUiEndpoints.BASE.getUrl();
    private MainPageSteps mainPageSteps;

    @BeforeEach
    public void setUpEachTest() {
        MainPage mainPage = new MainPage();
        mainPage.openPage(BASE_URL);
        mainPageSteps = new MainPageSteps();
    }

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
