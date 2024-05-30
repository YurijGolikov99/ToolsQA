package selenide_tests.api.book_store_application;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import selenide.api_module.data.book_store_application.BooksObjects;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookStoreTest  {

    // TODO слишком колхозно
    //  сделать степ класс
    //  добавить константу ендпоинта
    //  добавить проверку а не просто перебор книг
    @Test
    public void checkBooksObjects(){
        List<BooksObjects> booksTitle = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log()
                .all()
                .extract()
                .body()
                .jsonPath()
                .getList("title", BooksObjects.class);
        booksTitle.forEach(x-> Assert.assertTrue(x.getTitle().contains(x.getTitle())));
    }
}
