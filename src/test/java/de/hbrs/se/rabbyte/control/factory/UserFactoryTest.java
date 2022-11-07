package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserFactoryTest {

    private static final String exceptionMessage =
            "class de.hbrs.se.rabbyte.control.factory.UserFactoryTest cannot access a member of class de.hbrs.se.rabbyte.control.factory.UserFactory with modifiers \"private\"";
    @Mock
    private StudentDTO studentDTO;
    @Mock
    private BusinessDTO businessDTO;
    @Mock
    private User user;

    private static final String password = "password";
    private static final String emailStudent = "max@mustermann.de";
    private static final String studentFirstName = "Max";
    private static final String studentLastName = "Mustermann";

    private static final String businessMail = "musterman@gmbh.de";
    private static final String businessName = "Mustermann Gmbh";
    @Test
    void createStudent()  {
        Student student;
        when(studentDTO.getPassword()).thenReturn(password);
        when(studentDTO.getEmail()).thenReturn(emailStudent);
        when(studentDTO.getFirstName()).thenReturn(studentFirstName);
        when(studentDTO.getLastName()).thenReturn(studentLastName);


        student = UserFactory.createStudent(studentDTO);

        assertTrue(student instanceof Student);
        assertEquals(128 , student.getPassword().length());
        assertEquals(emailStudent , student.getEmail());
        assertEquals(studentFirstName , student.getFirstName());
        assertEquals(studentLastName , student.getLastName());
        assertEquals(128 , student.getSalt().length());

    }

    @Test
    void createBusiness() {
        Business business;

        when(businessDTO.getPassword()).thenReturn(password);
        when(businessDTO.getEmail()).thenReturn(businessMail);
        when(businessDTO.getBusinessName()).thenReturn(businessName);

        business = UserFactory.createBusiness(businessDTO);
        assertEquals(128 , business.getPassword().length());
        assertEquals(businessMail , business.getEmail());
        assertEquals(businessName , business.getBusinessName());
        assertEquals(128 , business.getSalt().length());

    }

    @Test
    void throwIllegalAccesExceptionWhenInstancingUserFactory() throws NoSuchMethodException {
        Constructor<UserFactory> constructor = UserFactory.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(exceptionMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);

    }
}