package com.jostens.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.jostens.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SchoolStorePage {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	
	TestUtil genMethods;
	
	//Constructor
	public SchoolStorePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new TestUtil();
	}
	
	//WebElement that contains exclusively text asking the user to accept cookies (the button to accept cookies is another @FindBy(WebElement)
	@FindBy(xpath="//p[contains(text(), 'We use cookies on our website')]")
	WebElement cookiesMessage;
	
	//WebElement - click to accept cookies
	@FindBy(xpath="//a[contains(text(), 'I Agree') and @class='button button--primary js-close-fixed-message']")
	WebElement cookiesAccept;
	
	//WebElement - click to leave a product's detail page & back to the school's store
	@FindBy(xpath="//div[@class='breadcrumb__wrapper']//a[contains(@href, '/collections/')]")
	WebElement backToStore;
	
	/*
	 * When going into the School Store Page, there may be a message asking to accept cookies
	 * This method checks if there is a message asking the user/script to accept the cookies
	 * If the message exists, the method accepts the cookies by selecting, 'I Agree'
	 * 
	 * This method should be called right after the 'School Store Page' is accessed, if the user wants the script to accept the cookies
	 * This method is used in the @Test method in the 'LoginPageTest.java' class (underneath the 'src/test/java' folder & 'com.jostens.qa.testcases' package)
	 */
	public void acceptCookies() {
		//Output a message to the report & system
		System.out.println("Accepting the webpage's cookies");
		reportLogger.log(LogStatus.INFO, "Accepting the webpage's cookies");
		
		//Accept the cookies, if the option is present
		if (cookiesMessage.isDisplayed()) {
			cookiesAccept.click();
		}
	}
	
	/*
	 * This method attempts to locate & select a product, which brings up that products detailed page
	 * Once the product's detailed page is brought up, the user/script can check different designs, sizes, description, etc.
	 * The product can also be added to the cart from the product's detailed page (covered in other method[s])
	 * 
	 * This method is used in the @Test method in the 'LoginPageTest.java' class (underneath the 'src/test/java' folder & 'com.jostens.qa.testcases' package)
	 */
	public void selectProduct(String product) throws InterruptedException {
		//Output a message to the report & system
		System.out.println("Attempting to select the specified product -> " + product);
		reportLogger.log(LogStatus.INFO, "Attempting to select the specified product -> " + product);
		
		//Create a specific/dynamic xpath expression based on which product is desired
		String xpathExp = genMethods.createXPath("//a[contains(text(), \"{0}\")]", product);
		String xpathAddendum = "//parent::div[@class='product-thumbnail']//parent::div[@class='thumbnail__caption text-align-center']//parent::div[@class='product-wrap']//div[@class='product-image__wrapper']";
		xpathExp = xpathExp + xpathAddendum;
		
		Thread.sleep(6000);
		
		//Click on the specified product
		eDriver.findElement(By.xpath(xpathExp)).click();
	}
	
	public void returnToSchoolStore() {
		backToStore.click();
	}
	
	public SoftAssert verifySuccessfulProductSearch(SoftAssert softAssert, String productName) {
		//Initialize Variable(s)
		String productPageTitle = eDriver.getTitle();
		
		//Check if the product name page title matches expectation
		softAssert.assertEquals(productPageTitle, productName);
		
		if (productName.equals(productPageTitle)) {
			System.out.println("Success - product page title matches expectation");
		} else {
			System.out.println("Failed - product page title does not match expectation. Expected product page title -> " + productName + ", actual product page title -> " + productPageTitle);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}