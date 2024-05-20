package selenide_tests.ui.book_store_application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import selenide.ui_module.steps.MainPageSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;
import selenium.common_module.data.Credentials;

public class LoginPageTests {

    protected static final Logger logger = LogManager.getLogger();

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
