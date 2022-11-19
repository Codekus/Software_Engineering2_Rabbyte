package de.hbrs.se.rabbyte.control.factory;


import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.util.CryptographyUtil;
import de.hbrs.se.rabbyte.util.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PersonFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonFactory.class);

    private PersonFactory() {
        throw new IllegalStateException(Globals.IllegalState.MESSAGE_FACTORY);
    }

    public static Student createStudent(StudentDTO studentDTO)  {
        Student student = new Student();

        passwordAndSaltGeneration(studentDTO, student);

        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setFaculty(studentDTO.getFaculty());

        student.setEmail(studentDTO.getEmail());
        student.setPlz(studentDTO.getPlz());
        student.setCity(studentDTO.getCity());
        student.setCountry(studentDTO.getCountry());
        student.setStreet(studentDTO.getStreet());
        student.setStreetNumber(studentDTO.getStreetNumber());
        return student;
    }

    public static Business createBusiness(BusinessDTO businessDTO) {
        Business business = new Business();

        passwordAndSaltGeneration(businessDTO, business);
        business.setEmail(businessDTO.getEmail());
        business.setBusinessName(businessDTO.getBusinessName());
        business.setPlz(businessDTO.getPlz());
        business.setCity(businessDTO.getCity());
        business.setCountry(businessDTO.getCountry());
        business.setStreet(businessDTO.getStreet());
        business.setStreetNumber(businessDTO.getStreetNumber());


        return business;
    }

    private static void passwordAndSaltGeneration(PersonDTO businessDTO, Person person) {
        try {
            byte[] salt = CryptographyUtil.generateSalt();
            person.setSalt(CryptographyUtil.toHex(salt));
            person.setPassword(CryptographyUtil.encryptPassword(businessDTO.getPassword() , salt));
        } catch (Exception  exception) {
            LOGGER.info(exception.getMessage());
        }
        person.setId(businessDTO.getId());
    }
}
