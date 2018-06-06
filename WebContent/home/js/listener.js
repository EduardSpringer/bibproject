//Eduard Springer

document.addEventListener("DOMContentLoaded", setAllEventListener);

function setAllEventListener() {
	// RADIOBUTTONS: Einzeltermin, Wiederholtermin
		var termin = document.getElementById("einzeltermin");
		termin.addEventListener("change", changeTermin);
		termin = document.getElementById("wiederholtermin");
		termin.addEventListener("change", changeTermin);
	// Date: Datum auf Datum des Wiederholtermins referenzieren & Zeiträume initialiseren
		var datum = document.getElementById("datum");
		datum.addEventListener("change", changeDatum);
	// Date: Datum (vom) auf Datum (bis) referenzieren
		var vom = document.getElementById("vom");
		vom.addEventListener("change", setVomMin);
	// Date: Ausgabe in Wochen
		var woche = document.getElementById("bis");
		woche.addEventListener("change", setWoche);
	// Änderungen nach Zeitraumauswahl
		var zeitraum = document.getElementById("zeitraum");
		zeitraum.addEventListener("change", changeZeitraum);
	// Button(Submit): Reservieren
		var form = document.getElementById("submitbutton");
		form.addEventListener("submit", checkForm);
}

function changeText(j) {
	return function() {
		document.getElementById("platznr").value = j;
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

function changeDatum() {
	document.getElementById("platznr").innerHTML = "";
	document.getElementById("platzverteilung").innerHTML ="";
	
	document.getElementById("vom").value = document.getElementById("datum").value;
	
	document.getElementById("bis").value = document.getElementById("datum").value;
	document.getElementById("bis").min = document.getElementById("datum").value;
	//Intervall von 7 Tagen
	document.getElementById("bis").step = 7;
	// Value um 7 Tagen erhöht
	document.getElementById("bis").stepUp(1);
	// Value auf Min referenziert
	document.getElementById("bis").min = document.getElementById("bis").value;
	// default-Value für Dauer
	document.getElementById("dauer").innerHTML = "Dauer: 1 Woche";
	// Initialisierung der Zeiträume
	initZeitraeume();
}

function initZeitraeume(){
	var dropdown = document.getElementById("zeitraum");
    var aktuellesDatum = new Date();
    var systemDatum = aktuellesDatum.getDate();
    
    var datum = new Date(document.getElementById("datum").value);
    var gewaehltesDatum = datum.getDate();
    
    if(systemDatum != gewaehltesDatum){
    	var dropdown = document.getElementById("zeitraum");
    	document.getElementById("zeitraum").options.length = 0;
    	
    	zeitraum = {
    		    "attribute": [
    		        { "text":"22:00 - 24:00", "value":"24"},
    		        { "text":"20:00 - 22:00", "value":"22"},
    		        { "text":"18:00 - 20:00", "value":"20"},
    		        { "text":"16:00 - 18:00", "value":"18"},
    		        { "text":"14:00 - 16:00", "value":"16"},
    		        { "text":"12:00 - 14:00", "value":"14"},
    		        { "text":"10:00 - 12:00", "value":"12"},
    		        { "text":"08:00 - 10:00", "value":"10"},
    		    ]
    		 }
    	
    	for (i = 0; i < zeitraum.attribute.length; i++) {
    		var opt = document.createElement('option');
            opt.text = zeitraum.attribute[i].text;
            opt.value = zeitraum.attribute[i].value;
            opt.selected = true;
            dropdown.appendChild(opt);
            dropdown.add(opt, dropdown[0]);
    	} 

        var opt = document.createElement('option');
        opt.text = "auswählen";
        opt.value = "select";
        opt.selected = true;
        opt.hidden = true;
        dropdown.appendChild(opt);
        dropdown.add(opt, dropdown[0]);
    }
    else{
    	var dropdown = document.getElementById("zeitraum");
        var datum = new Date();
        var uhrzeit = datum.getHours();
        
        //ältere Einträge löschen
        document.getElementById("zeitraum").options.length = 0;
        
        if (uhrzeit < 24){
        	var options = document.createElement("option");
            options.text = "22:00 - 24:00";
            options.value = 24;
            dropdown.add(options, dropdown[0]);
        }
        if (uhrzeit < 22){
        	var options = document.createElement("option");
            options.text = "20:00 - 22:00";
            options.value = 22;
            dropdown.add(options, dropdown[0]);
        }
        if (uhrzeit < 20){
        	var options = document.createElement("option");
            options.text = "18:00 - 20:00";
            options.value = 20;
            dropdown.add(options, dropdown[0]);
        }
        if (uhrzeit < 18){
        	var options = document.createElement("option");
            options.text = "16:00 - 18:00";
            options.value = 18;
            dropdown.add(options, dropdown[0]);
        }
        if (uhrzeit < 16){
        	var options = document.createElement("option");
            options.text = "14:00 - 16:00";
            options.value = 16;
            dropdown.add(options, dropdown[0]);
        }
        if (uhrzeit < 14){
        	var options = document.createElement("option");
            options.text = "12:00 - 14:00";
            options.value = 14;
            dropdown.add(options, dropdown[0]);
        }
        if (uhrzeit < 12){
        	var options = document.createElement("option");
            options.text = "10:00 - 12:00";
            options.value = 12;
            dropdown.add(options, dropdown[0]);
        }
        if (uhrzeit < 10){
        	var options = document.createElement("option");
            options.text = "08:00 - 10:00";
            options.value = 10;
            dropdown.add(options, dropdown[0]);
        }
        var opt = document.createElement('option');
        opt.text = "auswählen";
        opt.value = "select";
        opt.selected = true;
        opt.hidden = true;
        dropdown.appendChild(opt);
        dropdown.add(opt, dropdown[0]);
    }
}

function setVomMin(){
	document.getElementById("datum").value = document.getElementById("vom").value;
	document.getElementById("platznr").innerHTML = "";
	document.getElementById("platzverteilung").innerHTML ="";
	
	document.getElementById("bis").value = document.getElementById("vom").value;
	document.getElementById("bis").min = document.getElementById("vom").value;
	//Intervall von 7 Tagen
	document.getElementById("bis").step = 7;
	// Value um 7 Tagen erhöht
	document.getElementById("bis").stepUp(1);
	// Value auf Min referenziert
	document.getElementById("bis").min = document.getElementById("bis").value;
	
	document.getElementById("dauer").innerHTML = "Dauer: 1 Woche";
	initZeitraeume();
	setWoche();
}

//Author: Josiah Hester https://stackoverflow.com/questions/20587660/calculate-date-difference-in-weeks-javascript
function setWoche(){
	var vom = new Date(document.getElementById("vom").value);
	var bis = new Date(document.getElementById("bis").value);
	
	var weeks = Math.round((bis-vom)/ 604800000);
	if (weeks == 1){
		document.getElementById("dauer").innerHTML = "Dauer: " + weeks + " Woche";
	} else {
		document.getElementById("dauer").innerHTML = "Dauer: " + weeks + " Wochen";
	}
}

function changeZeitraum(){
	document.getElementById("platznr").innerHTML = ""; 
	
	var datum = document.getElementById("datum").value;
	var datumElement = document.getElementById("datum");
	var zeitraum = document.getElementById("zeitraum").value;
	var body = document.getElementById("platzverteilung");
	
	//falls datum invalid ist, dann tue nichts mehr
    if (!datumElement.checkValidity()) {
    	document.getElementById("platzverteilung").innerHTML ="";
        return;
    }
	
	var searchURL = "/bibproject/placeinitservlet";
	searchURL += "?datum=" + encodeURIComponent(datum) + "&zeitraum=" + encodeURIComponent(zeitraum);
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var plaetze = JSON.parse(xmlhttp.responseText);
			
			//Alte Plätze löschen
			document.getElementById("platzverteilung").innerHTML ="";
			
			// Initialisierung der Sitzplätze
			var array = [ "3", "4", "5", "8", "9", "10", "13", "14", "15", "18", "19",
					"20", "23", "24", "25", "28", "29", "30", "33", "34", "35", "36",
					"37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
					"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58",
					"59", "60", "61", "62", "65", "66", "71", "72", "76", "80", "81",
					"86", "87", "91", "95", "96", "101", "102", "106", "110", "111",
					"116", "117" ];
			var placenr = 1;

			for (var i = 1; i <= 120; i++) {
				var check = "0";

				for (var j = 0; j < array.length; j++) {
					if (i == array[j]) {
						var button = document.createElement("button");
						button.setAttribute("id", "grau" + i);
						button.setAttribute("class", "buttons");
						button.setAttribute("disabled", "true");
						var beschriftung = document.createTextNode(i);
						button.appendChild(beschriftung);

						var body = document.getElementById("platzverteilung");
						body.appendChild(button);

						check = "1";
					}
				}
				if (check == 0) {
					var button = document.createElement("button");
					button.setAttribute("id", "gruen" + i);
					button.setAttribute("class", "buttons");
					button.setAttribute("value", placenr);
					var beschriftung = document.createTextNode(placenr);
					button.appendChild(beschriftung);
					var body = document.getElementById("platzverteilung");
					body.appendChild(button);

					placenr += 1;
				}
			}
			
			//Wenn es reservierte Plätze gibt, dann diese als "rot"/ disabled setzen
			if (plaetze != null || plaetze.length != 0) {
				for (var i = 0; i < 120; i++) {
					var button = document.getElementsByClassName("buttons")[i];
					var j = button.value;
					
					for(k = 0; k < plaetze.length; k++){
						if(j == plaetze[k].reservedPlace){
							button.setAttribute("id", "rot" + i);
							button.setAttribute("class", "buttons");
							button.setAttribute("disabled", "true");
						}
					}
				}
			}
			
			// BUTTONS: Anzeige der Platznr
			for (var i = 0; i < 120; i++) {
				var element = document.getElementsByClassName("buttons")[i];
				var j = element.value;
				element.addEventListener("click", changeText(j));
			}
		}
	};
	xmlhttp.open("GET", searchURL, true);
//	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); //nur bei POST-Methode
	xmlhttp.send();
}

function checkForm(evt) {
	alert("Test");
}

