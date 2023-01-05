package de.hbrs.se.rabbyte.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class GeneralPersonRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    PersonRepository userRepository;

    @BeforeMethod
    void setup(){
        this.userRepository = applicationContext.getBean(PersonRepository.class);
    }

    @Test
    void findByEmail() {
        assertEquals(20000050 , userRepository.findByEmail("max@gmx.de").getId());
    }

    @Test
    void findByEmailNull() {
        assertNull(userRepository.findByEmail("tom@gmx.de"));
    }

    @Test
    void findById() {
        assertEquals("max@gmx.de" , userRepository.findPersonById(20000050).getEmail());
    }

    @Test
    void findByIdNull() {
        assertNull(userRepository.findPersonById(2));
    }

}