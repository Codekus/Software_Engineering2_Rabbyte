package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.entities.Person;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VerificationCodeDTOImplTest {

    private int id;
    @Mock
    private Person person;
    private LocalDateTime date;
    private String token;

    private VerificationCodeDTOImpl verificationCodeDTO;

    @BeforeMethod
    void setUp() {

        id = 100;
        person = new Person();
        date = LocalDateTime.now();
        token = UUID.randomUUID().toString();
        verificationCodeDTO = new VerificationCodeDTOImpl();
        verificationCodeDTO.setId(id);
        verificationCodeDTO.setPerson(person);
        verificationCodeDTO.setDate(date);
        verificationCodeDTO.setToken(token);


    }

    @Test
    void getId() {

        assertEquals(id , verificationCodeDTO.getId());
    }

    @Test
    void getPerson() {
        assertNotNull(verificationCodeDTO.getPerson());
        assertTrue(verificationCodeDTO.getPerson() instanceof  Person);
    }

    @Test
    void getDate() {
        assertEquals(date , verificationCodeDTO.getDate());
    }

    @Test
    void getToken() {
        assertEquals(token , verificationCodeDTO.getToken());
    }
}