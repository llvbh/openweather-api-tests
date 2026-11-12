package org.openweather.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openweather.helpers.TestData;
import org.openweather.models.ErrorResponseModel;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openweather.helpers.TestData.NOT_EXISTING_ID;
import static org.openweather.helpers.TestData.STATION_NOT_FOUND;

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
        step("Проверяем, что станция не найдена", () ->
                assertThat(response.getMessage()).isEqualTo(STATION_NOT_FOUND));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Нельзя удалить несуществующую станцию")
    void deleteNotExistingStationTest() {
        ErrorResponseModel response = stationApi.deleteStationWithError(NOT_EXISTING_ID);

        step("Проверяем сообщение об ошибке", () ->
                assertThat(response.getMessage()).isEqualTo(STATION_NOT_FOUND));
    }
}
