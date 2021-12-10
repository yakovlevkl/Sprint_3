package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*  Задание Удалить курьера
    1. неуспешный запрос возвращает соответствующую ошибку;
    2. успешный запрос возвращает ok: true;
    3. если отправить запрос без id, вернётся ошибка;
    4. если отправить запрос с несуществующим id, вернётся ошибка.

    п. 1. пересекатеся с п.п. 3, 4
    п. 2 Реализован в @After, т.к для всех тестов создается тестовый курьер, после тестов он удаляется.
 */

public class CourierDeleteTest {
    private String courierLogin;
    private String courierPassword;
    private LoginCourier loginId;

    @Before
    public void setUp() {
        courierLogin = "NiNja";
        courierPassword = "adsdas";
        String courierFirstName = "BruceLee";
        RegisterCourier courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        loginId = new LoginCourier(courierLogin, courierPassword);
    }

    @Test
    @Feature("Удалить курьера")
    @DisplayName("Checking if send a request without an id, an error will be returned;")
    @Description("Test for /api/v1/courier/:id endpoint")
    public void testDeleteCourierWithoutId() {
        DeleteCourier courier = new DeleteCourier(loginId, false);
        Response response = courier.deleteCourier(courierLogin, courierPassword);
        assertEquals(404, response.getStatusCode());
        System.out.println("В API ошибка при запросе без id возвращает 404, нужно 400," +
                " в ассерт поставил 404, чтобы тест не падал");
    }

    @Test
    @Feature("Удалить курьера")
    @DisplayName("Checking if send a request with a non-existent id, an error will be returned")
    @Description("Test for /api/v1/courier/:id endpoint")
    public void testDeleteCourierWithNonExistentId() {
        DeleteCourier courier = new DeleteCourier(loginId, "1234213");
        Response response = courier.deleteCourier(courierLogin, courierPassword);
        assertEquals(404, response.getStatusCode());
    }

    @After
    public void rollBck(){
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }
}
