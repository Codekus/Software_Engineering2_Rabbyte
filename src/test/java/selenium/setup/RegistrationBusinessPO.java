package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class RegistrationBusinessPO extends BaseFunctions {


    public String url = "http://localhost:8080/registration";

    By forwardRegButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-button");
    By regButton = By.xpath("//vaadin-button");
    public By business = By.xpath("//vaadin-text-field");
    public By password = By.xpath("//vaadin-password-field[1]");
    By passwordRepeat = By.xpath("//vaadin-password-field[2]");
    public By email = By.xpath("//vaadin-email-field");
    By headline = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/h1");
    By tabStudent = By.xpath("//vaadin-tab[1]");
    By tabBusiness = By.xpath("//vaadin-tab[2]");
    By successfulRegOkButton = By.xpath("/html/body/vaadin-dialog-overlay/flow-component-renderer/div/vaadin-vertical-layout/vaadin-horizontal-layout[2]/vaadin-button");
    By activationWindow = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout");
    public By activationButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/vaadin-button");
    By activationConfirmation = By.xpath("/html/body/vaadin-dialog-overlay/flow-component-renderer");
    By loginBanner = By.xpath("/html/body/vaadin-vertical-layout");

    public By successfulRegWindow = By.xpath("/html/body/vaadin-dialog-overlay/flow-component-renderer");

    public void openRegistration() {
        driver.get(url);
        clickElement(tabBusiness);
    }

    public String getTabBusinessSelected() {
        WebElement tabBusinessSelected = driver.findElement(tabBusiness);
        return tabBusinessSelected.getAttribute("aria-selected");
    }

    public void enterPassword(String pswd) {
        typeText(pswd, password);
    }

    public void enterPasswordRepeat(String pswd) {
        typeText(pswd, passwordRepeat);
    }

    public void enterBusinessName(String name) {
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

    public String getBusinessName() {
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

    public String getRegNotification() {
        return getString(successfulRegWindow);
    }

    public void clickSuccessfulRegOkButton() {
        clickElement(successfulRegOkButton);
    }

    public String getLoginBanner() {
        return getString(loginBanner);
    }

    public String getActivationText() {
        return getString(activationWindow);
    }

    public void clickActivationButton() {
        clickElement(activationButton);
    }

    public String getActivationConfiramtion() {
        return getString(activationConfirmation);
    }




}
