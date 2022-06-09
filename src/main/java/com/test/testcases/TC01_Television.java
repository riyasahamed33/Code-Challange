package com.test.testcases;


/**
 * TC_01 Validate the television product information in amazon web application
 *
 */
/**
 * @author Riyaz Ahamed
 *
 */
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.pages.HomePage;
import com.test.common.*;
public class TC01_Television extends ParentMethods {
	
	public static Logger log  = LogManager.getLogger(WebUiActions.class.getName());
	
	
	@BeforeTest
	public void setData() throws IOException {
		testCaseName="TC01_Television";
		testDescription="login to Amazon application";
		testNodes="Leads";
		category="Test";
		authors="Riyaz";
		browserName = "chrome";
		
	}
	
	
	
		
	@Test 
	public void validateamzaonapplication() {
		
		HomePage hp =new HomePage();
		hp.getSignin();
		hp.clickHarmbugermenu();
		hp.clickTVlink();
		hp.clickTelevisionlink();
	    hp.clickonCheckbox();
		hp.clickonSortBy();
		hp.clickonProduct();
		hp.switchwindow();
		
	}

}
