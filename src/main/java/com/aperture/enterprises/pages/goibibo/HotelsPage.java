package com.aperture.enterprises.pages.goibibo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.aperture.enterprises.pages.BasePage;

public class HotelsPage extends BasePage {

	public HotelsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[.='Hotels']")
	private WebElement hotelLink;

	@FindBy(xpath = "//input[contains(@placeholder,'Area, Landmark or Property Name')]")
	private WebElement hotelSearcTxtbox;

	@FindBy(xpath = "//ul[@id='downshift-1-menu']/li[1]")
	private WebElement hotelSearcListMenu;

	@FindBy(xpath = "//div[@data-id='auth-container']")
	private WebElement loginPopup;

	@FindBy(className = "icClose")
	private WebElement loginPopupClose;

	@FindBy(xpath = "//button[text()='Search']")
	private WebElement searchHotelButton;

	@FindBy(xpath = "//div[@itemtype and contains(@class,'HotelCardstyles')]//h4[@itemprop='name']")
	private List<WebElement> hotelSearchList;

	@FindBy(xpath = "//div[@itemtype and contains(@class,'HotelCardstyles')]")
	private List<WebElement> hotelSearchListCard;

	@FindBy(linkText = "Guest Reviews")
	private WebElement guestReviewsLink;

	@FindBy(linkText = "Property Policies")
	private WebElement properyPoliciesLink;

	@FindBy(linkText = "Location")
	private WebElement locationLink;

	@FindBy(linkText = "Questions & Answers")
	private WebElement QnAlink;

	@FindBy(xpath = "//div[contains(@class,'GuestReview')]")
	private WebElement reviewRatingSection;

	@FindBy(xpath = "//div[@id='hotel-policies']")
	private WebElement polociesSection;

	@FindBy(xpath = "//a[contains(text(),'property policies')]")
	private WebElement propertyPoliciesLink;

	@FindBy(xpath = "//section[@id='location']")
	private WebElement propertyLocationLink;

	@FindBy(xpath = "//h1[@itemprop='name']")
	private WebElement propName;

	@FindBy(xpath = "//button[@data-testid='selectRoomBtn']")
	private WebElement selectRoomButton;

	@FindBy(xpath = "//button[.='Proceed To Payment Options']")
	private WebElement proceedToCheckoutButton;

	@FindBy(xpath = "//select[contains(@class,'Name')]")
	private WebElement titleDropdown;

	@FindBy(xpath = "//input[contains(@placeholder,'First Name')]")
	private WebElement fnameTxtBox;

	@FindBy(xpath = "//input[contains(@placeholder,'Last Name')]")
	private WebElement lnameTxtBox;

	@FindBy(xpath = "//input[contains(@placeholder,'Email')]")
	private WebElement emailTxtBox;

	@FindBy(xpath = "//input[contains(@placeholder,'Phone')]")
	private WebElement phoneTxtBox;

	@FindBy(xpath = "//input[@id='confirm_check']/parent::span")
	private WebElement confirmProfileCheckbox;

	@FindBy(xpath = "//div[contains(.,'Credit/Debit Card') and contains(@class,'cardDesc')]")
	private WebElement creditDebitCardSection;

	@FindBy(xpath = "//p[contains(.,'Goibibo is enabling tokenization') and contains(@class,'subHeading')]/parent::div")
	private WebElement warningMsg;

	@FindBy(id = "cardNumber")
	private WebElement cardNumber;

	@FindBy(id = "expiryMonth")
	private WebElement expiryMonth;

	@FindBy(id = "expiryYear")
	private WebElement expiryYear;

	@FindBy(id = "cardCvv")
	private WebElement cardCvv;

	@FindBy(id = "nameOnCard")
	private WebElement nameOnCard;

	@FindBy(id = "billingCountry")
	private WebElement billingCountry;

	@FindBy(id = "billingState")
	private WebElement billingState;

	@FindBy(id = "billingCity")
	private WebElement billingCity;

	@FindBy(id = "billingPinCode")
	private WebElement billingPinCode;

	@FindBy(id = "billingAddress")
	private WebElement billingAddress;

	public WebElement getPropName() {
		return propName;
	}

	public List<WebElement> getHotelSearchListCard() {
		return hotelSearchListCard;
	}

	public WebElement getHotelLink() {
		return hotelLink;
	}

