package com.aperture.enterprises.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	@Parameters({ "appUrl", "browser", "gridUrl" })
	public void tearUp(String appUrl, String browser, String gridUrl) throws MalformedURLException {		
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "remote_chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setCapability("browserVersion", "100");
			driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
			break;
		default:
			throw new RuntimeException("invalid browser type to launch");
		}		
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
