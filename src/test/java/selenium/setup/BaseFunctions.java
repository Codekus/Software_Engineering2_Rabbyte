package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public void waitUntilElementIsVisible(By by) throws Exception {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }
}
