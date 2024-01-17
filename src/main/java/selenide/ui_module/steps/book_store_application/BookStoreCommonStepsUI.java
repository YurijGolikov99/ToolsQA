package selenide.ui_module.steps.book_store_application;

import selenide.api_module.pages.book_store_application.BookStorePage;
import selenide.api_module.data.book_store_application.BooksData;
import selenide.api_module.steps.boock_store_application.BookStoreCommonSteps;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookStoreCommonStepsUI {
    private final BookStoreCommonSteps bookStoreCommonSteps = new BookStoreCommonSteps();
    private final BookStorePage bookStorePage = new BookStorePage("https://demoqa.com/books");

    //TODO:
    // Разобраться в причине не работы анотации test
    // Выдаёт бинарный набор символов и ошибку

//    @Step("Проверили список книг на UI")
    public List<String> checkBooksListOnPageByTitle(){
        List<String> booksData = List.of(
                bookStorePage.getGitPocketGuide(),
                bookStorePage.getLearningJavaScriptDesigTitle(),
                bookStorePage.getDesigningEvolvableWebAPIsTitle(),
                bookStorePage.getSpeakingJavaScriptTitle(),
                bookStorePage.getYouDontKnowJSTitle(),
                bookStorePage.getProgrammingJavaScriptApplicationsTitle(),
                bookStorePage.getEloquentJavaScriptSecondEditionTitle(),
                bookStorePage.getUnderstandingECMAScriptTitle()
        );
        return booksData;
    }

//    @Step("Cравниваем соответствие списков книг")
    public void compareListsOfBooks(){
        List<BooksData> booksFromApi = bookStoreCommonSteps.getBooksListInApiByTitle();
        List<String> booksOnPage = checkBooksListOnPageByTitle();
        assertEquals("Список книг из API не соответствует списку на странице", booksFromApi, booksOnPage);
    }
}