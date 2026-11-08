package org.openweather.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openweather.helpers.TestData;
import org.openweather.models.ErrorResponseModel;

import static org.assertj.core.api.Assertions.assertThat;

@Owner("llvbh")
@DisplayName("Удаление станции")
public class DeleteStationTests extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаленная станция больше не находится")
    void deleteStationTest() {
        String id = stationApi.createStation(TestData.getStation()).getId();

        stationApi.deleteStation(id);

        ErrorResponseModel response = stationApi.getStationWithError(id, 404);
        assertThat(response.getMessage()).isEqualTo("Station not found");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Нельзя удалить несуществующую станцию")
    void deleteNotExistingStationTest() {
        ErrorResponseModel response = stationApi.deleteStationWithError("000000000000000000000000");

        assertThat(response.getMessage()).isEqualTo("Station not found");
    }
}
