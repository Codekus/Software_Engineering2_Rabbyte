package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class VerificationCodeTest {

    VerificationCode verificationCode;


    @Mock
    Person person;
    @BeforeEach
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