package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.entities.Business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationBusinessDTOImplTest {


    private RegistrationBusinessDTOImpl registrationBusinessDTO;

    @Mock
    BusinessDTOImpl  businessDTO;
    private String password;

    @BeforeEach
    void setUp() {
        businessDTO = new BusinessDTOImpl();
        password = "password";
        registrationBusinessDTO = new RegistrationBusinessDTOImpl(businessDTO , password);
    }

    @Test
    void getBusinessDTO() {
        assertTrue(registrationBusinessDTO.getBusinessDTO() instanceof BusinessDTO);
        assertNotNull(registrationBusinessDTO.getBusinessDTO());
    }
}