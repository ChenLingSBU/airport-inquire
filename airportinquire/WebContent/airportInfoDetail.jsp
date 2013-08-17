<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javabeans.airportInfo" %>
<%@ page import = "javabeans.cityInfo" %>
<%@ page import = "javabeans.weatherInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>airportInfoDetail</title>
</head>
<body>
<%
cityInfo cityInfo = (cityInfo)session.getAttribute("cityInfo");
airportInfo airport = (airportInfo)session.getAttribute("airport");
weatherInfo weatherInfo = (weatherInfo)session.getAttribute("weatherInfo");
String[] forecasts = weatherInfo.getForecast();
%>
<center>
<font size = 6>
Airport Name: <input style = "font-size:25px" name = "airportName" type = "text" readonly  value = "<%=airport.getAirportName()%>"/> &nbsp; &nbsp; &nbsp; &nbsp; 

Airport Code:<input style = "font-size:25px" name = "airportCode" type = "text" readonly value="<%=airport.getAirportCode()%>"/> <br>

City: &nbsp; <input style = "font-size:25px" name = "cityName" type = "text" readonly  value = "<%=cityInfo.getCityName() %>"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 

Distance:<input style = "font-size:25px" name = "distance" type = "text" readonly value="<%=airport.getDistance()%>"/> <br>
<br>
Current Weather Conditions
<br>
<br>
</font>
</center>
<center>
<font size = 4>
&nbsp; Temp: <input style = "font-size:25px" name = "temp" type = "text" readonly  value = "<%=weatherInfo.getTemp() + " degree"%>"/> &nbsp; &nbsp; &nbsp; &nbsp; 

Barometer:<input style = "font-size:25px" name = "barometer" type = "text" readonly value="<%=weatherInfo.getBarometer() + " in"%>"/> <br>
Wind Direction: <input style = "font-size:25px" name = "windDirection" type = "text" readonly  value = "<%=weatherInfo.getWindDirection() + " degree"%>"/> &nbsp; &nbsp; &nbsp; &nbsp; 

Wind Speed:<input style = "font-size:25px" name = "windSpeed" type = "text" readonly value="<%=weatherInfo.getWindSpeed() + "mph"%>"/> <br>

</font>
</center>
 

<font size = 4>Forecast:</font>
<div   style="margin:0px auto;width:800px;height:100px; overflow-y:scroll; border:1px solid;"> 
<%
	for(int i = 0; i <  forecasts.length; i++){
		out.print(forecasts[i]);
		out.print("<br>");
	}
%>

</div>
<center>
<font size = 6>
<br>
<br>
Current Flight Status
<br>
<br>
</font>
</center>
<div   style="margin:0px auto;width:1000px;height:50px; overflow-y:scroll; border:1px solid;"> 
<%

%>

</div>
</body>
</html>