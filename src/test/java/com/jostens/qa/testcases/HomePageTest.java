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

public class HomePageTest extends TestBase {
	//Define Variable(s)
	SoftAssert checkpoint;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("School Search");
		column = 3;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("HomePageTest Script");
		
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
	public void searchForSchoolTest(String schoolName, String schoolStoreTitle, String finalStatus, String dataRow) {
		System.out.println("@Test - HomePageTest()");
		System.out.println(schoolName);
		System.out.println("dataRow + " + dataRow);
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Go to the 'Shop Products' page of the Jostens.com website
		if (iteration == 1) {
			homePage.goToSchoolSearch();
		} else {
			homePage.goToChangeSchool();
		}
		
		//Search for the desired school
		homePage.searchSchool(schoolName);
		
		checkpoint = homePage.verifySuccessfulSchoolSearch(checkpoint, schoolStoreTitle);
		
		//Assert all checkpoints
		checkpoint.assertAll();
	}
}