package com.jostens.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.jostens.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ShoppingCartPage {
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	
	TestUtil genMethods;
	
	//Constructor
	public ShoppingCartPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new TestUtil();
	}
	
	@FindBy(xpath="//form[@id='cart_form']//div[@data-line-item='0']//span[@class='money ']")
	WebElement productPricePerProduct;
	
	@FindBy(xpath="//form[@id='cart_form']//div[@data-line-item='0']//input[@class='quantity-input quantity-element input']")
	WebElement productCount;
	
	@FindBy(xpath="//form[@id='cart_form']//div[@data-line-item='0']//span[@class='money']")
	WebElement productPriceTotal;
	
	@FindBy(xpath="//form[@id='cart_form']//p[@class='h3 cart__subtotal']//span[@class='money']")
	WebElement subtotal;
	
	@FindBy(xpath="//input[@id='checkout']")
	WebElement checkoutButton;
	
	public String getPricePerProduct(int row) {
		//Output a message to the report & system
		System.out.println("Retrieving the cost per copy of the product");
		reportLogger.log(LogStatus.INFO, "Retrieving the cost per copy of the product");
		
		//Initialize Variable(s)
		List<WebElement> productList = eDriver.findElements(By.xpath("//form[@id='cart_form']//div[@class='cart__card container is-align-center']//span[@class='money ']"));
		int productRow = productList.size() - row;
		
		return productList.get(productRow).getText();
	}
	
	public String getProductCount(int row) {
		//Output a message to the report & system
		System.out.println("Retrieving the number of copies of the product");
		reportLogger.log(LogStatus.INFO, "Retrieving the number of copies of the product");
		
		//Initialize Variable(s)
		List<WebElement> productList = eDriver.findElements(By.xpath("//form[@id='cart_form']//div[@class='cart__card container is-align-center']//input[@class='quantity-input quantity-element input']"));
		int productRow = productList.size() - row;
		
		return productList.get(productRow).getAttribute("value");
	}
	
	public String getProductPriceTotal(int row) {
		//Output a message to the report & system
		System.out.println("Retrieving the total cost of all copies of the product");
		reportLogger.log(LogStatus.INFO, "Retrieving the total cost of all copies of the product");
		
		//Initialize Variable(s)
		List<WebElement> productList = eDriver.findElements(By.xpath("//form[@id='cart_form']//div[@class='cart__card container is-align-center']//span[@class='money']"));
		int productRow = productList.size() - row;
		
		return productList.get(productRow).getText();
	}

	public String getProductSize(int row) {
		//Output a message to the report & system
		System.out.println("Retrieving the color of the product");
		reportLogger.log(LogStatus.INFO, "Retrieving the color of the product");
		
		//Initialize Variable(s)
		List<WebElement> productList = eDriver.findElements(By.xpath("//form[@id='cart_form']//div[@class='cart__card container is-align-center']//p[@class='item__properties']//span[2]"));
		int productRow = productList.size() - row;
		
		return productList.get(productRow).getText();
	}
	
	public String getProductColor(int row) {
		//Output a message to the report & system
		System.out.println("Retrieving the color of the product");
		reportLogger.log(LogStatus.INFO, "Retrieving the color of the product");
		
		//Initialize Variable(s)
		List<WebElement> productList = eDriver.findElements(By.xpath("//form[@id='cart_form']//div[@class='cart__card container is-align-center']//p[@class='item__properties']//span[4]"));
		int productRow = productList.size() - row;
		
		return productList.get(productRow).getText();
	}
	
	public String getSubtotal() {
		//Output a message to the report & system
		System.out.println("Retrieving the Subtotal for the Shopping Cart");
		reportLogger.log(LogStatus.INFO, "Retrieving the Subtotal for the Shopping Cart");
		
		//Return the Subtotal for the Shopping Cart
		return subtotal.getText();
	}
	
	public SoftAssert verifyShoppingCart(SoftAssert softAssert, String pricePerItem, String itemCount, String itemPriceTotal, String itemSubtotal, String dataRow) {
		//Initialize Variable(s)
		List<WebElement> productList = eDriver.findElements(By.xpath("//form[@id='cart_form']//div[@class='cart__card container is-align-center']"));
		int row = Integer.valueOf(dataRow);
		
		softAssert.assertEquals(getPricePerProduct(row), pricePerItem);
		softAssert.assertEquals(getProductCount(row), itemCount);
		softAssert.assertEquals(getProductPriceTotal(row), itemPriceTotal);
		
		softAssert.assertEquals(getSubtotal(), itemSubtotal);
		softAssert.assertEquals(productList.size(), 3);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
//	public SoftAssert verifyShoppingCart(SoftAssert softAssert, String pricePerItem, String itemCount, String itemPriceTotal, String itemSize, String itemColor, String itemSubtotal, String dataRow) {
//		//Initialize Variable(s)
//		List<WebElement> productList = eDriver.findElements(By.xpath("//form[@id='cart_form']//div[@class='cart__card container is-align-center']"));
//		int row = Integer.valueOf(dataRow);
//		
//		softAssert.assertEquals(getPricePerProduct(row), pricePerItem);
//		softAssert.assertEquals(getProductCount(row), itemCount);
//		softAssert.assertEquals(getProductPriceTotal(row), itemPriceTotal);
//		
//		softAssert.assertEquals(getProductSize(row), itemSize);
//		softAssert.assertEquals(getProductColor(row), itemColor);
//		
//		softAssert.assertEquals(getSubtotal(), itemSubtotal);
//		softAssert.assertEquals(productList.size(), 3);
//		
//		//Return the status for the SoftAssert
//		return softAssert;
//	}
	
	public void proceedToCheckout () {
		//Output a message to the report & system
		System.out.println("Proceeding to the Checkout Page");
		reportLogger.log(LogStatus.INFO, "Proceeding to the Checkout Page");
		
		//Click to proceed to the Checkout Page
		checkoutButton.click();
	}
	
	public SoftAssert verifyCheckoutPage(SoftAssert softAssert, String checkoutPageTitle) {
		//Initialize Variable(s)
		String browserTitle = eDriver.getTitle();
		
		softAssert.assertEquals(browserTitle, checkoutPageTitle);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}