/**
 * 
 */
document.addEventListener("DOMContentLoaded", setAllEventListener);

function setAllEventListener() {
	// BUTTONS
	for (var i = 0; i < 53; i++) {
		var element = document.getElementsByClassName("buttons")[i];
		var j = element.value;
		element.addEventListener("click", changeText(j));

	}
	// DROPDOWN
		var element = document.getElementById("zeitraum");
		element.addEventListener("change", changeTime());
}

function changeText(j) {
	return function() {
		document.getElementById("platznr").innerHTML = j;
		// document.getElementsByClassName("buttons").value;
	};

}

function changeTime() {

	var heute = new Date();
	var auswahl = document.getElementById("zeitraum").value;
	
	document.getElementById("datum").innerHTML = "Platzverteilung für den Zeitraum: "
			+ auswahl
			+ " am "
			+ heute.getDate()
			+ "."
			+ (heute.getMonth() + 1)
			+ "."
			+ heute.getFullYear();
	
	
}