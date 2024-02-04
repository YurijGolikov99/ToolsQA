package selenide.api_module.steps.boock_store_application;

import io.restassured.http.ContentType;
import org.junit.Assert;
import selenide.api_module.constants.ApiEndpoints;
import selenide.common_module.Specifications;
import selenide.api_module.data.book_store_application.BooksData;
import selenide.api_module.data.RegistrationRequest;
import selenide.api_module.data.RegistrationResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class BookStoreCommonSteps {
    private static final String BOOKSTORE_URL = ApiEndpoints.BOOKSTORE.getUrl();
    private static final String REGISTER_URL = ApiEndpoints.REGISTER.getUrl();

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

    //или можно вторым способом
    public void enterNotValidData(){
        Specifications.installSpecification(Specifications.requestSpecification(REGISTER_URL), Specifications.responseSpecificationOk(REGISTER_URL));
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
}
