package selenide.api_module.steps.boock_store_application;

import io.restassured.http.ContentType;
import org.junit.Assert;
import selenide.api.boock_store_application.BooksData;
import selenide.api.boock_store_application.RegistrationRequest;
import selenide.api.boock_store_application.RegistrationResponse;
import selenide.api_module.pages.book_store_application.BookStorePage;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class BookStoreCommonSteps {
    private static final String BOOKSTORE_URL = "https://demoqa.com/BookStore/v1/Books";
    private static final String REGISTER_URL = "https://demoqa.com/Account/v1/User";

    //TODO:
    // из-за объявления этого класса, всегда открыввется UI
    private static final BookStorePage bookStorePage = new BookStorePage("https://demoqa.com/books");


    //    @Step("Ввод валидных данных при регистрации")
    public void enterValidData(){
        RegistrationRequest testUser = new RegistrationRequest("Yurij", "Yurij_@1999");
        RegistrationResponse success = given()
                .body(testUser)
                .when()
                .contentType(ContentType.JSON)
                .post(REGISTER_URL)
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all()
                .extract()
                .as(RegistrationResponse.class);
        Assert.assertNotNull(success.getUserID());
        assertEquals("Имя пользователя не соответствует", testUser.getUserName(), success.getUsername());
    }

    public void generateTokenForUser(){

    }

    public void authorizeUserWithValidDate(){

    }

    public void deleteUser(){

    }


//    @Step("Проверили список книг в API")
    public List<BooksData> getBooksListInApiByTitle(){
        List<BooksData> booksData = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BOOKSTORE_URL)
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
