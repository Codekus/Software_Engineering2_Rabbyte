package de.hbrs.se.rabbyte.control.factory;


import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.util.CryptographyUtil;
import de.hbrs.se.rabbyte.util.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFactory.class);

    private UserFactory() {
        throw new IllegalStateException(Globals.IllegalState.MESSAGE_FACTORY);
    }
    public static Student createStudent(StudentDTO studentDTO)  {
        Student student = new Student();
        try {
            byte[] salt = CryptographyUtil.generateSalt();
            student.setSalt(CryptographyUtil.toHex(salt));
            student.setPassword(CryptographyUtil.encryptPassword(studentDTO.getPassword() , salt));
        } catch (Exception exception) {
            LOGGER.info(exception.getMessage());
        }
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

        try {
            byte[] salt = CryptographyUtil.generateSalt();
            business.setSalt(CryptographyUtil.toHex(salt));
            business.setPassword(CryptographyUtil.encryptPassword(businessDTO.getPassword() , salt));
        } catch (Exception  exception) {
            LOGGER.info(exception.getMessage());
        }
        business.setId(businessDTO.getId());
        business.setEmail(businessDTO.getEmail());
        business.setBusinessName(businessDTO.getBusinessName());

        return business;
    }
}
