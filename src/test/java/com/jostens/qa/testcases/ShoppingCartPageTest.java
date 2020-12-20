package com.jostens.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jostens.qa.base.TestBase;
import com.jostens.qa.pages.CheckoutPage;
import com.jostens.qa.pages.HomePage;
import com.jostens.qa.pages.LoginPage;
import com.jostens.qa.pages.ProductDetailPage;
import com.jostens.qa.pages.SchoolPage;
import com.jostens.qa.pages.SchoolStorePage;
import com.jostens.qa.pages.ShoppingCartPage;
import com.jostens.qa.util.ExcelMethods;
import com.jostens.qa.util.ExtentFactory;
import com.relevantcodes.extentreports.LogStatus;

public class ShoppingCartPageTest extends TestBase {
	//Define Variable(s)
	SoftAssert checkpoint;
	
	public ShoppingCartPageTest() {
		super();
	}
	
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Shopping Cart");
		column = 7;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("ShoppingCartPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		loginPage = new LoginPage(eDriver, reportLogger);
		homePage = new HomePage(eDriver, reportLogger);
		schoolPage = new SchoolPage(eDriver, reportLogger);
		schoolStorePage = new SchoolStorePage(eDriver, reportLogger);
		productDetailPage = new ProductDetailPage(eDriver, reportLogger);
		shoppingCartPage = new ShoppingCartPage(eDriver, reportLogger);
		checkoutPage = new CheckoutPage(eDriver, reportLogger);
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void addProductToCartTest(String productName, String shoppingCartTitle, String pricePerItem, String itemCount, String itemPriceTotal, String itemSubtotal, String finalResult, String dataRow) throws InterruptedException {
		System.out.println("@Test - ShoppingCartPageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Proceed to the Shopping Cart - only need to do this the one/first time
		if (dataRow.equals("1")) {
			productDetailPage.proceedToShoppingCart();
		}
		
		//Check if the Shopping Cart Page successfully loaded the relevant product's page
		checkpoint = productDetailPage.verifyShoppingCartPage(checkpoint, shoppingCartTitle);
		
		//Check if the Shopping Cart contains the relevant product info
		checkpoint = shoppingCartPage.verifyShoppingCart(checkpoint, pricePerItem, itemCount, itemPriceTotal, itemSubtotal, dataRow);
		
		//Assert all checkpoints
		checkpoint.assertAll();
	}
}