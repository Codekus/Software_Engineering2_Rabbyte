package de.hbrs.se.rabbyte.control;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})

class VerificationControlTest {

    @Autowired
    private VerificationControl verificationControl;

    @Autowired
    VerificationCodeRepository verificationCodeRepository;
    @BeforeEach
    void setUp() {

    }

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
        assertTrue(verificationControl.length("606728a3-f4dd-4a12-a75d-1411773e25b7"));
    }
}