package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationStudentDTOImplTest {

    private RegistrationStudentDTOImpl registrationStudentDTO;

    private static final String STUDENT_REPEAT_PW = "12345";

    @Mock
    private StudentDTOImpl studentDTO;

    @BeforeMethod
    void setUp() {

        studentDTO = new StudentDTOImpl();
        registrationStudentDTO = new RegistrationStudentDTOImpl(studentDTO , STUDENT_REPEAT_PW);
    }

    @Test
    void getStudentDTO() {
        assertTrue( registrationStudentDTO.getStudentDTO() instanceof StudentDTO);
        assertNotNull(registrationStudentDTO.getStudentDTO());
    }
}