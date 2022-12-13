package selenium.setup;

import org.openqa.selenium.By;

public class LoginPO extends BaseFunctions{

    public String url = "http://localhost:8080/login";

    By loginButton = By.name("buttonName");
    By loginButtonID = By.id("vaadinLoginUsername");

    public void openLogin(){
        driver.get(url);
    }

    public void enterName(String name){
        typeText(name, loginButtonID);
    }


}
