/**
 * Helene Akulow
 */

document.addEventListener("DOMContentLoaded",init);

function init(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		console.log("Collback reached with status " + xmlhttp.status + " and readyState " + xmlhttp.readyState);
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			ducument.getElementById("resPlaetze"),innerHTML = xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET","currentplaceservlet", true);
	xmlhttp.send();
	
	document.getElementById("resPlaetze");
	ducument.getElementById("freiePlaetze");
	
}