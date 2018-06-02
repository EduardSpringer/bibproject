document.addEventListener("DOMContentLoaded",init);

function init(){
	//Einzeltermine löschen auch bei den Wdhterminen
	var deletelink = document.getElementsByClassName("deletelink");
	
	
    for (var i = 0; i < deletelink.length; i++) {
    	deletelink[i].addEventListener("click", confirmDeleteLink);
    }
    //Alle Terminbezeichnung und alle dazugehörigen Einzeltermine löschen
	var deletewdhtermin = document.getElementsByClassName("deletewdhtermin");
	
	
    for (var i = 0; i < deletewdhtermin.length; i++) {
    	deletewdhtermin[i].addEventListener("click", confirmDeleteWdh);
    }
    //Auf- und zuklappen der Termine zu den Bezeichnungen
    var terminbez = document.getElementsByClassName("terminbez");
    
    for (var i = 0; i < terminbez.length; i++) {
    	terminbez[i].addEventListener("click", showAndHide);
    	
    }
	
}

function confirmDeleteLink(evt){
	var deletebox = confirm("Wollen Sie diesen Einzeltermin wirklich löschen?");
	
	if(!deletebox){
		evt.preventDefault();
	}
}

function confirmDeleteWdh(evt){
	var deletebox = confirm("Wollen Sie wirklich alle Einzeltermine zu \n dieser Terminbezeichnung löschen?");
	
	if(!deletebox){
		evt.preventDefault();
	}
};

function showAndHide(){
		//this=span, parentNode=p, nextSibling=#text, nextSibling=table
	if(this.parentNode.nextSibling.nextSibling.className === "hiddentable"){
		this.parentNode.nextSibling.nextSibling.classList.remove("hiddentable");
	}
	else{
		this.parentNode.nextSibling.nextSibling.className = "hiddentable";
	}
}