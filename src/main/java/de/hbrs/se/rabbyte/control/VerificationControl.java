package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.dtos.implemented.VerificationResultDTOImpl;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificationControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationControl.class);

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Autowired
    PersonRepository personRepository;

    private Person person;



    public class AuthException extends Exception {

    }

    public VerificationResultDTOImpl activate(String token) {

        VerificationResultDTOImpl activationResult = new VerificationResultDTOImpl();

            VerificationCodeDTO verificationCode = getVerificationCode(token);

            person = verificationCode.getPerson();
            person.setEnabled(true);

            personRepository.save(person);
            activationResult.setActivationResult(true);

            return activationResult;

    }

    public VerificationCodeDTO getVerificationCode(String token) {
        VerificationCodeDTO verificationCode = verificationCodeRepository.findVerificationCodeByToken(token);
        return verificationCode;
    }

    public boolean length(String token) {
        return (token.length() == 36);
    }

}
