package selenide.api_module.steps.book_store_application;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import selenide.api_module.constants.ApiEndpoints;
import selenide.api_module.data.book_store_application_dto.BooksObjects;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookStoreCommonSteps {

    @Step("Проверили список книг в API")
    public List<BooksObjects> getBooksListInApiByTitle(){
        List<BooksObjects> booksData = given()
                .when()
                .contentType(ContentType.JSON)
                .get(ApiEndpoints.BOOKSTORE_PAGE.getUrl())
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
}
