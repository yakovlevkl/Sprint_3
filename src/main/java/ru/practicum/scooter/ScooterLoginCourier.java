package ru.practicum.scooter;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

public class ScooterLoginCourier {
  /*
    метод удаления курьера
    удаление по логину и паролю
    */

    String courierLogin;
    String courierPassword;
    int courierId;

    ScooterLoginCourier(String courierLogin, String courierPassword) {
        this.courierLogin = courierLogin;
        this.courierPassword = courierPassword;
    }

    public Integer getIdCourier(){

       String loginRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\"}";

        // отправляем запрос на логин курьера возвращаем true или false
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(loginRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");

        if (response.statusCode() == 200) {
            courierId = response.getBody().jsonPath().getInt("id");
        }
        return courierId;
    }
}
