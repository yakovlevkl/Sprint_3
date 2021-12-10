package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateOrder {

    private final String json_file;

    CreateOrder(String json_file) {
        this.json_file = json_file;
    }

    @Step("Create order")
    public Response getResponse(){
        File json = new File(this.json_file);
        Allure.attachment("Запрос из файла: ", String.valueOf(json));

        // отправляем запрос на логин курьера возвращаем true или false
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/orders");
    }

}

