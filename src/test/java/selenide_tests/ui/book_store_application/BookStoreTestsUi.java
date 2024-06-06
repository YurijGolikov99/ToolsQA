package selenide_tests.ui.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import selenide.common_module.driver.hard_initialization.DriverProvider;
import selenide.ui_module.constants.UiEndpoints;
import selenide.ui_module.pages.book_store_application.BookStorePage;
import selenide.ui_module.steps.book_store_application.BookStoreStepsUI;


public class BookStoreTestsUi extends DriverProvider {

    private final BookStoreStepsUI bookStoreStepsUI = new BookStoreStepsUI();
    private final BookStorePage bookStorePage = new BookStorePage();

    @DisplayName("Проверка соответствия списков книг")
    @Description("Открываем страницу, делаем выборку на апи, а после сравниваем API и UI")
    @AllureId("1")
    @Issue("IDF-T2")
    @Tags({@Tag("UI"), @Tag("IM_SERVICE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
//    @Service(AllureServiceConstants.IM_MONITORING_SERVICE)
//    @Layer(AllureLayer.SYSTEM_TESTS)
    @Owner("Юра Голиков")
    @Test
    public void testListComplianceCheck(){
        bookStorePage.openPage(UiEndpoints.BOOK_STORE_PAGE.getUrl());
        bookStoreStepsUI.compareListsOfBooks();
    }
}
