package de.hbrs.se.rabbyte.entities;


import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.repository.ApplicationRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
public class ApplicationIntegrityTest {


    @Autowired
    ApplicationRepository applicationRepository;

    private ApplicationDTO applicationDTO;


    private int id;
    private int jobAdvertisementId;
    private int studentId;
    private String applicationText;

    @BeforeEach
    void setUp() {
        id = 10000001;
        jobAdvertisementId = 30000087;
        studentId = 20000050;
        applicationText = "Application Text";
        applicationDTO = applicationRepository.findApplicationById(id);

    }

    @Test
    void assertFoundDTO() {
        assertNotNull(applicationDTO);
    }

    @Test
    void jobAdvertisementId() {
        Assert.assertEquals(jobAdvertisementId , applicationDTO.getJobAdvertisement().getId());
    }

    @Test
    void userId() {
        Assert.assertEquals(studentId , applicationDTO.getStudent().getId());
    }

    @Test
    void date() {
        //TO-DO
    }

    @Test
    void applicationText() {
        Assert.assertEquals(applicationText , applicationDTO.getApplicationText());
    }
}
