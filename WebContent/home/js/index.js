/**
 * Helene Akulow
 */

"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
	var heute = new Date();
	
	if(heute.getHours() > 7){
	
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var anzPlatz = JSON.parse(xmlhttp.responseText);
	
				document.getElementById("meter").value = anzPlatz.anzBesetzt;
				document.getElementById("meterText").innerHTML = "Belegt: " + anzPlatz.anzBesetzt + " von 53";
			}
		}
		xmlhttp.open("GET", "../currentplaceservlet", true);
		xmlhttp.send();
	}
	else{
		document.getElementById("resPlaetze").className="hidden";
		document.getElementById("freiePlaetze").className="hidden";
		document.getElementByID("geschlossen").classList.remove("hidden");		
	}
}

