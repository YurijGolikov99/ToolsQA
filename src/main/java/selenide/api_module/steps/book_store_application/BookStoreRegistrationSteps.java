package selenide.api_module.steps.book_store_application;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.junit.Assert;
import selenide.api_module.constants.ApiEndpoints;
import selenide.api_module.data.book_store_application_dto.BadResponse;
import selenide.api_module.data.book_store_application_dto.DeleteUser;
import selenide.api_module.data.book_store_application_dto.RegistrationRequest;
import selenide.api_module.data.book_store_application_dto.RegistrationResponse;
import selenide.api_module.utils.rest.Specifications;
import selenide.common_module.data.Credentials;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

//шаги связанные с POJO классами
public class BookStoreRegistrationSteps {

    private static final String LOGIN = Credentials.TEST_USERNAME;
    private static final String PASSWORD = Credentials.TEST_PASSWORD;
    private static final String WRONG_LOGIN = Credentials.WRONG_LOGIN;
    private static final String WRONG_PASSWORD = Credentials.WRONG_PASSWORD;

    private static final ThreadLocal<String> userID = new ThreadLocal<>();

    // если версия java compiler будет другая, а не 11, то аннотация будет приводить к ошибке
    @Step("Ввод валидных данных при регистрации")
    public void enterValidDataDuringRegistration(){
        RegistrationRequest testUser = new RegistrationRequest(LOGIN, PASSWORD);
        RegistrationResponse success = given()
                .body(testUser)
                .when()
                .contentType(ContentType.JSON)
                .post(ApiEndpoints.REGISTER_URL.getUrl())
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

    //или можно вторым способом с использованием спецификаций
    @Step("Ввод валидных данных при регистрации")
    public void enterValidDataDuringRegistrationWithSpec(){
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.REGISTER_URL.getUrl()),
                Specifications.responseSpecificationCreated(ApiEndpoints.REGISTER_URL.getUrl()));
        RegistrationRequest testUser = new RegistrationRequest(LOGIN, PASSWORD);
        RegistrationResponse successResponse = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.REGISTER_URL.getUrl())
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
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.REGISTER_URL.getUrl()),
                Specifications.responseSpecificationBadRequest(ApiEndpoints.REGISTER_URL.getUrl()));
        RegistrationRequest testUser = new RegistrationRequest(WRONG_LOGIN, WRONG_PASSWORD);
        BadResponse unSuccessResponse = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.REGISTER_URL.getUrl())
                .then()
                .assertThat()
                .log()
                .all()
                .extract()
                .as(BadResponse.class);
        assertEquals("Passwords must have at least one non alphanumeric character, " +
                        "one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), " +
                        "one special character and Password must be eight characters or longer.",
                unSuccessResponse.getMessage());
    }

    @Step("Удаление пользователя")
    public void deleteUser(){
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.DELETE_USER_URL.getUrl()),
                Specifications.responseSpecificationNoContent(ApiEndpoints.DELETE_USER_URL.getUrl()));
        DeleteUser testUser = new DeleteUser(userID.get());
        BadResponse response = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.DELETE_USER_URL.getUrl()+userID)
                .then()
                .assertThat()
                .log()
                .all()
                .extract()
                .as(BadResponse.class);
        Assert.assertEquals("User has been delete", response.getMessage());
    }
}
