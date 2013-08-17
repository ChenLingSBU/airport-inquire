package javabeans;


import javabeans.HttpRequest;
import java.io.IOException;
import org.json.*;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

public class weatherInfo {
	private int woeid;
	private int temp;
	private int windDirection;
	private float barometer;
	private int windSpeed;
	private String[] forecast = new String[2];
	private String[] code = {"tornado","tropical storm","hurricane",
							 "severe thunderstorms","thunderstorms",
							 "mixed rain and snow","mixed rain and sleet",
							 "mixed snow and sleet","freezing drizzle","drizzle",
							 "freezing rain","showers","showers","snow flurries",
							 "light snow showers","blowing snow","snow","hail",
							 "sleet","dust","foggy","haze","smoky","blustery",
							 "windy","cold","cloudy","mostly cloudy (night)",
							 "mostly cloudy (day)","partly cloudy (night)",
							 "partly cloudy (day)","clear (night)","sunny",
							 "fair (night)","fair (day)","mixed rain and hail",
							 "hot","isolated thunderstorms","scattered thunderstorms",
							 "scattered thunderstorms","scattered showers","heavy snow",
							 "scattered snow showers",
							 "heavy snow","partly cloudy",
							 "thundershowers","snow showers",
							 "isolated thundershowers"};
	
	public weatherInfo(){}
	
	public weatherInfo(int woeid){
		this.woeid = woeid;
	}
	
	public void getWeatherInfo() throws IOException, JSONException{
		String url = "http://weather.yahooapis.com/forecastrss?w="+ woeid;
		System.out.println(url);
		HttpRequest getWeatherInfo = new HttpRequest(url);
		String xml = getWeatherInfo.requestConnectionString();
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xml);
		JSONObject jsonObject = new JSONObject(json.toString());
		JSONParser(jsonObject);
	}
	
	private void JSONParser(JSONObject jsonObject) throws JSONException{
		JSONObject channel = jsonObject.getJSONObject("channel");
		JSONObject wind = channel.getJSONObject("yweather:wind");
		this.windDirection = Integer.parseInt(wind.getString("@direction"));
		this.windSpeed = Integer.parseInt(wind.getString("@speed"));
		JSONObject atmosphere = channel.getJSONObject("yweather:atmosphere");
		this.barometer = Float.parseFloat(atmosphere.getString("@pressure"));
		JSONObject item = channel.getJSONObject("item");
		JSONObject condition = item.getJSONObject("yweather:condition");
		this.temp = Integer.parseInt(condition.getString("@temp"));
		JSONArray forecastArray = item.getJSONArray("yweather:forecast");
		for(int i = 0; i< forecastArray.length();i++){
			JSONObject forecast = (JSONObject) forecastArray.get(i);
			this.forecast[i] = forecast.getString("@day")+" " + forecast.getString("@date") + " " +
						       "lowest temp is:" + forecast.getString("@low") + " " +
						       "highest temp is:" + forecast.getString("@high")+ " " +
						       "condition:" + forecast.getString("@text") + " " +
						       code[Integer.parseInt(forecast.getString("@code"))];
		}
		
	}
	
	public String[] getForecast(){
		return forecast;
	}
	
	public int getTemp(){
		return temp;
	}
	
	public int getWoeid(){
		return woeid;
	}
	
	public int getWindDirection(){
		return windDirection;
	}
	
	public float getBarometer(){
		return barometer;
	}
	public int getWindSpeed(){
		return windSpeed;
	}
}
