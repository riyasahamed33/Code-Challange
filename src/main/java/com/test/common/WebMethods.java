

package com.test.common;

/**
 *WebMethods interface is an entry point to the application,contains only method declartion. 
 *
 */

/**
 * @author Riyaz Ahamed
 *
 */

import java.net.MalformedURLException;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public interface WebMethods {
		
	
		/**
		 * This method will launch the chrome browser and 
		 * Maximize the browser and set the wait for 30 seconds 
		 * and load the url
		 * @author Riyaz Ahamed
		 * @param browser - This will load the specified browser  
		 * @throws MalformedURLException 
		 */
		public void startApp(String browser, boolean bReturns) throws MalformedURLException ;

		/**
		 * This method will locate the element using any given locator
		 * @param locator  - The locator by which the element to be found
		 * @param locValue - The locator value by which the element to be found
		 * @author Riyaz Ahamed
		 * @throws NoSuchElementException
		 */
		public WebElement locateElement(String locator, String locValue) ;	
		
		/**
		 * This method will locate the element using id
		 * @param locValue - The locator value by which the element to be found
		 * @author  Riyaz Ahamed
		 * @throws NoSuchElementException
		 */
		public WebElement locateElement(String locValue) ;	
		
		/**
		 * This method will enter the value in the given text field 
		 * @param ele   - The Webelement (text field) in which the data to be entered
		 * @param data  - The data to be sent to the webelement
		 * @author  Riyaz Ahamed
		 * @throws ElementNotVisibleException		 * 
		 */
		public void type(WebElement ele, String data) ;
		
		/**
		 * This method will click the element and take snap
		 * @param ele   - The Webelement (button/link/element) to be clicked
		 * @author  Riyaz Ahamed
		 */
		public void click(WebElement ele);

		
		public long takeSnap();
			
		/**
		 * This method will close the active browser
		 * @author Riyaz Ahamed
		 */
		public void closeBrowser();		
		
		/**
		 * This method will close all the browsers
		 * @author Riyaz Ahamed
		 */
		public void closeAllBrowsers();

}



