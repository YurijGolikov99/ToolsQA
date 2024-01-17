package selenium_tests.api.book_store_application;


import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import selenium.api.BooksObjects;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class BookStoreTest  {
    @Test
    public void checkBooksObjects(){
        List<BooksObjects> booksTitle = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://demoqa.com/BookStore/v1/Books")
                .then().log().all()
                .extract().body().jsonPath().getList("title", BooksObjects.class);
        booksTitle.forEach(x-> Assert.assertTrue(x.getTitle().contains(x.getTitle())));

        List<String> title = booksTitle.stream().map(BooksObjects::getTitle).collect(Collectors.toList());
    }
}