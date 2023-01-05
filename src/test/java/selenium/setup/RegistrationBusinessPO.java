package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationBusinessPO extends BaseFunctions {


    public String url = "http://localhost:8080/registration";

    By forwardRegButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-button");
    By regButton = By.xpath("//vaadin-button");
    By business = By.xpath("//vaadin-text-field");
    By password = By.xpath("//vaadin-password-field[1]");
    By passwordRepeat = By.xpath("//vaadin-password-field[2]");
    By email = By.xpath("//vaadin-email-field");
    By headline = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/h1");
    By tabStudent = By.xpath("//vaadin-tab[1]");
    By tabBusiness = By.xpath("//vaadin-tab[2]");

    public void openRegistration() {
        driver.get(url);
        clickElement(tabBusiness);
    }

    public String getTabBusinessSelected(){
        WebElement tabBusinessSelected = driver.findElement(tabBusiness);
        return tabBusinessSelected.getAttribute("aria-selected");
    }

    public void enterPassword(String pswd) {
        typeText(pswd, password);
    }

    public void enterPasswordRepeat(String pswd) {
        typeText(pswd, passwordRepeat);
    }

    public void enterBusinessName(String name){
        typeText(name, business);
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

    public String getBusinessName(){
        return getString(business);
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

}
