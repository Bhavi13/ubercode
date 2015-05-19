function initializeMap()
 {
		var geocoder = new google.maps.Geocoder();
	
	    // Initializing the Map
	 	var map = new google.maps.Map(document.getElementById('map'), {
	      zoom: 12,
	      center: new google.maps.LatLng(37.7833, -122.4167),
	      mapTypeId: google.maps.MapTypeId.ROADMAP
	    });
	 
	 	// Clicked marker function
	 	var singleMarker,singleMarkerlocation,infowindow1;
	    
	 	//Places the marker wherever is clicked on map
	    function placeMarker(singleMarkerlocation)
	    {
	    	/* if(singleMarkerlocation){
	    		singleMarkerlocation.setMap(null);
	        } */
	        
	        singleMarker = new google.maps.Marker({
	            position:singleMarkerlocation, //mouse click position
	            map: map,
	            icon: "http://www.google.com/mapfiles/marker_green.png"
	            
	        });
	        
	         infowindow1 = new google.maps.InfoWindow({
	            content: 'Latitude: ' + singleMarkerlocation.lat() + '<br>Longitude: ' + singleMarkerlocation.lng()
	          });
	    }
	    
	    google.maps.event.addListener(map, 'click', function(event) {
	    	//The id loop removes the old marker whenever is new one is clicked 
	    	if(singleMarker){
	    		singleMarker.setMap(null);
	        }
	    	placeMarker(event.latLng);
	    	document.indexPage.clickMarker.value= singleMarker.position;
	    	infowindow1.open(map, singleMarker);
	  	});
	    
	   
	  // Create the search box and link it to the UI element.
	     var input = /** @type {HTMLInputElement} */(
	         document.getElementById('searchFoodItem'));
	     var submit = document.getElementById("submit-button");
	     var errMsg = document.getElementById("message");
	     map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	     map.controls[google.maps.ControlPosition.TOP_LEFT].push(submit);
	     map.controls[google.maps.ControlPosition.BOTTOM_CENTER].push(errMsg);
	     
	     var pinnedLat = "";
	     var pinnedLng = "";
	     
	     pinnedLat =  document.indexPage.elements['selectedLatJSP'].value;
	     pinnedLng =  document.indexPage.elements['selectedLngJSP'].value;
	     
	     var arrAllFood = document.indexPage['foodItemsJSP'].value.split(',');
	     var arrAllLat = document.indexPage['latListJSP'].value.split(',');
	     var arrAllLng = document.indexPage['lngListJSP'].value.split(',');
	     var arrAllAddress = document.indexPage['addressListJSP'].value.split(',');
	     /*for (var ii = 0; ii < arrAllLat.length-1; ii++) {
	         alert('@'+arrAllLat[ii]);
	     }*/
	     
	     // To show all the nearest locations
	     var infowindow = new google.maps.InfoWindow();
	     for (var p = 0;p<arrAllLat.length-1;p++)
		    {
			    var marker;
			      marker = new google.maps.Marker({
			    	position: new google.maps.LatLng(arrAllLat[p], arrAllLng[p]),
			        map: map,
			      });
			     //var  content = 'Latitude: ' + latitude[p] + '<br>Longitude: ' + longitude[p]+ '<br>Food Item: ' + foodItems[p]+ '<br>Location Id: ' + locationid[p];
			     var  content ='Latitude: ' + arrAllLat[p] + '<br>Longitude: '+arrAllLng[p] + '<br>Food Item: ' + arrAllFood[p]+  '<br>Address: ' + arrAllAddress[p];
			      google.maps.event.addListener(marker, 'click', (function(marker,content) {
			          return function() {
			        	infowindow.setContent(content);
			            infowindow.open(map, marker);
			          }
			        })(marker, content));
			     
		    }
	     
	     // Pins the selected marker 
	     var pinnedMarker = new google.maps.Marker({
	         position : new google.maps.LatLng(pinnedLat, pinnedLng),
	         icon: "http://www.google.com/mapfiles/marker_green.png"
	  });
	     
	     google.maps.event.addListener(map, 'click', function(event) {
		    	if(pinnedMarker){
		    		pinnedMarker.setMap(null);
		        }
		    	
		  	});
	     
	     pinnedMarker.setMap(map);

	     
}
