//Eduard Springer

"use strict";
document.addEventListener("DOMContentLoaded", setAllEventListener);

function setAllEventListener() {
	//Kontaktformular: Button (Reset):
	var form = document.getElementById("contactForm");
	form.addEventListener("reset", confirmResetForm);
}

/* Der User wird gewarnt, ob er wirklich die in dem Formular gesetzten Daten gelöscht werden!
 * */
function confirmResetForm(evt){
	var check = confirm("Möchten Sie wirklich das Formular zurücksetzen?");
	if(check == false){
		evt.preventDefault();
	}
}