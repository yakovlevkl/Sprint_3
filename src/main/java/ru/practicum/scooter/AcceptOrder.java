package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

class AcceptOrder {

    private final String orderId;
    private final String courierId;

    AcceptOrder(String orderId, String courierId) {
        this.orderId = orderId;
        this.courierId = courierId;
    }

    @Step("Принять заказ от курьера")
    Response acceptOrderFromCourier() {
        Allure.attachment("ID заказа: ", orderId);
        Allure.attachment("ID курьера: ", courierId);
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MWIzMWUxODQ4MDljMTAwMzZlOTJlOWQiLCJpYXQiOjE2" +
                "MzkxMjg3MTAsImV4cCI6MTYzOTczMzUxMH0.dnrR9lBS2LRWh19TZu2Q09HJIrWXd4YO9LQvwPdQCIM";
        return given()
                    .auth().oauth2(token)
                    .pathParam("id", this.orderId)
                    .when()
                    .put("https://qa-scooter.praktikum-services.ru/api/v1/orders/accept/{id}?courierId="+this.courierId);
    }
}


