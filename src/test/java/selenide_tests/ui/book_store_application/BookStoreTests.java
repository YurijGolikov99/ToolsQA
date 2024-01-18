package selenide_tests.ui.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenide.api_module.steps.boock_store_application.BookStoreCommonSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;

public class BookStoreTests {

    private final BookStoreCommonSteps bookStoreCommonSteps = new BookStoreCommonSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    @Test
    public void loginWithValidDate(){
    }

    @Test
    public void deleteUsers(){

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
        bookStoreCommonStepsUI.checkBooksListOnPageByTitle();
        bookStoreCommonStepsUI.compareListsOfBooks();
    }
}
