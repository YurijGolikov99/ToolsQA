package selenide.api_module.steps.book_store_application;

import io.qameta.allure.Step;
import org.junit.Assert;
import selenide.api_module.constants.ApiEndpoints;
import selenide.api_module.data.book_store_application_dto.GenerateTokenRequest;
import selenide.api_module.data.book_store_application_dto.GenerateTokenResponse;
import selenide.api_module.data.book_store_application_dto.RegistrationRequest;
import selenide.api_module.utils.rest.Specifications;
import selenide.common_module.data.Credentials;

import static io.restassured.RestAssured.given;

public class BookStoreAuthorisationSteps {

    private static final String LOGIN = Credentials.TEST_USERNAME;
    private static final String PASSWORD = Credentials.TEST_USERNAME;
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    @Step("Генерация токена новому пользователю")
    public void generateUserToken(){
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.GENERATE_TOKEN_URL.getUrl()),
                Specifications.responseSpecificationOk(ApiEndpoints.GENERATE_TOKEN_URL.getUrl()));
        GenerateTokenRequest testUser = new GenerateTokenRequest(LOGIN, PASSWORD);
        GenerateTokenResponse response = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.GENERATE_TOKEN_URL.getUrl())
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
        Specifications.installSpecification(Specifications.requestSpecification(ApiEndpoints.AUTHORISATION_URL.getUrl()),
                Specifications.responseSpecificationOk(ApiEndpoints.AUTHORISATION_URL.getUrl()));
        RegistrationRequest testUser = new RegistrationRequest(LOGIN, PASSWORD);
        Boolean response = given()
                .body(testUser)
                .when()
                .post(ApiEndpoints.AUTHORISATION_URL.getUrl())
                .then()
                .assertThat()
                .log()
                .all()
                .extract()
                .as(Boolean.class);
        Assert.assertTrue("Authorization failed", response);
    }
}
