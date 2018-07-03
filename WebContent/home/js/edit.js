/**
 * Sara Viktoria Miller
 */
"use strict";
document.addEventListener("DOMContentLoaded",init);

function init(){
	var form = document.getElementById("profileedit");
	form.addEventListener("submit", bildCheck);
	form.addEventListener("submit", vergleichePasswort); 
}

//Funktion vergleichePasswort: https://stackoverflow.com/questions/16990378/javascript-form-validation-with-password-confirming
function vergleichePasswort(evt){
	var passwort = document.getElementById("bearbeitepasswort").value;
	var passwortbestaetigen = document.getElementById("bearbeitepasswortbestaetigen").value;
	if(passwort != passwortbestaetigen){
		alert("Passworteingaben stimmen nicht überein!\nBitte Eingaben nochmal überprüfen! "); 
		evt.preventDefault();
	}
}

function bildCheck(evt){
	var profilbild = document.getElementById("bildaendern").value;  
	var bild = profilbild.substring(profilbild.lastIndexOf("."), profilbild.length)
	if (bild == ".png" || bild == ".jpg" || bild == ".jpeg" || bild == ".PNG" || bild == ".JPG" || bild == ".JPEG" || !profilbild) { 
	}
	else{ 
		alert("Dateityp wird nicht unterstützt!\nUnterstützt werden folgende Dateiformate: png, jpg und jpeg "); 
		evt.preventDefault(); 
	}
} 