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

import java.net.MalformedURLException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners({CustomErrorReporter.class})
public abstract class BaseTest {
	
	/*private ExtentReports extent;    
	public static StringBuilder reportLocation;
    public static StringBuilder reportDir = new StringBuilder(System.getenv("HOME")).append(File.separator).append("reports");
    */
   
   
    
           
    /*@DataProvider(parallel = true)
	public Object[][] xmlProvider(Method method) {
		return xmlDataProvider.retrieveTestData(method);
	}
    
    @BeforeClass(alwaysRun = true)
	public void initializeData(){
		xmlDataProvider.loadXMLTestDataFile(this.getClass().getSimpleName());
	}*/
    
    /*@AfterMethod (groups = { "bvt","regression" })
	protected void afterMethod(ITestResult result,final ITestContext testContext) {
    	
    	
		switch (result.getStatus()) {
		case ITestResult.FAILURE:
			
			
			if(WebDriverTasks.VerifyWebdriverSession()){
				WebDriverTasks.tearDown(true);
			}
			break;
		case ITestResult.SKIP:
			ParallelTestReporter.getTest().log(LogStatus.SKIP, "<b>Test skipped </b>" + result.getThrowable());
			break;
		case ITestResult.SUCCESS:
			if(WebDriverTasks.VerifyWebdriverSession()){
				WebDriverTasks.tearDown(false);
			}
			ParallelTestReporter.getTest().log(LogStatus.PASS, "<b>Test passed</b>");
			break;
		default:
			ParallelTestReporter.getTest().log(LogStatus.UNKNOWN, "<b> Unknown status , have a look at the execution</b>");
			break;
		}
		
		ParallelTestReporter.endTest();
	}*/
	@Parameters ( {"browser"})
    @BeforeTest (groups = {"bvt", "regression"})
    public void beforeTest(String browser) throws MalformedURLException{
    	WebDriverTasks.setUp(browser);
    }
    
    
    
   /* 
    @AfterSuite (groups = { "BVT","FAT1" })
    protected void afterSuite() throws Exception {
    	
    	//Create a archive of the report directory contents
    	//FileZipper.createArchive(reportDir.toString(), new StringBuffer(reportDir.toString()).append(File.separator).append("report.zip").toString(),false);
    	
    }*/
}
