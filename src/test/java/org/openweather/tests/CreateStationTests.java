package org.openweather.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openweather.helpers.TestData;
import org.openweather.models.CreateStationResponseModel;
import org.openweather.models.ErrorResponseModel;
import org.openweather.models.StationRequestModel;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openweather.helpers.TestData.BAD_EXTERNAL_ID;
import static org.openweather.helpers.TestData.BAD_NAME;

@Owner("llvbh")
@DisplayName("Создание станции")
public class CreateStationTests extends TestBase {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Станция успешно создается")
    void createStationTest() {
        StationRequestModel station = TestData.getStation();

        CreateStationResponseModel response = stationApi.createStation(station);
        stationId = response.getId();

        step("Проверяем параметры созданной станции", () -> {
            assertThat(response.getId()).isNotBlank();
            assertThat(response)
                    .extracting(
                            CreateStationResponseModel::getExternalId,
                            CreateStationResponseModel::getName,
                            CreateStationResponseModel::getLatitude,
                            CreateStationResponseModel::getLongitude,
                            CreateStationResponseModel::getAltitude)
                    .containsExactly(
                            station.getExternalId(),
                            station.getName(),
                            station.getLatitude(),
                            station.getLongitude(),
                            station.getAltitude());
        });
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Станция не создается без external_id")
    void createStationWithoutExternalIdTest() {
        StationRequestModel station = TestData.getStation();
        station.setExternalId(null);

        ErrorResponseModel response = stationApi.createStationWithError(station);

        step("Проверяем сообщение об ошибке", () ->
                assertThat(response.getMessage()).isEqualTo(BAD_EXTERNAL_ID));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Станция не создается без названия")
    void createStationWithoutNameTest() {
        StationRequestModel station = TestData.getStation();
        station.setName(null);

        ErrorResponseModel response = stationApi.createStationWithError(station);

        step("Проверяем сообщение об ошибке", () ->
                assertThat(response.getMessage()).isEqualTo(BAD_NAME));
    }

    @ParameterizedTest(name = "Станция не создается с координатами {0} и {1}")
    @CsvSource({
            "100, 76.92, Station latitude should be in (-90:90)",
            "43.25, 200, Station longitude should be in (-180:180)"
    })
    @Severity(SeverityLevel.NORMAL)
    void createStationWithBadCoordinatesTest(Double latitude, Double longitude, String message) {
        StationRequestModel station = TestData.getStation();
        station.setLatitude(latitude);
        station.setLongitude(longitude);

        ErrorResponseModel response = stationApi.createStationWithError(station);

        step("Проверяем сообщение об ошибке", () ->
                assertThat(response.getMessage()).isEqualTo(message));
    }
}
