package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

class DeleteCourier {


    private final LoginCourier loginId;
    private final Boolean getCourierId;
    private String courierId;

    DeleteCourier(LoginCourier loginId) {
        this.loginId = loginId;
        this.getCourierId = true;
        this.courierId = "";
    }

    DeleteCourier(LoginCourier loginId, Boolean getCourierId) {
        this.loginId = loginId;
        this.getCourierId = getCourierId;
        this.courierId = "";
    }

    DeleteCourier(LoginCourier loginId, String courierId) {
        this.loginId = loginId;
        this.getCourierId = false;
        this.courierId = courierId;
    }

    /*
        метод удаления курьера
        удаление по логину и паролю
        */
    @Step("Удаление курьера")
    Response deleteCourier(String courierLogin, String courierPassword) {
        Allure.attachment("Login: ", String.valueOf(courierLogin));
        Allure.attachment("Password: ", String.valueOf(courierPassword));
        if (this.getCourierId) {
            try {
                this.courierId = String.valueOf(loginId.getIdCourier());
            }catch (Exception e){
                System.out.println("Courier:" + courierLogin + " not deleted. Reason: it doesn't seem to exist: " + e);
                this.courierId = "0";
            }
        }
        return  given()
                .pathParam("id", this.courierId)
                .when()
                .delete("https://qa-scooter.praktikum-services.ru/api/v1/courier/{id}");
    }
}
