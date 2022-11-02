package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.UserFactory;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;
import de.hbrs.se.rabbyte.dtos.implemented.BusinessDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationResultDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationStudentDTOImpl;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;
import de.hbrs.se.rabbyte.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegistrationControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationControl.class);

    private RegistrationResultDTO registrationResultDTO;

    @Autowired
    GeneralUserRepository generalUserRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BusinessRepository businessRepository;


    public RegistrationResultDTO registerStudent(RegistrationStudentDTOImpl registrationStudentDTO) {
        registrationResultDTO = new RegistrationResultDTOImpl();
        Student newStudent = UserFactory.createStudent(registrationStudentDTO.getStudentDTO());

        if(inspectIfEmailIsAlreadyInUse(registrationStudentDTO.getStudentDTO().getEmail())) {
            registrationResultDTO.setReason("Email in use");
        }

        inspectIfSamePassword(registrationStudentDTO.getRepeatPassword() , registrationStudentDTO.getStudentDTO().getPassword());

        validateEmailName(registrationStudentDTO.getStudentDTO().getEmail());
        validateFirstName(registrationStudentDTO.getStudentDTO().getFirstName());
        validateLastName(registrationStudentDTO.getStudentDTO().getLastName());
        if(registrationResultDTO.getReasons().isEmpty()) {
            this.studentRepository.save(newStudent);
            registrationResultDTO.setRegistrationResult(true);
        } else {
            registrationResultDTO.setRegistrationResult(false);
        }
        return registrationResultDTO;
    }

    public RegistrationResultDTO registerBusiness(BusinessDTOImpl businessDTO) {

        registrationResultDTO = new RegistrationResultDTOImpl();

        Business newBusiness = UserFactory.createBusiness(businessDTO);
        this.businessRepository.save(newBusiness);

        return registrationResultDTO;
    }

    public boolean inspectIfEmailIsAlreadyInUse(String email) {
        GeneralUserDTO generalUser = generalUserRepository.findByEmail(email);

        return (generalUser != null && generalUser.getId() > 0);
    }

    public void inspectIfSamePassword(String password , String repeatPassword) {

        if(!password.equals(repeatPassword)) {
            registrationResultDTO.setReason("Passwords are different");
        }
    }

    public void validateEmailName(String email) {
         String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if(!Pattern.compile(regexPattern).matcher(email).matches()) {
            registrationResultDTO.setReason("Invalid Email");
        }
    }

    public void validateFirstName(String firstName) {
        if(!firstName.matches( "[A-Z][a-z]*")){
            registrationResultDTO.setReason("Invalid First Name");
        }
    }

    public void validateLastName(String lastName) {
        if(!lastName.matches( "[A-Z][a-z]*")){
            registrationResultDTO.setReason("Invalid Last Name");
        }
    }

    public void validateBusinessName() {

    }
}
