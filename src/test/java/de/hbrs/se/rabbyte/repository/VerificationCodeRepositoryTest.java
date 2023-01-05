package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.Person;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;


import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class VerificationCodeRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {


    VerificationCodeRepository verificationCodeRepository;

    PersonRepository personRepository;

    @BeforeMethod
    void setup(){
        this.verificationCodeRepository = applicationContext.getBean(VerificationCodeRepository.class);
        this.personRepository = applicationContext.getBean(PersonRepository.class);
    }

    @Test
    void findVerificationCodeByToken() {
        VerificationCodeDTO verificationCode = verificationCodeRepository.findVerificationCodeByToken("606728a3-f4dd-4a12-a75d-1411773e25b7");
        assertEquals(60000017 , verificationCode.getId());
    }
    @Test
    void findVerificationCodeById() {
        VerificationCodeDTO verificationCode = verificationCodeRepository.findVerificationCodeById(60000017);
        assertEquals(60000017 , verificationCode.getId());
    }
    @Test
    void findVer() {


        Person person = PersonFactory.createUser(personRepository.findPersonById(20000050));
        VerificationCodeDTO verificationCode = verificationCodeRepository.findVerificationCodeByPerson(person);
        assertEquals(20000050 , verificationCode.getPerson().getId());
    }


}