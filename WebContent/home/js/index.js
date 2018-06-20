/**
 * Helene Akulow
 */

"use strict";

document.addEventListener("ContentLoaded", initLastPage);

document.addEventListener("DOMContentLoaded", init);

function initLastPage(){
	
	
}

function init() {
	var heute = new Date();
	
	if(heute.getHours() > 7){
	
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
		document.getElementById("resPlaetze").className="hidden";
		document.getElementById("freiePlaetze").className="hidden";
		document.getElementByID("geschlossen").classList.remove("hidden");		
	}
}

