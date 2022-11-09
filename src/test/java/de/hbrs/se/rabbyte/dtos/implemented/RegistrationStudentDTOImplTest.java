package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationStudentDTOImplTest {

    private RegistrationStudentDTOImpl registrationStudentDTO;

    private String password = "password";

    @Mock
    private StudentDTOImpl studentDTO;

    @BeforeEach
    void setUp() {

        studentDTO = new StudentDTOImpl();
        registrationStudentDTO = new RegistrationStudentDTOImpl(studentDTO , password);
    }

    @Test
    void getStudentDTO() {
        assertTrue( registrationStudentDTO.getStudentDTO() instanceof StudentDTO);
        assertNotNull(registrationStudentDTO.getStudentDTO());
    }
}