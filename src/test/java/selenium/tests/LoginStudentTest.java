package selenium.tests;

import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;


public class LoginStudentTest extends BaseFunctions {

    LoginPO loginPO = new LoginPO();
/*
    @Test
    void loginStudentTest() {
        loginPO.openLogin();

        loginPO.enterName("rabbyteTestAutomation+student@outlook.de");
        //Man soll sich nicht ohne Passwort einloggen können
        loginPO.loginClick();
        //Überprüfen dass man sich noch auf der Login Seite befindet
        loginPO.checkForPresence();

        loginPO.enterPassword("superpasswort123");
        loginPO.loginClick();
    }

 */
}
