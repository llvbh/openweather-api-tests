package org.openweather.api;

import io.qameta.allure.Step;
import org.openweather.models.CreateStationResponseModel;
import org.openweather.models.ErrorResponseModel;
import org.openweather.models.StationRequestModel;
import org.openweather.models.StationResponseModel;
import org.openweather.models.UnauthorizedResponseModel;

import static io.restassured.RestAssured.given;
import static org.openweather.helpers.TestData.API_KEY;
import static org.openweather.helpers.TestData.WRONG_API_KEY;
import static org.openweather.specs.StationSpecs.requestSpec;
import static org.openweather.specs.StationSpecs.responseSpec;

public class StationApi {

    @Step("Создаем станцию")
    public CreateStationResponseModel createStation(StationRequestModel station) {
        return given(requestSpec(API_KEY))
                .body(station)
                .when()
                .post()
                .then()
                .spec(responseSpec(201))
                .extract().as(CreateStationResponseModel.class);
    }

    @Step("Пытаемся создать станцию с неверными данными")
    public ErrorResponseModel createStationWithError(StationRequestModel station) {
        return given(requestSpec(API_KEY))
                .body(station)
                .when()
                .post()
                .then()
                .spec(responseSpec(400))
                .extract().as(ErrorResponseModel.class);
    }

    @Step("Получаем станцию {id}")
    public StationResponseModel getStation(String id) {
        return given(requestSpec(API_KEY))
                .when()
                .get("/" + id)
                .then()
                .spec(responseSpec(200))
                .extract().as(StationResponseModel.class);
    }

    @Step("Пытаемся получить станцию {id} и ждем код {statusCode}")
    public ErrorResponseModel getStationWithError(String id, int statusCode) {
        return given(requestSpec(API_KEY))
                .when()
                .get("/" + id)
                .then()
                .spec(responseSpec(statusCode))
                .extract().as(ErrorResponseModel.class);
    }

    @Step("Получаем список станций")
    public StationResponseModel[] getStations() {
        return given(requestSpec(API_KEY))
                .when()
                .get()
                .then()
                .spec(responseSpec(200))
                .extract().as(StationResponseModel[].class);
    }

    @Step("Получаем список станций с неверным ключом")
    public UnauthorizedResponseModel getStationsWithWrongKey() {
        return given(requestSpec(WRONG_API_KEY))
                .when()
                .get()
                .then()
                .spec(responseSpec(401))
                .extract().as(UnauthorizedResponseModel.class);
    }

    @Step("Изменяем станцию {id}")
    public StationResponseModel updateStation(String id, StationRequestModel station) {
        return given(requestSpec(API_KEY))
                .body(station)
                .when()
                .put("/" + id)
                .then()
                .spec(responseSpec(200))
                .extract().as(StationResponseModel.class);
    }

    @Step("Удаляем станцию {id}")
    public void deleteStation(String id) {
        given(requestSpec(API_KEY))
                .when()
                .delete("/" + id)
                .then()
                .spec(responseSpec(204));
    }

    @Step("Пытаемся удалить несуществующую станцию {id}")
    public ErrorResponseModel deleteStationWithError(String id) {
        return given(requestSpec(API_KEY))
                .when()
                .delete("/" + id)
                .then()
                .spec(responseSpec(404))
                .extract().as(ErrorResponseModel.class);
    }
}
