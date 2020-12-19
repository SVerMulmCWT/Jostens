package com.jostens.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jostens.qa.base.TestBase;
import com.jostens.qa.pages.CheckoutPage;
import com.jostens.qa.pages.HomePage;
import com.jostens.qa.pages.LoginPage;
import com.jostens.qa.pages.PaymentPage;
import com.jostens.qa.pages.ProductDetailPage;
import com.jostens.qa.pages.SchoolPage;
import com.jostens.qa.pages.SchoolStorePage;
import com.jostens.qa.pages.ShoppingCartPage;
import com.jostens.qa.util.ExcelMethods;
import com.jostens.qa.util.ExtentFactory;
import com.relevantcodes.extentreports.LogStatus;

public class PaymentPageTest extends TestBase {
	//Define Variable(s)
	SoftAssert checkpoint;
	
	public PaymentPageTest() {
		super();
	}
	
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Payment");
		column = 3;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("PaymentPageTest Script");
		
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
		paymentPage = new PaymentPage(eDriver, reportLogger);
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void test(String creditCardNumber, String cardHolderName, String expiryDate, String securityCode, String expectedErrorMessage, String finalStatus, String dataRow) throws InterruptedException {
		System.out.println("@Test - PaymentPageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Click to continue to the payment page
		paymentPage.proceedToPayment();
		
		//Enter the user's credit card info
		paymentPage.enterCreditCardInfo(creditCardNumber, cardHolderName, expiryDate, securityCode);
		
		//Click 'Pay Now' to confirm payment
		paymentPage.proceedToPayment();
		Thread.sleep(6000);
		//Check if the expected error message appears
		checkpoint = paymentPage.verifyPayment(checkpoint, expectedErrorMessage);
		
	}
}