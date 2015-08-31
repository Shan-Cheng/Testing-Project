package edu.ucsc.extension.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.apache.commons.codec.binary.Base64;

import com.google.gson.*;




public class Tests {
	String access_token=null;
	
	
	@Test
	public void testPost() throws ClientProtocolException, IOException {
		String API_KEY = "1gt87XPLwIHOKDnMJxbZK9ssu";
		String API_SECRET = "yGpBrRsTu2TguJCSObcv5SJZU1odxhI2DtN4Leex0tipMk9OCk";
		String KEYANDSECRET=API_KEY+":"+ API_SECRET;
		String base64encode=Base64.encodeBase64String(KEYANDSECRET.getBytes());
		String fin = "Basic "+base64encode+"==";
		//System.out.println(fin);
		CloseableHttpClient client = HttpClients.createDefault();
		
		// Define POST request
		HttpPost httpPost = new HttpPost("https://api.twitter.com/oauth2/token");
		httpPost.setHeader("Host","api.twitter.com");
		httpPost.setHeader("Authorization",fin);
		httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.setHeader("User-Agent"," My Twitter App v1.0.23");
		httpPost.setHeader("Accept-Encoding","gzip");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("grant_type","client_credentials"));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
	
		// Execute POST
		CloseableHttpResponse response = client.execute(httpPost);
		
		try {
			// Print the status code
			
			//System.out.println(response);
			// Print the re-direct URL
			//System.out.println(response.getFirstHeader("Location"));
			HttpEntity entity = response.getEntity();
            String resp = EntityUtils.toString(entity);
			// Print this string
			//System.out.println(resp);
			Gson gson = new Gson(); 
			JsonObject jsonObject =gson.fromJson(resp, JsonObject.class);
			access_token=jsonObject.get("access_token").toString();	
			
		} finally {
			client.close();
		}
	   
	    
		CloseableHttpClient client2 = HttpClients.createDefault();
		
		// Define POST request
		HttpGet httpget = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?"
				+"q=%40carolynporco&count=50");
		String bearer = "Bearer"+ access_token;
		bearer = bearer.replace('"', ' ');
		//System.out.println(bearer);
		httpget.setHeader("Authorization", bearer);
		httpget.setHeader("Host","api.twitter.com");
		httpget.setHeader("Accept-Encoding","gzip");
		
		CloseableHttpResponse response2 = client2.execute(httpget);
	    try{
	    	
	    	HttpEntity entity2 = response2.getEntity();
	    	String resp2 = EntityUtils.toString(entity2);
	    	
	    	Gson gson2 = new Gson(); 
			JsonObject jsonObject2 =gson2.fromJson(resp2, JsonObject.class);
			JsonArray statuse =jsonObject2.getAsJsonArray("statuses");
			for(JsonElement t : statuse ){
				JsonObject to = t.getAsJsonObject();
				System.out.println("Tweets"+":"+to.get("text")+","+"post"+":"+to.get("created_at"));
			}
	    }finally{
	    	client2.close();
	    }
	   
	   
	
	}
	
}
 class Tweets{
	 String[] statuses;
	 
 }
