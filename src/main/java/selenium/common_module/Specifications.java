package selenium.common_module;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static selenium.common_module.logs.LoggerHelper.info;


//создали спецификацию/инструкцию по запросу и ответу
public class Specifications {

    //базовая спецификация к запросу на JSON
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    //базовая спецификация к 200 ответу
    public static ResponseSpecification responseSpecificationOk(String url){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecificationCreated(String url){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static ResponseSpecification responseSpecificationBadRequest(String url){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpecificationNotFound(String url){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }

    //установка спецификаций
    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public Response httpGetRequestWithQueryParameters(String URI, String paramName, List<String> queryParams) {
        info("Sending GET request to resource {%s} with parameter name: {%s} and query parameters: " + queryParams,
                URI,
                paramName);
        return RestAssured.given()
                .when()
                .queryParam(paramName, queryParams.toArray())
                .get(URI);
    }

    public void checkForStatusCodeEquivalence(Response response, int expectedStatusCode) {
        info("Check that {%s} code is equal to {%s}",
                String.valueOf(response.getStatusCode()),
                String.valueOf(expectedStatusCode));
        assertEquals(response.getStatusCode(), expectedStatusCode,
                String.format("Actual status code is %d and it's not equal to expected: %d", response.getStatusCode(), expectedStatusCode));
    }

//    public void checkThatResponseBodyIsNotEmpty(Response response) {
//        info("Check that {%s} response body is not empty",
//                String.valueOf(ResponseBodyParser.getResponseBodyAsString(response).isEmpty()));
//        assertFalse(ResponseBodyParser.getResponseBodyAsString(response).isEmpty(), "Response body is empty");
//    }

    public void checkThatAllElementsContainsValue(List<List<String>> elements, String value) {
        info("Check that value {%s} presented in the collection", value);
        assertTrue(elements.stream().allMatch(element -> element.contains(value)), "Elements don't contain value: " + value);
    }
}
