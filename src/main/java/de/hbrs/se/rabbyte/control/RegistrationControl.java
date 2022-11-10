package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.UserFactory;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
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
            registrationResultDTO.setReason(RegistrationResultDTO.Result.EMAIL_IN_USE);
        }
            validateStudent(registrationStudentDTO);
            if(registrationResultDTO.getReasons().isEmpty()) {

            Student newStudent = UserFactory.createStudent(registrationStudentDTO.getStudentDTO());
            this.studentRepository.save(newStudent);
            registrationResultDTO.setRegistrationResult(true);
        } else {
            registrationResultDTO.setRegistrationResult(false);
        }} catch (Exception exception) {
            registrationResultDTO.setRegistrationResult(false);
            registrationResultDTO.setReason(RegistrationResultDTO.Result.GENERAL_ERROR);
            LOGGER.info("INFO:" ,  exception.getMessage());
        }
        return registrationResultDTO;
    }

    private void validateStudent(RegistrationStudentDTOImpl registrationStudentDTO) {
        inspectIfPasswordIsTooShort(registrationStudentDTO.getStudentDTO().getPassword());
        inspectIfRepeatPasswordIsTooShort(registrationStudentDTO.getRepeatPassword());
        inspectIfSamePassword(registrationStudentDTO.getRepeatPassword() , registrationStudentDTO.getStudentDTO().getPassword());

        validateEmailName(registrationStudentDTO.getStudentDTO().getEmail());
        validateFirstName(registrationStudentDTO.getStudentDTO().getFirstName());
        validateLastName(registrationStudentDTO.getStudentDTO().getLastName());
    }


    public RegistrationResultDTO registerBusiness(RegistrationBusinessDTOImpl registrationBusinessDTO) {

        try {
            registrationResultDTO = new RegistrationResultDTOImpl();

            validateBusiness(registrationBusinessDTO);

            if (registrationResultDTO.getReasons().isEmpty()) {
                Business newBusiness = UserFactory.createBusiness(registrationBusinessDTO.getBusinessDTO());
                this.businessRepository.save(newBusiness);
                registrationResultDTO.setRegistrationResult(true);
            } else {
                registrationResultDTO.setRegistrationResult(false);
            }
        } catch(Exception exception) {
            registrationResultDTO.setRegistrationResult(false);
            registrationResultDTO.setReason(RegistrationResultDTO.Result.GENERAL_ERROR);
            LOGGER.info("INFO" , exception.getMessage());
        }
        return registrationResultDTO;
    }

    private void validateBusiness(RegistrationBusinessDTOImpl registrationBusinessDTO) {
        if (inspectIfEmailIsAlreadyInUse(registrationBusinessDTO.getBusinessDTO().getEmail())) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.EMAIL_IN_USE);
        }

        if(businessNameInUse(registrationBusinessDTO.getBusinessDTO().getBusinessName())) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.BUSINESS_NAME_IN_USE);
        }
        inspectIfPasswordIsTooShort(registrationBusinessDTO.getBusinessDTO().getPassword());
        inspectIfRepeatPasswordIsTooShort(registrationBusinessDTO.getRepeatPassword());
        inspectIfSamePassword(registrationBusinessDTO.getRepeatPassword() , registrationBusinessDTO.getBusinessDTO().getPassword());
        validateEmailName(registrationBusinessDTO.getBusinessDTO().getEmail());
        validateBusinessName(registrationBusinessDTO.getBusinessDTO().getBusinessName());

    }

    private boolean businessNameInUse(String businessName) {
        BusinessDTO businessDTO = businessRepository.findBusinessByBusinessName(businessName);
        return ( businessDTO != null && businessDTO.getId() > 0);
    }

    private boolean inspectIfEmailIsAlreadyInUse(String email) {
        GeneralUserDTO generalUser = generalUserRepository.findByEmail(email);

        return (generalUser != null && generalUser.getId() > 0);
    }

    private void inspectIfSamePassword(String password , String repeatPassword) {

        if(!password.equals(repeatPassword)) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.PASSWORD_DIFFERENT);
        }
    }

    private void validateEmailName(String email) {


        Pattern pattern =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.INVALID_EMAIL);
        }
    }

    private void validateFirstName(String firstName) {
        if(!firstName.matches( "[A-Z][a-z]*")){
            registrationResultDTO.setReason(RegistrationResultDTO.Result.INVALID_FIRST_NAME);
        }
    }

    private void validateLastName(String lastName) {
        if(!lastName.matches( "[A-Z][a-z]*")){
            registrationResultDTO.setReason(RegistrationResultDTO.Result.INVALID_LAST_NAME);
        }
    }

    private void validateBusinessName(String businessName) {
        if(!businessName.matches("^(?!\\s)(?!.*\\s)(?=.*[a-zA-Z0-9])[a-zA-Z0-9 '&]{2,10}$")) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.INVALID_BUSINESS_NAME);
        }
    }

    private void inspectIfRepeatPasswordIsTooShort(String repeatPassword) {
        if(passwordTooShort(repeatPassword)) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.PASSWORD_REPEAT_TO_SHORT);
        }
    }

    private void inspectIfPasswordIsTooShort(String password) {
        if(passwordTooShort(password)) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.PASSWORD_TO_SHORT);
        }
    }

    private boolean passwordTooShort(String password) {
        return (password.length() < 5);
    }

}
