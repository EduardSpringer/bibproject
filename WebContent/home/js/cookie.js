/**
 * Helene Akulow
 */

"use strict";

document.addEventListener("DOMContentLoaded",init);

function init(){
	if (navigator.cookieEnabled === false){
		document.getElementById("cookiebox").className="show";
		
		var cookiebutton = document.getElementById("cookiebutton");
		cookiebutton.addEventListener("click", cookieButton);
	}
	else{
		document.getElementById("cookiebox").className="hidden";
	}
}

function cookieButton(){
	document.getElementById("cookiebox").className="hidden";
}
