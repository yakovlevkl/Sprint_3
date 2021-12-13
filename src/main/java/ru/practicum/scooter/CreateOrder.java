package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateOrder extends BaseUrl{

    private final List<String> color;

    CreateOrder(List<String> color) {
        this.color = color;
    }

    public JSONObject getJson(){
        return new JSONObject()
                .put("firstName", "Naruto")
                .put("lastName", "Uchiha")
                .put("address", "Konoha, 142 apt.")
                .put("metroStation", 4)
                .put("phone", "+7 800 355 35 35")
                .put("rentTime", 5)
                .put("deliveryDate", "2020-06-06")
                .put("comment", "Saske, come back to Konoha")
                .put("color", List.of(""));
    }

    @Step("Create order")
    public Response getResponse() {
        JSONObject json = getJson();
        json.put("color", this.color);
        Allure.attachment("Передются данные заказа: ", String.valueOf(json));

        // отправляем запрос на логин курьера возвращаем true или false
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json.toString())
                .when()
                .post(getBaseUrl() +  "/api/v1/orders");
    }

}

