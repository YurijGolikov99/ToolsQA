package selenium.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import selenium.ui_module.pages.book_store_application.BookStorePage;

public class BookStoreCommonStepsUI {

    private final BookStorePage bookStorePage = new BookStorePage();

    @Step("Открыть страницу логин")
    public void openLoginPage(){
        bookStorePage.getLoginButton().click();
    }

    @Step("Найти книгу Git Pocket Guide")
    public String getGitPocketGuideEasy(){
        String bookName = bookStorePage.getGitPocketGuideTitle();
        if ("Git Pocket Guide".equals(bookName)) {
            return "Right book!";
        }
        return "Wrong book!";
    }

    //или более сложным способом через тернарный оператор
    @Step("Найти книгу Git Pocket Guide")
    public String getGitPocketGuide(){
        return bookStorePage.getGitPocketGuideTitle().equals("Git Pocket Guide") ? "Right book!" : "Wrong book!";
    }
}
