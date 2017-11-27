package com.poc.ui.pagetasks;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.poc.ui.pageobjects.JobListingPageObjects;
import com.poc.utils.WebDriverTasks;

public class JobListingPageTasks {
	public static WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
	
	public static void filterUserById(String userid){
		WebDriverTasks.sendTextWithObject(JobListingPageObjects.getTableUserIDTextBox(), userid);
	}
	
	public static void EditUser(){
		WebDriverTasks.clickObject(JobListingPageObjects.getEditLink());
	}
	
	public static void EditUserFirstName(String firstname){
		WebDriverTasks.sendTextWithObject(JobListingPageObjects.getRecordUserNameTextBox(), firstname);
	}
	
	public static void UpdateUserFirstName(String firstname,String userid){
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(JobListingPageObjects.tableuserid_locator)));
		filterUserById(userid);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(JobListingPageObjects.editlink_locator)));
		EditUser();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(JobListingPageObjects.rowusername_locator)));
		EditUserFirstName(firstname);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(JobListingPageObjects.updatelink_locator)));
		WebDriverTasks.clickObject(JobListingPageObjects.getUpdateLink());
	}

}
