package selenium.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class RegistrationOP extends BaseFunctions {


    public String url = "http://localhost:8080/registration";

    By forwardRegButton = By.xpath("/html/body/vaadin-vertical-layout/vaadin-button");
    By firstName = By.xpath("//vaadin-text-field[1]");
    By surName = By.xpath("//vaadin-text-field[2]");
    By password = By.xpath("//vaadin-text-field[3]");
    By passwordRepeat = By.xpath("//vaadin-text-field[4]");
    By email = By.xpath("//vaadin-text-field[5]");
    By headline = By.xpath("/html/body/vaadin-vertical-layout/vaadin-vertical-layout/h1");
    By tabStudent = By.xpath("//vaadin-tab[1]");
    By tabBusiness = By.xpath("//vaadin-tab[2]");

    public void openRegistration() { driver.get(url);  }

    public void enterName(String name) { typeText(name, firstName);
    }
    public void enterSurName(String name) { typeText(name, surName);
    }
    public void enterPassword(String pswd) { typeText(pswd, password);
    }
    public void enterPasswordRepeat(String pswd) { typeText(pswd, passwordRepeat);
    }
    public void enterEmail(String Email) { typeText(Email, email);
    }
    public void clickRegForward(){ clickElement(forwardRegButton);
    }
    public String getHeadLine(){return getString(headline);
    }
    public String getTabStudent(){return getString(tabStudent);
    }
    public String getTabBusiness(){return getString(tabBusiness);}


}