package com.jostens.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	
	//Constructor
	public LoginPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
	}
	
	//WebElement - click to bring down the drop-down list in order to access the login fields & button
	@FindBy(xpath="//a[@id='sign-in-dropdown-toggle__utility-bar']")
	WebElement signinDropDown;
	
	//WebElement - input field that holds/receives the user's username
	@FindBy(xpath="//input[@id='username']")
	WebElement usernameField;
	
	//WebElement - input field that holds/receives the user's password
	@FindBy(xpath="//input[@id='password']")
	WebElement passwordField;
	
	//WebElement - checkbox that tells the website whether to remember the user's credentials
	@FindBy(xpath="//input[@id='remember']")
	WebElement rememberUser;
	
	//WebElement - click to login with the current, specified credentials
	@FindBy(xpath="//div[@id='sign-in-dropdown-desktop']//button[contains(text(), 'Sign In')]")
	WebElement signinButton;
	
	//Retrieves the WebElement that displays the account owner's name - used to confirm that the correct person logged in
	@FindBy(xpath="//span[@id='profile-username']")
	WebElement usernameWelcome;
	
	//WebElement - click to bring down the user's account options
	@FindBy(xpath="//a[@id='user-dropdown-toggle__utility-bar']")
	WebElement accountDropDown;
	
	//WebElement - click to logout of account
	@FindBy(xpath="//div[@id='user-dropdown-desktop']//div[@class='link-section link-section--1-cols']//ul[@class='list-unstyled']//a[contains(text(), 'Logout')]")
	WebElement logoutButton;
	
	public void accessWebsite(String website) {
		//Output a message to the report & system
		System.out.println("Navigating to the website -> " + website);
		reportLogger.log(LogStatus.INFO, "Navigating to the website -> " + website);
		
		//Access the specified website
		for (int i = 0; i < 6; i++) {
			try {
				eDriver.get(website);
				break;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void login(String username, String password) {
		//Output a message to the report & system
		System.out.println("Attempting to login with the userid -> " + username);
		reportLogger.log(LogStatus.INFO, "Attempting to login with the userid -> " + username);
		
		//Bring up the login input fields
		signinDropDown.click();
		
		//Enter login credentials
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		
		//Un-select the 'Remember Me' checkbox
		if (rememberUser.isSelected()) {
			rememberUser.click();
		}
		
		signinButton.click();
	}
	
	public void logout() {
		//Bring up the user's account options
		accountDropDown.click();
		
		//Click the 'logout' option
		logoutButton.click();
	}
	
	/*
	 * Method used in the 'LoginPageTest' class
	 * Used to check if the 'Login' @Test passed successfully (aka. The account's user's name matches expectations)
	 */
	public SoftAssert verifySuccessfulLogin(SoftAssert softAssert, String expectedUserName) {
		//Check if the correct full name is being displayed as the 'welcome login text'
		softAssert.assertEquals(usernameWelcome.getText(), expectedUserName);
		
		//Output the results to the system and report
		if (expectedUserName.equals(usernameWelcome.getText())) {
			System.out.println("Success - user logged in correctly, with relevant name being displayed");
			reportLogger.log(LogStatus.PASS, "Success - user logged in correctly, with relevant name being displayed");
		} else {
			System.out.println("Failed - user logged in incorrectly, with an unexpected name being displayed. Expected the name -> " + expectedUserName + ", but found -> " + usernameWelcome.getText());
			reportLogger.log(LogStatus.PASS, "Failed - user logged in incorrectly, with an unexpected name being displayed. Expected the name -> " + expectedUserName + ", but found -> " + usernameWelcome.getText());
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}