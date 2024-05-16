package selenide.api_module.steps.boock_store_application;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.junit.Assert;
import selenide.api_module.constants.ApiEndpoints;
import selenide.api_module.data.Registartion.RegistrationRequest;
import selenide.api_module.data.Registartion.RegistrationResponse;
import selenide.api_module.data.book_store_application.BooksData;
import selenide.common_module.Specifications;
import selenide.common_module.data.Credentials;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class BookStoreCommonSteps {

    private static final String BOOKSTORE_URL = ApiEndpoints.BOOKSTORE_PAGE.getUrl();
    private static final String REGISTER_URL = ApiEndpoints.REGISTER_PAGE.getUrl();

    private final Credentials LOGIN = Credentials.USER_LOGIN;
    private final Credentials PASSWORD = Credentials.USER_PASSWORD;

    // если версия java compiler будет другая а не 11, nо аннотация не будет доступна
    @Step("Ввод валидных данных при регистрации")
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
    @Step("")
    public void enterValidData2(){
        Specifications.installSpecification(Specifications.requestSpecification(REGISTER_URL),
                Specifications.responseSpecificationOk(REGISTER_URL));
    }

    public void generateTokenForUser(){

    }

    public void authorizeUserWithValidDate(){

    }

    public void deleteUser(){

    }

    @Step("Проверили список книг в API")
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
