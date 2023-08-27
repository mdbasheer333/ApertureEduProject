package com.aperture.enterprises.tests.goibibo.scenario1;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aperture.enterprises.pages.goibibo.HotelsPage;
import com.aperture.enterprises.tests.BaseTest;

public class SearchHotelAndLogTests extends BaseTest{

	@Test(description = "Search a hotel for one adult and log the hotel's name in TestNG")
	public void searchForHotelAndLogDetailsTest() throws InterruptedException {
		
		HotelsPage hotelsPage=new HotelsPage(driver);
		hotelsPage.searchForHotel("Ooty, Tamil Nadu, India");
		hotelsPage.logSearchedHotelDetails();
		Assert.assertTrue(hotelsPage.getHotelSearchList().size()>0, "No hotel were found in search");
		
	}
	
}
