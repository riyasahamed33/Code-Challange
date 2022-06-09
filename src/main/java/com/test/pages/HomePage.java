package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.test.common.WebUiActions;

public class HomePage extends WebUiActions {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	private By Signin = By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]");

	private By Harmbugermenu = By.xpath("//*[@id=\"nav-hamburger-menu\"]");

	private By TVlink = By.xpath("//div[contains(text(),'TV, Appliances')]");
	private By ShopByDepartment = By.xpath("//div[contains(text(),'shop by department')]");
	private By Televisonlink = By.xpath("//a[contains(text(),'Televisions')]");
	private By Brands = By.xpath("//*[text() ='Brands']");
	private By Samsung = By.xpath("//*[@id=\"s-refinements\"]/div[19]/ul/li[2]/span/a/div/label/i");

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

}
