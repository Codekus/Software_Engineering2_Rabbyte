package selenium.tests;

import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;


public class LoginStudentTest extends BaseFunctions {

    LoginPO loginPO = new LoginPO();

    @Test
    void loginStudentTest() throws InterruptedException {
        loginPO.openLogin();

        loginPO.enterName("rabbyteTestAutomation+student@outlook.de");
        //Man soll sich nicht ohne Passwort einloggen können
        loginPO.loginClick();
        //Überprüfen dass man sich noch auf der Login Seite befindet
        loginPO.checkForLoginElements();

        //Mit einem falschen Passwort soll der Login nicht möglich sein
        loginPO.enterPassword("superpasswort12");
        loginPO.loginClick();
        loginPO.alertMessage();

        loginPO.checkForLoginElements();
        loginPO.errorMessage();

        loginPO.enterPassword("3");
        loginPO.loginClick();

        loginPO.checkMeinProfil();
    }


}
