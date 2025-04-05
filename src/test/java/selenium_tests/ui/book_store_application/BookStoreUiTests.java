package selenium_tests.ui.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import selenium.ui_module.constants.UiEndpoints;
import selenium.ui_module.pages.book_store_application.BookStorePage;
import selenium.ui_module.steps.book_store_application.BookStoreStepsUI;
import selenium_tests.BaseTest;

public class BookStoreUiTests extends BaseTest {

    private final BookStoreStepsUI bookStoreStepsUI = new BookStoreStepsUI();
    private BookStorePage bookStorePage;

    @AllureId("")
    @Issue("IDF-T2")
    @Epic("Книжный магазин")
    @Owner("Андрей Драмарецкий")
    @Test(description = "Проверка соответствия списков книг", //описание, тег аналог @DisplayName Junit
//            enabled = true, //Проигнорировать в запуске, тег аналог @Disabled Junit
            timeOut = 30000, //Задать время выполнения теста, тег аналог @Timeout Junit
//            invocationCount = 3, //Задаем количества повторений теста, тег аналог @RepeatedTest
            invocationTimeOut = 150000 //Задать общее время выполнения всех тестов

    )
    public void testListComplianceCheck(){
        bookStorePage = new BookStorePage(this.driver);
        bookStorePage.openPage(UiEndpoints.BOOK_STORE_PAGE.getUrl());
        bookStoreStepsUI.compareListsOfBooks();
    }
}
