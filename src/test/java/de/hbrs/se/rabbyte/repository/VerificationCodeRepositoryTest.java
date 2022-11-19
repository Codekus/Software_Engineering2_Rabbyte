package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class VerificationCodeRepositoryTest {


    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Test
    void findVerificationCodeByToken() {
        VerificationCodeDTO verificationCodeDTO = verificationCodeRepository.findByToken("606728a3-f4dd-4a12-a75d-1411773e25b7");
        assertEquals(100 , verificationCodeDTO.getId());
    }
    @Test
    void findVerificationCodeById() {
        VerificationCode verificationCodeDTO = verificationCodeRepository.findVerificationCodeById(60000017);
        assertEquals(100 , verificationCodeDTO.getId());
    }


}