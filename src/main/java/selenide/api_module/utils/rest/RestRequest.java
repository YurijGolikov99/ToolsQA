package selenide.api_module.utils.rest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class RestRequest {

    /**
     * Отправляет HTTP-запрос с RestAssured, проверяет только статус код.
     *
     * @param baseUri     базовый URL (например "https://api.server.com")
     * @param endpoint    часть URL после baseUri (например "/users")
     * @param headers     заголовки запроса (например Content-Type, Accept, Authorization и т.д.)
     * @param contentType тип контента (JSON, XML и т.д.)
     * @param method      HTTP-метод (GET, POST, PUT, DELETE и т.д.)
     * @param requestBody тело запроса. Может быть JSON, XML или объект, который сериализуется RestAssured
     * @param statusCode  ожидаемый HTTP-статус ответа (например 200, 201, 400)
     * @return {@link Response} - результат запроса
     */
    public Response sendRestRequest(String baseUri, String endpoint, Map<String, String> headers, ContentType contentType,  Method method,
                                    Object requestBody, int statusCode) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(baseUri)
                .headers(headers)
                .body(requestBody)
                .contentType(contentType)
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

    /**
     * Отправляет HTTP-запрос с RestAssured, проверяет статус код и тело ответа (JSONPath).
     *
     * @param baseUri                   базовый URL
     * @param endpoint                  часть URL (endpoint)
     * @param headers                   заголовки запроса
     * @param contentType               тип контента (JSON, XML и т.д.)
     * @param method                    HTTP-метод (GET, POST, PUT, DELETE и т.д.)
     * @param requestBody               тело запроса
     * @param statusCode                ожидаемый HTTP-статус ответа
     * @param path                      JSONPath-выражение (например "data.id" или "result.items[0]")
     * @param matcher                   Hamcrest Matcher, определяющий ожидаемое значение
     * @param additionalKeyMatcherPairs дополнительная пара ключ-значение (JSONPath, Matcher) для множественной проверки
     * @return {@link Response} - результат запроса
     */
    public Response sendRestRequestWithBodyChecks(String baseUri, String endpoint, Map<String, String> headers, ContentType contentType, Method method,
                                                  Object requestBody, int statusCode, String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
        return given()
                .filter(new AllureRestAssured())                // Подключает Allure-логирование
                .baseUri(baseUri)                               // Устанавливает базовый URL
                .headers(headers)                               // Устанавливает заголовки
                .body(requestBody)                              // Тело запроса (может быть Json/XML/строка/объект и пр.)
                .contentType(contentType)                       // Content-Type (например JSON, XML, TEXT и т.д.)
                .relaxedHTTPSValidation()                       // Отключает строгую проверку HTTPS-сертификата
                .log().all()                                    // Логирует весь запрос (headers + body + endpoint)
                .when()
                .request(method, endpoint)                      // Выполняет запрос (GET/POST/PUT/DELETE и т.д.)
                .then()
                .log().all()                                    // Логирует весь ответ
                .statusCode(statusCode)                         // Проверяет статус код
                .assertThat()
                .body(path, matcher, additionalKeyMatcherPairs) // Проверяет тело ответа по JSONPath.
                .extract()
                .response();
    }

    /**
     * Все настройки запроса аналогичны предыдущему методу,
     * но проверка выполняется по заголовкам ответа при помощи headers(path, matcher, additionalKeyMatcherPairs).
     * Используйте этот метод, когда необходимо убедиться, что ответ содержит нужные заголовки
     * и у них корректные значения (например, "Content-Type": "application/json" или "Cache-Control": "no-cache").
     *
     * @param baseUri                 базовый URL
     * @param endpoint                часть URL (endpoint)
     * @param headers                 заголовки запроса
     * @param contentType             тип контента
     * @param method                  HTTP-метод
     * @param requestBody             тело запроса
     * @param statusCode              ожидаемый HTTP-статус ответа
     * @param path                    название конкретного заголовка (или нескольких заголовков) в ответе
     * @param matcher                 проверка (Matcher) на соответствие значения заголовка
     * @param additionalKeyMatcherPairs дополнительные заголовки для множественной проверки
     * @return {@link Response}       результат запроса
     */
    public Response sendRestRequestWithHeadersChecks(String baseUri, String endpoint, Map<String, String> headers, ContentType contentType,  Method method,
                                                     Object requestBody, int statusCode, String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(baseUri)
                .headers(headers)
                .body(requestBody)
                .contentType(contentType)
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

    /**
     * Отправляет HTTP-запрос с полной проверкой:
     *
     * @param baseUri        базовый URL
     * @param endpoint       часть URL (endpoint)
     * @param headers        заголовки запроса
     * @param contentType    тип контента
     * @param method         HTTP-метод
     * @param requestBody    тело запроса
     * @param statusCode     ожидаемый статус код
     * @param headerMatchers Map вида ("Content-Type", equalTo("application/json"))
     * @param bodyJsonPath   JSONPath, который нужно проверить в теле ответа
     * @param bodyMatcher    Hamcrest Matcher для значения, полученного по bodyJsonPath
     * @return {@link Response} результат запроса
     */
    public Response sendRestRequestWithFullValidation(String baseUri, String endpoint, Map<String, String> headers, ContentType contentType, Method method,
                                                      Object requestBody, int statusCode, Map<String, Matcher<?>> headerMatchers, String bodyJsonPath, Matcher<?> bodyMatcher
    ) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(baseUri)
                .headers(headers)
                .body(requestBody)
                .contentType(contentType)
                .relaxedHTTPSValidation()
                .log().all()
                .when()
                .request(method, endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .headers(headerMatchers)                       // <-- Множество проверок заголовков
                .body(bodyJsonPath, bodyMatcher)               // <-- Проверка конкретного тела JsonPath
                .extract()
                .response();
    }



    /**
     * Упрощённая версия отправки запроса (JSON по умолчанию).
     * <p>Проверяется только статус код</p>
     *
     * @param baseUri   базовый URL
     * @param endpoint  часть URL
     * @param method    HTTP-метод
     * @param requestBody тело запроса
     * @param statusCode ожидаемый статус код
     * @return {@link Response} результат запроса
     */
    public Response sendRestRequest(String baseUri, String endpoint, Method method,
                                    Object requestBody, int statusCode) {
        return sendRestRequest(baseUri, endpoint, Map.of("accept", "application/json"), ContentType.JSON, method, requestBody, statusCode);
    }

    /**
     * Упрощённая версия отправки запроса (JSON по умолчанию) с проверкой тела.
     * <p>По умолчанию используется заголовок "accept: application/json" и ContentType.JSON</p>
     *
     * @param baseUri   базовый URL
     * @param endpoint  часть URL
     * @param method    HTTP-метод
     * @param requestBody тело запроса
     * @param statusCode ожидаемый статус код
     * @param path      JSONPath в ответе
     * @param matcher   Matcher для проверки
     * @param additionalKeyMatcherPairs дополнительная пара (JSONPath, Matcher)
     * @return {@link Response} результат запроса
     */
    public Response sendRestRequestWithBodyChecks(String baseUri, String endpoint, Method method,
                                                  Object requestBody, int statusCode, String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
        return sendRestRequestWithBodyChecks(baseUri, endpoint, Map.of("accept", "application/json"), // <-- Заголовок по умолчанию
                ContentType.JSON, // <-- Content-Type по умолчанию
                method, requestBody, statusCode, path, matcher, additionalKeyMatcherPairs);
    }

    /**
     * Упрощённая версия отправки запроса (JSON по умолчанию) с проверкой заголовков.
     *
     * @param baseUri   базовый URL
     * @param endpoint  часть URL
     * @param method    HTTP-метод
     * @param requestBody тело запроса
     * @param statusCode ожидаемый статус код
     * @param path      название конкретного заголовка в ответе
     * @param matcher   Matcher для проверки заголовка
     * @param additionalKeyMatcherPairs дополнительные заголовки для множественной проверки
     * @return {@link Response} результат запроса
     */
    public Response sendRestRequestWithHeadersChecks(String baseUri, String endpoint, Method method,
                                                     Object requestBody, int statusCode, String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
        return sendRestRequestWithHeadersChecks(baseUri, endpoint, Map.of("accept", "application/json"), ContentType.JSON, method, requestBody, statusCode, path, matcher, additionalKeyMatcherPairs);
    }

    /**
     * Упрощённая версия отправки запроса (JSON по умолчанию) с полной проверкой
     * (статус код, заголовки, тело).
     *
     * @param baseUri        базовый URL
     * @param endpoint       часть URL
     * @param method         HTTP-метод
     * @param requestBody    тело запроса
     * @param statusCode     ожидаемый статус код
     * @param headerMatchers проверки заголовков
     * @param bodyJsonPath   JSONPath в теле ответа
     * @param bodyMatcher    Matcher для значения, полученного по bodyJsonPath
     * @return {@link Response} результат запроса
     */
    public Response sendRestRequestWithFullValidation(String baseUri, String endpoint, Method method,
                                                      Object requestBody, int statusCode, Map<String, Matcher<?>> headerMatchers,
                                                      String bodyJsonPath, Matcher<?> bodyMatcher) {
        return sendRestRequestWithFullValidation(
                baseUri,
                endpoint,
                Map.of("accept", "application/json"),  // хэдеры по умолчанию
                ContentType.JSON,                     // контент-тайп
                method,
                requestBody,
                statusCode,
                headerMatchers,
                bodyJsonPath,
                bodyMatcher
        );
    }
}
