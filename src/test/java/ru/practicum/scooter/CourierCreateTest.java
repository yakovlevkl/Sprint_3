package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class CourierCreateTest {
    private RegisterCourier courier;
    private String courierLogin;
    private String courierPassword;
    private String courierFirstName;

    @Before
    public void setUp() {
        this.courierLogin = "NiNja";
        this.courierPassword = "adsdas";
        this.courierFirstName = "BruceLee";
    }

    @Test
    @Feature("Создание курьера")
    @DisplayName("Checking the possibility of creating a courier")
    @Description("Test for /api/v1/courier endpoint")
    public void testCanCreateCourier() {
        courier = new RegisterCourier();
        ArrayList<String> loginPass = courier.registerNewCourierAndReturnLoginPassword();
        assertFalse(loginPass.isEmpty());
        courierLogin = courier.courierLogin;
        courierPassword = courier.courierPassword;
        LoginCourier loginId = new LoginCourier(courierLogin, courierPassword);
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }

    @Test
    @Feature("Создание курьера")
    @DisplayName("Checking cannot create two identical couriers")
    @Description("Test for /api/v1/courier endpoint")
    public void testCantCreateTwoIdenticalCouriers() {
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        courier.registerNewCourierAndReturnLoginPassword();
        assertTrue(courier.registerNewCourierAndReturnLoginPassword().isEmpty());
        LoginCourier loginId = new LoginCourier(courierLogin, courierPassword);
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }

    @Test
    @Feature("Создание курьера")
    @DisplayName("Checking to create a courier, need to pass all the required fields to the handle")
    @Description("Test for /api/v1/courier endpoint")
    public void testCreateCouriersWithoutLogin() {
        courier = new RegisterCourier("", courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertTrue(courier.registerNewCourierAndReturnLoginPassword().isEmpty());
    }

    @Test
    @Feature("Создание курьера")
    @DisplayName("Checking successful request returns ok: true")
    @Description("Test for /api/v1/courier endpoint")
    public void testSuccessfulRequestReturnsTrue() {
        courier = new RegisterCourier(
                this.courierLogin,
                this.courierPassword,
                this.courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertTrue(courier.responseBody.getBoolean("ok"));
    }

    @Test
    @Feature("Создание курьера")
    @DisplayName("Checking if one of the fields is missing, the request returns an error")
    @Description("Test for /api/v1/courier endpoint")
    public void testResponseCodeIfOneFieldsIsMissing() {
        courier = new RegisterCourier("", courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(courier.responseCode, 400);
        LoginCourier loginId = new LoginCourier(courierLogin, courierPassword);
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }

    @Test
    @Feature("Создание курьера")
    @DisplayName("Checking if create a user with a username that already exists, an error is returned")
    @Description("Test for /api/v1/courier endpoint")
    public void testResponseCodeIfUserAlreadyExists() {
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(courier.responseCode, 409);
        LoginCourier loginId = new LoginCourier(courierLogin, courierPassword);
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }

    @After
    public void rollBck(){

    }
}
