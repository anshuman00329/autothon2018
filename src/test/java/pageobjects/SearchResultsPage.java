package pageobjects;

import commonutils.DriverUtil;
import commonutils.GenericUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class SearchResultsPage {

    GenericUtil genericUtil = new GenericUtil();

    @FindBy(xpath = "//strong[contains(text(),'Guest rating')] ")
    WebElement guestRatingDropDown;

    @FindBy(xpath = "//input[@title='Good' and @type='radio']")
    WebElement selectGoodRatingRadioButton;

    @FindBy(xpath = "//th[@id='cal-heading-month']")
    WebElement headingMonth;

    @FindBy(xpath = "//span[contains(text(),'Family room')]")
    WebElement selectFamilyRoom;

    @FindBy(xpath = "//span[text()='Confirm']")
    WebElement confirmRoomButton;

    @FindBy(xpath="//button[text()='Done']")
    WebElement doneButton;

    @FindBy(xpath = "//button[@title='Select']")
    WebElement moreFiltersButton;

    @FindBy(xpath = "//button[text()='free WiFi'] ")
    WebElement freeWifiButton;

    @FindBy(xpath = "//button[text()='View Deal'][1]")
    WebElement viewDealButton;

    public void selectGuestRating(String rating){
        genericUtil.hoverOnElement(guestRatingDropDown);
        if(rating.equals("good"))
            selectGoodRatingRadioButton.click();
            doneButton.click();
    }

    public void selectDate(String checkDate,String date){
        if(checkDate.equals("checkin")) {
            genericUtil.waitForElementAvailablility(headingMonth);
            DriverUtil.driver.findElement(By.xpath("//td/time[contains(@datetime,'" + date + "')]")).click();
        }
        else if(checkDate.equals("checkout")) {
            genericUtil.waitForElementAvailablility(headingMonth);
            DriverUtil.driver.findElement(By.xpath("//td/time[contains(@datetime,'" + date + "')]")).click();
        }
        else {
            Reporter.log("Enter proper checkin/checkout");
        }
    }

    public void selectRoom(String roomType){
        if(roomType.equals("FamilyRoom"))
            genericUtil.waitClick(selectFamilyRoom);
            genericUtil.waitClick(confirmRoomButton);
    }

    public void selectFilter(){
        genericUtil.waitHover(moreFiltersButton);
        genericUtil.waitClick(freeWifiButton);
        genericUtil.waitClick(doneButton);
    }

    public void clickOnViewDeal(){
        genericUtil.waitClick(viewDealButton);
    }


}
