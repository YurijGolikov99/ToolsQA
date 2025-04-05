package selenide.api_module.utils.soap;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SoapRequest {

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
    public Response sendSoapRequest(String baseUri, String endpoint, Map<String, String> headers, ContentType contentType, Method method,
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
     * Отправляет SOAP-запрос (XML) по умолчанию:
     *
     * @param baseUri    базовый URL (SOAP Endpoint)
     * @param endpoint   часть URL (если нужно)
     * @param method     HTTP-метод (обычно POST для SOAP)
     * @param requestBody тело запроса (должен содержать XML с envelope/body)
     * @param statusCode ожидаемый статус код
     * @return {@link Response} результат запроса
     */
    public Response sendSoapRequest(String baseUri, String endpoint, Method method,
                                    Object requestBody, int statusCode) {
        return sendSoapRequest(baseUri, endpoint, Map.of("accept", "*/*"), ContentType.XML, method, requestBody, statusCode);
    }

}
