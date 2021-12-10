package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

class RegisterCourier {

    /*
    метод регистрации нового курьера
    возвращает список из логина и пароля
    если регистрация не удалась, возвращает пустой список
    */

    String courierLogin;
    String courierPassword;
    private String courierFirstName;
    int responseCode;
    JsonPath responseBody;


    RegisterCourier() {
        // с помощью библиотеки RandomStringUtils генерируем логин
        // метод randomAlphabetic генерирует строку, состоящую только из букв,
        // в качестве параметра передаём длину строки
        this.courierLogin = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем пароль
        this.courierPassword = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
        this.courierFirstName = RandomStringUtils.randomAlphabetic(10);
    }

    RegisterCourier(String courierLogin, String courierPassword, String courierFirstName) {
        this.courierLogin = courierLogin;
        this.courierPassword = courierPassword;
        this.courierFirstName = courierFirstName;

    }

    @Step("Регистрация новго пользователя")
    ArrayList<String> registerNewCourierAndReturnLoginPassword(){
        // создаём список, чтобы метод мог его вернуть
        ArrayList<String> loginPass = new ArrayList<>();

        // собираем в строку тело запроса на регистрацию, подставляя в него логин, пароль и имя курьера
        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\","
                + "\"firstName\":\"" + courierFirstName + "\"}";

        // отправляем запрос на регистрацию курьера и сохраняем ответ в переменную response класса Response
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier");

        this.responseCode = response.statusCode();
        this.responseBody = response.getBody().jsonPath();
        // если регистрация прошла успешно (код ответа 201), добавляем в список логин и пароль курьера
        if ( this.responseCode == 201) {
            loginPass.add(courierLogin);
            loginPass.add(courierPassword);
        }
        Allure.attachment("возвращаем список", String.valueOf(loginPass));
        // возвращаем список
        return loginPass;

    }

}
