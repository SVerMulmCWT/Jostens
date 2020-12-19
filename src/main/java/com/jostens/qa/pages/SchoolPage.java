package com.jostens.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SchoolPage {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	
	//Constructor
	public SchoolPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
	}
	
	//Retrieves the WebElement that displays the account owner's name - used to confirm that the correct person logged in
	@FindBy(xpath="//h3[contains(text(), 'School Store')]")
	WebElement schoolStoreButton;
	
	public void clickSchoolStoreButton() {
		//Output a message to the report & system
		System.out.println("Attempting to navigate to the school store");
		reportLogger.log(LogStatus.INFO, "Attempting to navigate to the school store");
		
		//Click the School Store button
		schoolStoreButton.click();
	}
	
}