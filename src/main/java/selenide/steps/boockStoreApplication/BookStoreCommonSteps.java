package selenide.steps.boockStoreApplication;

import io.restassured.http.ContentType;
import selenide.api.BooksData;
import selenide.pages.boockStoreApplication.BookStorePage;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class BookStoreCommonSteps {

    private final BookStorePage bookStorePage = new BookStorePage("https://demoqa.com/books");

//    @Step("Проверили список книг в API")
    public List<BooksData> getBooksListInApiByTitle(){
        List<BooksData> booksData = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .body()
                .jsonPath()
                .getList("books.title");
        return booksData;
    }

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
        List<BooksData> booksFromApi = getBooksListInApiByTitle();
        List<String> booksOnPage = checkBooksListOnPageByTitle();
        assertEquals("Список книг из API не соответствует списку на странице", booksFromApi, booksOnPage);
    }
}
