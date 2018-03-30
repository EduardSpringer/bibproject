/**
 * Helene Akulow
 */

function cookieFunktion(){
			
	if (navigator.cookieEnabled === false){
		document.getElementById("cookiebox").className="show";
	}
	else{
		document.getElementById("cookiebox").className="hidden";
	}
}


function cookieButton(){
	document.getElementById("cookiebox").className="hidden";
}
