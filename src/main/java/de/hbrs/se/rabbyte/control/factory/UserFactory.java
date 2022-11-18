package de.hbrs.se.rabbyte.control.factory;


import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.dtos.implemented.GeneralUserDTOImpl;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.User;
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
        student.setEnabled(studentDTO.getEnabled());
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

    private static void passwordAndSaltGeneration(GeneralUserDTO businessDTO, User user) {
        try {
            byte[] salt = CryptographyUtil.generateSalt();
            user.setSalt(CryptographyUtil.toHex(salt));
            user.setPassword(CryptographyUtil.encryptPassword(businessDTO.getPassword() , salt));
        } catch (Exception  exception) {
            LOGGER.info(exception.getMessage());
        }
        user.setId(businessDTO.getId());
    }

    public static User enableUser(GeneralUserDTO generalUserDTO) {
        User user = UserFactory.createUser(generalUserDTO);
        user.setEnabled(true);
        return user;

    }

    public static User createUser(GeneralUserDTO generalUserDTO) {
        User user = new User();
        user.setId(generalUserDTO.getId());
        user.setEmail(generalUserDTO.getEmail());
        user.setPassword(generalUserDTO.getPassword());
        user.setPlz(generalUserDTO.getPlz());
        user.setCity(generalUserDTO.getCity());
        user.setCountry(generalUserDTO.getCountry());
        user.setStreet(generalUserDTO.getStreet());
        user.setStreetNumber(generalUserDTO.getStreetNumber());
        user.setSalt(generalUserDTO.getSalt());
        user.setEnabled(generalUserDTO.getEnabled());

        return user;
    }

    public static GeneralUserDTO createUserDTO(User user) {
        GeneralUserDTOImpl generalUserDTO = new GeneralUserDTOImpl();

        generalUserDTO.setId(user.getId());
        generalUserDTO.setEmail(user.getEmail());
        generalUserDTO.setPassword(user.getPassword());
        generalUserDTO.setPlz(user.getPlz());
        generalUserDTO.setCity(user.getCity());
        generalUserDTO.setCountry(user.getCountry());
        generalUserDTO.setStreet(user.getStreet());
        generalUserDTO.setStreetNumber(user.getStreetNumber());
        generalUserDTO.setSalt(user.getSalt());
        generalUserDTO.setEnabled(user.getEnabled());

        return generalUserDTO;
    }
}
