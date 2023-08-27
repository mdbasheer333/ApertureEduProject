package com.aperture.enterprises.tests.goibibo.scenario4;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aperture.enterprises.pages.goibibo.HotelsPage;
import com.aperture.enterprises.tests.BaseTest;

public class SearchHotelByFilterTests extends BaseTest {

	@Test(description = "Search for a hotel in Ooty and apply filter based on pay at hotel, with ratings as 4, and first price range. Verify the applied filter changes")
	public void searchForHotelAndLogDetailsTest() throws InterruptedException {

		HotelsPage hotelsPage = new HotelsPage(driver);
		hotelsPage.searchForHotel("Ooty, Tamil Nadu, India");
		Assert.assertTrue(hotelsPage.getHotelSearchList().size() > 0, "No hotel were found in search");

		int initialHotelCount =  hotelsPage.getHotelSearchList().size();
		Reporter.log("Initial Hotel Count is " + initialHotelCount);
		
		hotelsPage.clickOnRatingWithSectionAndValue("Customer Ratings","4+");
		
		int ratingFilterHotelCount =  hotelsPage.getHotelSearchList().size();
		Reporter.log("Rating fillter Hotel Count is " + ratingFilterHotelCount);
		
		Assert.assertTrue(initialHotelCount>=ratingFilterHotelCount, "Rating filter is not applied properly");
		
		hotelsPage.clickOnRatingWithSectionAndValue("Customer Ratings","4+");

		hotelsPage.clickOnRatingWithSectionAndValue("Price Range","₹4001 - ₹6000");
		
		int priceFilterHotelCount =  hotelsPage.getHotelSearchList().size();
		Reporter.log("Price fillter Hotel Count is " + priceFilterHotelCount);
		
		Assert.assertTrue(initialHotelCount>=priceFilterHotelCount, "Price filter is not applied properly");
		
	}

}
