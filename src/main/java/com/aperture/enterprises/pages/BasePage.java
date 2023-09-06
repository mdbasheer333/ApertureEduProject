package com.aperture.enterprises.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
	}

	public void waitForAllEelemnts(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void waitForEelemnt(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForEelemnt(By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	
	public boolean isPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void switchToWindowByPartialTitle(String titleName) {
		String parentWindowName = driver.getWindowHandle();
		Set<String> allWin = driver.getWindowHandles();
		boolean found = false;
		for (String eachWin : allWin) {
			driver.switchTo().window(eachWin);
			if (driver.getTitle().contains(titleName)) {
				found = true;
				break;
			}
		}
		if (!found) {
			driver.switchTo().window(parentWindowName);
			throw new RuntimeException("window not found with title " + titleName);
		}
	}
	
	public void switchToWindowByPartialTitleStartsWith(String titleName) {
		String parentWindowName = driver.getWindowHandle();
		Set<String> allWin = driver.getWindowHandles();
		boolean found = false;
		for (String eachWin : allWin) {
			driver.switchTo().window(eachWin);
			if (driver.getTitle().startsWith(titleName)) {
				found = true;
				break;
			}
		}
		if (!found) {
			driver.switchTo().window(parentWindowName);
			throw new RuntimeException("window not found with title " + titleName);
		}
	}
	
	public void switchToWindowByIndex(int pos) {
		String parentWindowName = driver.getWindowHandle();
		Set<String> allWin = driver.getWindowHandles();
		if (pos>allWin.size()) {
			driver.switchTo().window(parentWindowName);
			throw new RuntimeException("window not found with pos " + pos);
		}
		driver.switchTo().window(allWin.toArray()[pos].toString());		
	}

	public void selectByText(WebElement element, String value) {
		Select drp=new Select(element);
		drp.selectByVisibleText(value);
	}
	
}
