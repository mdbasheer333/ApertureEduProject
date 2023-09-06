package com.aperture.enterprises.tests.shopclues.scenario2;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aperture.enterprises.pages.shopclues.SearchPage;
import com.aperture.enterprises.pages.shopclues.SignUpPage;
import com.aperture.enterprises.tests.BaseTest;
import com.aperture.enterprises.utils.ExcelReader;

public class SearchProductTests extends BaseTest {

	@Test(description = "Search for various products such as nike shoes, fast track watches, apple phones and so on. Click on the product that is displayed first on the web page to log product name and price", dataProvider = "searchProductTestDP")
	public void searchProductTest(String data) throws InterruptedException {
		SearchPage searchPage = new SearchPage(driver);
		int noOfProducts = searchPage.searchProduct(data);
		Assert.assertTrue(noOfProducts > 0, "no search results found for product " + data);
		searchPage.getAllProducts().get(0).click();
		searchPage.logProductPriceAndName();
	}

	@DataProvider(name = "searchProductTestDP")
	public static Object[][] searchProductTestData() {
		ExcelReader exlReader = new ExcelReader("SearchProduct");
		Object data[][] = exlReader.getAllData();
		return data;
	}

}
