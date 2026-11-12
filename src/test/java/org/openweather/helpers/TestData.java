package org.openweather.helpers;

import org.openweather.models.StationRequestModel;

public class TestData {

    public static final String API_KEY = System.getProperty("apiKey", System.getenv("API_KEY"));
    public static final String WRONG_API_KEY = "wrong_key";
    public static final String NOT_EXISTING_ID = "000000000000000000000000";
    public static final String BAD_ID = "abc";

    public static final String STATION_NOT_FOUND = "Station not found";
    public static final String STATION_ID_NOT_VALID = "Station id not valid";
    public static final String BAD_EXTERNAL_ID = "Bad external id";
    public static final String BAD_NAME = "Bad or zero length station name";
    public static final String INVALID_API_KEY = "Invalid API key";

    public static final String NEW_NAME = "Станция Астана";
    public static final int NEW_ALTITUDE = 350;

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
