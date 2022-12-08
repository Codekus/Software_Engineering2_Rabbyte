package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.PersonFactory;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.dtos.implemented.JobAdvertisementDTOImpl;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.repository.JobAdvertisementRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY )
@Sql(scripts = {"file:src/test/ressources/rabbyte_schema.sql ", "file:src/test/ressources/rabbyte_data.sql"})
@Transactional
public class JobAdvertControlTest {

    @Autowired
    private JobAdvertControl jobAdvertControl;

    @Autowired
    JobAdvertisementRepository jobAdvertisementRepository;

    @Autowired
    BusinessRepository businessRepository;

    final int jobID = 30000087;
    String editText = "new Text";
    String editTitle = "new Title";


    @Test
    @Ignore
    void editJobAdvert_editJobAdvertValues_valuesChanged(){

        JobAdvertisementDTOImpl JobAdvertisementDTO = new JobAdvertisementDTOImpl();

        Assertions.assertEquals(JobAdvertisementDTO.getText(), editText);
        Assertions.assertEquals(JobAdvertisementDTO.getTitle(), editTitle);

        Business business = PersonFactory.createBusiness(businessRepository
                .findBusinessById(20000090));

        JobAdvertisementDTO = new JobAdvertisementDTOImpl();
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
