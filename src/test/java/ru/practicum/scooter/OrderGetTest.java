package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderGetTest {
    private String orderTrack;

    @Before
    public void setUp() {
        orderTrack = String.valueOf(new CreateOrder(List.of("BLACK")).getResponse().getBody().jsonPath().getInt("track"));
    }

    @Test
    @Feature("Получить заказ по его номеру")
    @DisplayName("успешный запрос возвращает объект с заказом")
    @Description("Test for /api/v1/orders/track endpoint")
    public void testGetOrder() {
        GetOrderByNumber response = new GetOrderByNumber(orderTrack);
        assertEquals(200, response.getOrder().getStatusCode());
    }

    @Test
    @Feature("Получить заказ по его номеру")
    @DisplayName("запрос без номера заказа возвращает ошибку")
    @Description("Test for /api/v1/orders/track endpoint")
    public void testGetOrderWithEmptyOrderTrack() {
        GetOrderByNumber response = new GetOrderByNumber("");
        assertEquals(400, response.getOrder().getStatusCode());
    }

    @Test
    @Feature("Получить заказ по его номеру")
    @DisplayName("запрос без номера заказа возвращает ошибку")
    @Description("Test for /api/v1/orders/track endpoint")
    public void testGetOrderWithNonExistentOrderTrack() {
        GetOrderByNumber response = new GetOrderByNumber("1233214");
        assertEquals(404, response.getOrder().getStatusCode());
    }

    @After
    public void rollBck(){

    }
}
