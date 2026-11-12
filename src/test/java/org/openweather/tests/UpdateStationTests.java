package org.openweather.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openweather.helpers.TestData;
import org.openweather.models.StationRequestModel;
import org.openweather.models.StationResponseModel;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openweather.helpers.TestData.NEW_ALTITUDE;
import static org.openweather.helpers.TestData.NEW_NAME;

@Owner("llvbh")
@DisplayName("Изменение станции")
public class UpdateStationTests extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Название и высота станции успешно изменяются")
    void updateStationTest() {
        StationRequestModel station = TestData.getStation();
        stationId = stationApi.createStation(station).getId();

        station.setName(NEW_NAME);
        station.setAltitude(NEW_ALTITUDE);
        stationApi.updateStation(stationId, station);

        StationResponseModel response = stationApi.getStation(stationId);
        step("Проверяем новое название и высоту станции", () ->
                assertThat(response)
                        .extracting(StationResponseModel::getName, StationResponseModel::getAltitude)
                        .containsExactly(NEW_NAME, NEW_ALTITUDE));
    }
}
