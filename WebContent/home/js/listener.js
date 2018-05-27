//Eduard Springer

document.addEventListener("DOMContentLoaded", setAllEventListener);

function setAllEventListener() {
	// BUTTONS: Anzeige der Platznr
	for (var i = 0; i < 53; i++) {
		var element = document.getElementsByClassName("buttons")[i];
		var j = element.value;
		element.addEventListener("click", changeText(j));
	}
	// RADIOBUTTONS: Einzeltermin, Wiederholtermin
		var termin = document.getElementById("einzeltermin")
		termin.addEventListener("change", changeTermin)
		termin = document.getElementById("wiederholtermin")
		termin.addEventListener("change", changeTermin)
	// Date: Datum auf Datum des Wiederholtermins referenzieren
		var datum = document.getElementById("datum")
		datum.addEventListener("change", setDatumMin)
	// Date: Datum (vom) auf Datum (bis) referenzieren
		var vom = document.getElementById("vom")
		vom.addEventListener("change", setVomMin)
	// Date: Ausgabe in Wochen
		var woche = document.getElementById("bis")
		woche.addEventListener("change", setWoche)
}

function changeText(j) {
	return function() {
		document.getElementById("platznr").innerHTML = j;
		// document.getElementsByClassName("buttons").value;
	};
}

function changeTermin() {
	if(document.getElementById("wiederholtermin").checked){
		document.getElementById("terminbezeichnung").disabled = false;
		document.getElementById("vom").disabled = false;
		document.getElementById("bis").disabled = false;
		
		document.getElementById("terminbezeichnung").required = true;
		document.getElementById("vom").required = true;
		document.getElementById("bis").required = true;
		
		document.getElementById("dauer").style.visibility = "visible";
	}
	else{
		document.getElementById("terminbezeichnung").disabled = true;
		document.getElementById("vom").disabled = true;
		document.getElementById("bis").disabled = true;
		
		document.getElementById("dauer").style.visibility = "hidden";
	}
}

function setDatumMin() {
	document.getElementById("vom").value = document.getElementById("datum").value;
	
	document.getElementById("bis").value = document.getElementById("datum").value;
	document.getElementById("bis").min = document.getElementById("datum").value;
	//Intervall von 7 Tagen
	document.getElementById("bis").step = 7;
	// Value um 7 Tagen erhöht
	document.getElementById("bis").stepUp(1);
	// Value auf Min referenziert
	document.getElementById("bis").min = document.getElementById("bis").value;
	
	document.getElementById("dauer").innerHTML = "Dauer: 1 Woche";
}

function setVomMin(){
	document.getElementById("bis").value = document.getElementById("vom").value;
	document.getElementById("bis").min = document.getElementById("vom").value;
	//Intervall von 7 Tagen
	document.getElementById("bis").step = 7;
	// Value um 7 Tagen erhöht
	document.getElementById("bis").stepUp(1);
	// Value auf Min referenziert
	document.getElementById("bis").min = document.getElementById("bis").value;
	
	document.getElementById("dauer").innerHTML = "Dauer: 1 Woche";
}

//Author: Josiah Hester https://stackoverflow.com/questions/20587660/calculate-date-difference-in-weeks-javascript
function setWoche(){
	var vom = new Date(document.getElementById("vom").value);
	var bis = new Date(document.getElementById("bis").value);
	
	var weeks = Math.round((bis-vom)/ 604800000);
	
	if (weeks > 1){
		document.getElementById("dauer").innerHTML = "Dauer: " + weeks + " Wochen";
	}
	else{
		document.getElementById("dauer").innerHTML = "Dauer: " + weeks + " Woche";
	}
}