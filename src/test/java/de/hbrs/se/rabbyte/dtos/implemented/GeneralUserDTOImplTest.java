package de.hbrs.se.rabbyte.dtos.implemented;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneralUserDTOImplTest {

    GeneralUserDTOImpl generalUserDTO = new GeneralUserDTOImpl();
    private final int id = 100;
    private final String email = "max@mustermann.de";
    private final String password = "password";
    private final int plz = 12345;
    private final String city = "Musterdorf";
    private final String country = "DE";
    private final String street = "Musterstra0e";
    private final String streetNumber = "5";

    @BeforeEach
    void setUp() {
        generalUserDTO.setId(100);
        generalUserDTO.setEmail(email);
        generalUserDTO.setPassword(password);
        generalUserDTO.setPlz(plz);
        generalUserDTO.setCity(city);
        generalUserDTO.setCountry(country);
        generalUserDTO.setStreet(street);
        generalUserDTO.setStreetNumber(streetNumber);
    }

    @Test
    void getId() {
        assertEquals(id , generalUserDTO.getId());
    }

    @Test
    void getEmail() {
        assertEquals(email , generalUserDTO.getEmail());

    }

    @Test
    void getPassword() {
        assertEquals(password , generalUserDTO.getPassword());
    }

    @Test
    void getPlz() {
        assertEquals(plz , generalUserDTO.getPlz());

    }

    @Test
    void getCity() {
        assertEquals(city , generalUserDTO.getCity());

    }

    @Test
    void getCountry() {
        assertEquals(country , generalUserDTO.getCountry());

    }

    @Test
    void getStreet() {
        assertEquals(street , generalUserDTO.getStreet());

    }

    @Test
    void getStreetNumber() {
        assertEquals(streetNumber , generalUserDTO.getStreetNumber());

    }
}