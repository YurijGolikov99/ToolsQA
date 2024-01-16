package selenide_tests.api;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenide.api_module.steps.boock_store_application.BookStoreCommonSteps;

public class BookStoreTests {

    private final BookStoreCommonSteps bookStoreCommonSteps = new BookStoreCommonSteps();

    //перед повторным запуском, стоит удалить пользователя
    @DisplayName("Успешная регистрация с валидными данными")
    @AllureId("")
    @Issue("IDF-T3")
//    @Tags({@Tag(), @Tag(IM_SERVICE), @Tag(SMOKE)})
    @Epic("Книжный магазин")
//    @Service(AllureServiceConstants.IM_MONITORING_SERVICE)
//    @Layer(AllureLayer.SYSTEM_TESTS)
    @Test
    public void successRegistrationWithValidDate(){
        bookStoreCommonSteps.enterValidData();
    }



    @DisplayName("Проверка соответствия списков книг")
    @AllureId("")
    @Issue("IDF-T2")
//    @Tags({@Tag(), @Tag(IM_SERVICE), @Tag(SMOKE)})
    @Epic("Книжный магазин")
//    @Service(AllureServiceConstants.IM_MONITORING_SERVICE)
//    @Layer(AllureLayer.SYSTEM_TESTS)
    @Test
    public void listComplianceCheck(){
        bookStoreCommonSteps.getBooksListInApiByTitle();
        bookStoreCommonSteps.checkBooksListOnPageByTitle();
        bookStoreCommonSteps.compareListsOfBooks();
    }
}