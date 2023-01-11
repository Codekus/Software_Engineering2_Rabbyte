package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.entities.Student;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonFactoryTest {

    private static final String EXCEPTION_MESSAGE =
            "class de.hbrs.se.rabbyte.control.factory.PersonFactoryTest cannot access a member of class de.hbrs.se.rabbyte.control.factory.PersonFactory with modifiers \"private\"";
    @Mock
    private StudentDTO studentDTO;
    @Mock
    private BusinessDTO businessDTO;
    @Mock
    private Person person;
    @Mock
    private PersonDTO personDTO;

    private static final int ID = 100;
    private static final int PLZ = 12345;
    private static final String CITY = "Mustermann";
    private static final String COUNTRY = "DE";
    private static final String STREET = "Musterstraße";
    private static final String STREETNUMBER = "1a";
    private static final String SALT = "71CB6FC62F2CA7F2B88B0E3E9ADDB047E9E45D8C180C78FE74E95D1AA54FE9FF77B990F605C4070A393501661E825AEABCC298F64C76045F0391B90C005B0CFA";

    private static final String PASSWORD = "password";
    private static final String EMAIL_STUDENT = "max@mustermann.de";
    private static final String STUDENT_FIRST_NAME = "Max";
    private static final String STUDENT_LAST_NAME = "Mustermann";

    private static final String BUSINESS_MAIL = "musterman@gmbh.de";
    private static final String BUSINESS_NAME = "Mustermann Gmbh";

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createStudent() {
        Student student;
        when(studentDTO.getPassword()).thenReturn(PASSWORD);
        when(studentDTO.getEmail()).thenReturn(EMAIL_STUDENT);
        when(studentDTO.getFirstName()).thenReturn(STUDENT_FIRST_NAME);
        when(studentDTO.getLastName()).thenReturn(STUDENT_LAST_NAME);


        student = PersonFactory.createStudent(studentDTO);

        assertTrue(student instanceof Student);
        assertEquals(128, student.getPassword().length());
        assertEquals(EMAIL_STUDENT, student.getEmail());
        assertEquals(STUDENT_FIRST_NAME, student.getFirstName());
        assertEquals(STUDENT_LAST_NAME, student.getLastName());
        assertEquals(128, student.getSalt().length());

    }

    @Test
    void createBusiness() {
        Business business;

        when(businessDTO.getPassword()).thenReturn(PASSWORD);
        when(businessDTO.getEmail()).thenReturn(BUSINESS_MAIL);
        when(businessDTO.getBusinessName()).thenReturn(BUSINESS_NAME);

        business = PersonFactory.createBusiness(businessDTO);
        assertEquals(128, business.getPassword().length());
        assertEquals(BUSINESS_MAIL, business.getEmail());
        assertEquals(BUSINESS_NAME, business.getBusinessName());
        assertEquals(128, business.getSalt().length());

    }

    @Test
    void throwIllegalAccesExceptionWhenInstancingUserFactory() throws NoSuchMethodException {
        Constructor<PersonFactory> constructor = PersonFactory.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(EXCEPTION_MESSAGE, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class, constructor::newInstance);

    }


    @Test
    void createPersonDTO() {
        mockingPerson();

        personDTO = PersonFactory.createUserDTO(person);

        assertNotNull(personDTO);
        assertTrue(personDTO instanceof PersonDTO);

        assertEquals(ID, personDTO.getId());
        assertEquals(EMAIL_STUDENT, personDTO.getEmail());
        assertEquals(PASSWORD, personDTO.getPassword());
        assertEquals(PLZ, personDTO.getPlz());
        assertEquals(CITY, personDTO.getCity());
        assertEquals(COUNTRY, personDTO.getCountry());
        assertEquals(STREET, personDTO.getStreet());
        assertEquals(STREETNUMBER, personDTO.getStreetNumber());
        assertEquals(SALT, personDTO.getSalt());
        assertEquals(false, personDTO.getEnabled());
    }

    @Test
    void enableStudent() {
        mockingPerson();


    }

    private void mockingPerson() {
        when(person.getId()).thenReturn(ID);
        when(person.getEmail()).thenReturn(EMAIL_STUDENT);
        when(person.getPassword()).thenReturn(PASSWORD);
        when(person.getPlz()).thenReturn(PLZ);
        when(person.getCity()).thenReturn(CITY);
        when(person.getCountry()).thenReturn(COUNTRY);
        when(person.getStreet()).thenReturn(STREET);
        when(person.getStreetNumber()).thenReturn(STREETNUMBER);
        when(person.getSalt()).thenReturn(SALT);
        when(person.getEnabled()).thenReturn(false);
    }

}