/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2016                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.poc.utils;

import io.qameta.allure.Step;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

//This class provides methods with logging for different object actions like click,send text etc
public class WebDriverTasks {
	
	//Webdribver session and the curresponding thread id's are mapped in a hashmap.This config is used for parallel execution of selenium tests
	private static Map<Integer, WebDriver> driverSessionMap = new HashMap<Integer,WebDriver>();
	private static long defaultwait = 15;
	private static long longwait = 120;
	public static int maxretry = 10;
	
	
	@Step("Clicking on Web Element")
	public static synchronized void clickObject(WebElement obj){
		obj.click();
		
		
	}
	
	@Step("Sending Text in a Web Element")
	public static synchronized void sendTextWithObject(WebElement obj,String webtext){
		obj.sendKeys(webtext);
		
		
	}
	
	@Step("Loading url...")
	public static synchronized void loadURL(String url){
		getWebdriverSession().get(url);
		
	}
	
	public static synchronized void actionClassClick(WebElement obj){
		Actions action = new Actions(getWebdriverSession());
		action.moveToElement(obj).click().perform();
		
	}
	
	public static synchronized boolean verifyVisibleText(String webtext){
		String pagesource = getWebdriverSession().getPageSource();
		Document doc = Jsoup.parse(pagesource);
		return doc.body().text().contains(webtext);
	}
	
	public static synchronized void assertCheckpoint(Boolean expected,Boolean actual,String message){
		Assert.assertEquals(actual,expected);
		
	}
	
	public static synchronized void setUp() throws MalformedURLException{
		/*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enabled", false);
		profile.setPreference("dom.webnotifications.serviceworker.enabled", false);
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		WebDriver driver = new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"),capabilities);*/
		//FirefoxProfile fprofile = new FirefoxProfile();
		//fprofile.setPreference("dom.webnotifications.enabled", false);
		//fprofile.setPreference("dom.webnotifications.serviceworker.enabled", false);
		System.setProperty("webdriver.gecko.driver", FileLoader.getFilePath("config/", "geckodriver.exe"));
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(defaultwait, TimeUnit.SECONDS); //default page object wait of 15 seconds
		driver.manage().timeouts().pageLoadTimeout(longwait, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		addWebdriverSession(driver);
		
	}
	
	public static synchronized void tearDown(/*Boolean captureScreenshot*/){
		//if(captureScreenshot)
		//	CaptureScreenshot.captureScreenshot(getWebdriverSession());
		getWebdriverSession().quit();
		removeWebdriverSession(getWebdriverSession());
	}
	
	public static synchronized void addWebdriverSession(WebDriver driver){
	    driverSessionMap.put((int) (long)(Thread.currentThread().getId()),driver);
	}
	    
	public static synchronized void removeWebdriverSession(WebDriver driver){
	    driverSessionMap.remove((int) (long)(Thread.currentThread().getId()));
	}
	    
	public static synchronized WebDriver getWebdriverSession(){
		return driverSessionMap.get((int)(long) (Thread.currentThread().getId()));
	}
	
	public static synchronized Boolean VerifyWebdriverSession(){
		return driverSessionMap.containsKey((int)(long) (Thread.currentThread().getId()));
	}
	
		
	
}
