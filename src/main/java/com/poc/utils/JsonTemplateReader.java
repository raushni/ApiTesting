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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTemplateReader {
	
	public static JSONObject getJsonTemplate (String jsonfile) throws FileNotFoundException, IOException, ParseException, JSONException{
		
		JSONParser parser = new JSONParser();
		File file = new File(FileLoader.getFilePath("jsontemplates/", jsonfile));		
		Object testobj = parser.parse(new FileReader(file));
		org.json.simple.JSONObject jsonobject = (org.json.simple.JSONObject) testobj;
		JSONObject testjson = new JSONObject(jsonobject.toString());
		return testjson;
	
	}
}
