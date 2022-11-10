package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessDTOImplTest {

    BusinessDTOImpl businessDTO= new BusinessDTOImpl();
    private String businessName = "Mustermann GmBH";

    @BeforeEach
    void setUp() {
        businessDTO.setBusinessName(businessName);
    }

    @Test
    void getBusinessName() {
        assertEquals(businessName , businessDTO.getBusinessName());
    }
}