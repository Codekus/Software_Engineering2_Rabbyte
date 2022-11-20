package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class VerificationControlTest {

    VerificationControl verificationControl;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @BeforeEach
    void setUp() {
        verificationControl = new VerificationControl();
    }

    @Test
    void activate() {
    }

    @Test
    void getVerificationCodeNullCheck() {

        assertNotNull(verificationCodeRepository.findVerificationCodeById(60000017));
        assertNotNull(verificationCodeRepository.findVerificationCodeByToken("606728a3-f4dd-4a12-a75d-1411773e25b7"));

    }

    @Test
    void testActivate() {
    }

    @Test
    void getVerificationCode() {
    }

    @Test
    void length() {
        assertTrue(verificationControl.length("606728a3-f4dd-4a12-a75d-1411773e25b7"));
    }
}