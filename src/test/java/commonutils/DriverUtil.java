package commonutils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverUtil {
    WebDriver driver;
    public WebDriver assignDriver(String browser) throws MalformedURLException {
        if (browser.equalsIgnoreCase("firefox"))
        {
            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("mobile")){
            DesiredCapabilities capabilities=DesiredCapabilities.android();

            // set the capability to execute test in chrome browser
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);

            // set the capability to execute our test in Android Platform
            capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);

            // we need to define platform name
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");

            // Set the device name as well (you can give any name)
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone");

            // set the android version as well
            capabilities.setCapability(MobileCapabilityType.VERSION,"6.0.1");

            // set the Android app package
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.chrome");

            // set the android app activity
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.chrome.Main");

            // Create object of URL class and specify the appium server address
            URL url= new URL("http://127.0.0.1:4723/wd/hub");

            // Create object of  AndroidDriver class and pass the url and capability that we created
            WebDriver driver = new AndroidDriver(url, capabilities);

            //Check whether the device is locked or not
            if (((AndroidDriver<WebElement>) driver).isLocked())
                ((AndroidDriver<WebElement>) driver).unlockDevice();
            else
                ((AndroidDriver<WebElement>) driver).lockDevice();
        }
        return driver;
    }
}
