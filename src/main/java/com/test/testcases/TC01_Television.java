package com.test.testcases;
import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.pages.HomePage;
import com.test.common.*;
public class TC01_Television extends ParentMethods {
	
	
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
	public void loginAndLogout() {
		
		HomePage hp =new HomePage();
		hp.getSignin();
		hp.clickHarmbugermenu();
		hp.clickTVlink();
		hp.clickTelevisionlink();
	    hp.clickonCheckbox();
		
		
	}

}
