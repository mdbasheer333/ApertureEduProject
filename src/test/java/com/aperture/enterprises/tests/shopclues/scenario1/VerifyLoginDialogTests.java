package com.aperture.enterprises.tests.shopclues.scenario1;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aperture.enterprises.pages.shopclues.SignUpPage;
import com.aperture.enterprises.tests.BaseTest;

public class VerifyLoginDialogTests extends BaseTest {

	@Test(description = "Search a hotel for one adult and log the hotel's name in TestNG")
	public void searchForHotelAndLogDetailsTest() throws InterruptedException {

		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.getSignInLink().click();

		String errorMsgActual = signUpPage.getErrorMsg("");
		String errorMsgExp = "Please enter email id or mobile number.";
		Assert.assertEquals(errorMsgActual, errorMsgExp, "Error message not displayed properly");
		Reporter.log("Error message displayed properly " + errorMsgExp);

		errorMsgActual = signUpPage.getErrorMsg("465465456");
		errorMsgExp = "Please enter valid email id or mobile number.";
		Assert.assertEquals(errorMsgActual, errorMsgExp, "Error message not displayed properly");
		Reporter.log("Error message displayed properly " + errorMsgExp);

		errorMsgActual = signUpPage.getErrorMsg("dummy@gmail");
		errorMsgExp = "Please enter valid email id or mobile number.";
		Assert.assertEquals(errorMsgActual, errorMsgExp, "Error message not displayed properly");
		Reporter.log("Error message displayed properly " + errorMsgExp);

	}

}
