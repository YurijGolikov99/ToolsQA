package selenium.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import selenium.api_module.data.book_store_application.BooksData;
import selenium.api_module.steps.book_store_application.BookStoreRegistrationCommonSteps;
import selenium.ui_module.pages.book_store_application.BookStorePage;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookStoreStepsUI {

    private final BookStoreRegistrationCommonSteps bookStoreCommonSteps = new BookStoreRegistrationCommonSteps();
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
        List<BooksData> booksFromApi = bookStoreCommonSteps.getBooksListInApiByTitle();
        List<String> booksOnPage = checkBooksListOnPageByTitle();
        assertEquals("Список книг из API не соответствует списку на странице", booksFromApi, booksOnPage);
    }
}
