package com.abhinav.mobiAssign.API;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdvertiserAPI {

	private static final String accountId = "d94ee7a33e2c401d942285199383ab6f";
	private static final String secretKey = "2274d2c8f8714f02b92c6a9b67271843";
	private static String sessionId;
	private static String userName = "abhinavojha93@gmail.com";
	
	static {
		GetMethod getMethod = new GetMethod("http://api.inmobi.com/v1.0/generatesession/generate");
		
		getMethod.setRequestHeader("Content-Type", "application/json");
		getMethod.setRequestHeader("secretKey", "2274d2c8f8714f02b92c6a9b67271843");
		getMethod.setRequestHeader("userName", userName);
		getMethod.setRequestHeader("subAccountTypeId", "-1");
		
		HttpClient client = new HttpClient();
		
		try {
			client.executeMethod(getMethod);
			String response = getMethod.getResponseBodyAsString();
			JSONArray responseList = new JSONObject(response).getJSONArray("respList");
			
			JSONObject item = responseList.getJSONObject(0);
			
			if (item != null) {
				sessionId = item.optString("sessionId", null);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getAdvReport(String getReportJson) {
		if (sessionId == null)
			return "Nothing Returned";
		
		PostMethod method = new PostMethod("https://api.inmobi.com/v1.0/reporting/advertiser");
		
		System.out.println("sessionId : " + sessionId);
		
		try {
			StringRequestEntity reqEntity = new StringRequestEntity(getReportJson, "application/json", "UTF-8");
			
			method.setRequestEntity(reqEntity);
			method.setRequestHeader("Content-Type", "application/json");
			method.setRequestHeader("accountId", accountId);
			method.setRequestHeader("secretKey", secretKey);
			method.setRequestHeader("sessionId", sessionId);
			
			HttpClient client = new HttpClient();
			client.executeMethod(method);
			
			String response = method.getResponseBodyAsString();
			System.out.println(response);
			
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
}
