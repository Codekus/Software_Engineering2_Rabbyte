package selenium.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import selenium.Utils.OutlookMail;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;
import selenium.setup.RegistrationPO;



public class RegistrationValidBusiness extends BaseFunctions {

    RegistrationPO registrationOP = new RegistrationPO();
    LoginPO loginPO = new LoginPO();
    OutlookMail outlookMail = new OutlookMail();

    @Test
    void forwardRegTest() {

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
    void tabTest() {
        registrationOP.openRegistration();
        registrationOP.clickBusiness();

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
    void registrationCorrectFormat() throws Exception {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        registrationOP.enterBusinessName("Business");
        registrationOP.enterEmail("test@test.com");
        registrationOP.clickReg();
        waitUntilElementIsVisible(registrationOP.business);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualBusinessName = registrationOP.getBusinessName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertFalse(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertFalse(actualBusinessName.contains("Ungültiger Geschäftsname"));
    }

    @Test
    void registrationCorrect() throws Exception {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        registrationOP.enterBusinessName("Business");
        registrationOP.enterEmail("test@test.com");
        registrationOP.clickReg();
        waitUntilElementIsVisible(registrationOP.business);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualBusinessName = registrationOP.getBusinessName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertFalse(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertFalse(actualBusinessName.contains("Ungültiger Geschäftsname"));

    }

    @Test
    void registrationCorrectFull() throws Exception {
        //check for correct registration process
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        String email = registrationOP.getEmailAndCount();
        String businessName = registrationOP.getUniqueBusinessName();
        registrationOP.enterBusinessName(businessName);
        registrationOP.enterPassword("+securepassword321");
        registrationOP.enterPasswordRepeat("+securepassword321");
        registrationOP.enterEmail(email);
        registrationOP.clickReg();
        Thread.sleep(10000);

        //String activationNotification = registrationOP.getRegNotification();
        //Assert.assertTrue(activationNotification.contains("Registrierung erfolgreich"));
        //registrationOP.clickSuccessfulRegOkButton();
        String loginText = registrationOP.getLoginBanner();
        Assert.assertTrue(loginText.contains("Herzlich Willkommen"));
        Thread.sleep(5000);

        String latestMail = outlookMail.getLatestEmail();
        String url = outlookMail.extractURl(latestMail);
        driver.get(url);
        Thread.sleep(500);
        registrationOP.clickActivationButton();
        String activationConfiramtion = registrationOP.getActivationConfiramtion();
        Assert.assertTrue(activationConfiramtion.contains("Ihr Account ist jetzt aktiv!"));


    }

}


