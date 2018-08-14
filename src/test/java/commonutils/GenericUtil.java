package commonutils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testdata.Constants;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GenericUtil {
//    WebDriverWait wait;
//    public WebElement explicitWait(WebDriver driver, String xpath){
//        wait = new WebDriverWait(driver,30);
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//    }


    WebDriverWait wait;
    Select select;
    Actions actions;
    Set<String> windowIDs;
    String parentWindowID,childWindowID;
    String inputFileName="C:\\Autothon-2\\autothon\\resources\\InputData.xlsx";

    public void waitForPageToLoad(){
        DriverUtil.driver.manage().timeouts().implicitlyWait(Constants.maxWait, TimeUnit.SECONDS);
    }

    public void waitForPageToLoad(int waitTime){
        DriverUtil.driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }

    public void waitForElementAvailablility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void select(WebElement element, String text){
        select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void hoverOnElement(WebElement element){
        actions =new Actions(DriverUtil.driver);
        actions.moveToElement(element).perform();
    }

    public void sendMultipleKeys(){

    }

    public void switchToChildWindow(){
        windowIDs = DriverUtil.driver.getWindowHandles();
        Iterator<String> idIterator = windowIDs.iterator();
        parentWindowID = idIterator.next();
        childWindowID = idIterator.next();
        DriverUtil.driver.switchTo().window(childWindowID);
    }


}
