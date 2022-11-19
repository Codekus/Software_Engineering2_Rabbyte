package de.hbrs.se.rabbyte.repository;



import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode , Integer> {


    VerificationCodeDTO findByToken(String token);

    VerificationCode findVerificationCodeById(Integer id);
}
