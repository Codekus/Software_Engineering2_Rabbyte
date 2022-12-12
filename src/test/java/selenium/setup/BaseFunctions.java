package selenium.setup;

public class BaseFunctions extends TestSetup{

    public String url = "http://localhost:8080/login";

    public void openLogin(){
        driver.get(url);
    }
}
