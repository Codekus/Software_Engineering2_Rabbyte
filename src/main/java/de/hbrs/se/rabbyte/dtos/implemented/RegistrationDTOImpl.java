package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.RegistrationDTO;
import de.hbrs.se.rabbyte.entities.User;

public class RegistrationDTOImpl implements RegistrationDTO  {

    private User user;

    private String repeatPassword;
    @Override
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
