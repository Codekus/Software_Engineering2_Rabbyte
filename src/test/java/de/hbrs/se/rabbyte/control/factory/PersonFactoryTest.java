package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class PersonFactoryTest {

    private static final String exceptionMessage =
            "class de.hbrs.se.rabbyte.control.factory.PersonFactoryTest cannot access a member of class de.hbrs.se.rabbyte.control.factory.PersonFactory with modifiers \"private\"";
    @Mock
    private StudentDTO studentDTO;
    @Mock
    private BusinessDTO businessDTO;
    @Mock
    private Person person;

    private static final String PASSWORD = "password";
    private static final String EMAIL_STUDENT = "max@mustermann.de";
    private static final String STUDENT_FIRST_NAME = "Max";
    private static final String STUDENT_LAST_NAME = "Mustermann";

    private static final String BUSINESS_MAIL = "musterman@gmbh.de";
    private static final String BUSINESS_NAME = "Mustermann Gmbh";
    @Test
    void createStudent()  {
        Student student;
        when(studentDTO.getPassword()).thenReturn(PASSWORD);
        when(studentDTO.getEmail()).thenReturn(EMAIL_STUDENT);
        when(studentDTO.getFirstName()).thenReturn(STUDENT_FIRST_NAME);
        when(studentDTO.getLastName()).thenReturn(STUDENT_LAST_NAME);


        student = PersonFactory.createStudent(studentDTO);

        assertTrue(student instanceof Student);
        assertEquals(128 , student.getPassword().length());
        assertEquals(EMAIL_STUDENT, student.getEmail());
        assertEquals(STUDENT_FIRST_NAME, student.getFirstName());
        assertEquals(STUDENT_LAST_NAME, student.getLastName());
        assertEquals(128 , student.getSalt().length());

    }

    @Test
    void createBusiness() {
        Business business;

        when(businessDTO.getPassword()).thenReturn(PASSWORD);
        when(businessDTO.getEmail()).thenReturn(BUSINESS_MAIL);
        when(businessDTO.getBusinessName()).thenReturn(BUSINESS_NAME);

        business = PersonFactory.createBusiness(businessDTO);
        assertEquals(128 , business.getPassword().length());
        assertEquals(BUSINESS_MAIL, business.getEmail());
        assertEquals(BUSINESS_NAME, business.getBusinessName());
        assertEquals(128 , business.getSalt().length());

    }

    @Test
    void throwIllegalAccesExceptionWhenInstancingUserFactory() throws NoSuchMethodException {
        Constructor<PersonFactory> constructor = PersonFactory.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(exceptionMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);

    }
}