package de.hbrs.se.rabbyte.entities;

import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@AutoConfigureEmbeddedDatabase
class BusinessIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {

    BusinessRepository businessRepository;

    private String businessName;
    private String email;

    private int plz;
    private String city;
    private String country;
    private String street;
    private String streetNumber;

    BusinessDTO businessDTOName;
    BusinessDTO businessDTOId;

    Business businessEntity;
    @BeforeMethod
    void setUp() {
        this.businessRepository = applicationContext.getBean(BusinessRepository.class);
        businessName = "MoneyInc";
        email = "money@gmx.de";
        plz = 54321;
        city = "Musterstadt";
        country = "DE";
        street = "Musterstadt";
        streetNumber = "5";

        businessDTOName = businessRepository.findBusinessByBusinessName("MoneyInc");
        businessDTOId = businessRepository.findBusinessById(20000090);
        businessEntity = PersonFactory.createBusiness(businessDTOName);
    }

    @Test
    void compareDTOBusinessNameAndId() {
        assertEquals(businessDTOName.getBusinessName() , businessDTOId.getBusinessName());
        assertEquals(businessDTOName.getId() , businessDTOId.getId());
    }

    @Test
    void businessName() {
        assertEquals(businessName , businessDTOName.getBusinessName() , businessEntity.getBusinessName() );
    }

    @Test
    void email() {
        assertEquals(email , businessDTOName.getEmail() , businessEntity.getEmail() );
    }

    @Test
    void plz() {
        assertEquals(plz , businessDTOName.getPlz() , businessEntity.getPlz() );
    }

    @Test
    void city() {
        assertEquals(city , businessDTOName.getCity() , businessEntity.getCity() );
    }

    @Test
    void country() {
        assertEquals(country , businessDTOName.getCountry() , businessEntity.getCountry() );
    }

    @Test
    void street() {
        assertEquals(street , businessDTOName.getStreet() , businessEntity.getStreet() );
    }

    @Test
    void streetNumber() {
        assertEquals(streetNumber , businessDTOName.getStreetNumber() , businessEntity.getStreetNumber() );
    }
}
