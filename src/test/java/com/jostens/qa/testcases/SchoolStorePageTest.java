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

public class SchoolStorePageTest extends TestBase {
	//Define Variable(s)
	SoftAssert checkpoint;
	
	public SchoolStorePageTest() {
		super();
	}
	
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Product Search");
		column = 11;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("SchoolStorePageTest Script");
		
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
	public void productSearchTest(String product, String productPageTitle, String productColor, String productSize, String logoNumber, String logoPosition, String logoLeftValue, String logoTopValue, String logoWidthValue, String logoHeightValue, String finalResult, String dataRow) throws InterruptedException {
		System.out.println("@Test - SchoolStorePageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		int logoNum = Integer.valueOf(logoNumber);
		
		if (dataRow.equals("1")) {
			//Go to the School's Store Page
			schoolPage.clickSchoolStoreButton();
			
			//Accept the cookies on the School's Store Page
			schoolStorePage.acceptCookies();
		}
		
		//Select the desired product
		schoolStorePage.selectProduct(product);
		
		//Check if the product search successfully loaded the relevant product's page
		checkpoint = schoolStorePage.verifySuccessfulProductSearch(checkpoint, productPageTitle);
		
		//Assert all checkpoints
		checkpoint.assertAll();
		
		//Select the product's color
		productDetailPage.selectProductColor(productColor);
		
		//Select the product's size
		productDetailPage.selectProductSize(productSize);
		
		//Select the product's design
		productDetailPage.selectProductLogo(logoNum);
		
		//Select the product's design position
		productDetailPage.selectProductLogoPosition(logoPosition);
		
		//Check that the correct design & design position were selected correctly
		checkpoint = productDetailPage.verifyProductLogoCoordinates(checkpoint, logoLeftValue, logoTopValue, logoWidthValue, logoHeightValue);
		
		Thread.sleep(3000);
		
		//Add the product into the cart
		productDetailPage.addToCart();
		
		Thread.sleep(7000);
		
		//Go back to the store
		schoolStorePage.returnToSchoolStore();
		
		//Assert all checkpoints
		checkpoint.assertAll();
	}
}