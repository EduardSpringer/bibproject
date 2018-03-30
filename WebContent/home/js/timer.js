/**
 * Helene Akulow
 */

function timer(){
	
var heute = new Date(); // aktuelles Datum und Zeit

var prufanfangWs = new Date(2018,0,19); //Prüfungsanfang WS 19.1.2018
var pruefendeWs = new Date(2018,1,3); //Prüfungsende WS 3.2.2018 

var prufanfangSs = new Date(2018,6,4); //Prüfungsanfang SS 7.2018
var prufendeSs= new Date(2018,6,20); //Prüfungsende SS 7.2018


if((heute.getTime() > prufanfangWs.getTime() && heute.getTime() < prufendeWs.getTime()) 
		|| (heute.getTime() > prufanfangSs.getTime() && heute.getTime() < PrufendeWs.getTime())){
	alert("Pruefungszeitraum!");
}

//Im Wintersemester:
if(heute.getTime() < prufanfangWs.getTime()){
	var h = heute.getTime();
	
	var ws = prufanfangWs.getTime();
	var diff = prufanfangWs.getTime() - heute.getTime();
	alert(" diff " + diff);
	var tage = Math.ceil(diff / (1000 * 60 * 60 * 24)); // Differenz in Tagen
	alert("Es sind noch " + tage + " Tage bis zum Prüfungszeitraum.");
}

//Im Sommersemster:
if(heute.getTime() > pruefendeWs.getTime() && heute.getTime() < prufanfangSs.getTime()){
	var h = heute.getTime();
	var ss = prufanfangSs.getTime();
	var diff = ws - h;
	var tage = Math.ceil(differenz / (1000 * 60 * 60 * 24)); // Differenz in Tagen
	alert("Es sind noch " + tage + " Tage bis zum Prüfungszeitraum.");
}
// nach dem Sommersemester 2018:
	//jahr++ und wieder von anfang an


	


var datum = new Date(2017,11,24,12,0,0); // Weihnachten 2017, 12:00 Uhr. 11 = Dezember!
//alert(heute.getDate() + "." + (heute.getMonth()+1) + "." + heute.getFullYear());
// Berechnung der verbleibenden Zeit bis Weihnachten
var jahr = heute.getFullYear();
var weihnachten = new Date(jahr, 11, 24);
if (heute.getTime() > weihnachten.getTime()) // getTime liefert Zeit in msec seit 1.1.1970
weihnachten.setYear(jahr+1);
var h = heute.getTime();
var w = weihnahten.getTime();
var differenz = w - h;
var tage = Math.ceil(differenz / (1000 * 60 * 60 * 24)); // Differenz in Tagen
alert("Es sind noch " + tage + " Tage bis Weihnachten.");
document.getElementById("timer").innerHTML = "Es sind noch " + tage + " Tage bis Weihnachten.";
}