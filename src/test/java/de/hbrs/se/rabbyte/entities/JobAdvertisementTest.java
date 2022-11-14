package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class JobAdvertisementTest {

    private JobAdvertisement jobAdvertisement;
    private int id;
    private String text;
    private String title;
    private String type;
    @Mock
    private Business business;

    @BeforeEach
    void setUp() {
        jobAdvertisement = new JobAdvertisement();
        business = new Business();
        jobAdvertisement.setId(id);
        jobAdvertisement.setText(text);
        jobAdvertisement.setTitle(title);
        jobAdvertisement.setType(type);
        jobAdvertisement.setBusiness(business);
    }
    @Test
    void getId() {
        assertEquals(id , jobAdvertisement.getId());
    }

    @Test
    void getText() {
        assertEquals(text , jobAdvertisement.getText());
    }

    @Test
    void getTitle() {
        assertEquals(title , jobAdvertisement.getTitle());
    }

    @Test
    void getType() {
        assertEquals(type , jobAdvertisement.getType());
    }

    @Test
    void getBusiness() {
        assertTrue( jobAdvertisement.getBusiness() instanceof Business);
        assertNotNull(jobAdvertisement.getBusiness());
    }
}