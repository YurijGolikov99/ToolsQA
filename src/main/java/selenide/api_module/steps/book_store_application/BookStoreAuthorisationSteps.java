package selenide.api_module.steps.book_store_application;

import io.qameta.allure.Step;
import org.junit.Assert;
import selenide.api_module.constants.ApiEndpoints;
import selenide.api_module.data.book_store_application.GenerateTokenRequest;
import selenide.api_module.data.book_store_application.GenerateTokenResponse;
import selenide.api_module.data.book_store_application.RegistrationRequest;
import selenide.common_module.Specifications;
import selenide.common_module.data.Credentials;

import static io.restassured.RestAssured.given;

public class BookStoreAuthorisationSteps {

    private static final String LOGIN = Credentials.USER_LOGIN.getProperty();
    private static final String PASSWORD = Credentials.USER_PASSWORD.getProperty();
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    @Step("Генерация токена новому пользователю")
    public void generateUserToken(){
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.getGenerateTokenUrl()),
                Specifications.responseSpecificationOk(ApiEndpoints.getGenerateTokenUrl()));
        GenerateTokenRequest testUser = new GenerateTokenRequest(LOGIN, PASSWORD);
        GenerateTokenResponse response = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.getGenerateTokenUrl())
                .then()
                .assertThat()
                .log()
                .all()
                .extract()
                .as(GenerateTokenResponse.class);
        Assert.assertNotNull(response.getToken());
        token.set(response.token);
    }

    @Step("Ввод валидных данных при авторизации")
    public void validAuthorisation(){
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.getAuthorisationUrl()),
                Specifications.responseSpecificationOk(ApiEndpoints.getAuthorisationUrl()));
        RegistrationRequest testUser = new RegistrationRequest(LOGIN, PASSWORD);
        Boolean response = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.getAuthorisationUrl())
                .then()
                .assertThat()
                .log()
                .all()
                .extract()
                .as(Boolean.class);
        Assert.assertTrue("Authorization failed", response);
    }
}
