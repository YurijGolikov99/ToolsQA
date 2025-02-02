package selenide.api_module.utils.rest;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.List;

import static selenide.common_module.logs.LoggerHelper.info;

//методы для работы с телом ответа HTTP-запроса
public class ResponseBodyParser {

    /**
     * Метод преобразует/возвращает тело ответа (ResponseBody) в том виде, в котором его предоставляет объект Response
     *  Применяется, когда нужно работать с сырым телом ответа.
     */
    public static ResponseBody getResponseBody(Response response) {
        return response.getBody();
    }

    /**
     * Метод преобразует/возвращает тело ответа в виде строки
     *  Логирует тело ответа с помощью LoggerHelper.info().
     *   Применяется, когда нужно анализировать ответ в виде текста или передавать его в другие методы.
     */
    public static String getResponseBodyAsString(Response response) {
        info("Getting response body as String %s", getResponseBody(response).asString());
        return getResponseBody(response).asString();
    }

    /**
     * Метод извлекает из тела ответа список значений, соответствующих заданному выражению JSONPath
     *  Использует JsonPath.read() для парсинга JSON.
     *   Применяется, когда нужно извлечь один набор значений по JsonPath.
     */
    public static List<String> getBodyValuesWithJsonPathSingle(Response response, String jsonPath) {
        info("Getting collection of values with jsonPath %s", jsonPath);
        return JsonPath.read(getResponseBodyAsString(response), jsonPath);
    }

    /**
     * Метод извлекает из тела ответа список списков значений, соответствующих заданному выражению JSONPath
     *  Применяется, когда JsonPath возвращает несколько наборов данных (например, массивы объектов).
     */
    public static List<List<String>> getBodyValuesWithJsonPathMultiple(Response response, String jsonPath) {
        info("Getting multiple collection of values with jsonPath %s", jsonPath);
        return JsonPath.read(getResponseBodyAsString(response), jsonPath);
    }
}
