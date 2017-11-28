package com.poc.ui.test;

import java.net.MalformedURLException;
import org.testng.annotations.Test;
import com.poc.ui.pagetasks.JobListingPageTasks;
import com.poc.utils.BaseTest;
import com.poc.utils.CustomDataLogger;
import com.poc.utils.WebDriverTasks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("POC Test Framework")
@Feature("App job listing feature test")

public class AngularAppTests extends BaseTest {
	//@Parameters ( {"browser"})
	@Test(groups = {"bvt","regression"} , description = "Validate Edit User Record operation")
	@Story("Angular App Test")
	public void TestUI001() throws MalformedURLException{
		
		//WebDriverTasks.setUp(browser);
		WebDriverTasks.loadURL("http://10.51.236.53:9000/joblisting");
		JobListingPageTasks.UpdateUserFirstName("Dario", "10");
		CustomDataLogger.saveScreenshotPNG(WebDriverTasks.getWebdriverSession());
		WebDriverTasks.tearDown();
	}

}
