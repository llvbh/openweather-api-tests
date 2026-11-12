package org.openweather.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.openweather.helpers.CustomAllureListener.withCustomTemplates;

public class StationSpecs {

    public static RequestSpecification requestSpec(String apiKey) {
        return with()
                .filter(withCustomTemplates())
                .log().method()
                .log().body()
                .contentType(ContentType.JSON)
                .queryParam("appid", apiKey);
    }

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
    }
}
