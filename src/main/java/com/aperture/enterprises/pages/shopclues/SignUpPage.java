package com.aperture.enterprises.pages.shopclues;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aperture.enterprises.pages.BasePage;

public class SignUpPage extends BasePage {

	public SignUpPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(linkText = "Sign In")
	private WebElement signInLink;
	
	@FindBy(linkText = "Login via OTP")
	private WebElement loginViaOtpLink;

	@FindBy(xpath = "//div[@id='login']//div[@class='error_text']/span[text()!='']")
	private WebElement errMsg;
	
	@FindBy(id = "main_user_login")
	private WebElement mainUserLoginTextbox;
	
	public WebElement getSignInLink() {
		return signInLink;
	}

	public WebElement getLoginViaOtpLink() {
		return loginViaOtpLink;
	}
	
	public String getErrorMsg(String data) {
		mainUserLoginTextbox.sendKeys(data);
		loginViaOtpLink.click();
		waitForEelemnt(errMsg);
		return errMsg.getText().trim();
	}
	
}
