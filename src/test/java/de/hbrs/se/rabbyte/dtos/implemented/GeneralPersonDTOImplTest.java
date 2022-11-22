package de.hbrs.se.rabbyte.dtos.implemented;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneralPersonDTOImplTest {

    PersonDTOImpl personDTO = new PersonDTOImpl();
    private static final int ID = 100;
    private static final String EMAIL = "max@mustermann.de";
    private static final String PASSWORD = "password";
    private static final int PLZ = 12345;
    private static final String CITY = "Musterdorf";
    private static final String COUNTRY = "DE";
    private static final String STREET = "Musterstra0e";
    private static final String STREET_NUMBER = "5";
    private static final String SALT = "5EFA521906785E5E37F2BED9C06712306FDF8CF21A15AB80E58DBF0FDF3161FC3198969B7550B891271CFDA08D5272E08FC1FB47A759CA6526EA0D15858DC56E";
    private static final boolean ENABLED = false;

    @BeforeEach
    void setUp() {
        personDTO.setId(100);
        personDTO.setEmail(EMAIL);
        personDTO.setPassword(PASSWORD);
        personDTO.setPlz(PLZ);
        personDTO.setCity(CITY);
        personDTO.setCountry(COUNTRY);
        personDTO.setStreet(STREET);
        personDTO.setStreetNumber(STREET_NUMBER);
        personDTO.setSalt(SALT);
        personDTO.setEnabled(ENABLED);
    }

    @Test
    void getId() {
        assertEquals(ID, personDTO.getId());
    }

    @Test
    void getEmail() {
        assertEquals(EMAIL, personDTO.getEmail());

    }

    @Test
    void getPassword() {
        assertEquals(PASSWORD, personDTO.getPassword());
    }

    @Test
    void getPlz() {
        assertEquals(PLZ, personDTO.getPlz());

    }

    @Test
    void getCity() {
        assertEquals(CITY, personDTO.getCity());

    }

    @Test
    void getCountry() {
        assertEquals(COUNTRY, personDTO.getCountry());

    }

    @Test
    void getStreet() {
        assertEquals(STREET, personDTO.getStreet());

    }

    @Test
    void getStreetNumber() {
        assertEquals(STREET_NUMBER, personDTO.getStreetNumber());
    }

    @Test
    void getSalt() {
        assertTrue(personDTO.getSalt().length() == 128);
        assertEquals(SALT , personDTO.getSalt());
    }
}