function getVisitorData(callback) {
    return $.ajax({
        type: "GET",
        url: "http://freegeoip.net/json/",
        dataType: "json",
        async: true

    });
}

jQuery(document).ready(function () {
	//alert("IP thing didn't work.");
	
    $.when(getVisitorData())
    .done(function(respone) {
      var ip = response.ip;
      var country = response.country_code;
      //here you can trigger a custom event with the values, wherever you need, just bind the event and use the data.
       $(document).trigger("responseDataPublished", [ip, country]);
    })
    .fail(function(response){
       alert("IP thing didn't work.");
    });
});


//$(document).ready(

//
//(function() {
//  $(function() {
//    $.getJSON('http://freegeoip.net/json/?callback=?', function(location, textStatus, jqXHR) {
//      console.log("callback running");
//      console.log(textStatus);
//      console.log(jqXHR);
//      $('region-name').html(location.region_name);
//      $('areacode').html(location.areacode);
//      $('ip').html(location.ip);
//      $('zipcode').html(location.zipcode);
//      $('longitude').html(location.longitude);
//      $('latitude').html(location.latitude);
//      $('country-name').html(location.country_name);
//      $('country-code').html(location.country_code);
//      $('city').html(location.city);
//      $('region-code').html(location.region_code);
//      localStorage['loc'] = location.country_code;
//      if (localStorage.loc === "US") {
//        return alert("Your From The US.");
//      }
//    });
//    return console.log(localStorage.loc);
//  });
//
//  console.log(typeof $ !== "undefined" && $ !== null);
//
//  console.log($.getJSON != null);
//
//}).call(this);
//
//
//function getloc(){
//  //document.getElementById("#test").value =  "okkkkk"; 
//  //jQuery("#test").html("okkkkk");
//  alert("change");
//  jQuery.ajax( {
//	  url: '//freegeoip.net/json/',
//	  type: 'POST',
//	  dataType: 'jsonp',
//	  success: function(location) {
//
//		  alert(location.city);
////	    jQuery('#city').html(location.city);
////	    jQuery('#region-code').html(location.region_code);
//	  }
//	} );
////  alert("finish");
//}