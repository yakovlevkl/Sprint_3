package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierLoginTest {
    /*
        1. курьер может авторизоваться;
        2. для авторизации нужно передать все обязательные поля;
        3. система вернёт ошибку, если неправильно указать логин или пароль;
        4. если какого-то поля нет, запрос возвращает ошибку;
        5. если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
        6. успешный запрос возвращает id.

        ВАЖНО!
        п.п.2,4 -- это одно и тоже
        п.п.3,5 --так же взаимо исключающие
     */
    RegisterCourier courier;
    Integer courierId;
    String courierLogin;
    String courierPassword;
    String courierFirstName;

    @Before
    public void setUp() {
        courierLogin = "NiNja";
        courierPassword = "adsdas";
        courierFirstName = "BruceLee";
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
    }

    @Test
    @DisplayName("Checking the possibility courier can login")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testLoginCourier() {
        LoginCourier courier = new LoginCourier(courierLogin, courierPassword);
        assertEquals(courier.loginCourier(),200 );
    }

    @Test
    @DisplayName("Checking for authorization, need to pass all the required fields")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testEmptyPasswordForLogin() {
        LoginCourier courier = new LoginCourier(courierLogin, "");
        assertEquals(courier.loginCourier(),400 );
    }

    @Test
    @DisplayName("Checking for authorization, need to pass all the required fields")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testEmptyLoginForLogin() {
        LoginCourier courier = new LoginCourier("", courierPassword);
        assertEquals(courier.loginCourier(),400 );
    }

    @Test
    @DisplayName("Checking for the system will return an error if the username is incorrect")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testIncorrectLoginForLogin() {
        LoginCourier courier = new LoginCourier("qwertytrwer", courierPassword);
        assertEquals(courier.loginCourier(),404 );
    }

    @Test
    @DisplayName("Checking for the system will return an error if password is incorrect")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testIncorrectPasswordForLogin() {
        LoginCourier courier = new LoginCourier(courierLogin, "qwertytrwer");
        assertEquals(courier.loginCourier(),404 );
    }

    @Test
    @DisplayName("Checking for a successful request returns an id.")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testLoginIdCourier() {
        LoginCourier courier = new LoginCourier(courierLogin, courierPassword);
        System.out.println(courier.getIdCourier());
        if (courier.getIdCourier() < 0) {
            throw new Error("id less than zero");
        }
    }

    @After
    public void rollBck(){
        DeleteCourier courier = new DeleteCourier();
        courier.deleteCourier(courierLogin, courierPassword);
    }
}
