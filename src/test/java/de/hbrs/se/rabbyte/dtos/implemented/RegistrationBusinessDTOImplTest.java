package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationBusinessDTOImplTest {


    private RegistrationBusinessDTOImpl registrationBusinessDTO;

    @Mock
    BusinessDTOImpl  businessDTO;
    @Mock
    BusinessDTOImpl  businessDTOTwo;
    private final String businesspw  = "12345";

    @BeforeEach
    void setUp() {
        businessDTO = new BusinessDTOImpl();
        registrationBusinessDTO = new RegistrationBusinessDTOImpl(businessDTO , businesspw);
    }

    @Test
    void getBusinessDTO() {
        assertTrue(registrationBusinessDTO.getBusinessDTO() instanceof BusinessDTO);
        assertNotNull(registrationBusinessDTO.getBusinessDTO());
    }
    @Test
    void setBusinessDTO() {
        businessDTOTwo = new BusinessDTOImpl();
        registrationBusinessDTO.setBusinessDTO(businessDTOTwo);
        assertTrue(registrationBusinessDTO.getBusinessDTO() instanceof BusinessDTO);
        assertNotNull(registrationBusinessDTO.getBusinessDTO());
    }
}