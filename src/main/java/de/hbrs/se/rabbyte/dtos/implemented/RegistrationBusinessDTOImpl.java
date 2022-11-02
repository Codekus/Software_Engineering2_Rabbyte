package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.RegistrationBusinessDTO;

public class RegistrationBusinessDTOImpl extends RegistrationDTOImpl implements RegistrationBusinessDTO {

    private BusinessDTO businessDTO;


    @Override
    public BusinessDTO getBusinessDTO() {
        return businessDTO;
    }

    public void setBusinessDTO(BusinessDTO businessDTO) {
        this.businessDTO = businessDTO;
    }

}
