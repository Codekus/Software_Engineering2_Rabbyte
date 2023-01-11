package de.hbrs.se.rabbyte.entities;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VerificationCodeTest {

    VerificationCode verificationCode;


    @Mock
    Person person;
    @BeforeMethod
    void setUp() {
        verificationCode = new VerificationCode();
        person = new Person();
        verificationCode.setToken("606728a3-f4dd-4a12-a75d-1411773e25b7");
        verificationCode.setId(60000017);
        verificationCode.setPerson(person);
        verificationCode.setDate(LocalDateTime.now());
    }
    @Test
    void getId() {
        assertEquals(60000017 , verificationCode.getId());
    }

    @Test
    void getUser() {
        assertNotNull(verificationCode.getPerson());
        assertTrue(verificationCode.getPerson() instanceof Person);
    }

    @Test
    void getDate() {
        assertNotNull(verificationCode.getDate());
    }

    @Test
    void getToken() {
        assertEquals("606728a3-f4dd-4a12-a75d-1411773e25b7" , verificationCode.getToken());
    }
}