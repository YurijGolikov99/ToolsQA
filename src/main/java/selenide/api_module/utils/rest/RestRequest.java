package selenide.api_module.utils.rest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
@Service
public class RestRequest {

    public Response sendRestRequest(String baseUri, String endpoint, Map<String, String> headers, Method method,
                                    Object requestBody, int statusCode, String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(baseUri)
                .headers(headers)
                .body(requestBody)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .request(method, endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .assertThat()
                .body(path, matcher, additionalKeyMatcherPairs)
                .extract()
                .response();
    }

    public Response sendRestRequestWithHeadersChecks(String baseUri, String endpoint, Map<String, String> headers, Method method,
                                                     Object requestBody, int statusCode, String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(baseUri)
                .headers(headers)
                .body(requestBody)
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .log().all()
                .when()
                .request(method, endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .headers(path, matcher, additionalKeyMatcherPairs)
                .extract()
                .response();
    }

    public Response sendRestRequest(String baseUri, String endpoint, Map<String, String> headers, Method method,
                                    Object requestBody, int statusCode) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(baseUri)
                .headers(headers)
                .body(requestBody)
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .log().all()
                .when()
                .request(method, endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public Response sendRestRequest(String baseUri, String endpoint, Method method,
                                    Object requestBody, int statusCode, String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
        return sendRestRequest(baseUri, endpoint, Map.of("accept", "application/json"), method, requestBody, statusCode, path, matcher, additionalKeyMatcherPairs);
    }

    public Response sendRestRequestWithHeadersChecks(String baseUri, String endpoint, Method method,
                                                     Object requestBody, int statusCode, String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
        return sendRestRequestWithHeadersChecks(baseUri, endpoint, Map.of("accept", "application/json"), method, requestBody, statusCode, path, matcher, additionalKeyMatcherPairs);
    }

    public Response sendRestRequest(String baseUri, String endpoint, Method method,
                                    Object requestBody, int statusCode) {
        return sendRestRequest(baseUri, endpoint, Map.of("accept", "application/json"), method, requestBody, statusCode);
    }

}
