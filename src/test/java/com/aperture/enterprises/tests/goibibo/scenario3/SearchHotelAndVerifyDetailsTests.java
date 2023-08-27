package com.aperture.enterprises.tests.goibibo.scenario3;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aperture.enterprises.pages.goibibo.HotelsPage;
import com.aperture.enterprises.tests.BaseTest;

public class SearchHotelAndVerifyDetailsTests extends BaseTest {

	@Test(description = "Search for a hotel and select the first the hotel to get the details on the rooms and rates, location, guest reviews, questions and answers and hotel policies")
	public void searchForHotelAndLogDetailsTest() throws InterruptedException {

		HotelsPage hotelsPage = new HotelsPage(driver);
		hotelsPage.searchForHotel("Ooty, Tamil Nadu, India");
		Assert.assertTrue(hotelsPage.getHotelSearchList().size() > 0, "No hotel were found in search");

		String hotelName = hotelsPage.getHotelSearchList().get(0).getText();
		hotelsPage.getHotelSearchListCard().get(0).click();

		hotelsPage.switchToWindowByPartialTitle("Book Cheap Flight");
		
		String actHotelName = hotelsPage.getPropName().getText().trim();
		Assert.assertEquals(hotelName, actHotelName, "hotel name before after click not matching, exp " + hotelName  + " and act is " + actHotelName);
		
		boolean isReviewSectionDisplayed = hotelsPage.verifyReviewSection();
		Assert.assertTrue(isReviewSectionDisplayed, "review section not displayed");

		boolean isLocationDisplayed = hotelsPage.verifyLocationSection();
		Assert.assertTrue(isLocationDisplayed, "location section not displayed");

		boolean isQnASectionDisplayed = hotelsPage.verifyQnASection();
		Assert.assertTrue(isQnASectionDisplayed, "QnA section not displayed");

		boolean isPoliciesSectionDisplayed = hotelsPage.verifyPoliciesSection();
		Assert.assertTrue(isPoliciesSectionDisplayed, "policies section not displayed");
		
	}

}
