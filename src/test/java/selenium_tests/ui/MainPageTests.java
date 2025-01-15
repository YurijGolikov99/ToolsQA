package selenium_tests.ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.ui_module.steps.MainPageSteps;
import selenium_tests.BaseTest;

/**
 *3. Тесты главной страницы
 */
public class MainPageTests extends BaseTest {

    //вызывается конструктор, с помощью использования ключевого слова new.
    private MainPageSteps mainPageSteps;

    @BeforeMethod
    public void init() {
        // В этот момент driver уже создан в BaseTest.beforeClass()
        mainPageSteps = new MainPageSteps(this.driver);
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
