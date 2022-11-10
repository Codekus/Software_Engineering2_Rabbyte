package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user = new User();

    private int id = 100;
    private String email = "test@gmx.de";
    private String password = "password!";
    private int plz = 12345;
    private String city = "Mustercity";
    private String country = "DE";
    private String street = "Mustermannstraße";
    private String  streetNumber = "1";
    private String salt = "FFFFFFFFF";

    @BeforeEach
    void setUp() {
        user.setId(100);
        user.setEmail(email);
        user.setPassword(password);
        user.setPlz(plz);
        user.setCity(city);
        user.setCountry(country);
        user.setStreet(street);
        user.setStreetNumber(streetNumber);
        user.setSalt(salt);
    }

    @Test
    void getId() {
        assertEquals(id , user.getId());
    }

    @Test
    void getEmail() {
        assertEquals(email , user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals(password , user.getPassword());
    }

    @Test
    void getPlz() {
        assertEquals(plz , user.getPlz());
    }

    @Test
    void getCity() {
        assertEquals(city , user.getCity());
    }

    @Test
    void getCountry() {
        assertEquals(country , user.getCountry());
    }

    @Test
    void getStreet() {
        assertEquals( street , user.getStreet());
    }

    @Test
    void getStreetNumber() {
        assertEquals(streetNumber , user.getStreetNumber());
    }

    @Test
    void setSalt() {
        assertEquals(salt , user.getSalt());
    }
}