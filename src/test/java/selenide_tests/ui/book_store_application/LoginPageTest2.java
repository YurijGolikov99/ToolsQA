package selenide_tests.ui.book_store_application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import selenide.common_module.data.Credentials;
import selenide.common_module.driver.easy_initialization.BaseSeleniumTest;
import selenide.ui_module.pages.MainPageFindBY;
import selenide.ui_module.steps.book_store_application.BookStorePageSteps;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;

public class LoginPageTest2 extends BaseSeleniumTest {

    protected static final Logger logger = LogManager.getLogger();

    //если вынести MainPageWithFindBY и BookStorePage код за пределы метода, код будет падать с ошибкой driver null
    @Test
    public void validAuthorisation2(){
        MainPageFindBY mainPageWithFindBY = new MainPageFindBY();
        LoginPageSteps loginPageSteps = new LoginPageSteps();
        BookStorePageSteps bookStorePageSteps = new BookStorePageSteps();

        logger.info("Starting validAuthorisation test");
        mainPageWithFindBY.openBookStore();
        bookStorePageSteps.openLoginPage();
        loginPageSteps.auth(Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD);
        logger.info("validAuthorisation test finished.");
    }
}