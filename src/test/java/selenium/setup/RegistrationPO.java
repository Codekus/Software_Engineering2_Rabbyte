package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class RegistrationPO extends BaseFunctions {


    public String url = "http://localhost:8080/registration";

    By forwardRegButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-button");
    By regButton = By.xpath("//vaadin-button");
    By firstName = By.xpath("//vaadin-text-field[1]");
    By surName = By.xpath("//vaadin-text-field[2]");
    By password = By.xpath("//vaadin-password-field[1]");
    By passwordRepeat = By.xpath("//vaadin-password-field[2]");
    By email = By.xpath("//vaadin-email-field");
    By headline = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/h1");
    By tabStudent = By.xpath("//vaadin-tab[1]");
    By tabBusiness = By.xpath("//vaadin-tab[2]");

    public void openRegistration() {
        driver.get(url);
    }

    public String getTabStudentSelected(){
        WebElement tabStudentSelected = driver.findElement(tabStudent);
        return tabStudentSelected.getAttribute("aria-selected");

    }
    public void enterFirstName(String name) {
        typeText(name, firstName);
    }

    public void enterSurName(String name) {
        typeText(name, surName);
    }

    public void enterPassword(String pswd) {
        typeText(pswd, password);
    }

    public void enterPasswordRepeat(String pswd) {
        typeText(pswd, passwordRepeat);
    }

    public void enterEmail(String Email) {
        typeText(Email, email);
    }

    public void clickRegForward() {
        clickElement(forwardRegButton);
    }

    public String getHeadLine() {
        return getString(headline);
    }

    public String getTabStudent() {
        return getString(tabStudent);
    }

    public String getTabBusiness() {
        return getString(tabBusiness);
    }

    public String getFirstName() {
        return getString(firstName);
    }

    public String getSurName() {
        return getString(surName);
    }

    public String getPassword() {
        return getString(password);
    }

    public String getPasswordRepeat() {
        return getString(passwordRepeat);
    }

    public String getEmail() {
        return getString(email);
    }

    public void clickReg() {
        clickElement(regButton);
    }

    public String getEmailAndCount() throws IOException {

        String propPath = "src/test/ressources/test_credentials.properties";
        Properties prop = new Properties();
        FileInputStream f = null;
        try{
            f = new FileInputStream(propPath);
            prop.load(f);
        } finally {
            assert f != null;
            f.close();
        }

        String testMailName = prop.getProperty("TEST_SELENIUM_MAIL_NAME");
        prop.setProperty("SELENIUM_MAIL_COUNT",
                String.valueOf(Integer.parseInt(prop.getProperty("SELENIUM_MAIL_COUNT")) + 1));
        FileOutputStream out = new FileOutputStream(propPath);
        prop.store(out, null);
        out.close();
        return testMailName + "+" + prop.getProperty("SELENIUM_MAIL_COUNT") + "@outlook.de";
    }


}