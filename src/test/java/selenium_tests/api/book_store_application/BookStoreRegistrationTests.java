package selenium_tests.api.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import selenium.api_module.steps.book_store_application.BookStoreRegistrationCommonSteps;

public class BookStoreRegistrationTests {

    private final BookStoreRegistrationCommonSteps bookStoreCommonSteps = new BookStoreRegistrationCommonSteps();

    //перед повторным запуском, стоит удалить пользователя
    @DisplayName("Успешная регистрация с валидными данными")
    @AllureId("")
    @Issue("")
    @Tags({@Tag("API"), @Tag("IM_SERVICE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
//    @Service(AllureServiceConstants.IM_MONITORING_SERVICE)
//    @Layer(AllureLayer.SYSTEM_TESTS)
    @Test
    public void testSuccessRegistrationWithValidDate(){
        bookStoreCommonSteps.enterValidDataDuringRegistration();
    }

    @Test
    public void testUnSuccessRegistration(){
        bookStoreCommonSteps.enterInvalidDataDuringRegistration();
    }

    @Test
    public void loginWithValidDate(){
    }

    @Test
    public void deleteUsers(){

    }
}
