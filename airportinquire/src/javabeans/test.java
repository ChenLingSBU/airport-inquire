package javabeans;

import java.io.IOException;
import org.json.*;

import net.sf.json.JSON;
//import net.sf.json.xml.XMLSerializer;
import net.sf.json.xml.*;





public class test {
	public static void main(String[] args) throws IOException, InterruptedException, JSONException{
		 String[] forecasts = new String[2];
		 String[] code = {"tornado","tropical storm","hurricane",
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
		String url = "http://weather.yahooapis.com/forecastrss?w=2502265";
		HttpRequest getWeatherInfo = new HttpRequest(url);
		String xml = getWeatherInfo.requestConnectionString();
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xml);
		JSONObject jsonObject = new JSONObject(json.toString());
		
		JSONObject channel = jsonObject.getJSONObject("channel");
		JSONObject wind = channel.getJSONObject("yweather:wind");
		String direction = wind.getString("@direction");
		System.out.printf("wind direction:%s degree\n", direction);
		String speed = wind.getString("@speed");
		System.out.printf("wind speed:%s mph\n", speed);
		JSONObject atmosphere = channel.getJSONObject("yweather:atmosphere");
		
		JSONObject item = channel.getJSONObject("item");
		JSONObject condition = item.getJSONObject("yweather:condition");
		System.out.printf("wind direction: %s, wind speed: %s, barometer: %s, temp: %s\n", direction, speed,atmosphere.getString("@pressure"),condition.getString("@temp"));
		JSONArray forecastArray = item.getJSONArray("yweather:forecast");
		for(int i = 0; i< forecastArray.length();i++){
			JSONObject forecast = (JSONObject) forecastArray.get(i);
			forecasts[i] = forecast.getString("@day")+" " + forecast.getString("@date") + " " +
						       "lowest temp is:" + forecast.getString("@low") + " " +
						       "highest temp is:" + forecast.getString("@high")+ " " +
						       "condition:" + forecast.getString("@text") + " " +
						       code[Integer.parseInt(forecast.getString("@code"))];
			System.out.println(forecasts[i]);
		}
	}
}
