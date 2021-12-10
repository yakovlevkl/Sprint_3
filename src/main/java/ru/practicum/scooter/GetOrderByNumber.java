package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

class GetOrderByNumber {

    private String orderTrack;

    GetOrderByNumber(String orderTrack) {
        this.orderTrack = orderTrack;
    }

    @Step("Заказ по номеру")
    Response getOrder() {
        Allure.attachment("Password: ", String.valueOf(this.orderTrack));

        return given()
                .queryParams("t", this.orderTrack)
                .when()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders/track");
    }
}

