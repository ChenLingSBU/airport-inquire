package javabeans;


import org.json.*;
import javabeans.HttpRequest;
import java.io.IOException;


public class searchGeoInfo {
	private int woeid;
	private double latitude;
	private double longitude;
	private String cityName;
	
	public searchGeoInfo(){}
	
	public searchGeoInfo(String cityName){
		this.cityName = cityName;
	}
	
	public void getGeoInfo() throws IOException, JSONException{
		String url = "http://where.yahooapis.com/v1/places.q('" + this.cityName + "')?"+
					 "format=json&appid=7DsXl3jV34EU563ecN_9y9oAWFTnWPEkTqskwy" +
					 "0Q65Z5eMsriBC4pG0mr6UfRDjvm7k0BQ--";
		HttpRequest getGeoInfoRequest = new HttpRequest(url);
		JSONObject geoInfo = getGeoInfoRequest.requestConnection();
		jsonParser(geoInfo);
	}
	
	private void jsonParser(JSONObject jsonObject) throws JSONException{
		JSONObject placeObject = jsonObject.getJSONObject("places");
		JSONArray  jsonArray = placeObject.getJSONArray("place");
		JSONObject place = (JSONObject)jsonArray.get(0);
		this.woeid = (int)place.getInt("woeid");
		JSONObject centroid = (JSONObject)place.getJSONObject("centroid");
		this.latitude = (double) centroid.getDouble("latitude");
		this.longitude = (double) centroid.getDouble("longitude");
		this.cityName = (String)place.getString("name");
	}
	
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public int getWoeid(){
		return woeid;
	}
	
	public String getCityName(){
		return cityName;
	}
		
}
