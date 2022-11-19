package de.hbrs.se.rabbyte.service;


import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Autowired
    PersonRepository personRepository;

    public class AuthException extends Exception {

    }

    public void activate(String activationCode) throws AuthException {
        VerificationCode verificationCodeDTO = verificationCodeRepository.findVerificationCodeByToken(activationCode);


        if (verificationCodeDTO.getPerson() != null) {
            PersonDTO personDTO = PersonFactory.createUserDTO(verificationCodeDTO.getPerson());
            Person person = PersonFactory.enableUser(personDTO);
            personRepository.save(person);
        } else {
            throw new AuthException();
        }
    }

    public PersonDTO generateUserDTO(Person person)  {
        return PersonFactory.createUserDTO(person);
    }
}