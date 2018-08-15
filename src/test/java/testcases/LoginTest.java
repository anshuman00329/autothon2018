package testcases;

import commonutils.DriverUtil;
import commonutils.GenericUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver driver;
    GenericUtil wait = new GenericUtil();
    @BeforeTest
    @Parameters("browser")
    public void preConfig(String browser) throws MalformedURLException
    {
        driver = DriverUtil.launchBrowser(browser);

    }

    @Test
    public void test() throws InterruptedException {
        driver.get("https://www.gmail.com");
    }

    @AfterTest
    public void AfterTest(){
        driver.quit();
    }
}
