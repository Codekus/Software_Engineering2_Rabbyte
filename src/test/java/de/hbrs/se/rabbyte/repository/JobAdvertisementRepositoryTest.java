package de.hbrs.se.rabbyte.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class JobAdvertisementRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    JobAdvertisementRepository jobAdvertisementRepository;

    @BeforeMethod
    void setup(){
        this.jobAdvertisementRepository = applicationContext.getBean(JobAdvertisementRepository.class);
    }

    @Test
    void findJobAdvertisementById() {
        assertEquals("Advertisement Title" , jobAdvertisementRepository.findJobAdvertisementById(30000087).getTitle());
    }
    @Test
    void findJobAdvertisementByIdNull() {
        assertNull(jobAdvertisementRepository.findJobAdvertisementById(30000999));
    }

    @Test
    void findJobAdvertisementByBusinessIsNotNull() {
    }


    @Test
    void search() {
    }
}