package selenide.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import selenide.ui_module.pages.book_store_application.BookStorePage;

public class BookStorePageSteps {

    private final BookStorePage bookStorePage = new BookStorePage();

    @Step("Открыть страницу логин")
    public void openLoginPage(){
        bookStorePage.getLoginButton().click();
    }
}
