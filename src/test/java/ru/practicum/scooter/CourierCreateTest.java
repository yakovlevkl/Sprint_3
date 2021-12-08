package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class CourierCreateTest {
    RegisterCourier courier;
    ArrayList<String> loginPass;
    String courierLogin;
    String courierPassword;
    String courierFirstName;

    @Before
    public void setUp() {
        courierLogin = "NiNja";
        courierPassword = "adsdas";
        courierFirstName = "BruceLee";
    }

    @Test
    @DisplayName("Checking the possibility of creating a courier")
    @Description("Test for /api/v1/courier endpoint")
    public void testCanCreateCourier() {
        courier = new RegisterCourier();
        loginPass = courier.registerNewCourierAndReturnLoginPassword();
        assertFalse(loginPass.isEmpty());
        courierLogin = courier.courierLogin;
        courierPassword = courier.courierPassword;
    }

    @Test
    @DisplayName("Checking cannot create two identical couriers")
    @Description("Test for /api/v1/courier endpoint")
    public void testCantCreateTwoIdenticalCouriers() {
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        courier.registerNewCourierAndReturnLoginPassword();
        assertTrue(courier.registerNewCourierAndReturnLoginPassword().isEmpty());
    }

    @Test
    @DisplayName("Checking to create a courier, need to pass all the required fields to the handle")
    @Description("Test for /api/v1/courier endpoint")
    public void testCreateCouriersWithoutLogin() {
        courierLogin = "";
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertTrue(courier.registerNewCourierAndReturnLoginPassword().isEmpty());
    }

    @Test
    @DisplayName("Checking the request returns the correct response code")
    @Description("Test for /api/v1/courier endpoint")
    public void testReturnsCorrectResponseCode() {
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(courier.responseCode, 201);
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(courier.responseCode, 409);
        courier = new RegisterCourier("", courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(courier.responseCode, 400);
    }

    @Test
    @DisplayName("Checking successful request returns ok: true")
    @Description("Test for /api/v1/courier endpoint")
    public void testSuccessfulRequestReturnsTrue() {
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertTrue(courier.responseBody.getBoolean("ok"));
    }

    @Test
    @DisplayName("Checking if one of the fields is missing, the request returns an error")
    @Description("Test for /api/v1/courier endpoint")
    public void testResponseCodeIfOneFieldsIsMissing() {
        courier = new RegisterCourier("", courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(courier.responseCode, 400);
    }

    @Test
    @DisplayName("Checking if you create a user with a username that already exists, an error is returned")
    @Description("Test for /api/v1/courier endpoint")
    public void testResponseCodeIfUserAlreadyExists() {
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(courier.responseCode, 409);
    }

    @After
    public void rollBck(){
        DeleteCourier courier = new DeleteCourier();
        courier.deleteCourier(courierLogin, courierPassword);
    }
}
