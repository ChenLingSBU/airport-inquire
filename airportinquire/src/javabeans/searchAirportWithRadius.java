package javabeans;

import java.io.IOException;
import java.net.MalformedURLException;

import javabeans.HttpRequest;
import javabeans.airportInfo;
import org.json.*;


public class searchAirportWithRadius {
	private String appID = "ecf48d15";
	private String appKey = "7cb060d0cdf116b7adb3907e1ea3b622";
	private double cityLongitude;
	private double cityLatitude;
	private int radiusMiles;
	private int airportsNumber;
	
	public searchAirportWithRadius(){}
	
	public searchAirportWithRadius(double cityLatitude, double cityLongitude, int radiusMiles){
		this.cityLatitude = cityLatitude;
		this.cityLongitude = cityLongitude;
		this.radiusMiles = radiusMiles;
		
	}
	
	public airportInfo[] getAirports() throws IOException, JSONException, InterruptedException{
		String url = "https://api.flightstats.com/flex/airports/rest/v1/json/" +
				     "withinRadius/"+ this.cityLongitude + "/" 
				      + this.cityLatitude + "/" + this.radiusMiles + "?appId=" + appID +"&" +
				     "appKey=" + appKey;
		//System.out.println(url);
		HttpRequest getAirportRequest = new HttpRequest(url);
		JSONObject jsonObject = getAirportRequest.requestConnection();
		
		return jsonParser(jsonObject);
	}
	
	private airportInfo[] jsonParser(JSONObject jsonObject) throws JSONException, IOException, InterruptedException{
		JSONArray airportsArray = jsonObject.getJSONArray("airports");
		airportInfo[] airports = new airportInfo[airportsArray.length()];
		//System.out.println(airports.length);
		for(int i = 0; i<airportsArray.length(); i++){
			JSONObject airport = (JSONObject)airportsArray.get(i);
			airports[i] = new airportInfo();
			airports[i].setAirportCode(airport.getString("fs"));
			airports[i].setAirportName(airport.getString("name"));
			airports[i].setAirportLatitude(airport.getDouble("latitude"));
			airports[i].setAirportLongitude(airport.getDouble("longitude"));
			airports[i].setDistance(getAirportCityDistance(airports[i].getAirportLatitude(), airports[i].getAirportLongitude()));
			//System.out.println(i);
		}
		setAirportsNumber(airportsArray.length());
		return airports;
	}
	
	private String getAirportCityDistance(double airportLatitude, double airportLongitude) throws IOException, JSONException, InterruptedException{
		String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" +
				this.cityLatitude + "," + this.cityLongitude +
				"&destination=" + airportLatitude + "," + airportLongitude +"&sensor=false";
		
		//System.out.println(url);
		HttpRequest getDistanceRequest = new HttpRequest(url);
		JSONObject googleMapDirection = null;
		String status = "OVER_QUERY_LIMIT";
		while(status.equals("OVER_QUERY_LIMIT")){
			googleMapDirection = getDistanceRequest.requestConnection();
			status = googleMapDirection.getString("status");
			if(status.equals("OK")){
				break;
			}
			Thread.currentThread();
			Thread.sleep(1000);
		}
		JSONArray jsonArray = googleMapDirection.getJSONArray("routes");
		System.out.printf("length of jsonArray: %d\n ",jsonArray.length());
		//if(jsonArray.length()==0){
			//System.out.printf("googleMapDirection %s\n", googleMapDirection.toString());
		//}
		JSONObject route = (JSONObject)jsonArray.get(0);
		JSONArray legs = route.getJSONArray("legs");
		JSONObject jsonobject = (JSONObject)legs.get(0);
		JSONObject distance = jsonobject.getJSONObject("distance");
		String dist = (String) distance.getString("text");
		
		return dist;
	}
	
	public void setAirportsNumber(int airportsNumber){
		this.airportsNumber = airportsNumber;
	}
	
	public int getAirportsNumber(){
		return airportsNumber;
	}
}
