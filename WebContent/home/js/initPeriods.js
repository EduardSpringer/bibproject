//Eduard Springer

document.addEventListener("DOMContentLoaded", init);

function init() {
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
    opt.hidden = true;
    dropdown.appendChild(opt);
    dropdown.add(opt, dropdown[0]);
}