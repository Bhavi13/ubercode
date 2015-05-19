<%@ page import ="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index Page</title>
<link href="/UberCodeSFFoodTruck/css/StyleImpl.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3&sensor=false&libraries=geometry"></script>
<script src="/UberCodeSFFoodTruck/js/IndexMap.js"></script>
</head>
<body onload="initializeMap()">
<form name="indexPage" id="indexPageID" action="/UberCodeSFFoodTruck/SearchServlet" method="post" >
<input type="hidden" name="action" value="getNearestFoodTrucks">

	<% if(request.getAttribute("searchedFood") != null)
		{
		String foodItem = request.getAttribute("searchedFood").toString();
	%>
<input name ="searchFoodItem" id="searchFoodItem" class="controls" list="searchFoodItem" placeholder="Food Type" value="<%=foodItem%>">
<datalist id="searchFoodItem">
    <option value="Tacos">
    <option value="Sandwich">
    <option value="Coffee">
    <option value="CupCake">
    <option value="Sausage">
    <option value="Burritos">
    <option value="Bagels">
    <option value="Muffins">
    <option value="Italian Subs">
    <option value="Juice">
    <option value="Soup">
    <option value="Nachos">
    <option value="Salads">
    <option value="Fresh Fruit">
    <option value="Soda">
    <option value="Pita">
    <option value="Chiense Spring Roll">
    <option value="BBQ chicken">
    <option value="Granola Bars">
    <option value="Onion Rings">
    <option value="Fries">
    <option value="Samosa">
    <option value="Mango Lassi">
    <option value="Espresso Drinks">
    <option value="Lobster">
    <option value="Crab">
    <option value="Vegetarian">
    <option value="Enchhiladas">
    <option value="Chocolate">
    <option value="Drinks">
    <option value="Sushi">
    <option value="Pizza">
    <option value="Indian">
    <option value="Tea">
    <option value="Quesadillas">
    <option value="Chips">
    <option value="Egg">
    <option value="Shrimp">
    <option value="Gyros">
    <option value="Brownie">
    
</datalist> 
<%} else { %>
<input name ="searchFoodItem" id="searchFoodItem" class="controls" list="searchFoodItem" placeholder="Food Type"> 
<datalist id="searchFoodItem">
    <option value="Tacos">
    <option value="Sandwich">
    <option value="Coffee">
    <option value="CupCake">
    <option value="Sausage">
    <option value="Burritos">
    <option value="Bagels">
    <option value="Muffins">
    <option value="Italian Subs">
    <option value="Juice">
    <option value="Soup">
    <option value="Nachos">
    <option value="Salads">
    <option value="Fresh Fruit">
    <option value="Soda">
    <option value="Pita">
    <option value="Chiense Spring Roll">
    <option value="BBQ chicken">
    <option value="Granola Bars">
    <option value="Onion Rings">
    <option value="Fries">
    <option value="Samosa">
    <option value="Mango Lassi">
    <option value="Espresso Drinks">
    <option value="Lobster">
    <option value="Crab">
    <option value="Vegetarian">
    <option value="Enchhiladas">
    <option value="Chocolate">
    <option value="Drinks">
    <option value="Sushi">
    <option value="Pizza">
    <option value="Indian">
    <option value="Tea">
    <option value="Quesadillas">
    <option value="Chips">
    <option value="Egg">
    <option value="Shrimp">
    <option value="Gyros">
    <option value="Brownie">
</datalist> 
<%} %> <input id="submit-button"  class="controls"  type="submit"  value="Submit">
<div id="map"></div>
<input type="hidden" name="clickMarker" >
	<% if(request.getAttribute("errMessage") != null)
	{  
		String error = request.getAttribute("errMessage").toString();
	%>
<input type="text" class="controls"  id="message" name="message" value="<%=error%>">
	<%} %>
	<% 
		if(session.getAttribute("foodStr") != null)
		{
		 String foodItems =  session.getAttribute("foodStr").toString();
		 String latList = session.getAttribute("latStr").toString();
		 String lngList = session.getAttribute("lngStr").toString();
		 String addressList = session.getAttribute("addressStr").toString();
		 String selectedLat = session.getAttribute("selectedLat").toString(); 
		 String selectedLng = session.getAttribute("selectedLng").toString();
	%>
<input type="hidden" name="foodItemsJSP" id="foodItemsJSP" value="<%=foodItems%>">
<input type="hidden" name="latListJSP" id="latListJSP" value="<%=latList%>">
<input type="hidden" name="lngListJSP" id="lngListJSP" value="<%=lngList%>">
<input type="hidden" name="addressListJSP" id="addressListJSP" value="<%=addressList%>">
<input type="hidden" name="selectedLatJSP" id="selectedLatJSP" value="<%=selectedLat%>">
<input type="hidden" name="selectedLngJSP" id="selectedLngJSP" value="<%=selectedLng%>">
	<%} %>
</form>
</body>
</html>