package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.dtos.implemented.ActivationResultDTOImpl;
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

    private ActivationResultDTOImpl activationResultDTO;

    public void activate(String token) {

        try {
            activationResultDTO = new ActivationResultDTOImpl();
            VerificationCode verificationCodeDTO = getVerificationCode(token);

            if (verificationCodeDTO.getId() > 0 & verificationCodeDTO != null) {
                activationResultDTO.setActivationResult(true);
            } else {
                activationResultDTO.setActivationResult(false);
            }
        } catch (Exception exception) {
            LOGGER.info("LOG : {}", exception.getMessage());
            activationResultDTO.setActivationResult(false);
        }

        //return activationResultDTO;
    }

    public VerificationCode getVerificationCode(String token) {
        VerificationCode verificationCodeDTO = verificationCodeRepository.findVerificationCodeByToken(token);
        return verificationCodeDTO;
    }

}
