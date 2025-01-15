package selenium.api_module.steps.book_store_application;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.junit.Assert;
import selenium.api_module.constants.ApiEndpoints;
import selenium.api_module.data.book_store_application.BadRegistrationResponse;
import selenium.api_module.data.book_store_application.RegistrationRequest;
import selenium.api_module.data.book_store_application.RegistrationResponse;
import selenium.api_module.data.book_store_application.BooksData;
import selenium.common_module.Specifications;
import selenium.common_module.data.CredentialsFromProperties;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

//шаги связанные с POJO классами
public class BookStoreRegistrationCommonSteps {

    private static final String BOOKSTORE_URL = ApiEndpoints.BOOKSTORE_PAGE.getUrl();
    private static final String REGISTER_URL = ApiEndpoints.REGISTER_PAGE.getUrl();

    private static final String LOGIN = CredentialsFromProperties.USER_LOGIN.getProperty();
    private static final String PASSWORD = CredentialsFromProperties.USER_PASSWORD.getProperty();
    private static final String WRONG_LOGIN = CredentialsFromProperties.WRONG_LOGIN.getProperty();
    private static final String WRONG_PASSWORD = CredentialsFromProperties.WRONG_PASSWORD.getProperty();

    private static final ThreadLocal<String> userID = new ThreadLocal<>();

    // если версия java compiler будет другая а не 11, nо аннотация будет приводить к ошибке
    @Step("Ввод валидных данных при регистрации")
    public void enterValidDataDuringRegistration(){
        RegistrationRequest testUser = new RegistrationRequest(LOGIN, PASSWORD);
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
        Specifications.installSpecification(Specifications.requestSpecification(REGISTER_URL),
                Specifications.responseSpecificationCreated(REGISTER_URL));
        RegistrationRequest testUser = new RegistrationRequest(LOGIN, PASSWORD);
        RegistrationResponse successResponse = given()
                .body(testUser)
                .when()
                .post(REGISTER_URL)
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
        Specifications.installSpecification(Specifications.requestSpecification(REGISTER_URL),
                Specifications.responseSpecificationBadRequest(REGISTER_URL));
        RegistrationRequest testUser = new RegistrationRequest(WRONG_LOGIN, WRONG_PASSWORD);
        BadRegistrationResponse unSuccessResponse = given()
                .body(testUser)
                .when()
                .post(REGISTER_URL)
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
