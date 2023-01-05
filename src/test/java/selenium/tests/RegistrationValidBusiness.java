package selenium.tests;
import org.junit.Assert;
import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;
import selenium.setup.RegistrationBusinessPO;

public class RegistrationValidBusiness extends BaseFunctions {

    RegistrationBusinessPO registrationOP = new RegistrationBusinessPO();
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

        String tabBusinessIsSelected = registrationOP.getTabBusinessSelected();
        Assert.assertEquals(tabBusinessIsSelected, "true");

        String tabBusinessExpected = "Business";
        String tabBusinessActual = registrationOP.getTabBusiness();
        Assert.assertEquals(tabBusinessExpected, tabBusinessActual);

    }

    @Test
    void registrationCorrectFormat() throws InterruptedException {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.enterBusinessName("Business");
        registrationOP.enterEmail("test@test.com");
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualBusinessName = registrationOP.getBusinessName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertFalse(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertFalse(actualBusinessName.contains("Ungültiger Geschäftsname"));
      }

    @Test
    void registrationCorrect() throws InterruptedException {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.enterBusinessName("Business");
        registrationOP.enterEmail("test@test.com");
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualBusinessName = registrationOP.getBusinessName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertFalse(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertFalse(actualBusinessName.contains("Ungültiger Geschäftsname"));

    }

}


