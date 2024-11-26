package selenide.api_module.utils.rest;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.List;

import static selenide.common_module.logs.LoggerHelper.info;

//методы для работы с телом ответа HTTP-запроса
public class ResponseBodyParser {

    //метод возвращает тело ответа (ResponseBody) в том виде, в котором его предоставляет объект Response
    public static ResponseBody getResponseBody(Response response) {
        return response.getBody();
    }

    //метод возвращает тело ответа в виде строки
    public static String getResponseBodyAsString(Response response) {
        info("Getting response body as String %s", getResponseBody(response).asString());
        return getResponseBody(response).asString();
    }

    //метод извлекает из тела ответа список значений, соответствующих заданному выражению JSONPath
    public static List<String> getBodyValuesWithJsonPathSingle(Response response, String jsonPath) {
        info("Getting collection of values with jsonPath %s", jsonPath);
        return JsonPath.read(getResponseBodyAsString(response), jsonPath);
    }

    //метод извлекает из тела ответа список списков значений, соответствующих заданному выражению JSONPath
    public static List<List<String>> getBodyValuesWithJsonPathMultiple(Response response, String jsonPath) {
        info("Getting multiple collection of values with jsonPath %s", jsonPath);
        return JsonPath.read(getResponseBodyAsString(response), jsonPath);
    }
}
