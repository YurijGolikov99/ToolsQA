package selenium_tests.ui.book_store_application;

import org.junit.jupiter.api.Test;
import selenium.ui_module.steps.MainPageSteps;
import selenium.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenium.ui_module.steps.book_store_application.LoginPageSteps;
import selenide.common_module.data.Credentials;

public class LoginPageTests {

    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    @Test
    public void testAuthorisationWithValidDate(){
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD);
    }
}
