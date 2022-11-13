package de.hbrs.se.rabbyte.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class JobAdvertisementRepositoryTest {

    @Autowired
    JobAdvertisementRepository jobAdvertisementRepository;

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