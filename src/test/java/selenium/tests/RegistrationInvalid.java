package selenium.tests;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import selenium.setup.BaseFunctions;
import selenium.setup.LoginPO;
import selenium.setup.RegistrationOP;

public class RegistrationInvalid extends BaseFunctions {

    RegistrationOP registrationOP = new RegistrationOP();


    @Test
    void tabTest() {
        registrationOP.openRegistration();

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
        String headline = registrationOP.getHeadLine();
        Assert.assertEquals(headline, "Registrierung");
    }

    @Test
    void registrationFailName() throws InterruptedException {
        //check for empty names
        registrationOP.openRegistration();
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualSurNameError = registrationOP.getSurName();
        String actualFirstNameError = registrationOP.getFirstName();
        Assert.assertTrue(actualFirstNameError.contains("Ungültiger Vorname"));
        Assert.assertTrue(actualSurNameError.contains("Ungültiger Nachname"));
    }

    @Test
    void registrationFailPassword() throws InterruptedException {
        //check for empty passwords
        registrationOP.openRegistration();
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualPasswordError = registrationOP.getPassword();
        String actualPasswordRepeatError = registrationOP.getPasswordRepeat();
        Assert.assertTrue(actualPasswordError.contains("Das Password muss mindestens 8 Zeichen lang sein"));
        Assert.assertTrue(actualPasswordRepeatError.contains("Das Password muss mindestens 8 Zeichen lang sein"));
    }

    @Test
    void registrationFailEmail() throws InterruptedException {
        //check for empty passwords
        registrationOP.openRegistration();
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualEmailError = registrationOP.getEmail();
        Assert.assertTrue(actualEmailError.contains("Das Format der Email ist nicht gültig"));
    }


    @Test
    void registrationFailCommonPassword() throws InterruptedException {
        //check for empty passwords
        registrationOP.openRegistration();
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

    @Test
    void registrationFailWrongFormat() throws InterruptedException {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.enterFirstName("12345678");
        registrationOP.enterSurName("12345678");
        registrationOP.enterEmail("12345678");
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualFirstNameError = registrationOP.getFirstName();
        String actualSurNameError = registrationOP.getSurName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertTrue(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertTrue(actualFirstNameError.contains("Ungültiger Vorname"));
        Assert.assertTrue(actualSurNameError.contains("Ungültiger Nachname"));

    }

    @Test
    void registrationFailWrongFormat2() throws InterruptedException {
        //check for wrong input format
        registrationOP.openRegistration();
        registrationOP.enterFirstName("*-*");
        registrationOP.enterSurName("*-*");
        registrationOP.enterEmail("name");
        registrationOP.clickReg();
        Thread.sleep(500);

        //After clicking registration with nothing entered an error notification should pop up
        //in the element, although it's a root element it is still accessible as part of the element
        String actualFirstNameError = registrationOP.getFirstName();
        String actualSurNameError = registrationOP.getSurName();
        String actualEmailError = registrationOP.getEmail();
        Assert.assertTrue(actualEmailError.contains("Das Format der Email ist nicht gültig"));
        Assert.assertTrue(actualFirstNameError.contains("Ungültiger Vorname"));
        Assert.assertTrue(actualSurNameError.contains("Ungültiger Nachname"));

    }


}
