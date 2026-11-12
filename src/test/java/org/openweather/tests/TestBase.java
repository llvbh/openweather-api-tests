package org.openweather.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openweather.api.StationApi;

public class TestBase {

    StationApi stationApi = new StationApi();
    String stationId;

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = System.getProperty("baseUrl", "https://api.openweathermap.org");
        RestAssured.basePath = System.getProperty("basePath", "/data/3.0/stations");
    }

    @AfterEach
    void deleteCreatedStation() {
        if (stationId != null) {
            stationApi.deleteStation(stationId);
            stationId = null;
        }
    }
}
