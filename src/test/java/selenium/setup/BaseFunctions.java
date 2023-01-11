package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BaseFunctions extends TestSetup{


    public String url = "http://localhost:8080/login";

    Duration waitTime = Duration.ofSeconds(7);

    /*public void openLogin(){
        driver.get(url);
    }*/

    public void clickElement(By by){
        outPrint("Trying to click on Element with the 'by' locator: " + by);
        new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void clickElement(WebElement el){
        outPrint("Trying to click on Element: " + el.getTagName());
        new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(el)).click();
    }

    public void typeText(String text, By by){
        outPrint("Typing following text: " + text);
        WebElement element = new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(by));
        element.sendKeys(text);

    }

    public void typeText(String text, WebElement el){
        WebElement element = new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(el));
        element.sendKeys(text);
    }

    public SearchContext getShadowRootOfElement(By by){
        WebElement shadowHost = new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(by));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        return shadowRoot;

    }

    public String getString(By by){
        outPrint("Trying to get the Text of element with the 'by' locator: " + by);
        WebElement e = driver.findElement(by);
        return e.getText();
    }

    public WebElement getShadowElement(String shadow_host, String shadow_content){
        // provide shadow_host as Xpath and shadow content as ccsSelector
        WebElement shadowHost = driver.findElement(By.xpath(shadow_host));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement shadowContent = shadowRoot.findElement(By.id(shadow_content));
        return  shadowContent;
    }

    public void checkForPresence(By by) {

        Assert.assertTrue(isDisplayed(by),
                "Assert-Fehler: Das Element konnte nicht identifiziert werden [Locator: " + by + "]");
    }

    public boolean isDisplayed(By by) {
        long endTime = System.currentTimeMillis() + waitTime.toMillis();

        while (retryTimeIsNotUp(endTime)) {
            try {
                if (locateElement(by) != null) {
                    outPrint("Assert-Hinweis: Das Element konnte identifiziert werden [Locator: " + by + "]");
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    protected boolean retryTimeIsNotUp(long endTime) {
        return endTime > System.currentTimeMillis();
    }

    public WebElement locateElement(By by) {

        try {
            List<WebElement> locatedElements = new WebDriverWait(driver, waitTime)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            if (locatedElements.size() == 1) {
                return locatedElements.get(0);
            } else {
                outPrint("Not Unique: Driver found multiple elements.");
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    public void clearInput(By by) {
        locateElement(by).clear();
    }

    public void waitUntilElementIsVisible(By by) throws Exception {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
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
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(propPath);
            prop.store(out, null);
        } finally {
            assert out != null;
            out.close();
        }
        return testMailName + "+" + prop.getProperty("SELENIUM_MAIL_COUNT") + "@outlook.de";
    }

    public String getUniqueBusinessName() throws IOException {

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
        prop.setProperty("SELENIUM_MAIL_COUNT",
                String.valueOf(Integer.parseInt(prop.getProperty("SELENIUM_MAIL_COUNT")) + 1));
        FileOutputStream out = null;


        try {
            out = new FileOutputStream(propPath);
            prop.store(out, null);
        } finally {
            assert out != null;
            out.close();
        }
        return prop.getProperty("SELENIUM_MAIL_COUNT");
    }

}
