package selenide_tests.ui.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import selenide.api_module.steps.boock_store_application.BookStoreCommonSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;

public class BookStoreTestsUi {

    private final BookStoreCommonSteps bookStoreCommonSteps = new BookStoreCommonSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();

    @DisplayName("Проверка соответствия списков книг")
    @AllureId("")
    @Issue("IDF-T2")
    @Tags({@Tag("UI"), @Tag("IM_SERVICE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
//    @Service(AllureServiceConstants.IM_MONITORING_SERVICE)
//    @Layer(AllureLayer.SYSTEM_TESTS)
    @Test
    public void testListComplianceCheck(){
        bookStoreCommonSteps.getBooksListInApiByTitle();
        bookStoreCommonStepsUI.checkBooksListOnPageByTitle();
        bookStoreCommonStepsUI.compareListsOfBooks();
    }
}
