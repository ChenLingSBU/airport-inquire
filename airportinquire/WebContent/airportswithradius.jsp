<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javabeans.airportInfo" %>
<%@ page import = "javabeans.cityInfo" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>airportsList</title>
</head>
<body>
<%
airportInfo[] airports = null;
airports = (airportInfo[])session.getAttribute("airportInfo");
cityInfo cityInfo = (cityInfo)session.getAttribute("cityInfo");
%>

<br>
<br>
<center> 
	<font size = 6>
City name: <input style = "font-size:25px" name = "cityName" type = "text" readonly  value = "<%=cityInfo.getCityName() %>"/> &nbsp; &nbsp; &nbsp; &nbsp; 

Radius:<input style = "font-size:25px" name = "radius" type = "text" readonly value="<%=cityInfo.getRadiusMiles() + "mi" %>"/> <br>
<br>
Airport Name Show all Airports within <%=cityInfo.getRadiusMiles()%> miles of a specific city<br>
<br>
	</font>
</center>

<div   style="margin:0px auto;width:1000px;height:300px; overflow-y:scroll; border:1px solid;"> 
	<table  style = "font-size:25px" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
     		<td width="60%">Airport Name</td>
     		<td width="20%">Airport Code</td>
     		<td width="20%">Distance from city</td>
  		</tr>
  		<% 
  			for(int i = 0; i < airports.length; i++){
  				out.println("<tr>");
  				out.print("<td width = \"60%\">");
  				//out.print(airports[i].getAirportName());
  				out.print("<a href = \"airportInfoDetailServlet?airportIndex="+i+"\">"+airports[i].getAirportName()+"</a>");
  				out.println("</td>");
  				out.print("<td width = \"20%\">");
  				out.print(airports[i].getAirportCode());
  				out.println("</td>");
  				out.print("<td width = \"20%\">");
  				out.print(airports[i].getDistance());
  				out.println("</td>");
  				out.println("<tr>");
  			}
  		%>
  
  
	</table>
</div>





</body>
</html>