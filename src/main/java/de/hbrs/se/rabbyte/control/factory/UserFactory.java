package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.control.RegistrationControl;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.util.CryptographyUtil;
import de.hbrs.se.rabbyte.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

public class UserFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFactory.class);

    static CryptographyUtil cryptographyUtil = new CryptographyUtil();
    private UserFactory() {
        throw new IllegalStateException("Factory Class");
    }
    public static Student createStudent(StudentDTO studentDTO)  {
        Student student = new Student();
        try {
            byte[] salt = CryptographyUtil.generateSalt();
            student.setSalt(CryptographyUtil.toHex(salt));
            student.setPassword(cryptographyUtil.encryptPassword(studentDTO.getPassword() , salt));
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.info(exception.getMessage());
        }

        student.setEmail(studentDTO.getEmail());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        return student;
    }

    public static Business createBusiness(BusinessDTO businessDTO) {
        Business business = new Business();

        try {
            byte[] salt = CryptographyUtil.generateSalt();
            business.setSalt(CryptographyUtil.toHex(salt));
            business.setPassword(cryptographyUtil.encryptPassword(businessDTO.getPassword() , salt));
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.info(exception.getMessage());
        }
        business.setEmail(businessDTO.getEmail());
        business.setBusinessName(businessDTO.getBusinessName());

        return business;
    }
}
