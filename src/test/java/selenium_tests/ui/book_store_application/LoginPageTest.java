package selenium_tests.ui.book_store_application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import selenium.common_module.data.Credentials;
import selenium.common_module.driver.BaseSeleniumTest;
import selenium.ui_module.pages.MainPageWithFindBY;
import selenium.ui_module.pages.book_store_application.BookStorePage;
import selenium.ui_module.steps.book_store_application.BookStorePageSteps;
import selenium.ui_module.steps.book_store_application.LoginPageSteps;

public class LoginPageTest extends BaseSeleniumTest {

    protected static final Logger logger = LogManager.getLogger();

    //если вынести MainPageWithFindBY и BookStorePage код за пределы метода,код будет падать с ошибкой driver null
    @Test
    public void validAuthorisation(){
        MainPageWithFindBY mainPageWithFindBY = new MainPageWithFindBY();
        BookStorePage bookStorePage = new BookStorePage();
        mainPageWithFindBY.openBookStore();
        logger.info("Starting validAuthorisation test...");
        bookStorePage.openLoginPage()
                .auth(Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD);
        logger.info("validAuthorisation test finished.");
    }

    //или второй способ
    @Test
    public void validAuthorisation2(){
        MainPageWithFindBY mainPageWithFindBY = new MainPageWithFindBY();
        LoginPageSteps loginPageSteps = new LoginPageSteps();
        BookStorePageSteps bookStorePageSteps = new BookStorePageSteps();

        mainPageWithFindBY.openBookStore();
        bookStorePageSteps.openLoginPage();
        logger.info("Starting validAuthorisation test");
        loginPageSteps.auth(Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD);
        logger.info("validAuthorisation test finished.");
    }
}
