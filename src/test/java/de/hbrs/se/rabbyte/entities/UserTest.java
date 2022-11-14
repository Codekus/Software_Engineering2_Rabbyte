package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final User user = new User();

    private final int id = 100;
    private final String email = "test@gmx.de";
    private final String password = "password!";
    private final int plz = 12345;
    private final String city = "Mustercity";
    private final String country = "DE";
    private final String street = "Mustermannstraße";
    private final String  streetNumber = "1";
    private final String salt = "FFFFFFFFF";

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