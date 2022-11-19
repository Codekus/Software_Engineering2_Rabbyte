package de.hbrs.se.rabbyte.service;


import de.hbrs.se.rabbyte.control.factory.UserFactory;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.Person;
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
        VerificationCodeDTO verificationCodeDTO = verificationCodeRepository.findByToken(activationCode);


        if (verificationCodeDTO.getUser() != null) {
            GeneralUserDTO generalUserDTO = UserFactory.createUserDTO(verificationCodeDTO.getUser());
            Person person = UserFactory.enableUser(generalUserDTO);
            generalUserRepository.save(person);
        } else {
            throw new AuthException();
        }
    }

    public GeneralUserDTO generateUserDTO(Person person)  {
        return UserFactory.createUserDTO(person);
    }
}