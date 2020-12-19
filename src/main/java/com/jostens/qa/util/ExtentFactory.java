package com.jostens.qa.util;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	
	public static ExtentReports getInstance() {
		ExtentReports extent;
//		String Path  = "C:\\Users\\Owner\\Documents\\Selenium DataTables\\clientfile.html";
		String Path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\jostens\\qa\\testdata\\TestSuiteResults.html";
		extent = new ExtentReports(Path, false);
		
		return extent;
	}
	
}