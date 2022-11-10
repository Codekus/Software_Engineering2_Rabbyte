package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;

import java.util.ArrayList;
import java.util.List;

public class RegistrationResultDTOImpl implements RegistrationResultDTO {
    private boolean result;
    private List<Result> reasons;

    public RegistrationResultDTOImpl() {
        this.reasons = new ArrayList<>();
    }


    @Override
    public void setRegistrationResult(boolean result) {
        this.result = result;
    }

    @Override
    public boolean getRegistrationResult() {
        return result;
    }

    @Override
    public void setReason(Result reason) {
        reasons.add(reason);
    }

    @Override
    public List<Result> getReasons() {
        return reasons;
    }
}
