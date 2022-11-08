package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.entities.Business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class JobAdvertisementDTOImplTest {

    private JobAdvertisementDTOImpl jobAdvertisementDTO= new JobAdvertisementDTOImpl();

    private int id = 100;
    private String text = "Job Ad DTO text";
    private String title = "Job Ad DTO title";
    private String type= "Student" ;
    @Mock
    private Business business;

    @BeforeEach
    void setUp() {
        business = new Business();
        jobAdvertisementDTO.setText(text);
        jobAdvertisementDTO.setType(type);
        jobAdvertisementDTO.setTitle(title);
        jobAdvertisementDTO.setId(100);
        jobAdvertisementDTO.setBusiness(business);
    }

    @Test
    void getId() {
        assertEquals(id , jobAdvertisementDTO.getId());
    }

    @Test
    void getText() {
        assertEquals(text , jobAdvertisementDTO.getText());

    }

    @Test
    void getTitle() {
        assertEquals(title , jobAdvertisementDTO.getTitle());
    }

    @Test
    void getType() {
        assertEquals(type , jobAdvertisementDTO.getType());

    }

    @Test
    void getBusiness() {
        assertTrue( jobAdvertisementDTO.getBusiness() instanceof Business );
        assertNotNull(jobAdvertisementDTO.getBusiness());
    }
}