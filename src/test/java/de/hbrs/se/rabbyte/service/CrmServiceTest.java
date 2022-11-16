package de.hbrs.se.rabbyte.service;

import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import de.hbrs.se.rabbyte.repository.JobAdvertisementRepository;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class CrmServiceTest {

    @Autowired
    private JobAdvertisementRepository jobAdvertisementRepository;

    @Autowired
    private CrmService crmService;

    private int tmpId ;
    @Before
    void setUp() {
        Business business = new Business();
        business.setBusinessName("testBus1");

        //job advertisement
        JobAdvertisement jAdv = new JobAdvertisement();
        jAdv.setText("testText1");
        jAdv.setTitle("testTitle1");
        jAdv.setBusiness(business);
        jAdv.setType("Vollzeit");
        tmpId = jAdv.getId();

        crmService.saveJobAdvertisement(jAdv);
    }


    @Test
    @DisplayName("Job-Advertisement-Search-Test")
    public void jobAdvertisementSearchTest(){

        Assert.assertEquals("testTitle1",jobAdvertisementRepository.search("test").get(0).getTitle());
        Assert.assertEquals("",jobAdvertisementRepository.search("test123").get(0).getTitle());
    }

    @After
    void tearDown() {
        crmService.deleteJobAdvertisement(jobAdvertisementRepository.findById(tmpId).get());
    }


}
