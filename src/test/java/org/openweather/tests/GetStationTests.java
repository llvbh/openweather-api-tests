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

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openweather.helpers.TestData.BAD_ID;
import static org.openweather.helpers.TestData.INVALID_API_KEY;
import static org.openweather.helpers.TestData.NOT_EXISTING_ID;
import static org.openweather.helpers.TestData.STATION_ID_NOT_VALID;
import static org.openweather.helpers.TestData.STATION_NOT_FOUND;

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

        step("Проверяем параметры полученной станции", () ->
                assertThat(response)
                        .extracting(
                                StationResponseModel::getId,
                                StationResponseModel::getExternalId,
                                StationResponseModel::getName)
                        .containsExactly(
                                stationId,
                                station.getExternalId(),
                                station.getName()));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Созданная станция есть в списке станций")
    void getStationsListTest() {
        stationId = stationApi.createStation(TestData.getStation()).getId();

        StationResponseModel[] stations = stationApi.getStations();

        step("Проверяем, что станция есть в списке", () ->
                assertThat(stations).extracting(StationResponseModel::getId).contains(stationId));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Нельзя получить несуществующую станцию")
    void getNotExistingStationTest() {
        ErrorResponseModel response = stationApi.getStationWithError(NOT_EXISTING_ID, 404);

        step("Проверяем сообщение об ошибке", () ->
                assertThat(response.getMessage()).isEqualTo(STATION_NOT_FOUND));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Нельзя получить станцию с неправильным id")
    void getStationWithBadIdTest() {
        ErrorResponseModel response = stationApi.getStationWithError(BAD_ID, 400);

        step("Проверяем сообщение об ошибке", () ->
                assertThat(response.getMessage()).isEqualTo(STATION_ID_NOT_VALID));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Нельзя получить список станций с неверным ключом")
    void getStationsWithWrongKeyTest() {
        UnauthorizedResponseModel response = stationApi.getStationsWithWrongKey();

        step("Проверяем код и сообщение об ошибке", () -> {
            assertThat(response.getCod()).isEqualTo(401);
            assertThat(response.getMessage()).contains(INVALID_API_KEY);
        });
    }
}
