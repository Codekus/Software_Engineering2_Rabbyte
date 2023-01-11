package selenium.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import selenium.Utils.OutlookMail;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;
import selenium.setup.RegistrationPO;

import javax.mail.MessagingException;
import java.io.IOException;

public class RegistrationValidStudent extends BaseFunctions {

    RegistrationPO registrationOP = new RegistrationPO();
    LoginPO loginPO = new LoginPO();

    OutlookMail outlookMail = new OutlookMail();

    @Test
    void forwardRegTest() throws MessagingException, IOException {

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

        String content = OutlookMail.getLatestEmail();
        String mail = registrationOP.getEmailAndCount();
        System.out.println(content);
        System.out.println(mail);
    }

    @Test(enabled = false)
    void tabTest() {
        registrationOP.openRegistration();

        String tabStudentExpected = "Student";
        String tabStudentActual = registrationOP.getTabStudent();
        Assert.assertEquals(tabStudentExpected, tabStudentActual);

        String tabStudentIsSelected = registrationOP.getTabStudentSelected();
        Assert.assertEquals(tabStudentIsSelected, "true");

        String tabBusinessExpected = "Business";
        String tabBusinessActual = registrationOP.getTabBusiness();
        Assert.assertEquals(tabBusinessExpected, tabBusinessActual);

    }

    @Test(enabled = false)
    void registrationCorrectFormat() throws Exception {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.enterFirstName("Firstname");
        registrationOP.enterSurName("Surname");
        registrationOP.enterEmail("test@test.com");
        registrationOP.clickReg();
        waitUntilElementIsVisible(registrationOP.firstName);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualFirstNameError = registrationOP.getFirstName();
        String actualSurNameError = registrationOP.getSurName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertFalse(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertFalse(actualFirstNameError.contains("Ungültiger Vorname"));
        Assert.assertFalse(actualSurNameError.contains("Ungültiger Nachname"));

    }

    @Test
    void registrationCorrectFormat2() throws Exception {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.enterFirstName("Firstname");
        registrationOP.enterSurName("Surname");
        registrationOP.enterEmail("test@test.com");
        registrationOP.clickReg();
        waitUntilElementIsVisible(registrationOP.firstName);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualFirstNameError = registrationOP.getFirstName();
        String actualSurNameError = registrationOP.getSurName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertFalse(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertFalse(actualFirstNameError.contains("Ungültiger Vorname"));
        Assert.assertFalse(actualSurNameError.contains("Ungültiger Nachname"));

    }

    @Test
    void registrationCorrectFull() throws Exception {
        //check for correct registration process
        registrationOP.openRegistration();
        registrationOP.enterFirstName("Firstname");
        registrationOP.enterSurName("Surname");
        registrationOP.enterPassword("+securepassword321");
        registrationOP.enterPasswordRepeat("+securepassword321");
        String email = registrationOP.getEmailAndCount();
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
        waitUntilElementIsVisible(registrationOP.activationButton);
        registrationOP.clickActivationButton();
        String activationConfiramtion = registrationOP.getActivationConfiramtion();
        Assert.assertTrue(activationConfiramtion.contains("Ihr Account ist jetzt aktiv!"));


    }

}
