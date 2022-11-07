package de.hbrs.se.rabbyte.dtos.implemented;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralUserDTOImplTest {

    GeneralUserDTOImpl generalUserDTO = new GeneralUserDTOImpl();
    private int id = 100;
    private String email = "max@mustermann.de";
    private String password = "password";
    private int plz = 12345;
    private String city = "Musterdorf";
    private String country = "DE";
    private String street = "Musterstra0e";
    private int streetNumber = 5;

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