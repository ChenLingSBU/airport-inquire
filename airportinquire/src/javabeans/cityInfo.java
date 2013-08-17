package javabeans;

public class cityInfo {
	private String cityName;
	
	private int cityWoeid;
	
	private double cityLatitude;
	
	private double cityLongitude;
	
	private int radiusMiles;
	
	public cityInfo(){}
	
	public void setRadiusMiles(int radiusMiles){
		this.radiusMiles = radiusMiles;
	}
	
	public int getRadiusMiles(){
		return radiusMiles;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	
	public void setCityWoeid(int cityWoeid){
		this.cityWoeid = cityWoeid;
	}
	
	public void setCityLatitude(double cityLatitude){
		this.cityLatitude = cityLatitude;
	}
	
	public void setCityLongitude(double cityLongitude){
		this.cityLongitude = cityLongitude;
	}
	
	
	public String getCityName(){
		return cityName;
	}
	
	public int getCityWoeid(){
		return cityWoeid;
	}
	
	public double getCityLatitude(){
		return cityLatitude;
	}
	
	public double getCityLongitude(){
		return cityLongitude;
	}

}
	
	
