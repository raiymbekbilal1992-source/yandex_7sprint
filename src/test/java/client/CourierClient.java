package client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierClient {

    public CourierClient() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    private static final String COURIER_PATH = "/api/v1/courier";
    private static final String LOGIN_PATH = "/api/v1/courier/login";

    @Step("Создать курьера")
    public Response create(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .body(courier)
                .post(COURIER_PATH);
    }

    @Step("Авторизация курьера")
    public Response login(CourierCredentials credentials) {
        return given()
                .header("Content-Type", "application/json")
                .body(credentials)
                .post(LOGIN_PATH);
    }

    @Step("Удаление курьера")
    public Response delete(int id) {
        return given()
                .delete(COURIER_PATH + "/" + id);
    }
}