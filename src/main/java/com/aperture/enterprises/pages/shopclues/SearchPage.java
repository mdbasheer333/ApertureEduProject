package com.aperture.enterprises.pages.shopclues;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.aperture.enterprises.pages.BasePage;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "autocomplete")
	private WebElement searchBox;

	@FindBy(linkText = "Search")
	private WebElement searchButton;

	@FindBy(className = "f_price")
	private WebElement productPrice;
	
	@FindBy(xpath = "//button[text()='Allow']")
	private WebElement allowPopup;

	@FindBy(xpath = "//h1[@itemprop='name']")
	private WebElement productName;

	@FindBy(xpath = "//div[@class='row']//a")
	private List<WebElement> allProducts;

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public List<WebElement> getAllProducts() {
		return allProducts;
	}

	public int searchProduct(String product) {
		searchBox.sendKeys(product);
		searchButton.click();
		return allProducts.size();
	}

	public void logProductPriceAndName() {
		switchToWindowByIndex(1);
		Reporter.log("Price is " + productPrice.getText());
		Reporter.log("Product full name is " + productName.getText());
	}

	public int searchProductUsingRobotClassMethod() throws AWTException, InterruptedException {
		Robot robot = new Robot();
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.delay(1000);
		searchBox.clear();
		robot.delay(1000);
		if(isPresent(allowPopup)) {
			allowPopup.click();
		}
		searchBox.click();
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		return allProducts.size();
	}

}
