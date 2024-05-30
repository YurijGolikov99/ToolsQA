package selenide.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import selenium.ui_module.pages.book_store_application.BookStorePageFindBy;

public class BookStorePageSteps {

    private final BookStorePageFindBy bookStorePage = new BookStorePageFindBy();

    @Step("Открыть страницу логин")
    public void openLoginPage(){
        bookStorePage.getLoginButton().click();
    }
}
