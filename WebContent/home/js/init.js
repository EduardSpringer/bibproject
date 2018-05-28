//Eduard Springer

document.addEventListener("DOMContentLoaded", init);

function init() {
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

			placenr += 1;
		}
	}
	// Initialisierung der Zeiträume
    var dropdown = document.getElementById("zeitraum");
    var datum = new Date();
    var uhrzeit = datum.getHours();
    
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
    dropdown.appendChild(opt);
    dropdown.add(opt, dropdown[0]);
}