package selenide_tests.api.book_store_application;

import org.junit.Test;
import selenide.api_module.steps.boock_store_application.BookStoreRegistrationCommonStepsNoPojo;

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
