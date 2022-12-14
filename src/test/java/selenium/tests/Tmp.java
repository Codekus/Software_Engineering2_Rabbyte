package selenium.tests;

import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;
import selenium.setup.RegistrationOP;

public class Tmp extends BaseFunctions{

    LoginPO loginPO = new LoginPO();
    RegistrationOP registrationOP = new RegistrationOP();
    @Test
    void test1(){
        loginPO.openLogin();
        loginPO.enterName("jobad@...");
    }

    @Test
    void registrationFail() {
        registrationOP.openRegistration();
        registrationOP.enterName("kai");    }


}
