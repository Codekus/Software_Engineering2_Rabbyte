package de.hbrs.se.rabbyte.repository;


import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer> {


    VerificationCodeDTO findVerificationCodeByToken(String token);

    VerificationCodeDTO findVerificationCodeById(int id);

    VerificationCodeDTO findVerificationCodeByPerson(Person person);
}
