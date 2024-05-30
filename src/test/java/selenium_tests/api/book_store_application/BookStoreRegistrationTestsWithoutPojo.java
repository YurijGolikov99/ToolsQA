package selenium_tests.api.book_store_application;

import org.junit.Test;
import selenium.api_module.steps.book_store_application.BookStoreRegistrationCommonStepsNoPojo;

public class BookStoreRegistrationTestsWithoutPojo {

    private final BookStoreRegistrationCommonStepsNoPojo bookStoreCommonStepsNoPojo = new BookStoreRegistrationCommonStepsNoPojo();

    @Test
    public void testSuccessRegistrationWithoutPojoClass(){
        bookStoreCommonStepsNoPojo.enterValidDataDuringRegistrationWithSpec();
    }

    @Test
    public void testUnSuccessRegistration(){
        bookStoreCommonStepsNoPojo.enterInvalidDataDuringRegistration();
    }
}
