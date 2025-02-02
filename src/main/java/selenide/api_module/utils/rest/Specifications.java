package selenide.api_module.utils.rest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

//создали спецификацию/инструкцию по запросу и ответу
public class Specifications {

    //базовая спецификация к запросу на JSON
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
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

    public static ResponseSpecification responseSpecificationNoContent(String url){
        return new ResponseSpecBuilder()
                .expectStatusCode(204)
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
}
