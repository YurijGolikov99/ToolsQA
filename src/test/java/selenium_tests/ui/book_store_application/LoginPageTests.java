package selenium_tests.ui.book_store_application;

import org.testng.annotations.Test;
import selenium.common_module.driver.hard_initialization.DriverProvider;
import selenium.common_module.property.PropertyHelper;
import selenium.ui_module.constants.UiEndpoints;
import selenium.ui_module.pages.MainPage;
import selenium.ui_module.steps.MainPageSteps;
import selenium.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenium.ui_module.steps.book_store_application.LoginPageSteps;
import selenium_tests.BaseTest;

public class LoginPageTests extends BaseTest {

    private final MainPage mainPage = new MainPage(DriverProvider.getDriver());
    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();


    @Test
    public void testAuthorisationWithValidDate(){
        mainPage.openPage(UiEndpoints.BASE_URL.getUrl());
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(PropertyHelper.getProperty("login.user"), PropertyHelper.getProperty("password.user"));
    }
}
