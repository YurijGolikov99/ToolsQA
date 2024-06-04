package selenide_tests.api.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import selenide.api_module.steps.book_store_application.BookStoreAuthorisationSteps;
import selenide.api_module.steps.book_store_application.BookStoreRegistrationSteps;

public class BookStoreRegistrationTests {

    private final BookStoreRegistrationSteps bookStoreRegistrationSteps = new BookStoreRegistrationSteps();
    private final BookStoreAuthorisationSteps bookStoreAuthorisationSteps = new BookStoreAuthorisationSteps();

    //перед повторным запуском, стоит удалить пользователя
    @DisplayName("Успешная регистрация с валидными данными")
    @AllureId("1")
    @Issue("IDF-T3")
    @Tags({@Tag("UI"), @Tag("IM_SERVICE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
//    @Service(AllureServiceConstants.IM_MONITORING_SERVICE)
//    @Layer(AllureLayer.SYSTEM_TESTS)
    @Test
    public void testSuccessRegistrationWithValidDate(){
        bookStoreRegistrationSteps.enterValidDataDuringRegistration();
    }

    //дубль такого же теста как и выше
    @Test
    public void testSuccessRegistrationWithValidDate2(){
        bookStoreRegistrationSteps.enterValidDataDuringRegistrationWithSpec();
    }

    @Test
    public void testUnSuccessRegistration(){
        bookStoreRegistrationSteps.enterInvalidDataDuringRegistration();
    }

    @Test
    public void testGenerateToken(){
        bookStoreAuthorisationSteps.generateUserToken();
    }

    @Test
    public void testLoginWithValidDate(){
        bookStoreAuthorisationSteps.validAuthorisation();
    }

    @Test
    public void testDeleteUsers(){

    }
}
