package selenium.setup;

import org.openqa.selenium.By;
import org.testng.Assert;


public class LoginPO extends BaseFunctions{

    public String url = "http://localhost:8080/login";

    By loginButton = By.xpath("//vaadin-button[@part=\"vaadin-login-submit\"]");
    By headerWelcome = By.xpath("//h1[text()=\"Herzlich Willkommen\"]");
    By headerLogin = By.xpath("//h2[text()=\"Log in\"]");
    By vaadinLoginForm = By.xpath("//vaadin-login-form-wrapper");
    By usernameInput = By.id("vaadinLoginUsername");
    By passwordInput = By.id("vaadinLoginPassword");
    By forgotPasswordButton = By.xpath("//vaadin-button[@id=\"forgotPasswordButton\"]");
    By registerButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-button");

    public void openLogin(){
        outPrint("Trying to open: " + url);
        driver.get(url);
    }

    public void enterName(String name){
        typeText(name, usernameInput);
    }

    public void enterPassword(String password) {
        typeText(password, passwordInput);
    }

    public void loginClick() {
        clickElement(loginButton);
    }

    public void checkForPresence() {

        checkForPresence(headerWelcome);
        Assert.assertNotNull(locateElement(vaadinLoginForm).getShadowRoot().findElement(headerLogin));

        checkForPresence(usernameInput);
        checkForPresence(passwordInput);
        checkForPresence(loginButton);
        //checkForPresence(forgotPasswordButton);
        checkForPresence(registerButton);
    }

    public void checkRegisterButton() {
        clickElement(registerButton);
        checkForPresence(By.xpath("//h1[text()=\"Registrierung\"]"));
    }
}
