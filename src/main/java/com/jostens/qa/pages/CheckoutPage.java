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

public class CheckoutPage {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	
	TestUtil genMethods;
	
	//Constructor
	public CheckoutPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new TestUtil();
	}
	
	@FindBy(xpath="//input[@id='checkout_email']")
	WebElement emailField;
	
	@FindBy(xpath="//input[@id='checkout_buyer_accepts_marketing']")
	WebElement sendEmailsCheckbox;
	
	@FindBy(xpath="//input[@id='checkout_shipping_address_first_name']")
	WebElement firstNameField;
	
	@FindBy(xpath="//input[@id='checkout_shipping_address_last_name']")
	WebElement lastNameField;
	
	@FindBy(xpath="//input[@id='checkout_shipping_address_address1']")
	WebElement addressField;
	
	@FindBy(xpath="//input[@id='checkout_shipping_address_city']")
	WebElement cityField;
	
	@FindBy(xpath="//select[@id='checkout_shipping_address_country']")
	WebElement countryDropDownList;
	
	@FindBy(xpath="//select[@id='checkout_shipping_address_province']")
	WebElement stateDropDownList;
	
	@FindBy(xpath="//input[@id='checkout_shipping_address_zip']")
	WebElement zipCodeField;
	
	@FindBy(xpath="//input[@id='checkout_shipping_address_phone']")
	WebElement phoneField;
	
	@FindBy(xpath="//input[@id='checkout_remember_me']")
	WebElement saveInfoCheckbox;
	
	@FindBy(xpath="//button[@id='continue_button']")
	WebElement continueToShippingButton;
	
	//Verify Checkout Info WebElements
	@FindBy(xpath="//bdo[@dir='ltr']")
	WebElement emailConfirmationField;
	
	@FindBy(xpath="//address[@class='address address--tight']")
	WebElement addressConfirmationField;
	
	@FindBy(xpath="//td[@class='total-line__price']//span[@data-checkout-subtotal-price-target]")
	WebElement subtotalConfirmationField;
	
	@FindBy(xpath="//td[@class='total-line__price']//span[@data-checkout-total-shipping-target]")
	WebElement shippingCostConfirmationField;
	
	@FindBy(xpath="//span[@class='payment-due__price']")
	WebElement totalCostConfirmationField;
	
	@FindBy(xpath="//a[@class='step__footer__previous-link']//span[contains(text(), 'Return to information')]")
	WebElement backToShippingInfoInput;
	
	public void enterEmail(String email, String sendEmails) {
		//Output a message to the report & system
		System.out.println("Entering the user's email in the Checkout Page for shipping");
		reportLogger.log(LogStatus.INFO, "Entering the user's email in the Checkout Page for shipping");
		
		//Initialize Variable(s)
		boolean sendEmailsCheck;
		
		//Determine if the user/script wants to have email updates or not
		sendEmails = sendEmails.toLowerCase();
		if (sendEmails.equals("yes") || sendEmails.equals("y") || sendEmails.equals("true")) {
			sendEmailsCheck = true;
		} else {
			sendEmailsCheck = false;
		}
		
		//Enter the user's first & last name for shipping
		emailField.clear();
		
		emailField.sendKeys(email);
		
		//Ensure the 'Email me Updates' checkbox is selected, if updates are desired, otherwise, ensure the checkbox is not selected
		if (sendEmailsCheck && !sendEmailsCheckbox.isSelected()) {
			System.out.println("Check the 'Send Email Updates' checkbox.");
			sendEmailsCheckbox.click();
		} else if (!sendEmailsCheck && sendEmailsCheckbox.isSelected()) {
			System.out.println("Remove the check for the 'Send Email Updates' checkbox.");
			sendEmailsCheckbox.click();
		} else {
			System.out.println("saveInfo=" + sendEmails);
			System.out.println("emailUpdatesCheckbox.isSelect()=" + sendEmailsCheckbox.isSelected());
		}
	}
	
	public void enterName(String firstName, String lastName) {
		//Output a message to the report & system
		System.out.println("Entering the user's name in the Checkout Page for shipping");
		reportLogger.log(LogStatus.INFO, "Entering the user's name in the Checkout Page for shipping");
		
		//Enter the user's first & last name for shipping
		firstNameField.clear();
		lastNameField.clear();
		
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
	}
	
	public void enterAddress(String address, String city, String country, String state, String zipCode) {
		//Output a message to the report & system
		System.out.println("Entering the user's address in the Checkout Page for shipping");
		reportLogger.log(LogStatus.INFO, "Entering the user's address in the Checkout Page for shipping");
		
		//Initialize Variable(s)
		Select countrySelector = new Select(countryDropDownList);
		Select stateSelector = new Select(stateDropDownList);
		
		//Enter the user's address for shipping
		addressField.clear();
		cityField.clear();
		zipCodeField.clear();
		
		addressField.sendKeys(address);
		cityField.sendKeys(city);
		countrySelector.selectByVisibleText(country);
		stateSelector.selectByVisibleText(state);
		zipCodeField.sendKeys(zipCode);
		
	}
	
	public void enterPhoneNumber(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Entering the user's phone number in the Checkout Page for contact info");
		reportLogger.log(LogStatus.INFO, "Entering the user's phone number in the Checkout Page for contact info");
		
		//Enter the user's phone number for contact info
		phoneField.clear();
		
		phoneField.sendKeys(phoneNumber);
	}
	
	public void continueToShipping(String saveInfo) {
		//Output a message to the report & system
		System.out.println("Proceeding to the Shipping Page");
		reportLogger.log(LogStatus.INFO, "Proceeding to the Shipping Page");
		
		//Initialize Variable(s)
		boolean saveInfoCheck;
		
		//Determine if the user/script wants to save their info or not
		saveInfo = saveInfo.toLowerCase();
		if (saveInfo.equals("yes") || saveInfo.equals("y") || saveInfo.equals("true")) {
			saveInfoCheck = true;
		} else {
			saveInfoCheck = false;
		}
		
		//Ensure the 'Email me Updates' checkbox is selected, if updates are desired, otherwise, ensure the checkbox is not selected
		if (saveInfoCheck && !saveInfoCheckbox.isSelected()) {
			System.out.println("Check the 'Save Info' checkbox.");
			saveInfoCheckbox.click();
		} else if (!saveInfoCheck && saveInfoCheckbox.isSelected()) {
			System.out.println("Remove the check for the 'Save Info' checkbox.");
			saveInfoCheckbox.click();
		} else {
			System.out.println("saveInfo=" + saveInfo);
			System.out.println("saveInfoCheckbox.isSelect()=" + saveInfoCheckbox.isSelected());
		}
		
		//Click to proceed to the Shipping Page
		continueToShippingButton.click();
	}
	
	public SoftAssert verifyProductFromCheckout(SoftAssert softAssert, String productName, String productQuantity, String productPrice) {
		//Initialize Variable(s)
		boolean productFound = false;
		
		//Initialize the lists that contain the info detailing the shopping cart's products
		List<WebElement> productNameList = eDriver.findElements(By.xpath("//tbody[@data-order-summary-section='line-items']//tr[@data-product-type='PD Custom Product']//span[@class='product__description__name order-summary__emphasis']"));
		List<WebElement> productQuantityList = eDriver.findElements(By.xpath("//tbody[@data-order-summary-section='line-items']//tr[@data-product-type='PD Custom Product']//span[@class='product-thumbnail__quantity']"));
		List<WebElement> productPriceList = eDriver.findElements(By.xpath("//tbody[@data-order-summary-section='line-items']//tr[@data-product-type='PD Custom Product']//span[@class='order-summary__emphasis']"));
		
		//Iterate through the lists to locate if the expected information is found - return true, if all info found, otherwise, return false
		for (int i = 0; i < productNameList.size(); i++) {
			System.out.println(productNameList.get(i).getText() + " - " + productQuantityList.get(i).getText() + " - " + productPriceList.get(i).getText());
			if (productName.equals(productNameList.get(i).getText()) && productQuantity.equals(productQuantityList.get(i).getText()) && productPrice.equals(productPriceList.get(i).getText())) {
				productFound = true;
				
				softAssert.assertEquals(productNameList.get(i).getText(), productName);
				softAssert.assertEquals(productQuantityList.get(i).getText(), productQuantity);
				softAssert.assertEquals(productPriceList.get(i).getText(), productPrice);
				
			}
		}
		
		if (!productFound) {
			softAssert.assertEquals(productNameList.get(0).getText(), productName);
			softAssert.assertEquals(productQuantityList.get(0).getText(), productQuantity);
			softAssert.assertEquals(productPriceList.get(0).getText(), productPrice);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyShippingInfo(SoftAssert softAssert, String email, String address, String city, String state, String zipCode, String country) {
		//Initialize Variable(s)
		String fullAddress = address + ", " + city + " " + genMethods.stateAbbreviation(state) + " " + zipCode + ", " + country;
		System.out.println(fullAddress);
		System.out.println(addressConfirmationField.getText());
		
		softAssert.assertEquals(fullAddress, addressConfirmationField.getText());
		softAssert.assertEquals(email, emailConfirmationField.getText());
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public void returnToShippingInputPage() {
		backToShippingInfoInput.click();
	}
	
}