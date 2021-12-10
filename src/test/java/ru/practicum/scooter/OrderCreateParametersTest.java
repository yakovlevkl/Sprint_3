package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderCreateParametersTest {
    private final String typeColor;
    private final int statusRequest;

    public OrderCreateParametersTest(String typeColor, int statusRequest) {
        this.typeColor = typeColor;
        this.statusRequest = statusRequest;
    }

    @Parameterized.Parameters
    public static Object[] getStatus() {
        return new Object[][] {
                { "src/main/resources/order_test_color_black.json", 201},
                { "src/main/resources/order_test_color_black_and_gray.json", 201},
                { "src/main/resources/order_test_no_color.json", 201},
        };
    }

    @Test
    @Feature("Создание заказа")
    @DisplayName("Checking can specify one of the colors — BLACK AND GREY")
    @Description("Test for /api/v1/orders endpoint")
    public void getFood() {
        CreateOrder order = new CreateOrder(typeColor);
        assertEquals(statusRequest, order.getResponse().statusCode());
    }
}
