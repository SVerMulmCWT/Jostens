package com.jostens.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.jostens.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProductDetailPage {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	
	TestUtil genMethods;
	
	//Constructor
	public ProductDetailPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new TestUtil();
	}
	
	@FindBy(xpath="//select[@name='logoPosition']")
	WebElement productLogoPositionSelection;
	
	@FindBy(xpath="//div[@id='shopify-section-product__main']//div[@class='logo-box']")
	WebElement productLogoCoordinates;
	
	//form[@id="product_form_4341286043702"]//button[@name='add']
	@FindBy(xpath="//button[@name='add']")
	WebElement addToCartButton;
	
	@FindBy(xpath="//header[@id='header']//span[contains(text(), 'Cart')]")
	WebElement cartCheckout;
	
	public void selectProductColor(String color) {
		//Output a message to the report & system
		System.out.println("Attempting to select the specified color -> " + color);
		reportLogger.log(LogStatus.INFO, "Attempting to select the specified color -> " + color);
		
		//Create a specific/dynamic xpath expression based on which product color is desired
		String xpathExp = genMethods.createXPath("//div[@data-value=\"{0}\"]", color);
		
		//Select the desired product color (if not found, the default color will remain selected)
		if (eDriver.findElement(By.xpath(xpathExp)).isDisplayed()) {
			eDriver.findElement(By.xpath(xpathExp)).click();
		}
	}
	
	public void selectProductSize(String size) {
		//Output a message to the report & system
		System.out.println("Attempting to select the specified size -> " + size);
		reportLogger.log(LogStatus.INFO, "Attempting to select the specified size -> " + size);
		
		//Create a specific/dynamic xpath expression based on which product color is desired
		String xpathExp = genMethods.createXPath("//div[@data-value=\"{0}\"]", size);
		
		//Click the desired product size (if not found, the default size will remain selected)
		if (eDriver.findElement(By.xpath(xpathExp)).isDisplayed()) {
			eDriver.findElement(By.xpath(xpathExp)).click();
		}
	}
	
	public void selectProductLogo(int logoNumber) throws InterruptedException {
		//Output a message to the report & system
		System.out.println("Attempting to select the specified logo from the list, located at #" + logoNumber);
		reportLogger.log(LogStatus.INFO, "Attempting to select the specified logo from the list, located at #" + logoNumber);
		
		//Initialize the List of all WebElements that contain a logo
		List<WebElement> logoList = eDriver.findElements(By.xpath("//div[@id='design-selectors-container']//label[@class='valtira-radio-design']"));
		
		//Set the logoNumber's value to correspond with the xth entry of the list of logos
		if (logoNumber > 0 && logoNumber <= logoList.size()) {
			logoNumber = logoNumber - 1;
		} else {
			logoNumber = 0;
		}
		
		//Select the logo based on the 'logo number' sent in by the user
		logoList.get(logoNumber).click();
	}
	
	public void selectProductLogoPosition(String logoPosition) {
		//Output a message to the report & system
		System.out.println("Attempting to select the logo position -> " + logoPosition);
		reportLogger.log(LogStatus.INFO, "Attempting to select the logo position -> " + logoPosition);
		
		//Setup a 'Select' variable in reference to the Drop-Down WebElement that has the options for the logo's positions
		Select select = new Select(productLogoPositionSelection);
		
		//Select the relevant option from the drop-down list
		select.selectByVisibleText(logoPosition);
	}
	
	public SoftAssert verifyProductLogoCoordinates(SoftAssert softAssert, String left, String top, String width, String height) {
		//Output a message to the report & system
		System.out.println("Checking if the logo's coordinates matches expectations");
		reportLogger.log(LogStatus.INFO, "Checking if the logo's coordinates matches expectations");
		
		//Initialize Variable(s)
		boolean verificationStatus = false; //The 'verificationStatus' becomes 'true' if the logo's position passed successfully
		String positionAttribute = productLogoCoordinates.getAttribute("style");
		String leftAttribute = "left: " + left + "%";
		String topAttribute = "top: " + top + "%";
		String widthAttribute = "width: " + width + "%";
		String heightAttribute = "height: " + height + "%";
		
		//Check if all the positions match expectations, then output the results to the report & system, and return the status for the @Test's SoftAssert
		if (positionAttribute.contains(leftAttribute) && positionAttribute.contains(topAttribute) && positionAttribute.contains(widthAttribute) && positionAttribute.contains(heightAttribute)) {
			verificationStatus = true;
			System.out.println("Product's Logo Position Successful - The logo's coordinates/location on the T-Shirt matches expectations");
			reportLogger.log(LogStatus.PASS, "Product's Logo Position Successful - The logo's coordinates/location on the T-Shirt matches expectations");
		} else {
			verificationStatus = false;
			System.out.println("Product's Logo Position Failed - The logo's coordinates/location on the T-Shirt does not match expectations");
			reportLogger.log(LogStatus.FAIL, "Product's Logo Position Failed - The logo's coordinates/location on the T-Shirt does not match expectations");
		}
		
		//Check if the 'left' position matches expectations
		if (!positionAttribute.contains(leftAttribute)) {
			System.out.println("Product's Logo Position Failed - The logo's 'left' position on the T-Shirt does not match expectations. Expected 'left' position = " + leftAttribute + ". Actual position = " + positionAttribute);
			reportLogger.log(LogStatus.FAIL, "Product's Logo Position Failed - The logo's 'left' position on the T-Shirt does not match expectations. Expected 'left' position = " + leftAttribute + ". Actual position = " + positionAttribute);
		}
		
		//Check if the 'top' value/position matches expectations
		if (!positionAttribute.contains(topAttribute)) {
			System.out.println("Product's Logo Position Failed - The logo's 'top' value/position on the T-Shirt does not match expectations. Expected 'top' value/position = " + topAttribute + ". Actual position = " + positionAttribute);
			reportLogger.log(LogStatus.FAIL, "Product's Logo Position Failed - The logo's 'top' value/position on the T-Shirt does not match expectations. Expected 'top' value/position = " + topAttribute + ". Actual position = " + positionAttribute);
		}
		
		//Check if the 'width' value/position matches expectations
		if (!positionAttribute.contains(widthAttribute)) {
			System.out.println("Product's Logo Position Failed - The logo's 'width' value/position on the T-Shirt does not match expectations. Expected 'width' value/position = " + widthAttribute + ". Actual position = " + positionAttribute);
			reportLogger.log(LogStatus.FAIL, "Product's Logo Position Failed - The logo's 'width' value/position on the T-Shirt does not match expectations. Expected 'width' value/position = " + widthAttribute + ". Actual position = " + positionAttribute);
		}
		
		//Check if the 'height' position matches expectations
		if (!positionAttribute.contains(heightAttribute)) {
			System.out.println("Product's Logo Position Failed - The logo's 'height' value/position on the T-Shirt does not match expectations. Expected 'height' value/position = " + heightAttribute + ". Actual position = " + positionAttribute);
			reportLogger.log(LogStatus.FAIL, "Product's Logo Position Failed - The logo's 'height' value/position on the T-Shirt does not match expectations. Expected 'height' value/position = " + heightAttribute + ". Actual position = " + positionAttribute);
		}
		
		//Run the position check(s) through the SoftAssert, then return the SoftAssert
		softAssert.assertTrue(verificationStatus);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public void addToCart() {
		//Output a message to the report & system
		System.out.println("Adding the product to the Shopping Cart");
		reportLogger.log(LogStatus.INFO, "Adding the product to the Shopping Cart");
		
		//Check if the product is able to be added to the shopping cart
		if (addToCartButton.getText().equals("Sold Out")) {
			System.out.println("SOLD OUT - Cannot add the product to the Shopping Cart");
			reportLogger.log(LogStatus.INFO, "SOLD OUT - Cannot add the product to the Shopping Cart");
		}
		
		//Click to add the specified product to the Shopping Cart
		addToCartButton.click();
	}
	
	public void proceedToShoppingCart() {
		//Output a message to the report & system
		System.out.println("Proceeding to the Checkout Page");
		reportLogger.log(LogStatus.INFO, "Proceeding to the Checkout Page");
		
		//Click to proceed to the Checkout Page
		cartCheckout.click();
	}
	
	public SoftAssert verifyShoppingCartPage(SoftAssert softAssert, String shoppingCartTitle) {
		//Initialize Variable(s)
		String browserTitle = eDriver.getTitle();
		
		//Check if the shopping cart page title matches expectation
		softAssert.assertEquals(browserTitle, shoppingCartTitle);
		
		if (shoppingCartTitle.equals(browserTitle)) {
			System.out.println("Success - shopping cart page title matches expectation");
		} else {
			System.out.println("Failed - shopping cart page title does not match expectation. Expected shopping cart page title -> " + shoppingCartTitle + ", actual shopping cart page title -> " + browserTitle);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}