package ru.practicum.scooter;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginCourier {

    String loginRequestBody;

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

    public int loginCourier(){
        return getResponse().statusCode();
    }

    public int getIdCourier(){
        return getResponse().getBody().jsonPath().getInt("id");
    }
}
