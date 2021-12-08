package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderCreateTest {
    /*
        Создание заказа
        Проверь, что когда создаёшь заказ:
        1. можно указать один из цветов — BLACK или GREY;
        2. можно указать оба цвета;
        3. можно совсем не указывать цвет;
        4. тело ответа содержит track.
     */

    @Before
    public void setUp() {
    }

    @Test
    @DisplayName("Checking can specify one of the colors — BLACK AND GREY")
    @Description("Test for /api/v1/orders endpoint")
    public void testOrderGetTrack() {
        String json_file = "src/main/resources/order_test_no_color.json";
        CreateOrder order = new CreateOrder(json_file);
        System.out.println(order.getResponse().getBody().jsonPath().getInt("track"));
        if (order.getResponse().getBody().jsonPath().getInt("track") < 0) {
            throw new Error("track less than zero");
        }
    }

    @After
    public void rollBck(){
    }
}
