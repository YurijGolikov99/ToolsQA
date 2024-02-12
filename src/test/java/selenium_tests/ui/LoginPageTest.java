package selenium_tests.ui;

import selenium.common_module.data.Credentials;
import selenium.common_module.driver.BaseSeleniumTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import selenium.ui_module.pages.book_store_application.BookStorePage;

public class LoginPageTest extends BaseSeleniumTest {
    protected static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void validAuthorisation(){
        LOGGER.info("Starting validAuthorisation test...");
        BookStorePage bookStoreApplication = new BookStorePage();
        bookStoreApplication.openLoginPage()
                .auth(Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD);
        LOGGER.info("validAuthorisation test finished.");
    }
}
