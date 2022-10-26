package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;

public class BusinessDTOImpl extends GeneralUserDTOImpl implements BusinessDTO {

    private String businessName;

    @Override
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
