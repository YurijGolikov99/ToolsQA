import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.driver.SeleniumSettings;
import org.example.pages.BookStore;
import org.example.data.TestValues;
import org.junit.Test;

public class LoginPageTest extends SeleniumSettings {
    protected static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void validAuthorisation(){
        LOGGER.info("Starting validAuthorisation test...");
        BookStore bookStoreApplication = new BookStore();
        bookStoreApplication.openLoginPage()
                .auth(TestValues.TEST_USERNAME, TestValues.TEST_PASSWORD);
        LOGGER.info("validAuthorisation test finished.");
    }
}
