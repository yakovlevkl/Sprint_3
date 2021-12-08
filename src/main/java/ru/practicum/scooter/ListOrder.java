package ru.practicum.scooter;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ListOrder {

    public ListOrder() {
    }

    public Response getResponse(){
        // отправляем запрос на логин курьера возвращаем true или false
        return given()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders");
    }

}
