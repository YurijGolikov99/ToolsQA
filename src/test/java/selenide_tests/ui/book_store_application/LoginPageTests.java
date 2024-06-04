package selenide_tests.ui.book_store_application;

import org.junit.jupiter.api.Test;
import selenide.common_module.data.Credentials;
import selenide.common_module.driver.hard_initialization.DriverProvider;
import selenide.ui_module.steps.MainPageSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;


public class LoginPageTests extends DriverProvider {

    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    @Test
    public void testAuthorisationWithValidDate(){
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(Credentials.USER_LOGIN.getProperty(), Credentials.USER_PASSWORD.getProperty());
    }
}
