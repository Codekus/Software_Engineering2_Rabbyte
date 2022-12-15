package de.hbrs.se.rabbyte.control;
import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import de.hbrs.se.rabbyte.RabbyteApplication;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import de.hbrs.se.rabbyte.security.SecurityService;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@Transactional()
class VerificationControlTest extends AbstractTransactionalTestNGSpringContextTests {


    //@Autowired
    private VerificationControl verificationControl;

    //@Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @BeforeMethod
    void setup(){
        this.verificationControl = applicationContext.getBean(VerificationControl.class);
        this.verificationCodeRepository = applicationContext.getBean(VerificationCodeRepository.class);
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
        assertTrue(verificationControl.length("72db6fa3-2511-4776-b24f-4fc3ab8e1206"));
    }
}