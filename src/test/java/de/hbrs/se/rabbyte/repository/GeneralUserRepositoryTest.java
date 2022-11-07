package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql({"/rabbyte_schema.sql", "/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase

class GeneralUserRepositoryTest {

    @Autowired
    GeneralUserRepository userRepository;

    @Test
    void findByEmail() {
        assertEquals(20000086 , userRepository.findByEmail("max@gmx.de").getId());
    }


}