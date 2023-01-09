package selenium.setup;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


import java.io.File;
import java.io.IOException;

public class Listeners extends TestSetup implements ITestListener {

    public void onTestStart(ITestResult result){
        Reporter.log("Method name is - " + result.getName());
    }

    public void onTestSuccess(ITestResult result){

        try {
            File destFile = TestSetup.takeScreenshots(result.getName());
            System.out.println("before");
            Reporter.log("<a href='../../tmp/uploads/screenshots/" + destFile.getName() +
                    "'> <img src='../../tmp/uploads/screenshots/" + destFile.getName() + "' height='100' width='100'/> </a>");
            System.out.println("after");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestFailure(ITestResult result){
        try {
            File destFile = TestSetup.takeScreenshots(result.getName());
            Reporter.log("<a href='../../tmp/uploads/screenshots/" + destFile.getName() +
                    "'> <img src='../../tmp/uploads/screenshots/" + destFile.getName() + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
