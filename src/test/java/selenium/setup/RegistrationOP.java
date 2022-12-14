package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class RegistrationOP extends BaseFunctions {


    public String url = "http://localhost:8080/registration";

    SearchContext shadowHost = driver.findElement(By.xpath("pwa-id")).getShadowRoot();
    WebElement shadowContent = shadowHost.findElement(By.id("body > vaadin-vertical-layout"));/*
            .findElement(By.cssSelector("body > vaadin-vertical-layout")).getShadowRoot()
            .findElement(By.cssSelector("body > vaadin-vertical-layout > vaadin-vertical-layout")).getShadowRoot()
            .findElement(By.cssSelector("body > vaadin-vertical-layout > vaadin-vertical-layout > vaadin-vertical-layout")).getShadowRoot()
            .findElement(By.cssSelector("body > vaadin-vertical-layout > vaadin-vertical-layout > vaadin-vertical-layout > div > vaadin-form-layout")).getShadowRoot()
            .findElement(By.cssSelector("body > vaadin-vertical-layout > vaadin-vertical-layout > vaadin-vertical-layout > div > vaadin-form-layout > vaadin-text-field:nth-child(1)"))
            .findElement(By.cssSelector("#vaadin-text-field-input-10"));*/

    //SearchContext shadowRoot = shadowHost.getShadowRoot();
    //WebElement shadowContent = shadowRoot.findElement(By.cssSelector("body > vaadin-vertical-layout:nth-child(8)"));


    By firstName = By.id("vaadin-text-field-input-3");
    By surName = By.id("vaadin-text-field-input-3");
    By password = By.id("vaadin-text-field-input-4");
    By passwordRepeat = By.id("vaadin-text-field-input-5");
    By email = By.id("vaadin-text-field-input-7");

    public void openRegistration() { driver.get(url);  }

    public void enterName(String name) { typeText(name, shadowContent);  }
}