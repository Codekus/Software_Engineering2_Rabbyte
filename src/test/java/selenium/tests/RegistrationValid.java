package selenium.tests;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
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

        //Boolean tabStudentIsSelected = registrationOP.getTabStudentSelected();
        //Assert.assertTrue(tabStudentIsSelected);

        String tabBusinessExpected = "Business";
        String tabBusinessActual = registrationOP.getTabBusiness();
        Assert.assertEquals(tabBusinessExpected, tabBusinessActual);

    }

    @Test
    void registrationFailWrongFormat() throws InterruptedException {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.enterFirstName("Firstname");
        registrationOP.enterSurName("Surname");
        registrationOP.enterEmail("test@test.com");
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualFirstNameError = registrationOP.getFirstName();
        String actualSurNameError = registrationOP.getSurName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertFalse(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertFalse(actualFirstNameError.contains("Ungültiger Vorname"));
        Assert.assertFalse(actualSurNameError.contains("Ungültiger Nachname"));

    }

}
