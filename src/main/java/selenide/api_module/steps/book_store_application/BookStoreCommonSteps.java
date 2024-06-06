package selenide.api_module.steps.book_store_application;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import selenide.api_module.constants.ApiEndpoints;
import selenide.api_module.data.book_store_application.BooksObjects;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookStoreCommonSteps {

    @Step("Проверили список книг в API")
    public List<BooksObjects> getBooksListInApiByTitle(){
        List<BooksObjects> booksData = given()
                .when()
                .contentType(ContentType.JSON)
                .get(ApiEndpoints.getBookStoreUrl())
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
