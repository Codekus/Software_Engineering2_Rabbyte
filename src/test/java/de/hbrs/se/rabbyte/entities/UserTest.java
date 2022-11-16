package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private final User user = new User();

    private static final int ID = 100;
    private static final String EMAIL = "test@gmx.de";
    private static final String PW = "password!";
    private static final int PLZ = 12345;
    private static final String CITY = "Mustercity";
    private static final String COUNTRY = "DE";
    private static final String STREET = "Mustermannstraße";
    private static final String STREET_NUMBER = "1";
    private static final String SALT = "FFFFFFFFF";

    @BeforeEach
    void setUp() {
        user.setId(100);
        user.setEmail(EMAIL);
        user.setPassword(PW);
        user.setPlz(PLZ);
        user.setCity(CITY);
        user.setCountry(COUNTRY);
        user.setStreet(STREET);
        user.setStreetNumber(STREET_NUMBER);
        user.setSalt(SALT);
    }

    @Test
    void getId() {
        assertEquals(ID, user.getId());
    }

    @Test
    void getEmail() {
        assertEquals(EMAIL, user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals(PW, user.getPassword());
    }

    @Test
    void getPlz() {
        assertEquals(PLZ, user.getPlz());
    }

    @Test
    void getCity() {
        assertEquals(CITY, user.getCity());
    }

    @Test
    void getCountry() {
        assertEquals(COUNTRY, user.getCountry());
    }

    @Test
    void getStreet() {
        assertEquals(STREET, user.getStreet());
    }

    @Test
    void getStreetNumber() {
        assertEquals(STREET_NUMBER, user.getStreetNumber());
    }

    @Test
    void setSalt() {
        assertEquals(SALT, user.getSalt());
    }
}