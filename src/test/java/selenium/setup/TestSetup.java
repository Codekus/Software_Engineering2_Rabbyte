package selenium.setup;


//import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class TestSetup {


    public static RemoteWebDriver driver;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setup(String browser){

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=de-DE");

            try{
                driver = new ChromeDriver(options);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--lang=de-DE");
            try{
                driver = new FirefoxDriver(options);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.manage().window().maximize();


    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
