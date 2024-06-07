package selenide_tests.ui.book_store_application;

import org.junit.jupiter.api.Test;
import selenide.common_module.data.Credentials;
import selenide.ui_module.constants.UiEndpoints;
import selenide.ui_module.pages.MainPage;
import selenide.ui_module.steps.MainPageSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;
import selenide_tests.BaseTest;


public class LoginPageTests extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    @Test
    public void testAuthorisationWithValidDate(){
        mainPage.openPage(UiEndpoints.BASE_URL.getUrl());
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(Credentials.USER_LOGIN.getProperty(), Credentials.USER_PASSWORD.getProperty());
    }
}
