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

    public void openLogin(){
        driver.get(url);
    }

    public void clickElement(By by){
        new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void clickElement(WebElement el){
        new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(el)).click();
    }

    public void typeText(String text, By by){
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
}
