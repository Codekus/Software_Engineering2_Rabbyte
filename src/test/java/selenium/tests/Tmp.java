package selenium.tests;

import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;

public class Tmp extends BaseFunctions{

    LoginPO loginPO = new LoginPO();

    @Test
    void test1(){
        loginPO.openLogin();
        loginPO.enterName("jobad@...");
    }




}
