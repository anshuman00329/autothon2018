package Listeners;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class MyListener implements ITestListener {
    protected static ExtentReports reports;
    protected static ExtentTest test;
    public void onTestStart(ITestResult result) {
        System.out.println("on test start");
        test = reports.startTest(result.getMethod().getDescription());
        //test.log(LogStatus.INFO, result.getMethod().getDescription() + "test is started");
        //test.log(LogStatus.INFO, result.getTestName() + "test is started");

    }
    public void onTestSuccess(ITestResult result) {
        System.out.println("on test success");
        test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test has passed");
    }
    public void onTestFailure(ITestResult result) {
        System.out.println("on test failure");
       // test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test has failed");
        test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable());
        //test.log(LogStatus.FAIL,  result.getThrowable().getStackTrace().getClass());

    }
    public void onTestSkipped(ITestResult result) {
        System.out.println("on test skipped");
        test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");

    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("on test sucess within percentage");
    }
    public void onStart(ITestContext context) {
        System.out.println("on start");
        //driver = new ChromeDriver(); // Set the drivers path in environment variables to avoid code(System.setProperty())
        reports = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");

    }
    public void onFinish(ITestContext context) {
        System.out.println("on finish");
        //driver.close();
        reports.endTest(test);
        reports.flush();
    }
}