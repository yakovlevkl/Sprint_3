package ru.practicum.scooter;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ScooterDeleteCourier {
    /*
    метод удаления курьера
    удаление по логину и паролю
    */
    public Boolean deleteCourier(String courierLogin, String courierPassword) {
        ScooterLoginCourier loginId = new ScooterLoginCourier(courierLogin, courierPassword);
        try {
            Integer courierId = loginId.getIdCourier();
            Response response = given()
                    .pathParam("id", courierId)
                    .when()
                    .delete("https://qa-scooter.praktikum-services.ru/api/v1/courier/{id}");
            if (response.statusCode() == 200) {
                System.out.println("Courier:" + courierLogin + " deleted, data rollback.");
            } else {
                System.out.println("Courier:" + courierLogin + " NOT deleted. Reason: " + response.statusCode());
            }
            return response.statusCode() == 200;
        }catch (Exception e){
            System.out.println("Courier:" + courierLogin + " NOT deleted. Reason: " + e);
        }
        return false;
    }
}
