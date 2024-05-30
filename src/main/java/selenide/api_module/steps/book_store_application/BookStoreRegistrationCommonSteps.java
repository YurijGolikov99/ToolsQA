package selenide.api_module.steps.book_store_application;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.junit.Assert;
import selenide.api_module.constants.ApiEndpoints;
import selenium.api_module.data.book_store_application.BadRegistrationResponse;
import selenium.api_module.data.book_store_application.BooksData;
import selenium.api_module.data.book_store_application.RegistrationRequest;
import selenium.api_module.data.book_store_application.RegistrationResponse;
import selenium.common_module.Specifications;
import selenium.common_module.data.Credentials;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

//шаги связанные с POJO классами
public class BookStoreRegistrationCommonSteps {

    private static ApiEndpoints apiEndpoints = new ApiEndpoints();

    private static final String LOGIN = Credentials.USER_LOGIN.getProperty();
    private static final String PASSWORD = Credentials.USER_PASSWORD.getProperty();
    private static final String WRONG_LOGIN = Credentials.WRONG_LOGIN.getProperty();
    private static final String WRONG_PASSWORD = Credentials.WRONG_PASSWORD.getProperty();

    private static final ThreadLocal<String> userID = new ThreadLocal<>();

    // если версия java compiler будет другая а не 11, то аннотация будет приводить к ошибке
    @Step("Ввод валидных данных при регистрации")
    public void enterValidDataDuringRegistration(){
        RegistrationRequest testUser = new RegistrationRequest(LOGIN, PASSWORD);
        RegistrationResponse success = given()
                .body(testUser)
                .when()
                .contentType(ContentType.JSON)
                .post(ApiEndpoints.getRegisterUrl())
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all()
                .extract()
                .as(RegistrationResponse.class); /*указали сразу так обращение к классу, так как внутри нет массива
        можно было бы указать путь как books.title, если бы надо было обратится к элементу массива
        */
        Assert.assertNotNull(success.getUserID());
        assertEquals("Имя пользователя не соответствует", testUser.getUserName(), success.getUsername());
        userID.set(success.getUserID());
    }

    public String getUserID() {
        return userID.get();
    }

    //или можно вторым способом с использованием спецификаций
    @Step("Ввод валидных данных при регистрации")
    public void enterValidDataDuringRegistrationWithSpec(){
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.getRegisterUrl()),
                Specifications.responseSpecificationCreated(ApiEndpoints.getRegisterUrl()));
        RegistrationRequest testUser = new RegistrationRequest(LOGIN, PASSWORD);
        RegistrationResponse successResponse = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.getRegisterUrl())
                .then()
                .assertThat()
                .log()
                .all()
                .extract()
                .as(RegistrationResponse.class);
        Assert.assertNotNull(successResponse.getUserID());
        assertEquals("Имя пользователя не соответствует", testUser.getUserName(), successResponse.getUsername());
        userID.set(successResponse.getUserID());
    }

    @Step("Ввод невалидных данных при регистрации")
    public void enterInvalidDataDuringRegistration(){
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.getRegisterUrl()),
                Specifications.responseSpecificationBadRequest(ApiEndpoints.getRegisterUrl()));
        RegistrationRequest testUser = new RegistrationRequest(WRONG_LOGIN, WRONG_PASSWORD);
        BadRegistrationResponse unSuccessResponse = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.getRegisterUrl())
                .then()
                .assertThat()
                .log()
                .all()
                .extract()
                .as(BadRegistrationResponse.class);
        assertEquals("Passwords must have at least one non alphanumeric character, " +
                        "one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), " +
                        "one special character and Password must be eight characters or longer.",
                unSuccessResponse.getMessage());
    }

    @Step("Получение токена")
    public void generateTokenForUser(){

    }

    @Step("Авторизация с валидными данными")
    public void authorizeUserWithValidDate(){

    }

    public void deleteUser(){

    }

    @Step("Проверили список книг в API")
    public List<BooksData> getBooksListInApiByTitle(){
        List<BooksData> booksData = given()
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
