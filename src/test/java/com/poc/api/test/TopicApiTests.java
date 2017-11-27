package com.poc.api.test;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.poc.api.metadata.TopicApiTasks;
import com.poc.api.metadata.beans.TopicApiBeans;





@Epic("POC Regression Test")
@Feature("Backend Springboot app")

public class TopicApiTests {
	
	@Test(groups = {"bvt","resgression"} , description="Validate Create Topic Api")
	@Story("POC Backend api tests")
	public void test001() throws JSONException, FileNotFoundException, IOException, ParseException{
		
		Response response;
		TopicApiTasks newtopictask = new TopicApiTasks();
		TopicApiBeans newtopicbean = new TopicApiBeans();
		
		newtopicbean.setTopicId("123");
		newtopicbean.setTopicName("Test POC Topic");
		newtopicbean.setTopicDescription("Sample description for new poc topic");
		
		newtopictask.jsonBuilder(newtopicbean);
		response = newtopictask.createTopic();
		
		assertEquals(response.getStatusCode(), 201);
	}

}
