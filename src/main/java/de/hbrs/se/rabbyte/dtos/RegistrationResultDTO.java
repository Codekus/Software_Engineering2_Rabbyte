package de.hbrs.se.rabbyte.dtos;

import java.util.List;

public interface RegistrationResultDTO {

    public void setRegistrationResult(boolean result);
    public boolean getRegistrationResult();

    public void setReason(String reason);

    public List<String> getReasons();
}
