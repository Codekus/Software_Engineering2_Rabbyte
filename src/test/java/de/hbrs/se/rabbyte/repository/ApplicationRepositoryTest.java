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
class ApplicationRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    ApplicationRepository applicationRepository;

    @BeforeMethod
    void setup(){
        this.applicationRepository = applicationContext.getBean(ApplicationRepository.class);
    }

    @Test
    void findApplicationById() {
        assertEquals("Application Text" , applicationRepository.findApplicationById(10000001).getApplicationText());
    }
    @Test
    void findApplicationByIdNull() {
        assertNull(applicationRepository.findApplicationById(10000999));
    }
}