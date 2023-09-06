package com.aperture.enterprises.tests.shopclues.scenario1;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aperture.enterprises.pages.shopclues.SignUpPage;
import com.aperture.enterprises.tests.BaseTest;
import com.aperture.enterprises.utils.ExcelReader;

public class VerifyLoginDialogTests extends BaseTest {

	@Test(description = "Check the login credentials using various combinations of incorrect input values and verify the error message", dataProvider = "verifyLoginTestDP")
	public void verifyLoginTest(String data, String expMsg) throws InterruptedException {

		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.getSignInLink().click();

		String errorMsgActual = signUpPage.getErrorMsg(data);
		Assert.assertEquals(errorMsgActual, expMsg, "Error message not displayed properly");
		Reporter.log("Error message displayed properly " + expMsg);

	}

	@DataProvider(name = "verifyLoginTestDP")
	public static Object[][] verifyLoginTestData() {
		ExcelReader exlReader=new ExcelReader("Login");
		Object data[][] = exlReader.getAllData();
		return data;
	}

}
