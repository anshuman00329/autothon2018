package testcases;

import commonutils.DriverUtil;
import commonutils.GenericUtil;
import commonutils.LoadDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testdata.Constants;

import java.io.File;
import java.net.MalformedURLException;

public class TrivagoBookingTest {

    static Logger logger = Logger.getLogger(TrivagoBookingTest.class);
    RemoteWebDriver driver;
    GenericUtil genericUtil;
    DriverUtil driverUtil = new DriverUtil();

    @BeforeTest
    @Parameters({"executionEnv","browser"})
    public void preConfig(String executionEnv, String browser) throws MalformedURLException
    {
        String log4jConfigFile = System.getProperty("user.dir")
                + File.separator + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
        logger.info("Preconfig");
        driver = driverUtil.launchDriver(executionEnv, browser);
        LoadDriverManager.setWebdriver(driver);
        driver.get(Constants.testUrl);
        genericUtil = new GenericUtil();
        genericUtil.waitForPageToLoad();
    }

    @Test(description = "Trivago Booking Test")
    public void trivagoTest() throws InterruptedException {
        logger.info("Test to book tickets on Trivago");
        try {
            //genericUtil.waitForPageToLoad();
//            searchResultsPage.selectDate("checkin", "2018-08-27");
//            searchResultsPage.selectDate("checkout", "2018-08-31");
//            searchResultsPage.selectRoom("FamilyRoom");
//            searchResultsPage.selectGuestRating("good");
//            searchResultsPage.selectFilter();
//            genericUtil.waitForPageToLoad();
//            searchResultsPage.clickOnViewDeal();
//            //fabHotelPage.selectRoom();
//            genericUtil.switchToChildWindow();
//            fabHotelPage.selectRoom();
//            fabHotelPage.clickOnProceedToPayButton();
        }
        catch(Exception ex)
        {
            logger.error("Something is wrong",ex);
        }
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,1000)");

        Thread.sleep(5000);

    }
    @AfterTest
    public void close(){
        driverUtil.driver.close();
    }
}
