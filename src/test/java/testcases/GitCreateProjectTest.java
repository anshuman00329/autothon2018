package testcases;

import commonutils.DriverUtil;
import commonutils.GenericUtil;
import commonutils.LoadDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LaunchPage;
import pageobjects.Loginpage;
import testdata.Constants;

import java.io.File;
import java.net.MalformedURLException;

public class GitCreateProjectTest extends GenericUtil{
    static Logger logger = Logger.getLogger(TrivagoBookingTest.class);
    RemoteWebDriver driver;
    DriverUtil driverUtil;
    LaunchPage launchPage;
    Loginpage loginpage;

    @BeforeTest
    @Parameters({"executionEnv","browser"})
    public void preConfig(String executionEnv, String browser) throws MalformedURLException
    {
        String log4jConfigFile = System.getProperty("user.dir")
                + File.separator + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
        logger.info("Preconfig");
        driverUtil = new DriverUtil();
        driver = driverUtil.launchDriver(executionEnv, browser);
        LoadDriverManager.setWebdriver(driver);
        LoadDriverManager.getWebdriver().get(Constants.testUrl);
        waitForPageToLoad();
        launchPage = PageFactory.initElements(LoadDriverManager.getWebdriver(),LaunchPage.class);
        loginpage = PageFactory.initElements(LoadDriverManager.getWebdriver(),Loginpage.class);
    }

    @Test(description = "Trivago Booking Test")
    public void trivagoTest() {
        logger.info("Test to create project on git");
        launchPage.clickOnSignInButton();
        loginpage.enterUserName(fetchData("USERNAME"));
        loginpage.enterPassword(fetchData("PASSWORD"));
        loginpage.clickOnSignInButton();
    }
    @AfterTest
    public void close(){
        driverUtil.driver.quit();
    }
}
