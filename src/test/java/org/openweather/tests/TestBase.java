package org.openweather.tests;

import org.junit.jupiter.api.AfterEach;
import org.openweather.api.StationApi;

public class TestBase {

    StationApi stationApi = new StationApi();
    String stationId;

    @AfterEach
    void deleteCreatedStation() {
        if (stationId != null) {
            stationApi.deleteStation(stationId);
        }
    }
}
