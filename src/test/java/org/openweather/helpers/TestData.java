package org.openweather.helpers;

import org.openweather.models.StationRequestModel;

public class TestData {

    public static StationRequestModel getStation() {
        StationRequestModel station = new StationRequestModel();
        station.setExternalId("llvbh_" + System.currentTimeMillis());
        station.setName("Станция Алматы");
        station.setLatitude(43.25);
        station.setLongitude(76.92);
        station.setAltitude(850);
        return station;
    }
}
