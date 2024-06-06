package selenide.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import selenide.api_module.data.book_store_application.BooksObjects;
import selenide.api_module.steps.book_store_application.BookStoreCommonSteps;
import selenide.ui_module.pages.book_store_application.BookStorePage;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookStoreStepsUI {
    private final BookStoreCommonSteps bookStoreCommonSteps = new BookStoreCommonSteps();
    private final BookStorePage bookStorePage = new BookStorePage();

    @Step("Проверили список книг на UI")
    public List<String> checkBooksListOnPageByTitle(){
        List<String> booksData = List.of(
                bookStorePage.getGitPocketGuideTitle(),
                bookStorePage.getLearningJavaScriptDesignTitle(),
                bookStorePage.getDesigningEvolvableWebAPIsTitle(),
                bookStorePage.getSpeakingJavaScriptTitle(),
                bookStorePage.getYouDontKnowJSTitle(),
                bookStorePage.getProgrammingJavaScriptApplicationsTitle(),
                bookStorePage.getEloquentJavaScriptSecondEditionTitle(),
                bookStorePage.getUnderstandingECMAScriptTitle()
        );
        return booksData;
    }

    @Step("Сравниваем соответствие списков книг")
    public void compareListsOfBooks(){
        List<BooksObjects> booksFromApi = bookStoreCommonSteps.getBooksListInApiByTitle();
        List<String> booksOnPage = checkBooksListOnPageByTitle();
        assertEquals("Список книг из API не соответствует списку на странице", booksFromApi, booksOnPage);
    }
}
