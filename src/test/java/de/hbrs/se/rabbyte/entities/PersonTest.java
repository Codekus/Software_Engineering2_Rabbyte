package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    private final Person person = new Person();

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
        person.setId(100);
        person.setEmail(EMAIL);
        person.setPassword(PW);
        person.setPlz(PLZ);
        person.setCity(CITY);
        person.setCountry(COUNTRY);
        person.setStreet(STREET);
        person.setStreetNumber(STREET_NUMBER);
        person.setSalt(SALT);
    }

    @Test
    void getId() {
        assertEquals(ID, person.getId());
    }

    @Test
    void getEmail() {
        assertEquals(EMAIL, person.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals(PW, person.getPassword());
    }

    @Test
    void getPlz() {
        assertEquals(PLZ, person.getPlz());
    }

    @Test
    void getCity() {
        assertEquals(CITY, person.getCity());
    }

    @Test
    void getCountry() {
        assertEquals(COUNTRY, person.getCountry());
    }

    @Test
    void getStreet() {
        assertEquals(STREET, person.getStreet());
    }

    @Test
    void getStreetNumber() {
        assertEquals(STREET_NUMBER, person.getStreetNumber());
    }

    @Test
    void setSalt() {
        assertEquals(SALT, person.getSalt());
    }
}