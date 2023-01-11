package selenium.setup;


//import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;
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
                System.out.println("Failed setting up ChromeDriver");
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--lang=de-DE");
            try{
                driver = new FirefoxDriver(options);
            } catch (Exception e){
                System.out.println("Failed setting up FirefoxDriver");
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

    public static File takeScreenshots(String testCase) throws IOException {
        // gets Name of TabellenTest
        // getDate();
        // Take base64Screenshot screenshot.
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/tmp/uploads/screenshots/";

        String screenshotName;

        File base64Screenshot = driver.getScreenshotAs(OutputType.FILE);
        // Create folder under project with name "screenshots" provided to destDir.
        if (!new File(path).exists()){
            new File(path).mkdirs();
        }
        // Set file testCase using current date time.
        screenshotName = testCase + "-" + dateFormatterMS() + ".png";
        String imagePath = path+ "/" + screenshotName;
        // setScrennshotName(screenshotName);
        // path to image
        File file = new File(imagePath);
        FileUtils.copyFile(base64Screenshot, file);
        return file;
    }

    public static String dateFormatterMS(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd_HH_mm_ss_SSS");
        String formattedDate = now.format(formatter);
        return formattedDate;
    }

    public static void outPrint(String text) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyy - HH:mm:ss");
        String formattedText = "[" + formatter.format(currentTime) + "] " + text;
        System.out.println(formattedText);

        try {
            Reporter.log(formattedText);
        } catch (Exception e){
            System.out.println("Failed Reporter.log");
        }
    }
}
