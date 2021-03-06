
package com.test.common;

/**
 * @author Riyaz Ahamed
 *
 */

/**
 * In Parent methods class we are having different testng annotations and it will specify which method need to run before and after methods
 *
 */
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class ParentMethods extends WebUiActions{

	public String browserName;
	public String dataSheetName;
	 
	@BeforeSuite
	public void beforeSuite(){
		startResult();
	}

	@BeforeClass
	public void beforeClass(){		
		startTestModule(testCaseName, testDescription);	
	}



	@BeforeMethod
	public void beforeMethod(){
		test = startTestCase(testNodes);
		test.assignCategory(category);
		test.assignAuthor(authors);
		startApp(browserName);		
	}

	@AfterSuite
	public void afterSuite(){
		
		endResult();
	}

	@AfterTest
	public void afterTest(){
	}

	@AfterMethod
	public void afterMethod(){
		closeAllBrowsers();

	}
}

