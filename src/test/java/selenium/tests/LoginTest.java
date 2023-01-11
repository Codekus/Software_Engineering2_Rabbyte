package selenium.tests;

import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;

public class LoginTest extends BaseFunctions {

    LoginPO loginPO = new LoginPO();

    @Test
    void loginTest(){
        loginPO.openLogin();

        loginPO.checkForLoginElements();
        loginPO.checkRegisterButton();
    }

}
