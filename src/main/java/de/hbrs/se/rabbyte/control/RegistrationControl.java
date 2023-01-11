package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.control.factory.VerificationFactory;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationBusinessDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationResultDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.RegistrationStudentDTOImpl;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.repository.StudentRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import de.hbrs.se.rabbyte.util.EmailSenderService;
import de.hbrs.se.rabbyte.util.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegistrationControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationControl.class);

    private RegistrationResultDTO registrationResultDTO;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    EmailSenderService emailSenderService;

    public RegistrationResultDTO registerStudent(RegistrationStudentDTOImpl registrationStudentDTO) {
        try {
        registrationResultDTO = new RegistrationResultDTOImpl();

            if(inspectIfEmailIsAlreadyInUse(registrationStudentDTO.getStudentDTO().getEmail())) {
                registrationResultDTO.setReason(RegistrationResultDTO.Result.EMAIL_IN_USE);
            }

            validateStudent(registrationStudentDTO);

            if(registrationResultDTO.getReasons().isEmpty()) {
                Student newStudent = PersonFactory.createStudent(registrationStudentDTO.getStudentDTO());
                newStudent.setEnabled(true);
                this.studentRepository.save(newStudent);

                try {
                    VerificationCode verificationCode = VerificationFactory.createVerificationToken(newStudent );
                    verificationCodeRepository.save(verificationCode);

                    /*

                    emailSenderService = new EmailSenderService(verificationCode);
                    emailSenderService.sendEmail();
                    */
                } catch (Exception exception) {
                    LOGGER.info("INFO Verification: {}"  ,  exception.getMessage());
                }

                registrationResultDTO.setRegistrationResult(true);


        } else {
            registrationResultDTO.setRegistrationResult(false);
        }} catch (Exception exception) {
            registrationResultDTO.setRegistrationResult(false);
            registrationResultDTO.setReason(RegistrationResultDTO.Result.GENERAL_ERROR);
            LOGGER.info("INFO: {}" ,  exception.getMessage());
        }
        return registrationResultDTO;
    }


    private void validateStudent(RegistrationStudentDTOImpl registrationStudentDTO) {
        inspectIfEmailIsAlreadyInUse(registrationStudentDTO.getStudentDTO().getEmail());
        inspectIfPasswordIsTooShort(registrationStudentDTO.getStudentDTO().getPassword());
        inspectIfRepeatPasswordIsTooShort(registrationStudentDTO.getRepeatPassword());
        inspectIfSamePassword(registrationStudentDTO.getRepeatPassword() , registrationStudentDTO.getStudentDTO().getPassword());
        inspectIfPasswordIsTooCommon(registrationStudentDTO.getStudentDTO().getPassword());
        validateEmailName(registrationStudentDTO.getStudentDTO().getEmail());
        validateFirstName(registrationStudentDTO.getStudentDTO().getFirstName());
        validateLastName(registrationStudentDTO.getStudentDTO().getLastName());
    }


    public RegistrationResultDTO registerBusiness(RegistrationBusinessDTOImpl registrationBusinessDTO) {

        try {
            registrationResultDTO = new RegistrationResultDTOImpl();

            validateBusiness(registrationBusinessDTO);

            if (registrationResultDTO.getReasons().isEmpty()) {
                Business newBusiness = PersonFactory.createBusiness(registrationBusinessDTO.getBusinessDTO());
                this.businessRepository.save(newBusiness);

                try {
                    VerificationCode verificationCode = VerificationFactory.createVerificationToken(newBusiness );
                    verificationCodeRepository.save(verificationCode);

                    emailSenderService = new EmailSenderService(verificationCode);
                    emailSenderService.sendEmail();

                } catch (Exception exception) {
                    LOGGER.info("INFO Verification: {}"  ,  exception.getMessage());
                }
                
                registrationResultDTO.setRegistrationResult(true);
            } else {
                registrationResultDTO.setRegistrationResult(false);
            }
        } catch(Exception exception) {
            registrationResultDTO.setRegistrationResult(false);
            registrationResultDTO.setReason(RegistrationResultDTO.Result.GENERAL_ERROR);
            LOGGER.info("INFO: {}" ,  exception.getMessage());
        }
        return registrationResultDTO;
    }

    private void validateBusiness(RegistrationBusinessDTOImpl registrationBusinessDTO) {
        inspectIfBusinessNameIsInUse(registrationBusinessDTO.getBusinessDTO().getBusinessName());
        inspectIfEmailIsInUse(registrationBusinessDTO.getBusinessDTO().getEmail());
        inspectIfPasswordIsTooShort(registrationBusinessDTO.getBusinessDTO().getPassword());
        inspectIfRepeatPasswordIsTooShort(registrationBusinessDTO.getRepeatPassword());
        inspectIfSamePassword(registrationBusinessDTO.getRepeatPassword() , registrationBusinessDTO.getBusinessDTO().getPassword());
        inspectIfPasswordIsTooCommon(registrationBusinessDTO.getBusinessDTO().getPassword());
        validateEmailName(registrationBusinessDTO.getBusinessDTO().getEmail());
        validateBusinessName(registrationBusinessDTO.getBusinessDTO().getBusinessName());

    }

    private void inspectIfBusinessNameIsInUse(String businessName) {
        if(businessNameInUse(businessName)) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.BUSINESS_NAME_IN_USE);
        }
    }

    private boolean businessNameInUse(String businessName) {
        BusinessDTO businessDTO = businessRepository.findBusinessByBusinessName(businessName);
        return ( businessDTO != null && businessDTO.getId() > 0);
    }
    private void inspectIfEmailIsInUse(String email) {
        if(inspectIfEmailIsAlreadyInUse(email)) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.EMAIL_IN_USE);
        }
    }

    private boolean inspectIfEmailIsAlreadyInUse(String email) {
        PersonDTO generalUser = personRepository.findByEmail(email);

        return (generalUser != null && generalUser.getId() > 0);
    }

    private void inspectIfSamePassword(String password , String repeatPassword) {

        if(!password.equals(repeatPassword)) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.PASSWORD_DIFFERENT);
        }
    }

    private void validateEmailName(String email) {

        Pattern pattern =
                Pattern.compile(Globals.Regex.EMAIL, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) {
            registrationResultDTO.setReason(RegistrationResultDTO.Result.INVALID_EMAIL);
        }
    }

    private void validateFirstName(String firstName) {
        if(!firstName.matches(Globals.Regex.FIRST_NAME)){
            registrationResultDTO.setReason(RegistrationResultDTO.Result.INVALID_FIRST_NAME);
        }
    }

    private void validateLastName(String lastName) {
        if(!lastName.matches( Globals.Regex.LAST_NAME)){
            registrationResultDTO.setReason(RegistrationResultDTO.Result.INVALID_LAST_NAME);
        }
    }

    private void validateBusinessName(String businessName) {
        if(!businessName.matches(Globals.Regex.BUSINESS_NAME)) {
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
        return (password.length() < 8);
    }

    private void inspectIfPasswordIsTooCommon(String password) {
        try {
            if(passwordCommonList(password)) {
                registrationResultDTO.setReason(RegistrationResultDTO.Result.PASSWORD_TOO_COMMON);
            }
        } catch (IOException exception) {
            LOGGER.info("INFO: {}" ,  exception.getMessage());
            registrationResultDTO.setReason(RegistrationResultDTO.Result.GENERAL_ERROR);
        }
    }

    private boolean passwordCommonList(String password) throws IOException {
        boolean commonPassword = false;
        try (final Scanner scanner = new Scanner(new File(Globals.Path.COMMON_PASSWORD_LIST))) {
            while (scanner.hasNext()) {
                if(scanner.next().equals(password)) {
                    commonPassword = true;
                }
            }
            return commonPassword;
        } catch (IOException exception) {
            LOGGER.info("INFO: {}" ,  exception.getMessage());
            return false;
        }

    }

}
