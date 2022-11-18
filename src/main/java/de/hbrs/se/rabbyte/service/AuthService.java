package de.hbrs.se.rabbyte.service;


import de.hbrs.se.rabbyte.control.factory.UserFactory;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.entities.User;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Autowired
    GeneralUserRepository generalUserRepository;

    public class AuthException extends Exception {

    }

    public void activate(String activationCode) throws AuthException {
        VerificationCode verificationCodeDTO = verificationCodeRepository.findByToken(activationCode);


        if (verificationCodeDTO.getUser() != null) {
            GeneralUserDTO generalUserDTO = UserFactory.createUserDTO(verificationCodeDTO.getUser());
            User user = UserFactory.enableUser(generalUserDTO);
            generalUserRepository.save(user);
        } else {
            throw new AuthException();
        }
    }

    public GeneralUserDTO generateUserDTO(User user)  {
        return UserFactory.createUserDTO(user);
    }
}