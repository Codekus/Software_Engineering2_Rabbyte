package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class VerificationCodeTest {

    VerificationCode verificationCode;


    @Mock
    User user;
    @BeforeEach
    void setUp() {
        verificationCode = new VerificationCode();
        user = new User();
        verificationCode.setToken("606728a3-f4dd-4a12-a75d-1411773e25b7");
        verificationCode.setId(60000017);
        verificationCode.setUser(user);
        verificationCode.setDate(new Date());
    }
    @Test
    void getId() {
        assertEquals(60000017 , verificationCode.getId());
    }

    @Test
    void getUser() {
        assertNotNull(verificationCode.getUser());
        assertTrue(verificationCode.getUser() instanceof User);
    }

    @Test
    void getDate() {
        assertEquals(new Date().toString() , verificationCode.getDate().toString());
        assertTrue(verificationCode.getDate() instanceof  Date);
    }

    @Test
    void getToken() {
        assertEquals("606728a3-f4dd-4a12-a75d-1411773e25b7" , verificationCode.getToken());
    }
}