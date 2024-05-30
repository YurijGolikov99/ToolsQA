package selenium_tests.ui.book_store_application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import selenium.common_module.data.Credentials;
import selenium.common_module.driver.EasyInicialisation.BaseSeleniumTest;
import selenium.ui_module.pages.MainPageFindBY;
import selenium.ui_module.pages.book_store_application.BookStorePage;
import selenium.ui_module.steps.book_store_application.BookStorePageSteps;
import selenium.ui_module.steps.book_store_application.LoginPageSteps;

public class LoginPageTest extends BaseSeleniumTest {

    protected static final Logger logger = LogManager.getLogger();

    //если вынести MainPageWithFindBY и BookStorePage код за пределы метода, код будет падать с ошибкой driver null
    @Test
    public void validAuthorisation(){
        MainPageFindBY mainPageFindBY = new MainPageFindBY();
        BookStorePage bookStorePage = new BookStorePage();

        logger.info("Starting validAuthorisation test...");
        mainPageFindBY.openBookStore();
        bookStorePage.openLoginPage()
                .auth(Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD);
        logger.info("validAuthorisation test finished.");
    }

    //или второй способ
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
