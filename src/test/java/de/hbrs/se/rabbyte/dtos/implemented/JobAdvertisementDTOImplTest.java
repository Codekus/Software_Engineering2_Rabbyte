package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.entities.Business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class JobAdvertisementDTOImplTest {

    private final JobAdvertisementDTOImpl jobAdvertisementDTO= new JobAdvertisementDTOImpl();

    private static final int ID = 100;
    private static final String TEXT = "Job Ad DTO text";
    private static final String TITLE = "Job Ad DTO title";
    private static final String TYPE = "Student" ;
    @Mock
    private Business business;

    @BeforeEach
    void setUp() {
        business = new Business();
        jobAdvertisementDTO.setText(TEXT);
        jobAdvertisementDTO.setType(TYPE);
        jobAdvertisementDTO.setTitle(TITLE);
        jobAdvertisementDTO.setId(100);
        jobAdvertisementDTO.setBusiness(business);
    }

    @Test
    void getId() {
        assertEquals(ID, jobAdvertisementDTO.getId());
    }

    @Test
    void getText() {
        assertEquals(TEXT, jobAdvertisementDTO.getText());

    }

    @Test
    void getTitle() {
        assertEquals(TITLE, jobAdvertisementDTO.getTitle());
    }

    @Test
    void getType() {
        assertEquals(TYPE, jobAdvertisementDTO.getType());

    }

    @Test
    void getBusiness() {
        assertTrue( jobAdvertisementDTO.getBusiness() instanceof Business );
        assertNotNull(jobAdvertisementDTO.getBusiness());
    }
}