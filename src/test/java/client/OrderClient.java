package client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {

    public OrderClient() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    private static final String ORDER_PATH = "/api/v1/orders";

    @Step("Создать заказ")
    public Response create(Order order) {
        return given()
                .header("Content-Type", "application/json")
                .body(order)
                .post(ORDER_PATH);
    }

    @Step("Получить список заказов")
    public Response getOrders() {
        return given()
                .get(ORDER_PATH);
    }
}