/**
 * Sara V. Miller
 */

"use strict";

document.addEventListener("DOMContentLoaded",init);

function init(){
	
	var form = document.getElementById("absenden");
	form.addEventListener("submit", formularAbsenden);
	
	var form2 = document.getElementById("bearbeiten");
	form2.addEventListener("submit", formularAbsenden);

}

function vergleichePasswort(){

	var passwort = document.getElementById("passwort").value;
	
	var passwortbestaetigen = document.getElementById("passwortbestaetigen").value;
	
	if(passwort != passwortbestaetigen){
	
		alert("Passworteingaben stimmen nicht überein!\nBitte Eingaben nochmal überprüfen! "); 
		
	return false; 

	}

	return true;   

}

function bildcheck(){

	var profilbild = document.getElementById("profilbild").value; 
	
	var bild = profilbild.substring(profilbild.lastIndexOf("."), profilbild.length)
	
	if (bild == ".png" || bild == ".jpg" || bild == ".jpeg" || !profilbild) { 
		
		return true; 
	
	}
	
	else{ 
	
		alert("Dateityp wird nicht unterstützt!\nUnterstützt werden folgende Dateiformate: png, jpg und jpeg "); 
		
		return false; 

	}

} 

function formularAbsenden(){	

	var passwort = vergleichePasswort(); 	
	
	var bild = bildcheck(); 
	
	if(passwort == true && bild == true){
	
		return true; 
	
	}  
	//evt.preventDefault();
	return false; 

}