/**
 * 
 */

"use strict";

document.addEventListener("DOMContentLoaded",init);

function init(){
	
	var form = document.getElementById("absenden");
	form.addEventListener("submit", formularAbsenden);
	//document.getElementById("absenden").addEventListener("submit",formularAbsenden);
	alert("init" + form);
}

function vergleichePasswort(){

	var passwort = document.getElementById("passwort").value;
	
	var passwortbestaetigen = document.getElementById("passwortbestaetigen").value;
	
	if(passwort != passwortbestaetigen){
	
		alert("Passworteingaben stimmen nicht überein!\nBitte Eingaben nochmal überprüfen! "); 
		evt.preventDefault();
	return false; 

	}

	return true;   

}

function bildcheck(){

	var profilbild = document.getElementById("profilbild").value; 
	
	var bild = profilbild.substring(profilbild.lastIndexOf("."), profilbild.length)
	
	if (bild == ".png" || bild == ".jpg" || bild == ".jpeg" || !profilbild) { 
		alert("true bildcheck");
		return true; 
	
	}
	
	else{ 
	
		alert("Dateityp wird nicht unterstützt!\nUnterstützt werden folgende Dateiformate: png, jpg und jpeg "); 
		evt.preventDefault();
		return false; 

	}

} 

function formularAbsenden(evt){
	alert("fomular absenden");

	var passwort = vergleichePasswort(); 
	alert("Var passwort "+ passwort );
	
	var bild = bildcheck(); 
	alert("Var bild" + bild);
	if(passwort == true && bild == true){
	alert("formular absenden true");
		return true; 
	
	}  
	alert("formular absenden false");
	evt.preventDefault(); 
	return false; 

}