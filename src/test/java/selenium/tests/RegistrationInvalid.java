package selenium.tests;

import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;
import selenium.setup.RegistrationOP;

public class RegistrationInvalid extends BaseFunctions{

    RegistrationOP registrationOP = new RegistrationOP();


    @Test
    void registrationFail() {
        registrationOP.openRegistration();
        registrationOP.enterName("kai");    }
}
