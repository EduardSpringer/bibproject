/**
 * Helene Akulow
 */

"use strict";

document.addEventListener("DOMContentLoaded", init);
function init() {
	var heute = new Date();
	alert(heute.getHours());
	if(heute.getHours() > 7){
	
		alert("test");
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var anzPlatz = JSON.parse(xmlhttp.responseText);
	
				document.getElementById("resPlaetze").innerHTML = "Reservierte Plätze: " + anzPlatz.anzBesetzt;
				document.getElementById("freiePlaetze").innerHTML = "Freie Plätze: " + anzPlatz.anzFrei;
				
			}
		}
		xmlhttp.open("GET", "../currentplaceservlet", true);
		xmlhttp.send();
	}
	else{
		alert("test2");
		document.getElementById("resPlaetze").className="hidden";
		document.getElementById("freiePlaetze").className="hidden";
		document.getElementByID("geschlossen").classList.remove("hidden");		
	}
}

