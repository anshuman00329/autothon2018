package testcases;

import commonutils.DriverUtil;
import commonutils.GenericUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.FabHotelPage;
import pageobjects.SearchResultsPage;
import pageobjects.TrivagoLaunchPage;
import testdata.Constants;

import java.net.MalformedURLException;

public class TrivagoBookingTest {

    WebDriver driver;
    TrivagoLaunchPage launchPage;
    SearchResultsPage searchResultsPage;
    GenericUtil genericUtil;
    FabHotelPage fabHotelPage;

    @BeforeTest
    @Parameters("browser")
    public void preConfig(String browser) throws MalformedURLException
    {
        driver = DriverUtil.launchBrowser(browser);
        driver.get(Constants.trivagoUrl);
        genericUtil = new GenericUtil();
        genericUtil.waitForPageToLoad();
        launchPage = PageFactory.initElements(DriverUtil.driver, TrivagoLaunchPage.class);
        searchResultsPage = PageFactory.initElements(DriverUtil.driver, SearchResultsPage.class);
        fabHotelPage = PageFactory.initElements(DriverUtil.driver, FabHotelPage.class);
    }

    @Test
    public void trivagoTest() throws InterruptedException {
        launchPage.searchCity("Pune");
        //genericUtil.waitForPageToLoad();
        searchResultsPage.selectDate("checkin","2018-08-27");
        searchResultsPage.selectDate("checkout","2018-08-31");
        searchResultsPage.selectRoom("FamilyRoom");
        searchResultsPage.selectGuestRating("good");
        searchResultsPage.selectFilter();
        genericUtil.waitForPageToLoad();
        searchResultsPage.clickOnViewDeal();
        //fabHotelPage.selectRoom();
        genericUtil.switchToChildWindow();
        fabHotelPage.selectRoom();
        fabHotelPage.clickOnProceedToPayButton();

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,1000)");

        Thread.sleep(5000);

    }
    //@AfterTest
    public void close(){
        DriverUtil.driver.quit();
    }
}
