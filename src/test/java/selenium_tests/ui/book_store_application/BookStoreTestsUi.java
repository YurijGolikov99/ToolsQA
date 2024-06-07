package selenium_tests.ui.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import selenium.common_module.driver.hard_initialization.DriverProvider;
import selenium.ui_module.constants.UiEndpoints;
import selenium.ui_module.pages.book_store_application.BookStorePage;
import selenium.ui_module.steps.book_store_application.BookStoreStepsUI;
import selenium_tests.BaseTest;

public class BookStoreTestsUi extends BaseTest {

    private final BookStoreStepsUI bookStoreStepsUI = new BookStoreStepsUI();
    private final BookStorePage bookStorePage = new BookStorePage(DriverProvider.getDriver());

    @AllureId("")
    @Issue("IDF-T2")
    @Epic("Книжный магазин")
    @Owner("Андрей Драмарецкий")
    @Test
    public void testListComplianceCheck(){
        bookStorePage.openPage(UiEndpoints.BOOK_STORE_PAGE.getUrl());
        bookStoreStepsUI.compareListsOfBooks();
    }
}
