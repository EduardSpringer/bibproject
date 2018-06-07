/**
 * Helene Akulow
 */
"use strict";

document.addEventListener("DOMContentLoaded", init);
function init() {
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var anzPlatz = JSON.parse(xmlhttp.responseText);
	
			document.getElementById("resPlaetze").innerHTML = anzPlatz;
			document.getElementById("freiePlaetze");
		}
	}
	xmlhttp.open("GET", "currentplaceservlet", true);
	xmlhttp.send();
}

