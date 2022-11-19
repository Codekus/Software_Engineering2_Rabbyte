package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.RegistrationDTO;
import de.hbrs.se.rabbyte.entities.Person;

public class RegistrationDTOImpl implements RegistrationDTO  {

    private Person person;

    private String repeatPassword;
    @Override
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
