package de.hbrs.se.rabbyte.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class BusinessRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    BusinessRepository businessRepository;

    @BeforeMethod
    void setup(){
        this.businessRepository = applicationContext.getBean(BusinessRepository.class);
    }
    @Test
    void findBusinessByBusinessName() {
       assertEquals(20000090 , businessRepository.findBusinessByBusinessName("MoneyInc").getId());
    }
}