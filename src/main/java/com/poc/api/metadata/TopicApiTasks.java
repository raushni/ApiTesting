package com.poc.api.metadata;


import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import com.poc.api.metadata.beans.TopicApiBeans;
import com.poc.utils.EnvConfig;
import com.poc.utils.JsonTemplateReader;



public class TopicApiTasks {
	
	private static final String API_PATH= "/topics";
	private static final StringBuilder ABS_API_PATH = new StringBuilder(EnvConfig.hostBaseURL).append(API_PATH);
	private String jsonfile = "Topics.json";
	private Response apiResponse;
	public JSONObject testjson;
	
	
	@Step("Create CreateTopic api payload")
	public void jsonBuilder(TopicApiBeans topic) throws JSONException, FileNotFoundException, IOException, ParseException {

		testjson = JsonTemplateReader.getJsonTemplate(jsonfile);
		
		testjson.put("id", topic.getTopicId());
		testjson.put("name", topic.getTopicName());
		testjson.put("description", topic.getTopicDescription());
		
	}
	
	@Step("Execute Create Topic Api")
	public Response createTopic() throws JSONException{
		apiResponse = 
				given().
					filter(new AllureRestAssured()).
					contentType("application/json").
					body(testjson.toString()).
					config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
				when().
					post(ABS_API_PATH.toString()).
					then().
				extract().response();

		
		return apiResponse;
	}
}