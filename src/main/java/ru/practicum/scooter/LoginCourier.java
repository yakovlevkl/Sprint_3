package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginCourier {

    private String loginRequestBody;

    LoginCourier(String courierLogin, String courierPassword) {
        this.loginRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\"}";
    }

    public Response getResponse(){
        // отправляем запрос на логин курьера возвращаем true или false
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(this.loginRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
    }

    @Step("Логин курьера")
    int loginCourier(){
        int statusCode = getResponse().statusCode();
        Allure.attachment("Получено значение: ", String.valueOf(statusCode));
        return getResponse().statusCode();
    }

    @Step("Получение id курьера")
    int getIdCourier(){
        int id = getResponse().getBody().jsonPath().getInt("id");
        Allure.attachment("Получено значение: ", String.valueOf(id));
        return id;
    }
}
