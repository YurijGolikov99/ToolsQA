package selenide.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

//создали спецификацию/инструкцию  по запросу и ответу
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

    //установка спецификаций
    public static void instalSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
