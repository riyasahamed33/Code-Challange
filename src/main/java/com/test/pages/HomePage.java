package com.test.pages;

/**
 * Home page class - we are storing all the Web Element properties and using it when ever it is required.Any change the WebElement 
 * properties we can directly update the changes here.
 *
 */
/**
 * @author Riyaz Ahamed
 *
 */
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.Iterator;

import com.test.common.WebUiActions;

public class HomePage extends WebUiActions {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	private By Signin = By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]");
	private By Harmbugermenu = By.xpath("//*[@id=\"nav-hamburger-menu\"]");
    private By TVlink = By.xpath("//div[contains(text(),'TV, Appliances')]");
	private By Televisonlink = By.xpath("//a[contains(text(),'Televisions')]");
	private By Brands = By.xpath("//*[text() ='Brands']");
	private By Samsung = By.xpath("//*[@id=\"s-refinements\"]/div[19]/ul/li[2]/span/a/div/label/i");
	private By Productresult = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div");
    private By Sortbyfeature = By.xpath("//*[@id=\"a-autoid-0-announce\"]");
    private By price = By.xpath("//*[@id=\"s-result-sort-select_2\"]");
	public WebElement getSignin() {

		waitForElementVisibility(Signin);

		return driver.findElement(Signin);
	}

	public WebElement clickHarmbugermenu() {

		waitForElementVisibility(Harmbugermenu);
		WebElement hmenu = driver.findElement(Harmbugermenu);
		click(hmenu);

		return hmenu;
	}

	public WebElement clickTVlink() {

		waitForElementVisibility(TVlink);
		WebElement tvlink = driver.findElement(TVlink);
		click(tvlink);

		return tvlink;
	}

	public WebElement clickTelevisionlink() {

		waitForElementVisibility(Televisonlink);
		WebElement televisionink = driver.findElement(Televisonlink);
		click(televisionink);

		return televisionink;
	}

	public WebElement clickonCheckbox() {
        
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		waitForElementVisibility(Brands);
		WebElement samsungcheckbox = driver.findElement(Samsung);
		waitForElementVisibility(Samsung);
		click(samsungcheckbox);
		return samsungcheckbox;

	}
	
	public WebElement clickonProduct() {
		waitForElementVisibility(Productresult);
		WebElement productresult = driver.findElement(Productresult);
		click(productresult);
		return productresult;
		
		
	}
	
	public WebElement clickonSortBy() {
		WebElement featured = driver.findElement(Sortbyfeature);
		click(featured);
		WebElement pricehigh = driver.findElement(price);
		click(pricehigh);
			
	WebElement prodprice = driver.findElement(Sortbyfeature);
	
	return prodprice;
	}
  
	public void switchwindow() {
		Set<String> newAllWindowHandles = driver.getWindowHandles();
       
 
        // Get the detail of the parent window
        String ParentHandle = driver.getWindowHandle();
        System.out.println("Parent Window :" + ParentHandle);
		 Iterator<String> iterator = newAllWindowHandles.iterator();
	        String mainWindow = iterator.next();
	        String childWindow = iterator.next();
	       
	        //Switch control to child window
	        driver.switchTo().window(childWindow);
	        WebElement text = driver.findElement(By.xpath("//*[contains(text(), 'About this item')]"));
	        
	 
	        // Close Child window
	        driver.close();
	 
	        // Switch back to parent window
	        driver.switchTo().window(ParentHandle);
	       
	}
	
	
}
