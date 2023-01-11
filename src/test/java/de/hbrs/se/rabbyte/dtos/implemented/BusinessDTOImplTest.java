package de.hbrs.se.rabbyte.dtos.implemented;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessDTOImplTest {

    BusinessDTOImpl businessDTO= new BusinessDTOImpl();
    private static final String BUSINESS_NAME = "Mustermann GmBH";

    @BeforeMethod
    void setUp() {
        businessDTO.setBusinessName(BUSINESS_NAME);
    }

    @Test
    void getBusinessName() {
        assertEquals(BUSINESS_NAME, businessDTO.getBusinessName());
    }
}