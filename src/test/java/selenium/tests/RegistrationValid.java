package selenium.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;
import selenium.setup.RegistrationOP;

public class RegistrationValid extends BaseFunctions {

    RegistrationOP registrationOP = new RegistrationOP();
    LoginPO loginPO = new LoginPO();

    @Test
    void forwardRegTest(){

        loginPO.openLogin();
        registrationOP.clickRegForward();
        /*
        String expectedUrl="http://localhost:8080/registration";
        String actualUrl= loginPO.driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        */
        String headlineExpected = "Registrierung";
        String headlineActual = registrationOP.getHeadLine();
        Assert.assertEquals(headlineExpected, headlineActual);
    }
    @Test
    void tabTest(){
        registrationOP.openRegistration();

        String tabStudentExpected = "Student";
        String tabStudentActual = registrationOP.getTabStudent();
        Assert.assertEquals(tabStudentExpected, tabStudentActual);

        String tabBusinessExpected = "Business";
        String tabBusinessActual = registrationOP.getTabBusiness();
        Assert.assertEquals(tabBusinessExpected, tabBusinessActual);

    }
}
