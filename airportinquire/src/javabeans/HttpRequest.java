package javabeans;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;

public class HttpRequest {
	private URL requestUrl;
	
	private URLConnection connection;
	
	public HttpRequest(){}
	
	public HttpRequest(String url) throws MalformedURLException {
		this.requestUrl = new URL(url);
	}
	
	public JSONObject requestConnection() throws IOException, JSONException{
		connection = requestUrl.openConnection();
		InputStream in = (InputStream)connection.getInputStream();
		JSONObject jsonObject = convertToJson(in);
		return jsonObject;
	}
	
	public String requestConnectionString() throws IOException{
		connection = requestUrl.openConnection();
		InputStream in = (InputStream)connection.getInputStream();
		return convertToString(in);
	}
	
	
	private String convertToString(InputStream in) throws IOException {
		InputStreamReader isr = new InputStreamReader(in);
		int z;
		StringBuilder jsonString = new StringBuilder();
		
		while((z = isr.read()) != -1)
			jsonString.append((char)z);
		return jsonString.toString();
	}
	
	private JSONObject convertToJson(InputStream in) throws IOException, JSONException{
		InputStreamReader isr = new InputStreamReader(in);
		int z;
		StringBuilder jsonString = new StringBuilder();
		
		while((z = isr.read()) != -1)
			jsonString.append((char)z);
		JSONObject jsonObject = new JSONObject(jsonString.toString());
		return jsonObject;
	}
	
}
