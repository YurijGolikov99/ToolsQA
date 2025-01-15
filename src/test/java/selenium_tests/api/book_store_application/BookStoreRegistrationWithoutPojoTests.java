package selenium_tests.api.book_store_application;

import org.testng.annotations.Test;
import selenium.api_module.steps.book_store_application.BookStoreRegistrationStepsNoPojo;

public class BookStoreRegistrationWithoutPojoTests {

    private final BookStoreRegistrationStepsNoPojo bookStoreCommonStepsNoPojo = new BookStoreRegistrationStepsNoPojo();

    //перед повторным запуском, стоит удалить пользователя
    @Test
    public void testSuccessRegistrationWithoutPojoClass(){
        bookStoreCommonStepsNoPojo.enterValidDataDuringRegistrationWithSpec();
    }

    @Test
    public void testUnSuccessRegistration(){
        bookStoreCommonStepsNoPojo.enterInvalidDataDuringRegistration();
    }
}
