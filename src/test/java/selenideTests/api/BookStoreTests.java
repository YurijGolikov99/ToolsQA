package selenideTests.api;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenide.steps.boockStoreApplication.BookStoreCommonSteps;

public class BookStoreTests {

    private final BookStoreCommonSteps bookStoreCommonSteps = new BookStoreCommonSteps();

    @DisplayName("Проверка соответствия списков книг")
    @AllureId("1")
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