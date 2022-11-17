package de.hbrs.se.rabbyte.repository;


import de.hbrs.se.rabbyte.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findVerificationTokenByToken(String token);


}
