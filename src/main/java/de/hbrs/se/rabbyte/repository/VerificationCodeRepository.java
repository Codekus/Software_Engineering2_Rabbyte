package de.hbrs.se.rabbyte.repository;


import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer> {

    @Query("select v from VerificationCode v where v.token = ?1")
    VerificationCodeDTO findVerificationCodeByToken(String token);

}