	public WebElement getHotelSearcTxtbox() {
		return hotelSearcTxtbox;
	}

	public WebElement getHotelSearcListMenu() {
		return hotelSearcListMenu;
	}

	public WebElement getLoginPopup() {
		return loginPopup;
	}

	public WebElement getLoginPopupClose() {
		return loginPopupClose;
	}

	public WebElement getSearchHotelButton() {
		return searchHotelButton;
	}

	public List<WebElement> getHotelSearchList() {
		return hotelSearchList;
	}

	public void searchForHotel(String hotelName) {
		if (loginPopup.isDisplayed()) {
			loginPopupClose.click();
		}
		hotelLink.click();
		hotelSearcTxtbox.sendKeys(hotelName);
		hotelSearcListMenu.click();
		searchHotelButton.click();
	}

	public void logSearchedHotelDetails() {
		waitForAllEelemnts(hotelSearchList);
		Reporter.log("Total number of hotels are " + hotelSearchList.size());
		for (int i = 0; i < hotelSearchList.size(); i++) {
			Reporter.log("Hotel name is " + hotelSearchList.get(i).getText().trim());
		}
	}

	public boolean verifyReviewSection() {
		guestReviewsLink.click();
		if (reviewRatingSection.isDisplayed()) {
			Reporter.log("Reporting section displayed, details are " + reviewRatingSection.getText().trim());
			return true;
		} else {
			Reporter.log("Reporting section not displayed");
			return false;
		}
	}

	public boolean verifyPoliciesSection() {
		properyPoliciesLink.click();
		if (polociesSection.isDisplayed() && propertyPoliciesLink.isDisplayed()) {
			String hotelPolicy = polociesSection.getText().trim();
			if (!hotelPolicy.isEmpty()) {
				Reporter.log("Policies section displayed with content...!");
				return true;
			} else {
				Reporter.log("Policies section content not displayed");
				return false;
			}
		} else {
			Reporter.log("Policies section not displayed");
			return false;
		}
	}

	public boolean verifyLocationSection() {
		locationLink.click();
		if (propertyLocationLink.isDisplayed()) {
			Reporter.log("Location section displayed");
			return true;
		} else {
			Reporter.log("Location section not displayed");
			return false;
		}
	}

	public boolean verifyQnASection() {
		QnAlink.click();
		return QnAlink.isDisplayed();
	}

	public void clickOnSelectRoom() {
		selectRoomButton.click();
	}

	public void fillGuestDetails(String title, String fname, String lname, String mail, String phone) {
		selectByText(titleDropdown, title);
		fnameTxtBox.sendKeys(fname);
		lnameTxtBox.sendKeys(lname);
		emailTxtBox.sendKeys(mail);
		phoneTxtBox.sendKeys(phone);
		confirmProfileCheckbox.click();
	}

	public void proceedToCheckout() {
		proceedToCheckoutButton.click();
	}

	public void selectCreditDebitCardForCheckout() {
		creditDebitCardSection.click();
	}

	public void fillCardDetails(String cardNumb, String month, String yr, String cvv, String cardHolderName) {
		waitForEelemnt(cardNumber);
		cardNumber.sendKeys(cardNumb);
		expiryMonth.sendKeys(month);
		driver.findElement(By.xpath("//li[text()='"+month+"']")).click();
		expiryYear.sendKeys(yr);
		driver.findElement(By.xpath("//li[text()='"+yr+"']")).click();
		cardCvv.sendKeys(cvv);
		nameOnCard.sendKeys(cardHolderName);
	}

	public void fillBillDetails(String country, String state, String city, String pincode, String billingAdd) {
		billingCountry.sendKeys(country);
		driver.findElement(By.xpath("//li[text()='"+country+"']")).click();
		billingState.sendKeys(state);
		billingCity.sendKeys(city);
		billingCity.sendKeys(city);
		billingPinCode.sendKeys(pincode);
		billingAddress.sendKeys(billingAdd);
	}

	public String getWarnignMessage() {
		return warningMsg.getText().trim();
	}

	public void clickOnRatingWithSectionAndValue(String section, String subSection) {
		By ratingsFilter=By.xpath("//div[.='"+section+"']/parent::div//span[contains(@class,'CheckBoxListText') and contains(.,'"+subSection+"')]");
		waitForEelemnt(ratingsFilter);
		driver.findElement(ratingsFilter).click();
	}

}
