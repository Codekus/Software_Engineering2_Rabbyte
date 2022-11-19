package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.ActivationResultDTO;

public class ActivationResultDTOImpl implements ActivationResultDTO {

    private boolean activationResult;

    public void setActivationResult(Boolean activationResult) {
        this.activationResult = activationResult;
    }

    @Override
    public boolean getActivationResult() {
        return activationResult;
    }
}
