package de.hbrs.se.rabbyte.dtos.implemented;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneralUserDTOImplTest {

    PersonDTOImpl generalUserDTO = new PersonDTOImpl();
    private static final int ID = 100;
    private static final String EMAIL = "max@mustermann.de";
    private static final String PASSWORD = "password";
    private static final int PLZ = 12345;
    private static final String CITY = "Musterdorf";
    private static final String COUNTRY = "DE";
    private static final String STREET = "Musterstra0e";
    private static final String STREET_NUMBER = "5";

    @BeforeEach
    void setUp() {
        generalUserDTO.setId(100);
        generalUserDTO.setEmail(EMAIL);
        generalUserDTO.setPassword(PASSWORD);
        generalUserDTO.setPlz(PLZ);
        generalUserDTO.setCity(CITY);
        generalUserDTO.setCountry(COUNTRY);
        generalUserDTO.setStreet(STREET);
        generalUserDTO.setStreetNumber(STREET_NUMBER);
    }

    @Test
    void getId() {
        assertEquals(ID, generalUserDTO.getId());
    }

    @Test
    void getEmail() {
        assertEquals(EMAIL, generalUserDTO.getEmail());

    }

    @Test
    void getPassword() {
        assertEquals(PASSWORD, generalUserDTO.getPassword());
    }

    @Test
    void getPlz() {
        assertEquals(PLZ, generalUserDTO.getPlz());

    }

    @Test
    void getCity() {
        assertEquals(CITY, generalUserDTO.getCity());

    }

    @Test
    void getCountry() {
        assertEquals(COUNTRY, generalUserDTO.getCountry());

    }

    @Test
    void getStreet() {
        assertEquals(STREET, generalUserDTO.getStreet());

    }

    @Test
    void getStreetNumber() {
        assertEquals(STREET_NUMBER, generalUserDTO.getStreetNumber());

    }
}