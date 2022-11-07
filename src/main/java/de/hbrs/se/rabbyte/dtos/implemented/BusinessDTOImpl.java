package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;


/**
 * A DTO for the {@link de.hbrs.se.rabbyte.entities.Business} entity
 */
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
