package ru.practicum.scooter;

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
    public void getFood() {
        CreateOrder order = new CreateOrder(typeColor);
        order.getResponse().statusCode();
        assertEquals(statusRequest, order.getResponse().statusCode());
    }
}
