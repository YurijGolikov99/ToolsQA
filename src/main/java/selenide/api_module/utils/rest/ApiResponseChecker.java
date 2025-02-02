package selenide.api_module.utils.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static selenium.common_module.logs.LoggerHelper.info;

public class ApiResponseChecker {

    /**
     * Отправляет GET-запрос на заданный URI, добавляя к нему query-параметры.
     *
     * @param URI — адрес API, на который отправляется запрос.
     * @param paramName — имя query-параметра, который будет добавлен к URI.
     * @param queryParams — список значений для query-параметра.
     * @return Возвращает объект {@link Response}, который можно использовать для проверки статуса ответа или его содержимого.
     */
    public Response httpGetRequestWithQueryParameters(String URI, String paramName, List<String> queryParams) {
        info("Sending GET request to resource {%s} with parameter name: {%s} and query parameters: " + queryParams,
                URI,
                paramName);
        if (queryParams == null || queryParams.isEmpty()) {
            return RestAssured.given()
                    .when()
                    .get(URI);
        }
        return RestAssured.given()
                .when()
                .queryParam(paramName, queryParams.toArray())
                .get(URI);
    }

    /**
     * Проверяет, что HTTP-статус ответа совпадает с ожидаемым статусом.
     *  Если код не совпадает, тест упадет (assertEquals).
     * @param response — объект {@link Response}, содержащий тело ответа.
     * @param expectedStatusCode — ожидаемый статус код HTTP-ответа.
     */
    public void checkForStatusCodeEquivalence(Response response, int expectedStatusCode) {
        info("Check that {%s} code is equal to {%s}",
                String.valueOf(response.getStatusCode()),
                String.valueOf(expectedStatusCode));
        assertEquals(response.getStatusCode(), expectedStatusCode,
                String.format("Actual status code is %d and it's not equal to expected: %d", response.getStatusCode(), expectedStatusCode));
    }

    /**
     * Проверяет, что тело ответа не пустое (assertFalse).
     *  Если тело ответа пустое, тест падает.
     *
     * @param response — объект {@link Response}, содержащий тело ответа.
     */
    public void checkThatResponseBodyIsNotEmpty(Response response) {
        info("Check that {%s} response body is not empty",
                String.valueOf(ResponseBodyParser.getResponseBodyAsString(response).isEmpty()));
        assertFalse(ResponseBodyParser.getResponseBodyAsString(response).isEmpty(), "Response body is empty");
    }

    /**
     * Проверяет, что все элементы во вложенных коллекциях содержат заданное значение.
     *  Если хотя бы один список не содержит это значение, тест не проходит.
     *
     * @param elements Принимает List<List<String>> elements — список списков строк.
     * @param value Проверяет, что каждый вложенный список содержит строку value
     */
    public void checkThatAllElementsContainsValue(List<List<String>> elements, String value) {
        info("Check that value {%s} presented in the collection", value);
        assertTrue(elements.stream().allMatch(element -> element.contains(value)), "Elements don't contain value: " + value);
    }
}
