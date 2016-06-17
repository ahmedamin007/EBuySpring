//getZip = function(cb) {
//  if (document.location.protocol === 'http:' && (navigator.geolocation != null)) {
//    return navigator.geolocation.getCurrentPosition(function(pos) {
//      var coords, url;
//      coords = pos.coords;
//      url = "http://nominatim.openstreetmap.org/reverse?format=json&lat=" + coords.latitude + "&lon=" + coords.longitude + "&addressdetails=1";
//      return $.ajax({
//        url: url,
//        dataType: 'jsonp',
//        jsonp: 'json_callback',
//        cache: true
//      }).success(function(data) {
//        return cb(data.address.postcode);
//      });
//    });
//  }
//};

$(document).ready(function(){
   // $("#getnav").click(searchWord);
	  alert("form load");
//	  document.getElementById("#Latitude").value =  "first"; 
//      document.getElementById("#Longitude").value = "second";	
	  
	  jQuery.ajax( {
		  url: '//freegeoip.net/json/',
		  type: 'POST',
		  dataType: 'jsonp',
		  success: function(location) {
		    // example where I update content on the page.
		    jQuery('#city').html(location.city);
		    jQuery('#region-code').html(location.region_code);
//		    jQuery('#region-name').html(location.region_name);
//		    jQuery('#areacode').html(location.areacode);
//		    jQuery('#ip').html(location.ip);
//		    jQuery('#zipcode').html(location.zipcode);
//		    jQuery('#longitude').html(location.longitude);
//		    jQuery('#latitude').html(location.latitude);
//		    jQuery('#country-name').html(location.country_name);
//		    jQuery('#country-code').html(location.country_code);
		  }
		} );
});

//$(document).ready (function(cb) {
//      alert("form load");
//	  document.getElementById("Latitude").value =  "first"; 
//      document.getElementById("Longitude").value = "second";	
//	}
//);

//var x=document.getElementById("demo"); 


//$(document).ready (function(cb) {
//	  if (document.location.protocol === 'http:' && (navigator.geolocation != null)) {
//	    return navigator.geolocation.getCurrentPosition(function(pos) {
//	      var coords, url;
//	      coords = pos.coords;
//		  document.getElementById("Latitude").value =  coords.latitude; 
//		  document.getElementById("Longitude").value = coords.longitude;
//	      url = "http://nominatim.openstreetmap.org/reverse?format=json&lat=" + coords.latitude + "&lon=" + coords.longitude + "&addressdetails=1";
//	      return $.ajax({
//	        url: url,
//	        dataType: 'jsonp',
//	        jsonp: 'json_callback',
//	        cache: true
//	      }).success(function(data) {
//
//	        return cb(data.address.postcode);
//	      });
//	    });
//	  }
//	});

function test() {
	alert("form load");
	
	jQuery.ajax( {
		  url: '//freegeoip.net/json/',
		  type: 'POST',
		  dataType: 'jsonp',
		  success: function(location) {
		    // example where I update content on the page.
		    jQuery('#city').html(location.city);
		    jQuery('#region-code').html(location.region_code);
//		    jQuery('#region-name').html(location.region_name);
//		    jQuery('#areacode').html(location.areacode);
//		    jQuery('#ip').html(location.ip);
//		    jQuery('#zipcode').html(location.zipcode);
//		    jQuery('#longitude').html(location.longitude);
//		    jQuery('#latitude').html(location.latitude);
//		    jQuery('#country-name').html(location.country_name);
//		    jQuery('#country-code').html(location.country_code);
		  }
		} );
	
}
function getCurrentLocation() 
  { 
 alert(getZip);
 if (navigator.geolocation) 
   { 
	 navigator.geolocation.getCurrentPosition(onGeoSuccess,onGeoError); 
   } 
  else{
	  alert("Geolocation is not supported by this browser.");
	  } 
  } 
function showPosition(position) 
{ 

	var latlon=position.coords.latitude+","+position.coords.longitude; 

	//alert(position.coords.longitude);
	//alert(position.coords.latitude);

//var img_url="http://maps.googleapis.com/maps/api/staticmap?center=" + latlon + "&zoom=14&size=400x300&sensor=false"; 
//document.getElementById("mapholder").innerHTML="<img src='"+img_url+"'>"; 
} 


function onGeoError(event)
{
   alert("Error code " + event.code + ". " + event.message);
}

function onGeoSuccess(event)
{
    document.getElementById("Latitude").value =  event.coords.latitude; 
    document.getElementById("Longitude").value = event.coords.longitude;
}
