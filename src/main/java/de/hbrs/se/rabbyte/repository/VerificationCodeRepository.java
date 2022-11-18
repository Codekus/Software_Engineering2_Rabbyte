package de.hbrs.se.rabbyte.repository;



import de.hbrs.se.rabbyte.entities.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VerificationCodeRepository extends JpaRepository<VerificationCode , Integer> {


    VerificationCode findByToken(String token);

    VerificationCode findByVerificationId(int id);
}
