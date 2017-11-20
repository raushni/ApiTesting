package com.rest.commonUtils;

import org.json.JSONObject;

public class CreateJsonTemplate {

	public JSONObject createTopicJson(int id, String name, String description) {
		return (new JSONObject().put("id", id).put("name", name).put("description", description));
	}
}
