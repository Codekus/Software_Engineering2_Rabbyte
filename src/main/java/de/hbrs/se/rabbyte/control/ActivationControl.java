package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.dtos.ActivationResultDTO;
import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.dtos.implemented.ActivationResultDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.VerificationCodeDTOImpl;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivationControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivationControl.class);

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Autowired
    PersonRepository personRepository;

    private Person person;

    public class AuthException extends Exception {

    }

    public ActivationResultDTOImpl activate(String token) {

        ActivationResultDTOImpl activationResult = new ActivationResultDTOImpl();

            VerificationCode verificationCode = getVerificationCode(token);

            person = verificationCode.getPerson();
            person.setEnabled(true);

            personRepository.save(person);
            activationResult.setActivationResult(true);

            return activationResult;

    }

    public VerificationCode getVerificationCode(String token) {
        VerificationCode verificationCodeDTO = verificationCodeRepository.findVerificationCodeByToken(token);
        return verificationCodeDTO;
    }

    public boolean length(String token) {
        return (token.length() == 128);
    }

}
