package com.aperture.enterprises.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	@Parameters({ "appUrl" })
	public void tearUp(String appUrl) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(appUrl);
	}

	@AfterMethod
	public void teadDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeTest
	public void beforeTest() {

	}

	@AfterTest
	public void afterTest() {

	}

	@BeforeSuite
	public void beforeSuit() {

	}

	@AfterSuite
	public void afterSuit() {

	}

}
