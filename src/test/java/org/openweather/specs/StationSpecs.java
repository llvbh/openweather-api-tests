package org.openweather.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.openweather.helpers.CustomAllureListener.withCustomTemplates;

public class StationSpecs {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().method()
            .log().body()
            .contentType(ContentType.JSON)
            .baseUri("https://api.openweathermap.org")
            .basePath("/data/3.0/stations")
            .queryParam("appid", System.getProperty("apiKey"));

    public static RequestSpecification wrongKeyRequestSpec = with()
            .filter(withCustomTemplates())
            .log().method()
            .contentType(ContentType.JSON)
            .baseUri("https://api.openweathermap.org")
            .basePath("/data/3.0/stations")
            .queryParam("appid", "wrong_key");

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
    }
}
