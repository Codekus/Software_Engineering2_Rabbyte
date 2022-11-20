package de.hbrs.se.rabbyte.control;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@Transactional
class VerificationControlTest {

    @Autowired
    private VerificationControl verificationControl;

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Test
    void getVerificationCode() {
        assertNotNull(verificationControl.getVerificationCode("606728a3-f4dd-4a12-a75d-1411773e25b7"));
    }

    @Test
    void getVerificationCodeWithId() {
        assertNotNull(verificationControl.getVerificationCodeDTOById(60000017));
    }

    @Test
    void length() {
        assertTrue(verificationControl.length("72db6fa3-2511-4776-b24f-4fc3ab8e1206"));
    }
}