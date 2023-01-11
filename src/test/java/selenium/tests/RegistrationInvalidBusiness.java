package selenium.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;
import selenium.setup.RegistrationPO;

public class RegistrationInvalidBusiness extends BaseFunctions{

    RegistrationPO registrationOP = new RegistrationPO();
    LoginPO loginPO = new LoginPO();

    @Test
    void tabTest() {
        registrationOP.openRegistration();
        registrationOP.clickBusiness();

        String tabStudentExpected = "Student";
        String tabStudentActual = registrationOP.getTabStudent();
        Assert.assertEquals(tabStudentExpected, tabStudentActual);

        String tabBusinessExpected = "Business";
        String tabBusinessActual = registrationOP.getTabBusiness();
        Assert.assertEquals(tabBusinessExpected, tabBusinessActual);

    }

    @Test
    void titleHeadline(){
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        String headline = registrationOP.getHeadLine();
        Assert.assertEquals(headline, "Registrierung");
    }

    @Test
    void registrationFailName() throws Exception {
        //check for empty names
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        registrationOP.clickReg();
        Thread.sleep(1000);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualPassword = registrationOP.getPassword();
        Assert.assertTrue(actualPassword.contains("Das Password muss mindestens 8 Zeichen lang sein"));
    }

    @Test
    void registrationFailPassword() throws Exception {
        //check for empty passwords
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        registrationOP.clickReg();
        Thread.sleep(1000);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualPasswordError = registrationOP.getPassword();
        String actualPasswordRepeatError = registrationOP.getPasswordRepeat();
        Assert.assertTrue(actualPasswordError.contains("Das Password muss mindestens 8 Zeichen lang sein"));
        Assert.assertTrue(actualPasswordRepeatError.contains("Das Password muss mindestens 8 Zeichen lang sein"));
    }

    @Test
    void registrationFailEmail() throws Exception {
        //check for empty passwords
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualEmailError = registrationOP.getEmail();
        Assert.assertTrue(actualEmailError.contains("Das Format der Email ist nicht gültig"));
    }


    @Test
    void registrationFailCommonPassword() throws Exception {
        //check for empty passwords
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        registrationOP.enterPassword("12345678");
        registrationOP.enterPasswordRepeat("12345678");
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualPasswordError = registrationOP.getPassword();
        String actualPasswordRepeatError = registrationOP.getPasswordRepeat();
        Assert.assertTrue(actualPasswordError.contains("Ihr Passwort ist eines der häufigsten Passwörter. Bitte wählen sie ein anderes"));
        Assert.assertTrue(actualPasswordRepeatError.contains("Ihr Passwort ist eines der häufigsten Passwörter. Bitte wählen sie ein anderes"));
    }

    //@Test
    void registrationFailWrongFormat() throws Exception {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        registrationOP.enterBusinessName("-");
        registrationOP.enterEmail("12345678");
        registrationOP.clickReg();
        waitUntilElementIsVisible(registrationOP.business);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualBusinessName = registrationOP.getBusinessName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertTrue(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertTrue(actualBusinessName.contains("Ungültiger Geschäftsname"));

    }

    @Test
    void registrationFailWrongFormat2() throws Exception {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.clickBusiness();
        registrationOP.enterBusinessName("*-*");
        registrationOP.enterEmail("name");
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualBusinessName = registrationOP.getBusinessName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertTrue(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertTrue(actualBusinessName.contains("Ungültiger Geschäftsname"));

    }



}
