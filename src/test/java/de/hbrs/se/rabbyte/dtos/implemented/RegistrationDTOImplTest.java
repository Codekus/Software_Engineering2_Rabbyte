package de.hbrs.se.rabbyte.dtos.implemented;


import de.hbrs.se.rabbyte.entities.Person;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationDTOImplTest {

    private final RegistrationDTOImpl registrationDTO = new RegistrationDTOImpl();
    private static final String REGISTRATION_REPEAT_PW = "12345";
    @Mock
    private Person person;
    @BeforeMethod
    void setup() {
        registrationDTO.setRepeatPassword(REGISTRATION_REPEAT_PW);
        person = new Person();
        registrationDTO.setPerson(person);
    }
    @Test
    void getPassword() {
        assertEquals(REGISTRATION_REPEAT_PW, registrationDTO.getRepeatPassword());
    }

    @Test
    void getPerson() {
        assertNotNull(registrationDTO.getPerson());
        assertTrue(registrationDTO.getPerson() instanceof Person);
    }
}