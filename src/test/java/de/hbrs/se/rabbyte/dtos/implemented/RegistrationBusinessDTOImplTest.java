package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationBusinessDTOImplTest {


    private RegistrationBusinessDTOImpl registrationBusinessDTO;

    @Mock
    BusinessDTOImpl  businessDTO;
    @Mock
    BusinessDTOImpl  businessDTOTwo;
    private static final String BUSINESSPW = "12345";

    @BeforeMethod
    void setUp() {
        businessDTO = new BusinessDTOImpl();
        registrationBusinessDTO = new RegistrationBusinessDTOImpl(businessDTO , BUSINESSPW);
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