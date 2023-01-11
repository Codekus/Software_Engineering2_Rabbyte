package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class LoginPO extends BaseFunctions {

    public String url = "http://localhost:8080/login";

    By loginButton = By.xpath("//vaadin-button[@part=\"vaadin-login-submit\"]");
    By headerWelcome = By.xpath("//h1[text()=\"Herzlich Willkommen\"]");
    By headerLogin = By.cssSelector("section > h2");
    By vaadinLoginForm = By.xpath("//vaadin-login-form-wrapper");
    By usernameInput = By.id("vaadinLoginUsername");
    By passwordInput = By.id("vaadinLoginPassword");
    By forgotPasswordButton = By.cssSelector("#forgotPasswordButton");
    By registerButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-button");
    By errorMessageText = By.cssSelector("section > div:nth-child(2) > h5");
    By alertMessage = By.id("vaadin-notification-card");

    public void openLogin() {
        outPrint("Trying to open: " + url);
        driver.get(url);
    }

    public void enterName(String name) {
        typeText(name, usernameInput);
    }

    public void enterPassword(String password) {
        typeText(password, passwordInput);
    }

    public void loginClick() {
        outPrint("Trying to click on Element: Log in Button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public void checkForLoginElements() {

        checkForPresence(headerWelcome);
        Assert.assertNotNull(locateElement(vaadinLoginForm).getShadowRoot().findElement(headerLogin));
        outPrint("Log in konnte gelesen werden.");

        checkForPresence(usernameInput);
        checkForPresence(passwordInput);
        checkForPresence(loginButton);
        Assert.assertNotNull(locateElement(vaadinLoginForm).getShadowRoot().findElement(forgotPasswordButton));
        outPrint("Passwort vergessen Button ist vorhanden.");

        checkForPresence(registerButton);
    }

    public void checkRegisterButton() {
        clickElement(registerButton);
        checkForPresence(By.xpath("//h1[text()=\"Registrierung\"]"));
        outPrint("Die Registrierung Seite wurde aufgerufen.");
    }

    public void errorMessage() {
        Assert.assertNotNull(locateElement(vaadinLoginForm).getShadowRoot().findElement(errorMessageText));
        outPrint("Die Error Message konnte gelesen werden.");
    }

    public void alertMessage() {
        checkForPresence(alertMessage);
    }

    public void checkMeinProfil() {
        checkForPresence(By.cssSelector("#header > vaadin-horizontal-layout > vaadin-menu-bar"));
    }

}
