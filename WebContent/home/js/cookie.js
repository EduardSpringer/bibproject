/**
 * Helene Akulow
 */
document.addEventListener("DOMContentLoaded",init);

function init(){
	
	if (navigator.cookieEnabled === false){
		document.getElementById("cookiebox").className="show";
		
	}
	else{
		document.getElementById("cookiebox").className="hidden";
		
	}
	
	var cookiebutton = document.getElementById("cookiebutton");
	cookiebutton.addEventListener("click", cookieButton());
	
}


function cookieButton(){
	document.getElementById("cookiebox").className="hidden";
	alert("cookiebutton");
}
