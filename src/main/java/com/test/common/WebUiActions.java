package com.test.common;

/**
 * Webui Action class - Providing Implementation to the interface class 
 *
 *
/**
 * @author Riyaz Ahamed
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.test.utility.*;

/**
 * @author Dell
 *
 */
public class WebUiActions extends Reporter implements WebMethods {
	

	public static RemoteWebDriver driver;
	public String sUrl, sHubUrl, sHubPort, sbrowser;
	public Properties prop;
	public static Logger log  = LogManager.getLogger(WebUiActions.class.getName());
	
	
	public WebUiActions() {
		prop = new Properties();
		try {
			/*
			 * Steps to read the value from the properties file
			 */
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
			sbrowser = prop.getProperty("browser");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * start app method to launch the browser
	 * @param browser
	 * @param remote
	 */

	public void startApp(String browser, boolean bRemote) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setPlatform(Platform.WINDOWS);
			// this is for grid run
			if (bRemote)
				try {
					driver = new RemoteWebDriver(new URL("http://" + sHubUrl + ":" + sHubPort + "/wd/hub"), dc);
				} catch (MalformedURLException e) {
				}
			else { // this is for local run
				if (sbrowser.equalsIgnoreCase("chrome")) {
					log.info("chrome Browser launch method");
					System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
					driver = new ChromeDriver(); //launch Chrome 
				} else {
					System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
					driver = new FirefoxDriver();
				}
			}
			driver.manage().window().maximize(); //maximize browser window
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //static wait
			driver.get(sUrl);//enter url
			log.info("Browser launched successfully");
			reportStep("The browser: " + sbrowser + " launched successfully", "PASS");
		} catch (WebDriverException e) {
			log.info("Browser could not lauched");
			reportStep("The browser: " + sbrowser + " could not be launched", "FAIL");
		}
	}

	public void startApp(String browser) {
		startApp(browser, false);
	}

	public WebElement locateElement(String locator, String locValue) {
		try {
			switch (locator) {
			case ("id"):
				return driver.findElementById(locValue);
			case ("link"):
				return driver.findElementByLinkText(locValue);
			case ("xpath"):
				return driver.findElementByXPath(locValue);
			case ("name"):
				return driver.findElementByName(locValue);
			case ("class"):
				return driver.findElementByClassName(locValue);
			case ("tag"):
				return driver.findElementByTagName(locValue);
			}
		} catch (NoSuchElementException e) {
			reportStep("The element with locator " + locator + " not found.", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while finding " + locator + " with the value " + locValue, "FAIL");
		}
		return null;
	}

	public WebElement locateElement(String locValue) {
		return driver.findElementById(locValue);
	}

	public void type(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			String x = "" + ele;
			reportStep("The data: " + data + " entered successfully in the field :" + ele, "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The data: " + data + " could not be entered in the field :" + ele, "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while entering " + data + " in the field :" + ele, "FAIL");
		}
	}

	public void click(WebElement ele) {
		String text = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			text = ele.getText();
			ele.click();
			reportStep("The element " + text + " is clicked", "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + text + " could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :", "FAIL");
		}
	}
	/*
	 * To Retrieve the text from the Webpage 
	 */

	public String getText(WebElement ele) {
		String bReturn = "";
		try {
			bReturn = ele.getText();
		} catch (WebDriverException e) {
			reportStep("The element: " + ele + " could not be found.", "FAIL");
		}
		return bReturn;
	}

	/*
	 * To get the title of the browser window
	 */
	public String getTitle() {
		String bReturn = "";
		try {
			bReturn = driver.getTitle();
		} catch (WebDriverException e) {
			reportStep("Unknown Exception Occured While fetching Title", "FAIL");
		}
		return bReturn;
	}
	

	public boolean verifyTitle(String title) {
		boolean bReturn = false;
		try {
			if (getTitle().equals(title)) {
				reportStep("The title of the page matches with the value :" + title, "PASS");
				bReturn = true;
			} else {
				reportStep("The title of the page:" + driver.getTitle() + " did not match with the value :" + title,
						"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
		return bReturn;
	}

	public void verifyExactText(WebElement ele, String expectedText) {
		try {
			if (getText(ele).equals(expectedText)) {
				reportStep("The text: " + getText(ele) + " matches with the value :" + expectedText, "PASS");
			} else {
				reportStep("The text " + getText(ele) + " doesn't matches the actual " + expectedText, "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		}

	}

	public void verifyPartialText(WebElement ele, String expectedText) {
		try {
			if (getText(ele).contains(expectedText)) {
				reportStep("The expected text contains the actual " + expectedText, "PASS");
			} else {
				reportStep("The expected text doesn't contain the actual " + expectedText, "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		}
	}

	
	public void verifySelected(WebElement ele) {
		try {
			if (ele.isSelected()) {
				reportStep("The element " + ele + " is selected", "PASS");
			} else {
				reportStep("The element " + ele + " is not selected", "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public void verifyDisplayed(WebElement ele) {
		try {
			if (ele.isDisplayed()) {
				reportStep("The element " + ele + " is visible", "PASS");
			} else {
				reportStep("The element " + ele + " is not visible", "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public void switchToWindow(int index) {
		try {
			Set<String> allWindowHandles = driver.getWindowHandles();
			List<String> allHandles = new ArrayList<>();
			allHandles.addAll(allWindowHandles);
			driver.switchTo().window(allHandles.get(index));
		} catch (NoSuchWindowException e) {
			reportStep("The driver could not move to the given window by index " + index, "PASS");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public void switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			reportStep("switch In to the Frame " + ele, "PASS");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public void acceptAlert() {
		String text = "";
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			reportStep("The alert " + text + " is accepted.", "PASS");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public void dismissAlert() {
		String text = "";
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			reportStep("The alert " + text + " is dismissed.", "PASS");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}

	}

	public String getAlertText() {
		String text = "";
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
		return text;
	}
    
	
	/**
	 * takeSnap method is used to capture the screenshot
	 */
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
					new File("./reports/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

	/**
	 * close method is used to close the currently opened browser window
	 */
	public void closeBrowser() {
		try {
			driver.close();
			reportStep("The browser is closed", "PASS", false);
		} catch (Exception e) {
			reportStep("The browser could not be closed", "FAIL", false);
		}
	}

	/**
	 * quit method is used to close all the browser windows
	 */
	public void closeAllBrowsers() {
		try {
			driver.quit();
			reportStep("The opened browsers are closed", "PASS", false);
		} catch (Exception e) {
			reportStep("Unexpected error occured in Browser", "FAIL", false);
		}
	}

	/**
	 * wait for visibility of element.
	 *
	 * @param element the element
	 * @param timeout the timeout
	 * @return true, if is element displayed
	 */
	public static void waitForElementVisibility(By element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void ScrollElement(WebElement ele) {

		try {

			Actions a = new Actions(driver);
			a.moveToElement(ele);
			a.perform();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
