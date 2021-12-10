package ru.practicum.scooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ListOrder {

    ListOrder() {
    }

    @Step("Запрос списка заказов")
    public Response getResponse(){
        // отправляем запрос на логин курьера возвращаем true или false
        return given()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders");
    }

}
