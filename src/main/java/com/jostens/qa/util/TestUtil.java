package com.jostens.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestUtil {
	
	/*
	 * This method takes a screenshot of the entire web browser
	 * 
	 * This method is used in the @AfterMethod method in the 'LoginPageTest.java' class (underneath the 'src/test/java' folder & 'com.jostens.qa.testcases' package)
	 */
	public String getScreenshot(EventFiringWebDriver eDriver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) eDriver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		//FileUtils.copyFile(source, finalDestination);
		FileHandler.copy(source, finalDestination);
		return destination;
	}
	
	/*
	 * This method is used when the xpath of a WebElement cannot be pre-determined
	 *    (aka. Either the WebElement has a changing xpath, or a different WebElement may need to be referenced, depending on the test)
	 * This method can be called by using...
	 *    .createXPath("//a[contains(text(), \"{0}\")]", varName0);
	 *    .createXPath("//a[contains(text(), \"{0}\") and @name=\"{1}\"]", varName0, varName1);
	 *    .createXPath("//a[contains(text(), \"{0}\") and @name=\"{1}\"] and @type=\"{2}\"]", varName0, varName1, varName2);
	 *    etc..., where varName0 will replace {0}, varName1 will replace {1}, varName2 will replace {2}, etc.
	 *    Calling this method will work with any number of varName# entries, as long as there are {#} to coincide with them
	 * 
	 * This method is used in the 'selectProduct' method in the 'SchoolStorePage.java' class (underneath the 'src/main/java' folder & 'com.jostens.qa.pages' package)
	 */
	public String createXPath(String xpathExp, Object ...args) {
		
		for (int i = 0; i < args.length; i++) {
			xpathExp = xpathExp.replace("{" + i + "}", (CharSequence) args[i]);
		}
		
		return xpathExp;
	}
	
	public String stateAbbreviation(String state) {
		//Initialize Variable(s)
		String stateAbb = "";
		state = state.toLowerCase();
		
		switch(state) {
			case "alabama":
				stateAbb = "AL";
				break;
			case "alaska":
				stateAbb = "AK";
				break;
			case "arizona":
				stateAbb = "AZ";
				break;
			case "arkansas":
				stateAbb = "AR";
				break;
			case "california":
				stateAbb = "CA";
				break;
			case "colorado":
				stateAbb = "CO";
				break;
			case "connecticut":
				stateAbb = "CT";
				break;
			case "delaware":
				stateAbb = "DE";
				break;
			case "florida":
				stateAbb = "FL";
				break;
			case "georgia":
				stateAbb = "GA";
				break;
			case "hawaii":
				stateAbb = "HI";
				break;
			case "idaho":
				stateAbb = "ID";
				break;
			case "illinois":
				stateAbb = "IL";
				break;
			case "indiana":
				stateAbb = "IN";
				break;
			case "iowa":
				stateAbb = "IA";
				break;
			case "kansas":
				stateAbb = "KS";
				break;
			case "kentucky":
				stateAbb = "KY";
				break;
			case "louisiana":
				stateAbb = "LA";
				break;
			case "maine":
				stateAbb = "ME";
				break;
			case "maryland":
				stateAbb = "MD";
				break;
			case "massachusetts":
				stateAbb = "MA";
				break;
			case "michigan":
				stateAbb = "MI";
				break;
			case "minnesota":
				stateAbb = "MN";
				break;
			case "mississippi":
				stateAbb = "MS";
				break;
			case "missouri":
				stateAbb = "MO";
				break;
			case "montana":
				stateAbb = "MT";
				break;
			case "nebraska":
				stateAbb = "NE";
				break;
			case "nevada":
				stateAbb = "NV";
				break;
			case "new hampshire":
				stateAbb = "NH";
				break;
			case "new jersey":
				stateAbb = "NJ";
				break;
			case "new mexico":
				stateAbb = "NM";
				break;
			case "new york":
				stateAbb = "NY";
				break;
			case "north carolina":
				stateAbb = "NC";
				break;
			case "north dakota":
				stateAbb = "ND";
				break;
			case "ohio":
				stateAbb = "OH";
				break;
			case "oklahoma":
				stateAbb = "OK";
				break;
			case "oregon":
				stateAbb = "OR";
				break;
			case "pennsylvania":
				stateAbb = "PA";
				break;
			case "rhode island":
				stateAbb = "RI";
				break;
			case "south carolina":
				stateAbb = "SC";
				break;
			case "south dakota":
				stateAbb = "SD";
				break;
			case "tennessee":
				stateAbb = "TN";
				break;
			case "texas":
				stateAbb = "TX";
				break;
			case "utah":
				stateAbb = "UT";
				break;
			case "vermont":
				stateAbb = "VT";
				break;
			case "virginia":
				stateAbb = "VA";
				break;
			case "washington":
				stateAbb = "WA";
				break;
			case "west virginia":
				stateAbb = "WV";
				break;
			case "wisconsin":
				stateAbb = "WI";
				break;
			case "wyoming":
				stateAbb = "WY";
				break;
		}
		
		return stateAbb;
	}
	
}