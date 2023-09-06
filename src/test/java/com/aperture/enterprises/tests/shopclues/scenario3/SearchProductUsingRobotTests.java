package com.aperture.enterprises.tests.shopclues.scenario3;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aperture.enterprises.pages.shopclues.SearchPage;
import com.aperture.enterprises.pages.shopclues.SignUpPage;
import com.aperture.enterprises.tests.BaseTest;
import com.aperture.enterprises.utils.ExcelReader;

public class SearchProductUsingRobotTests extends BaseTest {

	@Test(description = "Search for a product and count the result using robot class library, and the result should not be less than zero")
	public void searchProductUsingRobotTest() throws InterruptedException, AWTException {
		SearchPage searchPage = new SearchPage(driver);
		int noOfProducts = searchPage.searchProductUsingRobotClassMethod();
		Assert.assertTrue(noOfProducts > 0, "no search results found for product ");
		searchPage.getAllProducts().get(0).click();
		searchPage.logProductPriceAndName();
	}

}
