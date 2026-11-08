package org.openweather.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openweather.helpers.TestData;
import org.openweather.models.StationRequestModel;
import org.openweather.models.StationResponseModel;

import static org.assertj.core.api.Assertions.assertThat;

@Owner("llvbh")
@DisplayName("Изменение станции")
public class UpdateStationTests extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Название и высота станции успешно изменяются")
    void updateStationTest() {
        StationRequestModel station = TestData.getStation();
        stationId = stationApi.createStation(station).getId();

        station.setName("Станция Астана");
        station.setAltitude(350);
        stationApi.updateStation(stationId, station);

        StationResponseModel response = stationApi.getStation(stationId);
        assertThat(response.getName()).isEqualTo("Станция Астана");
        assertThat(response.getAltitude()).isEqualTo(350);
    }
}
