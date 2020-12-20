package com.jostens.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	
	//Constructor
	public HomePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
	}
	
	//WebElement, when clicked, goes to the page that the script can search for a school/group & locate their specific store page
	@FindBy(xpath="//div[@id='utility-bar']//span[contains(text(), 'Shop Products at my School/Group')]")
	WebElement shopProductsButton;
	
	//The School/Group field that can be used to search for that specific School's/Group's store page
	@FindBy(xpath="//input[@name='search']")
	WebElement searchSchoolName;
	
	@FindBy(xpath="//a[@id='affiliation-dropdown-container']")
	WebElement schoolDropDownList;
	
	@FindBy(xpath="//div[@id='affiliation-dropdown-desktop']//div[contains(text(), 'Change School')]")
	WebElement changeSchoolButton;
	
	/*
	 * Go to the 'School Search' page
	 * This method will allow the script go search for a school/group, then bring up that school's/group's store page
	 */
	public void goToSchoolSearch() {
		//Output a message to the report & system
		System.out.println("Proceeding to the School Search Page");
		reportLogger.log(LogStatus.INFO, "Proceeding to the School Search Page");
		
		//Click to proceed to the School Search Page
		shopProductsButton.click();
	}
	
	public void goToChangeSchool() {
		//Output a message to the report & system
		System.out.println("Proceeding to the School Search Page");
		reportLogger.log(LogStatus.INFO, "Proceeding to the School Search Page");
		
		//Click the School drop-down list to locate the 'Change School' option
		schoolDropDownList.click();
		
		//Click the 'Change School' option
		changeSchoolButton.click();
	}
	
	/*
	 * Enter the school/group name that the user wants to search for, then select that school/group from a list of search results
	 * Click the search result that matches the school/group name that was being searched for
	 * The function will leave the user with the school's/group's store page
	 * 
	 * Return the page for the school or group's store page
	 */
	public void searchSchool(String school) {
		//Output a message to the report & system
		System.out.println("Attempting search for the school -> " + school);
		reportLogger.log(LogStatus.INFO, "Attempting search for the school -> " + school);
		
		//Initialize Variable(s)
		boolean schoolFound = false; //Becomes true, if the school is found - if it remains false, the script will select the first school from the search results
		
		//Enter the search criteria (school name)
		searchSchoolName.sendKeys(school);
		
		//Retrieve the list of search results
		List<WebElement> schoolList = eDriver.findElements(By.xpath("//tbody[@class='results' and @data-url='/apps/profile/setCustomer.mvc']//td[@class='customer-name']"));
		
		//Loop through the search results & click the relevant search result
		for (int i = 0; i < schoolList.size(); i++) {
			
			if (schoolList.get(i).getText().equals(school)) {
				schoolFound = true;
				schoolList.get(i).click();
				break;
			}
		}
		
		/*
		 * If the desired school was not found in the search results, select the first entry of the results
		 * This way, the rest of the test (suite) can continue, even if the relevant school was not found
		 */
		if (!schoolFound) {
			schoolList.get(0).click();
		}
	}
	
	public SoftAssert verifySuccessfulSchoolSearch(SoftAssert softAssert, String schoolName) {
		//Output a message to the report & system
		System.out.println("Checking if the school page title matches expectation (aka. the relevant school page was properly searched)");
		reportLogger.log(LogStatus.INFO, "Checking if the school page title matches expectation (aka. the relevant school page was properly searched)");
		
		//Initialize Variable(s)
		String schoolPageTitle = eDriver.getTitle();
		
		//Check if the school's page title matches expectation
		softAssert.assertEquals(schoolPageTitle, schoolName);
		
		if (schoolName.equals(schoolPageTitle)) {
			System.out.println("Success - school page title matches expectation");
		} else {
			System.out.println("Failed - school page title does not match expectation. Expected school page title info -> " + schoolName + ", actual school page title info -> " + schoolPageTitle);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}