package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.dtos.implemented.VerificationResultDTOImpl;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationControl {

    private final VerificationCodeRepository verificationCodeRepository;

    public VerificationControl(VerificationCodeRepository verificationCodeRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationControl.class);

    @Autowired
    private  PersonRepository personRepository;

    private Person person;

    public VerificationResultDTOImpl activate(String token) {
        VerificationResultDTOImpl activationResult = new VerificationResultDTOImpl();
        try {

            VerificationCodeDTO verificationCode = getVerificationCode(token);

            person = verificationCode.getPerson();
            person.setEnabled(true);

            personRepository.save(person);
            activationResult.setActivationResult(true);

            return activationResult;
        } catch (Exception exception) {
            activationResult.setActivationResult(false);
        }
        return activationResult;
    }

    public VerificationCodeDTO getVerificationCode(String token) {
        return verificationCodeRepository.findVerificationCodeByToken(token);
    }

    public VerificationCodeDTO getVerificationCodeDTOById(int id) {
        return verificationCodeRepository.findVerificationCodeById(id);
    }
    public boolean length(String token) {
        return (token.length() == 36);
    }

}
