package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.dtos.implemented.JobAdvertisementDTOImpl;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.repository.JobAdvertisementRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@Transactional
public class JobAdvertControlTest extends AbstractTransactionalTestNGSpringContextTests {

    private JobAdvertControl jobAdvertControl;

    JobAdvertisementRepository jobAdvertisementRepository;

    BusinessRepository businessRepository;

    final int jobID = 30000087;
    String editText = "new Text";
    String editTitle = "new Title";

    @BeforeMethod
    void setup() {
        this.businessRepository = applicationContext.getBean(BusinessRepository.class);
        this.jobAdvertisementRepository = applicationContext.getBean(JobAdvertisementRepository.class);
        this.jobAdvertControl = applicationContext.getBean(JobAdvertControl.class);
    }


    @Test
    void editJobAdvert_editJobAdvertValues_valuesChanged() {
        Business business = PersonFactory.createBusiness(businessRepository
                .findBusinessById(20000090));

        JobAdvertisementDTOImpl JobAdvertisementDTO = new JobAdvertisementDTOImpl();
        JobAdvertisementDTO.setBusiness(business);
        JobAdvertisementDTO.setId(jobID);
        JobAdvertisementDTO.setTitle(editTitle);
        JobAdvertisementDTO.setText(editText);
        JobAdvertisementDTO.setType("Vollzeit");

        jobAdvertControl.editJobAdvert(JobAdvertisementDTO);
        JobAdvertisementDTO editJobAdvertisementDTO = jobAdvertisementRepository.findJobAdvertisementById(jobID);

        Assertions.assertEquals(editJobAdvertisementDTO.getText(), editText);
        Assertions.assertEquals(editJobAdvertisementDTO.getTitle(), editTitle);
    }
}
