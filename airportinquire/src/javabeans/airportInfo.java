package javabeans;

public class airportInfo {
	
	private String airportName;
	
	private String airportCode; //FS code
	
	private double airportLatitude;
	
	private double airportLongitude;
	
	private String distance; //distance from City
	
	public airportInfo(){}
	
	public void setAirportName(String airportName){
		this.airportName = airportName;
	}    
	
	public void setAirportCode(String airportCode){
		this.airportCode = airportCode;
	}
	
	public void setAirportLatitude(double airportLatitude) {
		this.airportLatitude = airportLatitude;
	}
	
	public void setAirportLongitude(double airportLongitude){
		this.airportLongitude = airportLongitude;
	}
	
	public void setDistance(String distance){
		this.distance = distance;
	}
	
	public String getDistance(){
		return distance;
	}
	
	public String getAirportName(){
		return airportName;
	}
	
	public String getAirportCode(){
		return airportCode;
	}
	
	public double getAirportLatitude(){
		return  airportLatitude;
	}
	
	public double getAirportLongitude(){
		return airportLongitude;
	}
	

}
