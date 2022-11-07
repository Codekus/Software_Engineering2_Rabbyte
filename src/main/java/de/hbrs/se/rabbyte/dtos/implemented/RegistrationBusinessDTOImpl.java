package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.RegistrationBusinessDTO;

public class RegistrationBusinessDTOImpl extends RegistrationDTOImpl implements RegistrationBusinessDTO {

    private BusinessDTOImpl  businessDTO;

    public RegistrationBusinessDTOImpl(BusinessDTOImpl  businessDTO, String repeatPassword) {
        this.businessDTO = businessDTO;
        this.setRepeatPassword(repeatPassword);
    }


    @Override
    public BusinessDTO getBusinessDTO() {
        return businessDTO;
    }

    public void setBusinessDTO(BusinessDTOImpl  businessDTO) {
        this.businessDTO = businessDTO;
    }

}
