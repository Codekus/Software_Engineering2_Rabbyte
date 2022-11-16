package de.hbrs.se.rabbyte.dtos.implemented;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessDTOImplTest {

    BusinessDTOImpl businessDTO= new BusinessDTOImpl();
    private static final String BUSINESS_NAME = "Mustermann GmBH";

    @BeforeEach
    void setUp() {
        businessDTO.setBusinessName(BUSINESS_NAME);
    }

    @Test
    void getBusinessName() {
        assertEquals(BUSINESS_NAME, businessDTO.getBusinessName());
    }
}