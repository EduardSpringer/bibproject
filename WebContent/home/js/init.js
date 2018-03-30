/**
 * 
 */

document.addEventListener("DOMContentLoaded", init);

function init() {
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
				button.setAttribute("id", "grau");
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
			button.setAttribute("id", "gruen");
			button.setAttribute("class", "buttons");
			button.setAttribute("value", placenr);
			var beschriftung = document.createTextNode(placenr);
			button.appendChild(beschriftung);
			var body = document.getElementById("platzverteilung");
			body.appendChild(button);

			placenr = placenr + 1;

		}
	}
	
	//aktuelle Uhrzeit
	document.getElementById("uhrzeit").innerHTML = "Aktuelle Uhrzeit: " + heute.getHours() + ":"
	+ ((heute.getMinutes() > 10) ? "" : "0") + heute.getMinutes() + " Uhr";
}