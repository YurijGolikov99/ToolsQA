package selenium.api_module.steps.book_store_application;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import selenium.api_module.constants.ApiEndpoints;
import selenium.common_module.Specifications;
import selenium.common_module.data.Credentials;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookStoreRegistrationStepsNoPojo {

    private static final String REGISTER_URL = ApiEndpoints.REGISTER_PAGE.getUrl();

    private static final String LOGIN = Credentials.TEST_USERNAME;
    private static final String PASSWORD = Credentials.TEST_PASSWORD;
    private static final String WRONG_LOGIN = Credentials.WRONG_LOGIN;
    private static final String WRONG_PASSWORD = Credentials.WRONG_PASSWORD;

    public void enterValidDataDuringRegistrationWithSpec(){
        Specifications.installSpecification(Specifications.requestSpecification(REGISTER_URL),
                Specifications.responseSpecificationCreated(REGISTER_URL));
        Map<String, String> user = new HashMap<>();
        user.put("userName", LOGIN);
        user.put("password", PASSWORD);
        Response response = given()
                .body(user)
                .when()
                .post(REGISTER_URL)
                .then()
                .log()
                .all()
                .body("username", equalTo(LOGIN))
                .extract()
                .response();
        JsonPath jsonPath = response.jsonPath();
        String userName = jsonPath.get("username");
        Assert.assertEquals( LOGIN, userName);
    }

    @Step("Ввод невалидных данных при регистрации")
    public void enterInvalidDataDuringRegistration(){
        Specifications.installSpecification(Specifications.requestSpecification(REGISTER_URL),
                Specifications.responseSpecificationBadRequest(REGISTER_URL));
        Map<String, String> user = new HashMap<>();
        user.put("userName", WRONG_LOGIN);
        user.put("password", WRONG_PASSWORD);
        given()
                .body(user)
                .when()
                .post(REGISTER_URL)
                .then()
                .log()
                .all()
                .body("message", equalTo("Passwords must have at least one non alphanumeric character, " +
                        "one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), " +
                        "one special character and Password must be eight characters or longer."))
                .extract()
                .response();
    }
}
