package com.poc.ui.test;

import java.net.MalformedURLException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.poc.ui.pagetasks.JobListingPageTasks;
import com.poc.utils.CustomDataLogger;
import com.poc.utils.CustomErrorReporter;
import com.poc.utils.WebDriverTasks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("POC Angular App Test")
@Feature("App job listing feature test")
@Listeners({CustomErrorReporter.class})
public class AngularAppTests {
	
	@Test(groups = {"bvt","regression"} , description = "Validate Edit User Record operation")
	public void TestUI001() throws MalformedURLException{
		WebDriverTasks.setUp();
		WebDriverTasks.loadURL("http://10.51.236.53:4200/joblisting");
		JobListingPageTasks.UpdateUserFirstName("Dario", "10");
		CustomDataLogger.saveScreenshotPNG(WebDriverTasks.getWebdriverSession());
		WebDriverTasks.tearDown();
	}

}
