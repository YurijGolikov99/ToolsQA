package selenium_tests.api.book_store_application;

import org.junit.Test;
import selenium.api_module.steps.book_store_application.BookStoreRegistrationStepsNoPojo;

public class BookStoreRegistrationTestsWithoutPojo {

    private final BookStoreRegistrationStepsNoPojo bookStoreCommonStepsNoPojo = new BookStoreRegistrationStepsNoPojo();

    @Test
    public void testSuccessRegistrationWithoutPojoClass(){
        bookStoreCommonStepsNoPojo.enterValidDataDuringRegistrationWithSpec();
    }

    @Test
    public void testUnSuccessRegistration(){
        bookStoreCommonStepsNoPojo.enterInvalidDataDuringRegistration();
    }
}
