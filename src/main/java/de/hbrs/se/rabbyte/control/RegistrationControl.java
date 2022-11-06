package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.UserFactory;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationBusinessDTOImpl;
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

import java.util.regex.Matcher;
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

        try {
        registrationResultDTO = new RegistrationResultDTOImpl();

        if(inspectIfEmailIsAlreadyInUse(registrationStudentDTO.getStudentDTO().getEmail())) {
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.EMAIL_IN_USE);
        }
        inspectIfPasswordIsTooShort(registrationStudentDTO.getStudentDTO().getPassword());
        inspectIfRepeatPasswordIsTooShort(registrationStudentDTO.getRepeatPassword());
        inspectIfSamePassword(registrationStudentDTO.getRepeatPassword() , registrationStudentDTO.getStudentDTO().getPassword());

        validateEmailName(registrationStudentDTO.getStudentDTO().getEmail());
        validateFirstName(registrationStudentDTO.getStudentDTO().getFirstName());
        validateLastName(registrationStudentDTO.getStudentDTO().getLastName());
        if(registrationResultDTO.getReasons().isEmpty()) {

            Student newStudent = UserFactory.createStudent(registrationStudentDTO.getStudentDTO());
            this.studentRepository.save(newStudent);
            registrationResultDTO.setRegistrationResult(true);
        } else {
            registrationResultDTO.setRegistrationResult(false);
        }} catch (Exception exception) {
            registrationResultDTO.setRegistrationResult(false);
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.GENERAL_ERROR);
            LOGGER.info("INFO:" ,  exception.getMessage());
        }
        return registrationResultDTO;

    }



    public RegistrationResultDTO registerBusiness(RegistrationBusinessDTOImpl registrationBusinessDTO) {

        try {
            registrationResultDTO = new RegistrationResultDTOImpl();
            inspectIfSamePassword(registrationBusinessDTO.getBusinessDTO().getPassword(), registrationBusinessDTO.getRepeatPassword());

            if (inspectIfEmailIsAlreadyInUse(registrationBusinessDTO.getBusinessDTO().getEmail())) {
                registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.EMAIL_IN_USE);
            }
            Business newBusiness = UserFactory.createBusiness(registrationBusinessDTO.getBusinessDTO());

            if (registrationResultDTO.getReasons().isEmpty()) {

                this.businessRepository.save(newBusiness);
                registrationResultDTO.setRegistrationResult(true);
            } else {
                registrationResultDTO.setRegistrationResult(false);
            }
        } catch(Exception exception) {
            registrationResultDTO.setRegistrationResult(false);
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.GENERAL_ERROR);
            LOGGER.info("INFO" , exception.getMessage());
        }

        return registrationResultDTO;
    }

    public boolean inspectIfEmailIsAlreadyInUse(String email) {
        GeneralUserDTO generalUser = generalUserRepository.findByEmail(email);

        return (generalUser != null && generalUser.getId() > 0);
    }

    public void inspectIfSamePassword(String password , String repeatPassword) {

        if(!password.equals(repeatPassword)) {
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.PASSWORD_DIFFERENT);
        }
    }

    public void validateEmailName(String email) {


        Pattern pattern =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) {
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.INVALID_EMAIL);
        }
    }

    public void validateFirstName(String firstName) {
        if(!firstName.matches( "[A-Z][a-z]*")){
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.INVALID_FIRST_NAME);
        }
    }

    public void validateLastName(String lastName) {
        if(!lastName.matches( "[A-Z][a-z]*")){
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.INVALID_LAST_NAME);
        }
    }

    public void validateBusinessName(String businessName) {
        if(!businessName.matches("^(?!\\s)(?!.*\\s$)(?=.*[a-zA-Z0-9])[a-zA-Z0-9 '&]{2,10}$")) {
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.INVALID_BUSINESS_NAME);
        }
    }

    public void inspectIfRepeatPasswordIsTooShort(String repeatPassword) {
        if(passwordTooShort(repeatPassword)) {
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.PASSWORD_REPEAT_TO_SHORT);
        }
    }

    public void inspectIfPasswordIsTooShort(String password) {
        if(passwordTooShort(password)) {
            registrationResultDTO.setReason(RegistrationResultDTO.RegistrationResultType.PASSWORD_TO_SHORT);
        }
    }

    private boolean passwordTooShort(String password) {
        return (password.length() < 5);
    }

}
