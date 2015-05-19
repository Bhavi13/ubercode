# ubercodechallenge
coding-challenge-tools-SF Food Truck

This Project Prototype is created as a part of Uber Code Challenge.
This service allows user to search for particular food truck available at near provided location in the San Francisco. 


<font face="verdana" size="10"><b>URL of EC2 Instance:</b></font>

The website is single page and hosted on Amazon EC2 and the URL is:
<br>
http://54.191.98.11:8080/UberCodeSFFoodTruck/jsp/IndexPage.jsp

<br><br>
<font face="verdana" size="10"><b>Important Note:</b></font>


<p>
If the User is not selecting any location on the map by clicking on it, It will by default take the center of the San Francisco and search the locations according to it. 
<br><br>Also if user is not giving any particular food type to search, it will be by default search for "Sandwich".
<br><br>Additionally, if the user is searching for any item which is not provided in the Data Set, it will show the message on the bottom of the map indicating that particular food truck is not available.
<br><br>
It will search for top 10 nearest locations for particular food item. It can show less than 10 nearest locations if more data is not available for that particular food item.
</p>

<br><br>
<font face="verdana" size="6"><b>Technical Specification:</b></font>

<b>Track: Full Stack</b>

The <b>MVC Patteren Architecture</b> is used to impement the application framework. Most of the business logic is handled through backend using <b>Java</b>. The light weight front-end MVC has been implemented using <b>JavaScript</b>. The view portion of the application has been created using <b>HTML5</b>, <b>JSP</b> and <b>CSS</b>.


<font face="verdana" size="6"><b>Backend:</b></font>
<p>
  The Backend implementation is done using Java. The Backend API "searchTypeOfTrucks" will be used to search 10 nearest        locations for a specific type of food trucks. Also, "findClosestN" API will be used by the above API to sort the result      according to the distance from the provided point.
</p>

<font face="verdana" size="6"><b>Middle Layer:</b></font>
<p>
  Servlet is used to handle the request from the user[frontend] and redirecting the result to frontend. 
</p>

<font face="verdana" size="6"><b>Front-End:</b></font>
<p>
  The frontend is created using HTML5 and CSS. JavaScript and JSP is used to render the data on the google map. The Google     Map API is used to plot the markers on the map and fetching the selected marker's location. 
  <br><br>
  The marker shows the latitude/longitude of the location. Also the markers of the food truck shows the served food items and   address mentioned in the Data Set file provided as a part of code challenge.
  <br><br>
  CSS File is used to beautify the frontend portion. 
</p>

<br><br>
<font face="verdana" size="10"><b>Experience:</b></font>
I have worked with front-end portion in my academic project but I have mostly worked as backend developer till now. So developing a full-stack application was an exciting experience. It was very informative to use Google API to render your data. Also I have tried to apply my knowledge of HTML5 and CSS to make the front-end simple and easy. JSP is used to handle the request from the user.

In terms of development of backend code, I always enjoy writing the code and testing it from the different perspective of the user to make it more reliable. 

