package org.openweather.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openweather.helpers.TestData;
import org.openweather.models.ErrorResponseModel;
import org.openweather.models.StationRequestModel;
import org.openweather.models.StationResponseModel;
import org.openweather.models.UnauthorizedResponseModel;

import static org.assertj.core.api.Assertions.assertThat;

@Owner("llvbh")
@DisplayName("Получение станции")
public class GetStationTests extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Созданную станцию можно получить по id")
    void getStationTest() {
        StationRequestModel station = TestData.getStation();
        stationId = stationApi.createStation(station).getId();

        StationResponseModel response = stationApi.getStation(stationId);

        assertThat(response.getId()).isEqualTo(stationId);
        assertThat(response.getExternalId()).isEqualTo(station.getExternalId());
        assertThat(response.getName()).isEqualTo(station.getName());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Созданная станция есть в списке станций")
    void getStationsListTest() {
        stationId = stationApi.createStation(TestData.getStation()).getId();

        StationResponseModel[] stations = stationApi.getStations();

        assertThat(stations).extracting(StationResponseModel::getId).contains(stationId);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Нельзя получить несуществующую станцию")
    void getNotExistingStationTest() {
        ErrorResponseModel response = stationApi.getStationWithError("000000000000000000000000", 404);

        assertThat(response.getMessage()).isEqualTo("Station not found");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Нельзя получить станцию с неправильным id")
    void getStationWithBadIdTest() {
        ErrorResponseModel response = stationApi.getStationWithError("abc", 400);

        assertThat(response.getMessage()).isEqualTo("Station id not valid");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Нельзя получить список станций с неверным ключом")
    void getStationsWithWrongKeyTest() {
        UnauthorizedResponseModel response = stationApi.getStationsWithWrongKey();

        assertThat(response.getCod()).isEqualTo(401);
        assertThat(response.getMessage()).contains("Invalid API key");
    }
}
