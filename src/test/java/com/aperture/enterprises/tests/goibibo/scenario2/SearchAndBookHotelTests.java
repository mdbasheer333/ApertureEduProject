package com.aperture.enterprises.tests.goibibo.scenario2;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aperture.enterprises.pages.goibibo.HotelsPage;
import com.aperture.enterprises.tests.BaseTest;

public class SearchAndBookHotelTests extends BaseTest {

	@Test(description = "Book for a hotel stay for one adult in Ooty, wherein the stay should be more than a day. Fill in dummy card details while making the payment and observe the error message",
			dataProvider = "hoteldetails")
	public void searchForHotelAndLogDetailsTest(Map<String, String> data) throws InterruptedException {

		HotelsPage hotelsPage = new HotelsPage(driver);
		hotelsPage.searchForHotel("Ooty, Tamil Nadu, India");
		Assert.assertTrue(hotelsPage.getHotelSearchList().size() > 0, "No hotel were found in search");

		String hotelName = hotelsPage.getHotelSearchList().get(0).getText();
		hotelsPage.getHotelSearchListCard().get(0).click();

		hotelsPage.switchToWindowByPartialTitle("Book Cheap Flight");

		String actHotelName = hotelsPage.getPropName().getText().trim();
		Assert.assertEquals(hotelName, actHotelName,
				"hotel name before after click not matching, exp " + hotelName + " and act is " + actHotelName);

		hotelsPage.clickOnSelectRoom();
		hotelsPage.fillGuestDetails(data.get("title"), data.get("fname"), data.get("lname"), data.get("mail"), data.get("mobile"));
		hotelsPage.proceedToCheckout();
		hotelsPage.selectCreditDebitCardForCheckout();

		hotelsPage.fillCardDetails(data.get("cardnumb"), data.get("date"), data.get("yr"), data.get("cvv"), data.get("cardholdername"));
		hotelsPage.fillBillDetails(data.get("country"), data.get("state"), data.get("city"), data.get("pin"), data.get("address"));

		String warningMsg = hotelsPage.getWarnignMessage();

		String msg1 = "Goibibo doesn’t store card info anymore.";
		String msg2 = "Goibibo is enabling tokenization as per RBI guidelines & will no longer save your card details. It will be stored within secure vaults of card networks like Visa, Mastercard, Rupay, etc.";

		Assert.assertTrue(warningMsg.contains(msg1), "warning message not displayed");
		Assert.assertTrue(warningMsg.contains(msg2), "warning message not displayed");

		Reporter.log("message displayed is " + warningMsg);

	}

	@DataProvider(name = "hoteldetails")
	public Object[][] hotelDetailsDP() {
		Object[][] data = new Object[1][1];
		Map<String, String> test_data = new HashMap<String, String>();

		test_data.put("title", "Mrs");
		test_data.put("fname", "dummy");
		test_data.put("lname", "test");
		test_data.put("mail", "dummy.test@gmail.com");
		test_data.put("mobile", "9897878708");

		test_data.put("cardnumb", "5458764645445578");
		test_data.put("date", "03");
		test_data.put("yr", "2026");
		test_data.put("cvv", "123");
		test_data.put("cardholdername", "dummy card");

		test_data.put("country", "India");
		test_data.put("state", "telangana");
		test_data.put("city", "hyd");
		test_data.put("pin", "500008");
		test_data.put("address", "dummy address");

		data[0][0] = test_data;
		return data;
	}

}
