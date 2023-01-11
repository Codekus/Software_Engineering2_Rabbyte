package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class RegistrationPO extends BaseFunctions {


    public String url = "http://localhost:8080/registration";

    By forwardRegButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-button");
    By regButton = By.xpath("//vaadin-button");
    public By firstName = By.xpath("//vaadin-text-field[1]");
    By surName = By.xpath("//vaadin-text-field[2]");

    public By business = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/vaadin-vertical-layout/div/vaadin-form-layout/vaadin-text-field");
    public By password = By.xpath("//vaadin-password-field[1]");
    By passwordRepeat = By.xpath("//vaadin-password-field[2]");

    public By email = By.xpath("//vaadin-email-field");
    By headline = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/h1");
    By tabStudent = By.xpath("//vaadin-tab[1]");
    By tabBusiness = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/vaadin-tabs/vaadin-tab[2]");

    public By successfulRegWindow = By.xpath("/html/body/vaadin-dialog-overlay/flow-component-renderer");

    By successfulRegOkButton = By.xpath("/html/body/vaadin-dialog-overlay/flow-component-renderer/div/vaadin-vertical-layout/vaadin-horizontal-layout[2]/vaadin-button");

    By activationWindow = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout");
    public By activationButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/vaadin-button");
    By activationConfirmation = By.xpath("/html/body/vaadin-dialog-overlay/flow-component-renderer");
    By loginBanner = By.xpath("/html/body/vaadin-vertical-layout");


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
    public String getLoginBanner(){return getString(loginBanner);}
    public void clickActivationButton(){clickElement(activationButton);}
    public String getActivationConfiramtion(){return getString(activationConfirmation);}
    public void enterBusinessName(String name) {
        typeText(name, business);
    }
    public String getBusinessName() {
        return getString(business);
    }
    public String getTabBusinessSelected() {
        WebElement tabBusinessSelected = driver.findElement(tabBusiness);
        return tabBusinessSelected.getAttribute("aria-selected");
    }
    public void clickBusiness(){
        clickElement(tabBusiness);
    }
}